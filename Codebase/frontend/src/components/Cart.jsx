import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "./UserContext";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Cart = () => {
  const [cart, setCart] = useState(null);
  const { user, fetchUserData } = useContext(UserContext);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/cart/get/${user.id}`
        );
        const data = await response.json();
        setCart(data);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching cart:", error);
        setLoading(false);
      }
    };

    fetchCart();
  }, [user.id]);

  const removeItemFromCart = async (cartId, cartItemId) => {
    try {
      const response = await fetch(
        `http://localhost:8080/cart/remove-item/${cartId}/${cartItemId}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (response.ok) {
        toast.success("Item removed from cart");
      } else {
        throw new Error(
          `Failed to remove item from cart, HTTP Status: ${response.statusText}`
        );
      }
      const data = await response.json();
      setCart(data);
    } catch (error) {
      console.error("Error removing item from cart:", error);
    }
  };

  const handleCheckout = () => {
    navigate("/userCheckout", { state: { cart } });
  };

  if (loading) return <p>Loading...</p>;
  if (!cart) return <p>Cart not found</p>;

  return (
    <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
      <h2 className="text-2xl font-bold mb-4">Cart</h2>
      <ul>
        {cart.items.map((item) => (
          <li
            key={item.id}
            className="flex items-center justify-between border-b-2 border-gray-200 py-2"
          >
            <span className="text-lg">{item.book.title}</span>
            <span className="text-lg">{item.book.price}</span>
            <button
              onClick={() => removeItemFromCart(cart.cartId, item.cartItemId)}
              className="text-sm text-red-500 hover:text-red-700"
            >
              Remove
            </button>
          </li>
        ))}
      </ul>
      <div className="flex justify-end">
        <p className="text-xl pt-5 text-green-800 font-bold">
          Cart Total: {cart.totalPrice}
        </p>
      </div>

      <button
        onClick={handleCheckout}
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mt-4"
      >
        Proceed to Checkout
      </button>
      <ToastContainer />
    </div>
  );
};

export default Cart;
