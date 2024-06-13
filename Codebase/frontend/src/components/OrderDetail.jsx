import { useContext } from "react";
import { UserContext } from "./UserContext";
import { useLocation } from "react-router-dom";

export default function OrderDetail() {
  const { user } = useContext(UserContext);
  const location = useLocation();
  const order = location.state;

  return (
    <div>
      <h3>Order Details</h3>
      <p>{order.id}</p>
      <ul>
        {order.items.map((item) => (
          <li key={item.id}>
            <span>{item.book.title}</span>
            <span>{item.book.price}</span>
          </li>
        ))}
      </ul>
      <p>{order.shippingAddress}</p>
      <p>{order.totalOrderAmount}</p>
    </div>
  );
}
