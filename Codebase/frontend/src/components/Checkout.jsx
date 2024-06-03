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

  useEffect(() => {
    fetch("http://localhost.com:8080/create-payment-intent", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ items: [{}] }),
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("Network response was not ok.");
        }
        return res.json();
      })
      .then((data) => setClientSecret(data.clientSecret))
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const options = {
    clientSecret: clientSecret,
  };

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
        </div>
      ) : book ? (
        <div>
          <h3>Selected Book:</h3>
          <div>
            <p>Title: {book.title}</p>
            <p>Price: {book.price}</p>
          </div>
        </div>
      ) : (
        <div>
          <p>No items found for checkout.</p>
        </div>
      )}
      <Elements options={options} stripe={stripePromise}>
        <CheckoutForm user={user} cart={cart} />
      </Elements>
    </div>
  );
}
