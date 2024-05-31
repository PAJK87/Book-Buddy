import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import CheckoutForm from "./CheckoutForm";
import { useEffect } from "react";

const stripePromise = loadStripe(
  "pk_test_51P0pzxBFz5sYcIhUUQfIpmiDDkvNzyNUthfJfJ9IQw3EdLXo2U3f79HKQuj7QhA3CrBpna2nIr9xSS0ESMn6uYyG00rEZuDONA"
);

export default function StripeCheckout({ user, cart }) {
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
      {clientSecret && (
        <Elements options={options} stripe={stripePromise}>
          <CheckoutForm user={user} cart={cart} />
        </Elements>
      )}
    </div>
  );
}
