import React, { useState, useEffect, useContext } from "react";
import { useParams, Link } from "react-router-dom";
import { UserContext } from "./UserProvider";
function BookDetail() {
  const { id } = useParams();
  const { user } = useContext(UserContext);
  const [book, setBook] = useState(null);
  const [quantity, setQuantity] = useState(1);

  useEffect(() => {
    const fetchBookDetail = async () => {
      const response = await fetch(`http://localhost:8080/books/${id}`);
      const data = await response.json();
      setBook(data);
    };

    fetchBookDetail();
  }, [id]);

  const handleAddToCart = async () => {
    if (user && user.isAuthenticated) {
      console.log("Adding to cart:", book.title);
    }
  };

  if (!book) {
    return <div>Loading...</div>;
  }

  return (
    <div className="bg-white rounded-lg shadow-md overflow-hidden">
      <div className="relative">
        <img
          src="https://demo.publishr.cloud/assets/common/images/edition_placeholder.png"
          alt={book.title}
          className="w-full h-64 object-cover"
          style={{ objectFit: "contain" }}
        />
      </div>
      <div className="p-4">
        <h3 className="text-lg font-semibold text-gray-800 mb-2">
          {book.title}
        </h3>
        <p className="text-gray-600">{book.author}</p>
        <br />
        <p className="text-black-600">{book.description}</p>
        <br />
        <p className="text-black-600">{book.price}</p>
        {user && user.isAuthenticated ? (
          <button
            className="bg-blue-500 text-white px-4 py-2 rounded-lg mt-4"
            onClick={handleAddToCart}
          >
            Add to Cart
          </button>
        ) : (
          <Link to="/guestcheckout">
            <button className="bg-blue-500 text-white px-4 py-2 rounded-lg mt-4">
              Proceed to Guest Checkout
            </button>
          </Link>
        )}
      </div>
    </div>
  );
}

export default BookDetail;