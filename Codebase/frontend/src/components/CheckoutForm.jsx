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

      const addressString = `${address.line1 || ""}, ${address.line2 || ""}, ${
        address.city || ""
      }, ${address.state || ""}, ${address.postal_code || ""}, ${
        address.country || ""
      }`;
      setFullName(name);
      setAddress(addressString);
    }
  };

  const handleInputChange = (event) => {
    setGuestEmail(event.target.value);
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
          cartId: cart.cartId,
          paymentIntentId: paymentIntentId,
          userShippingAddress: address,
        };
        console.log("UserOrder: ", userOrder);
        const userResponse = await fetch(
          `http://localhost:8080/user-order/create`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(userOrder),
          }
        );

        if (userResponse.ok) {
          const userOrderId = await userResponse.json();
          orderId = userOrderId;
          alert("User order created with orderId: " + orderId);
        } else {
          alert("Error creating user order:", userResponse.statusText);
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
        console.log("GuestOrder: ", guestOrder);
        const guestResponse = await fetch(
          "http://localhost:8080/guest-order/create",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(guestOrder),
          }
        );

        if (guestResponse.ok) {
          const guestOrderId = await guestResponse.json();
          orderId = guestOrderId;
          alert("Guest order created with orderId: " + orderId);
          console.log("Guest order created with orderId:", orderId);
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
          return_url: `http://localhost:5173/orderSuccess?orderId=${orderId}`,
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
