import axios from "axios";
import {BASE_ADD_ANNOUNCEMENT_URL} from "./urlRepository";

export const addAnnouncement = (data) => async (dispatch) => {
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    await axios.post(BASE_ADD_ANNOUNCEMENT_URL, data, config);
}