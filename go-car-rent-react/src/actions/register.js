import axios from "axios";
import {BASE_REGISTER_URL} from "./urlRepository";

export const registerUser = (data) => async (dispatch) => {
    await axios.post(BASE_REGISTER_URL, data);
};