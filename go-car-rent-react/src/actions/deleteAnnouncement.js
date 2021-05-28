import axios from 'axios';
import {BASE_DELETE_ANNOUNCEMENT_URL} from "./urlRepository";

export const deleteAnnouncement = (data) => async (dispatch) => {

    const config = {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('token')
        }
    }

    await axios.delete(BASE_DELETE_ANNOUNCEMENT_URL
        .replace(":announcementId", data.announcementId)
        .replace(":userId", localStorage.getItem('userId'))
        .replace(":password", data.password),
        config);
}