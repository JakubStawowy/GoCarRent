import axios from "axios";
import {BASE_LOGIN_URL} from "./urlRepository";

export const loginUser = (data) => async (dispatch) => {

    const response = await axios.post(BASE_LOGIN_URL.replace(":email", data.email).replace(":password", data.password));
    dispatch({
        type: 'LOGIN',
        payload: response
    });
};