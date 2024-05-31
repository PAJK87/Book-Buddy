import { useLocation } from "react-router-dom";
import CheckoutForm from "./CheckoutForm";

export default function GuestCheckout() {
  const location = useLocation();
  const { book } = location.state;

  const handleCheckout = async () => {};

  return (
    <div className="max-w-md mx-auto bg-white shadow-lg rounded-lg p-6">
      <h2 className="text-2xl font-semibold text-gray-800 mb-4">
        Guest Checkout
      </h2>
      <div>
        <h3>${book.title}</h3>
        <h4>${book.author}</h4>
      </div>
      <div>
        <CheckoutForm />
      </div>
    </div>
  );
}
