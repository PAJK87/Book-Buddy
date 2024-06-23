import React, { useState, useEffect, useContext } from "react";
import { Link } from "react-router-dom";
import { UserContext } from "./UserContext";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function UserProfile() {
  const { user } = useContext(UserContext);
  const [collections, setCollections] = useState([]);
  const [collectionName, setCollectionName] = useState("");
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    if (user) {
      fetchUserCollections();
      fetchUserOrders();
    }
  }, [user]);

  const fetchUserCollections = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/collections/get/${user.userId}`
      );
      const data = await response.json();
      console.log("collectionData:", data);
      setCollections(data);
    } catch (error) {
      console.error("Error fetching user collections:", error);
      console.error("Error message:", error.message);
      console.error("Error stack trace:", error.stack);
    }
  };

  const fetchUserOrders = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/user-order/all/${user.userId}`
      );
      const data = await response.json();
      console.log("orderData:", data);
      setOrders(data);
    } catch (error) {
      console.error("Error getting orders:", error);
    }
  };

  const handleDeleteCollection = async (collectionId) => {
    try {
      await fetch(`http://localhost:8080/collections/${collectionId}`, {
        method: "DELETE",
      });
      setCollections(
        collections.filter((collection) => collection.id !== collectionId)
      );
    } catch (error) {
      console.error("Error deleting collection:", error);
    }
  };

  const handleCreateCollection = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/collections/${user.userId}/new/${collectionName}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            userId: user.id,
            name: collectionName,
          }),
        }
      );
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const newCollection = await response.json();
      setCollections([...collections, newCollection]);
      setCollectionName("");
    } catch (error) {
      console.error("Error creating collection:", error);
    }
  };

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="bg-gray-900 text-white rounded-lg shadow-lg p-8">
        <div className="flex justify-between items-center mb-8">
          <h1 className="text-3xl font-bold">User Profile</h1>
        </div>
        <div className="mb-8">
          <p>
            <strong>Name:</strong> {user.firstName} {user.lastName}
          </p>
          <p>
            <strong>Email:</strong> {user.email}
          </p>
          <p>
            <strong>Date of Birth:</strong> {user.dateOfBirth}
          </p>
        </div>
        <h2 className="text-xl font-bold mb-4">Book Collections</h2>
        {collections.length > 0 ? (
          <ul>
            {collections.map((collection) => (
              <li key={collection.id} className="mb-4">
                <Link
                  to={`/collectionDetail/${collection.id}`}
                  className="text-blue-500 hover:text-blue-600 text-xl font-bold"
                >
                  {collection.collectionName}
                </Link>
                <button
                  onClick={() => handleDeleteCollection(collection.id)}
                  className="ml-2 p-2 bg-red-500 hover:bg-red-600 text-white font-bold rounded transition duration-300 ease-in-out"
                >
                  Delete
                </button>
              </li>
            ))}
          </ul>
        ) : (
          <p>No collections found.</p>
        )}
        <div className="flex mt-4">
          <input
            type="text"
            className="mr-2 p-2 border rounded text-gray-900"
            placeholder="Collection Name"
            value={collectionName}
            onChange={(e) => setCollectionName(e.target.value)}
          />
          <button
            onClick={handleCreateCollection}
            className="bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out"
          >
            Create New Collection
          </button>
        </div>
        <br />
        <div>
          <h2 className="text-xl font-bold mb-4">Orders</h2>
          {orders.length > 0 ? (
            <ul>
              {orders.map((order) => (
                <li
                  key={order.orderId}
                  className="flex items-center justify-between py-2"
                >
                  <Link to={`/orderDetail`} state={order}>
                    <span className="border-b-2">
                      Order ID: {order.orderId}{" "}
                    </span>
                    <span className="border-b-2">
                      Total Amount: {order.totalOrderAmount}
                    </span>
                  </Link>
                </li>
              ))}
            </ul>
          ) : (
            <p>No orders found.</p>
          )}
        </div>
        <div>
          <Link
            to={`/cart/`}
            className="mt-8 inline-block bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out"
          >
            Go to Cart
          </Link>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
}
