import React, { useEffect } from "react";
import {
  PaymentElement,
  AddressElement,
  useStripe,
  useElements,
} from "@stripe/react-stripe-js";
import { useState } from "react";
import { useSearchParams } from "react-router-dom";

const CheckoutForm = ({ user, cart, book, paymentIntentId }) => {
  const stripe = useStripe();
  const elements = useElements();
  const [message, setMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [fullName, setFullName] = useState("");
  const [address, setAddress] = useState("");
  const [guestEmail, setGuestEmail] = useState("");

  const handleAddressChange = (event) => {
    if (event.complete) {
      const { value } = event;
      const { name, address } = value;

      const fullName = `${name.firstName || ""} ${name.lastName || ""}`;

      const addressString = `${address.line1 || ""}, ${address.line2 || ""}, ${
        address.city || ""
      }, ${address.state || ""}, ${address.postalCode || ""}, ${
        address.country || ""
      }`;

      setFullName(fullName);
      setAddress(addressString);
    }
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;

    if (name === "guestEmail") {
      setGuestEmail(value);
    } else if (name === "guestName") {
      setGuestName(value);
    }

    setInputs((values) => ({ ...values, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!stripe || !elements) {
      return;
    }

    setIsLoading(true);

    try {
      let orderId;

      if (user) {
        const userOrder = {
          userId: user.id,
          cartId: cart.id,
          paymentIntentId: paymentIntentId,
          shippingAddress: address,
        };

        const userResponse = await fetch("/create-user-order", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(userOrder),
        });

        if (userResponse.ok) {
          const { orderId: userOrderId } = await userResponse.json();
          orderId = userOrderId;
        } else {
          console.error("Error creating user order:", userResponse.statusText);
        }
      } else {
        const guestOrder = {
          guestName: fullName,
          guestEmail: guestEmail,
          guestShippingAddress: address,
          paymentIntentId: paymentIntentId,
          totalOrderAmount: book.price,
          bookId: book.id,
        };

        const guestResponse = await fetch("/create-guest-order", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(guestOrder),
        });

        if (guestResponse.ok) {
          const { orderId: guestOrderId } = await guestResponse.json();
          orderId = guestOrderId;
        } else {
          console.error(
            "Error creating guest order:",
            guestResponse.statusText
          );
        }
      }

      const { error } = await stripe.confirmPayment({
        elements,
        confirmParams: {
          return_url: `http://localhost:5173/order-success?orderId=${orderId}`,
        },
      });

      if (
        error &&
        (error.type === "card_error" || error.type === "validation_error")
      ) {
        setMessage(error.message);
      }
    } catch (error) {
      console.error("Error creating order:", error);
    }

    setIsLoading(false);
  };

  return user ? (
    <form onSubmit={handleSubmit}>
      <h3>Card Details</h3>
      <PaymentElement />
      <h3>Shipping Address</h3>
      <AddressElement
        options={{ mode: "shipping", allowedCountries: ["US"] }}
        onChange={handleAddressChange}
      />
      <button>Submit</button>
    </form>
  ) : (
    <form onSubmit={handleSubmit}>
      <label>
        Email Address:
        <input
          name="guestEmail"
          type="email"
          value={guestEmail}
          onChange={handleInputChange}
        />
      </label>
      <h3>Card Details</h3>
      <PaymentElement />
      <h3>Shipping Address</h3>
      <AddressElement
        options={{ mode: "shipping", allowedCountries: ["US"] }}
        onChange={handleAddressChange}
      />
      <button>Submit</button>
    </form>
  );
};

export default CheckoutForm;
