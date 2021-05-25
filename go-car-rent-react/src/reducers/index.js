import {combineReducers} from 'redux';
import loginReducer from './isLogged';

const combinedReducers = combineReducers({
    isLogged: loginReducer
});

export default combinedReducers;