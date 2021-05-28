import axios from 'axios';
import {BASE_RENT_REGISTER_URL} from "./urlRepository";

export const registerRent = (announcementId, tenantId) => async (dispatch) => {

    const config = {
        headers: {
            Authorization: "Bearer " + localStorage.getItem('token')
        }
    };
    return axios.post(BASE_RENT_REGISTER_URL.replace(":tenantId", tenantId).replace(":announcementId", announcementId), null, config);
}