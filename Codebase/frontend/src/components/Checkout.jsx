import { useLocation } from "react-router-dom";
import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { UserContext } from "./UserContext";
import React, { useState, useEffect, useContext } from "react";
import CheckoutForm from "./CheckoutForm";

const stripePromise = loadStripe(
  "pk_test_51P0pzxBFz5sYcIhUUQfIpmiDDkvNzyNUthfJfJ9IQw3EdLXo2U3f79HKQuj7QhA3CrBpna2nIr9xSS0ESMn6uYyG00rEZuDONA"
);

export default function Checkout() {
  const location = useLocation();
  const { user } = useContext(UserContext);

  const { cart, book } = location.state || {};

  const [clientSecret, setClientSecret] = useState("");
  const [paymentIntentId, setPaymentIntentId] = useState("");

  let totalAmount = user ? cart.totalAmount : book.price;

  useEffect(() => {
    let isCancelled = false;

    const fetchData = async () => {
      if (!clientSecret && !paymentIntentId) {
        try {
          const res = await fetch(
            "http://localhost:8080/stripe/create-payment-intent",
            {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(totalAmount),
            }
          );

          if (!res.ok) {
            throw new Error("Network response was not ok.");
          }

          const data = await res.json();

          if (!isCancelled) {
            console.log("Response data:", data);
            setClientSecret(data.clientSecret);
            setPaymentIntentId(data.paymentIntentId);
            console.log("Client Secret:", data.clientSecret);
            console.log("Payment Intent ID:", data.paymentIntentId);
          }
        } catch (error) {
          if (!isCancelled) {
            console.log(error);
          }
        }
      }
    };

    fetchData();

    return () => {
      isCancelled = true;
    };
  }, [clientSecret, paymentIntentId]);

  return (
    <div>
      <h2>Checkout</h2>
      {user && cart ? (
        <div>
          <h3>Cart Items:</h3>
          {cart.items.map((item) => (
            <div key={item.id}>
              <p>Book: {item.book.title}</p>
              <p>Quantity: {item.quantity}</p>
              <p>Price: {item.book.price}</p>
            </div>
          ))}
          <h3>Order Total: {totalAmount}</h3>
        </div>
      ) : book ? (
        <div>
          <h3>Selected Book:</h3>
          <div>
            <p>Title: {book.title}</p>
            <p>Price: {book.price}</p>
          </div>
          <h3>Order Total: {totalAmount}</h3>
        </div>
      ) : (
        <div>
          <p>No items found for checkout.</p>
        </div>
      )}
      {clientSecret && (
        <Elements options={{ clientSecret }} stripe={stripePromise}>
          <CheckoutForm
            user={user}
            cart={cart}
            book={book}
            paymentIntentId={paymentIntentId}
          />
        </Elements>
      )}
    </div>
  );
}
