import { useLocation } from "react-router-dom";
import { UserContext } from "./UserContext";
import React, { useContext, useEffect, useState } from "react";

export default function OrderSuccess() {
  const { user } = useContext(UserContext);
  const location = useLocation();
  const [order, setOrder] = useState(null);
  const searchParams = new URLSearchParams(location.search);

  const orderId = searchParams.get("orderId");

  useEffect(() => {
    const getOrderDetails = async () => {
      const url = user
        ? `http://localhost:8080/user-order/${orderId}`
        : `http://localhost:8080/guest-order/${orderId}`;

      try {
        const response = await fetch(url);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        console.log(data);
        setOrder(data);
      } catch (error) {
        console.error("Error fetching order:", error);
      }
    };
    if (orderId) {
      getOrderDetails();
    }
  }, [orderId, user]);

  if (!order) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Order Complete!</h1>
      {user ? (
        <div>
          <p>Thank you for your order, {user.firstName}!</p>
          <p>Items in your order:</p>
          <ul>
            {order.items.map((item) => (
              <li
                key={item.id}
                className="flex items-center justify-between border-b-2 border-gray-200 py-2"
              >
                <span className="text-lg">{item.book.title}</span>
                <span className="text-lg">{item.book.price}</span>
              </li>
            ))}
          </ul>
          <p>Total Price: {order.totalAmount}</p>
          <p>Shipping Address: {order.shippingAddress}</p>
        </div>
      ) : (
        <div>
          <p>Thank you for your order, {order.guestName}!</p>
          <p>Email: {order.guestEmail}</p>
          <p>Book Ordered: {order.book.title}</p>
          <p>Order Amount: {order.totalAmount}</p>
          <p>Shipping Address: {order.guestShippingAddress}</p>
        </div>
      )}
    </div>
  );
}
