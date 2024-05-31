import { useLocation } from "react-router-dom";
import React, { useState, useEffect, useContext } from "react";
import { UserContext } from "./UserContext";
import StripeCheckout from "./StripeCheckout";

export default function UserCheckout() {
  const location = useLocation();
  const { user, fetchUserData } = useContext(UserContext);
  const { cart } = location.state;

  return (
    <div className="max-w-md mx-auto bg-white shadow-lg rounded-lg p-6">
      <h2 className="text-2xl font-semibold text-gray-800 mb-4">Checkout</h2>
      <ul>
        {cart.items.map((item) => (
          <li
            key={item.id}
            className="flex items-center justify-between border-b-2 border-gray-200 py-2"
          >
            <span className="text-lg">{item.book.title}</span>
            <span className="text-lg">{item.book.price}</span>
          </li>
        ))}
      </ul>
      <div className="flex justify-end">
        <p className="text-xl pt-5 text-green-800 font-bold">
          Cart Total: {cart.totalPrice}
        </p>
      </div>
      <div>
        <StripeCheckout user={user} cart={cart} />
      </div>
    </div>
  );
}
