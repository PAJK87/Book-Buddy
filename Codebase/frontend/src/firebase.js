import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyBtuU6Y1EkMh2P_4kmnrtihhXSbltI3e9s",
  authDomain: "bookbuddy-48294.firebaseapp.com",
  projectId: "bookbuddy-48294",
  storageBucket: "bookbuddy-48294.appspot.com",
  messagingSenderId: "288802447531",
  appId: "1:288802447531:web:0df4a16f037e9eb684d7ee"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

export { auth };
