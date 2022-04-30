import { combineReducers } from "redux";

import { addsubReducer } from "./addSub";
import { countingReducer } from "./counting";

const rootReducer = combineReducers({
    value : addsubReducer,
    count : countingReducer
})

export default rootReducer;