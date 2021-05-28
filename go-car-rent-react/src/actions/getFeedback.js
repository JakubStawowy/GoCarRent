import axios from 'axios';
import {BASE_FEEDBACK_URL} from "./urlRepository";

export const getFeedback = async (userId) => {
    const config = {
        headers: {
            Authorization: "Bearer " + localStorage.getItem('token')
        }
    }
    return await axios.get(BASE_FEEDBACK_URL.replace(":userId", userId), config);
}