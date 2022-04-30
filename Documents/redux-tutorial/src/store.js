import { createStore } from "redux";
import rootReducer from "./reducer";
import { persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';



const store=createStore(rootReducer);
export default store;