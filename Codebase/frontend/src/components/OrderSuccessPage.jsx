import { UserContext } from "./UserContext";
import React, { useContext } from "react";

export default function OrderSuccess() {
  const { user } = useContext(UserContext);

  const getOrderDetails = async (e) => {
    if (user) {
    } else {
    }
  };

  return (
    <div>
      <h1>Order Complete!</h1>
      {user ? (
        <div>
          <p>Thank you for your order, {user.firstName}!</p>
          <p>Your order details:</p>
          {/* Display order details for logged-in user */}
        </div>
      ) : (
        <div>
          <p>Thank you for your order, {guest.name}!</p>
          <p>Your order confirmation number is: XXXX</p>
          {/* Display order details for guest user */}
        </div>
      )}
    </div>
  );
}
