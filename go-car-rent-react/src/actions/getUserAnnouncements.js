import axios from 'axios';
import {BASE_USER_ANNOUNCEMENTS_URL} from "./urlRepository";

export const getUserAnnouncements = async (userId) => {
    const config = {
        headers: {
            "Authorization" : "Bearer " + localStorage.getItem("token")
        }
    }

    return await axios.get(BASE_USER_ANNOUNCEMENTS_URL.replace(":userId", userId), config);
}