import React, { useState, useEffect } from "react";
import UserContext from "./UserContext";

const UserProvider = ({ children }) => {
  const [user, setUser] = useState(() => {
    const savedUser = localStorage.getItem("user");
    return savedUser ? JSON.parse(savedUser) : null;
  });

  useEffect(() => {
    if (user) {
      localStorage.setItem("user", JSON.stringify(user));
    } else {
      localStorage.removeItem("user");
    }
  }, [user]);

  const fetchUserData = async (email) => {
    try {
      console.log("email:", email);
      const response = await fetch(
        `http://localhost:8080/users/email?email=${email}`
      );
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const userData = await response.json();
      setUser(userData);
    } catch (error) {
      console.error("Failed to fetch user data:", error);
    }
  };

  const handleSignUpSuccess = async (newUserData) => {
    try {
      const response = await fetch(
        `http://localhost:8080/users/email?email=${newUserData.email}`
      );
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const userData = await response.json();
      setUser(userData);
    } catch (error) {
      console.error("Failed to fetch user data after sign-up:", error);
    }
  };

  return (
    <UserContext.Provider value={{ user, fetchUserData, handleSignUpSuccess }}>
      {children}
    </UserContext.Provider>
  );
};

export { UserContext };
export default UserProvider;
