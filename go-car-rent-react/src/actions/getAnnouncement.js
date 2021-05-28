import axios from 'axios';
import {BASE_ANNOUNCEMENT_URL} from "./urlRepository";

export const getAnnouncement = async (announcementId) => {
    const config = {
        headers: {
            "Authorization" : "Bearer " + localStorage.getItem("token")
        }
    }

    return await axios.get(BASE_ANNOUNCEMENT_URL.replace(":announcementId", announcementId), config);
}