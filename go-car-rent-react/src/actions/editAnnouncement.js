import axios from "axios";
import {BASE_EDIT_ANNOUNCEMENT_URL} from "./urlRepository";

export const editAnnouncement = (data, announcementId) => async (dispatch) => {
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    await axios.put(BASE_EDIT_ANNOUNCEMENT_URL.replace(":announcementId", announcementId), data, config);
}