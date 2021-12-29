import firebase from 'firebase/app';
import "firebase/auth";
import "firebase/firestore";
const firebaseConfig = {
    apiKey: process.env.REACT_APP_API_KEY,
    authDomain: process.env.REACT_APP_AUTH_DOMAIN,
    projectId: process.env.REACT_APP_DATABASE_URL,
    storageBucket: process.env.REACT_APP_STORAGE_BUCKET,
    messagingSenderId: process.env.REACT_APP_MESSAGIN_ID,
    appId: process.env.REACT_APP_APP_ID
  };

  // Initialize Firebase
firebase.initializeApp(firebaseConfig);

export const firebaesInstance =firebase;
export const dbService = firebase.firestore();
export const authService=firebase.auth();

