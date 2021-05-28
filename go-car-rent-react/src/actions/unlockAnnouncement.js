import axios from 'axios';
import {BASE_UNLOCK_ANNOUNCEMENT_URL} from "./urlRepository";

export const unlockAnnouncement = (announcementId) => async (dispatch) => {

    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }

    return await axios.put(BASE_UNLOCK_ANNOUNCEMENT_URL.replace(":announcementId", announcementId), null, config);
}