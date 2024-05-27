import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function CollectionDetail() {
  const { id } = useParams();
  const [collection, setCollection] = useState(null);
  const [newCollectionName, setNewCollectionName] = useState("");

  useEffect(() => {
    const fetchUserCollections = async () => {
      try {
        const response = await fetch(`http://localhost:8080/collections/${id}`);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        setCollection(data);
      } catch (error) {
        console.error("Error fetching collection:", error);
      }
    };
    fetchUserCollections();
  }, [id]);

  const handleRemoveBook = async (bookId, title) => {
    try {
      const response = await fetch(
        `http://localhost:8080/collections/${id}/remove_book/${bookId}`,
        {
          method: "PUT",
        }
      );
      if (response.ok) {
        toast.success(`${title} was removed successfully`);
        const updatedCollection = await response.json();
        setCollection(updatedCollection);
      } else {
        toast.error(`${title} not removed successfully`);
        throw new Error(`HTTP error! status: ${response.status}`);
      }
    } catch (error) {
      console.error("Error deleting book:", error);
    }
  };

  const handleRenameCollection = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/collections/${id}/rename/${newCollectionName}`,
        {
          method: "PUT",
        }
      );

      if (response.ok) {
        const updatedCollection = await response.json();
        setCollection(updatedCollection);
        toast.success("Collection renamed successfully");
      } else {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
    } catch (error) {
      console.error("Error renaming collection:", error);
      toast.error("Failed to rename collection");
    }
  };

  if (!collection) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="bg-white shadow-md rounded-lg p-8">
        <h2 className="text-3xl font-bold mb-6 text-center text-gray-800">
          {collection.collectionName}
        </h2>
        <div className="mb-6">
          <label
            htmlFor="collectionName"
            className="block text-sm font-bold mb-2 text-gray-700"
          >
            Name:
          </label>
          <div className="mt-1 relative rounded-md shadow-sm">
            <input
              type="text"
              id="collectionName"
              className="focus:ring-orange-500 focus:border-orange-500 block w-full sm:text-sm border-gray-300 rounded-md"
              value={newCollectionName}
              onChange={(e) => setNewCollectionName(e.target.value)}
            />
          </div>
          <button
            className="mt-4 bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded-lg focus:outline-none focus:shadow-outline"
            onClick={handleRenameCollection}
          >
            Rename
          </button>
        </div>
        <ul>
          {collection.booksInCollection.map((book) => (
            <div className="flex justify-between items-center border-b border-gray-200 py-4">
              <Link key={book.id} to={`/bookDetail/${book.id}`}>
                <div>
                  <h4 className="text-lg font-bold text-gray-800 hover:text-red-900">
                    {book.title}
                  </h4>
                  <p className="text-gray-600">Author: {book.author}</p>
                </div>
              </Link>
              <button
                className="bg-red-500 hover:bg-red-600 text-white py-1 px-4 rounded-lg focus:outline-none focus:shadow-outline"
                onClick={() => handleRemoveBook(book.id, book.title)}
              >
                Remove Book
              </button>
            </div>
          ))}
        </ul>
      </div>
      <ToastContainer />
    </div>
  );
}

export default CollectionDetail;
