import {applyMiddleware, createStore} from "redux";
import combinedReducers from "./reducers";
import {composeWithDevTools} from "redux-devtools-extension";
import thunk from "redux-thunk";

const store = createStore(
    combinedReducers,
    composeWithDevTools(applyMiddleware(thunk))
);
// window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()

export default store;