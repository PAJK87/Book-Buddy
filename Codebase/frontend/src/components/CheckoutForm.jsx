import React, { useEffect } from "react";
import {
  PaymentElement,
  AddressElement,
  useStripe,
  useElements,
} from "@stripe/react-stripe-js";
import { useState } from "react";

const CheckoutForm = ({ user, cart }) => {
  const stripe = useStripe();
  const elements = useElements();

  const [message, setMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    if (!stripe) {
      return;
    }

    const clientSecret = new URLSearchParams(window.location.search).get(
      "payment_intent_client_secret"
    );

    if (!clientSecret) {
      return;
    }

    stripe.retrievePaymentIntent(clientSecret).then(({ paymentIntent }) => {
      setMessage(
        paymentIntent.status === "succeeded"
          ? "Your payment succeeded"
          : "Unexpected error occurred"
      );
    });
  }, [stripe]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!stripe || !elements) {
      return;
    }

    setIsLoading(true);

    const { error } = await stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: "http://localhost:5173/order-success",
      },
    });

    if (
      error &&
      (error.type === "card_error" || error.type === "validation_error")
    ) {
      setMessage(error.message);
    }

    setIsLoading(false);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Card Details</h3>
      <PaymentElement />
      <h3>Shipping Address</h3>
      <AddressElement
        options={{ mode: "shipping", allowedCountries: ["US"] }}
      />
      <button>Submit</button>
    </form>
  );
};

export default CheckoutForm;
