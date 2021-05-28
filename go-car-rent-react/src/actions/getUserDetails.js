import axios from "axios";
import {BASE_USER_DETAILS_URL} from "./urlRepository";

export const getUserDetails = async (userId) => {
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    return await axios.get(BASE_USER_DETAILS_URL.replace(":userId", userId), config);
}