import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import BookGrid from "./components/BookGrid";
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";
import BookDetail from "./components/BookDetail";
import UserProvider from "./components/UserProvider";
import GuestCheckout from "./components/GuestCheckout";
import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";

const stripePromise = loadStripe("your_stripe_public_key");

function App() {
  return (
    <UserProvider>
      <Router>
        <div className="min-h-screen bg-gray-100">
          <Navbar />
          <main className="container mx-auto px-4 py-8">
            <Routes>
              <Route
                path="/"
                element={
                  <>
                    <h1 className="text-3xl font-bold mb-8">
                      Welcome to Book Buddy
                    </h1>
                    <BookGrid />
                  </>
                }
              />
              <Route path="/signin" element={<SignIn />} />
              <Route path="/signup" element={<SignUp />} />
              <Route path="/bookdetail/:id" element={<BookDetail />} />
              <Route
                path="/guestcheckout"
                element={
                  <Elements stripe={stripePromise}>
                    <GuestCheckout />
                  </Elements>
                }
              />
            </Routes>
          </main>
          <footer className="bg-black py-4">
            <div className="container mx-auto px-4 text-center">
              <p className="text-white">
                &copy; 2024 Book Buddy. All rights reserved.
              </p>
            </div>
          </footer>
        </div>
      </Router>
    </UserProvider>
  );
}

export default App;
