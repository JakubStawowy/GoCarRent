import axios from "axios";

import {
    BASE_ADD_ANNOUNCEMENT_URL,
    BASE_ANNOUNCEMENT_URL,
    BASE_ANNOUNCEMENTS_FILTER_URL,
    BASE_ANNOUNCEMENTS_URL,
    BASE_BLOCK_ANNOUNCEMENT_URL,
    BASE_DELETE_ANNOUNCEMENT_URL,
    BASE_EDIT_ANNOUNCEMENT_URL, BASE_EDIT_USER_URL,
    BASE_FEEDBACK_URL, BASE_LOGIN_URL, BASE_LOGOUT_URL, BASE_REGISTER_URL, BASE_RENT_REGISTER_URL,
    BASE_TENANT_RENTS_URL, BASE_UNLOCK_ANNOUNCEMENT_URL,
    BASE_USER_ANNOUNCEMENTS_URL, BASE_USER_DETAILS_URL,

} from "./urlRepository";
import {getConfig} from "./getConfig";

export const addAnnouncement = (data) => async (dispatch) => await axios.post(BASE_ADD_ANNOUNCEMENT_URL, data, getConfig());
export const blockAnnouncement = (announcementId) => async (dispatch) => await axios.put(BASE_BLOCK_ANNOUNCEMENT_URL.replace(":announcementId", announcementId), null, getConfig());
export const deleteAnnouncement = (data) => async (dispatch) => await axios.delete(BASE_DELETE_ANNOUNCEMENT_URL
            .replace(":announcementId", data.announcementId)
            .replace(":userId", localStorage.getItem('userId'))
            .replace(":password", data.password), getConfig());
export const editAnnouncement = (data, announcementId) => async (dispatch) => await axios.put(BASE_EDIT_ANNOUNCEMENT_URL.replace(":announcementId", announcementId), data, getConfig());
export const getAnnouncement = async (announcementId) => await axios.get(BASE_ANNOUNCEMENT_URL.replace(":announcementId", announcementId), getConfig());
export const getAnnouncements = async (body = []) => {
    if (body.length !== 0) {
        let criteria = '';
        body.forEach(specification => {
            criteria = criteria + specification.key + specification.operation + specification.value + ";";
        });
        criteria = criteria.substr(0, criteria.length - 1);

        return axios.get(BASE_ANNOUNCEMENTS_FILTER_URL.replace(":criteria", criteria), getConfig());
    }
    return axios.get(BASE_ANNOUNCEMENTS_URL, getConfig());
}
export const getFeedback = async (userId) => await axios.get(BASE_FEEDBACK_URL.replace(":userId", userId), getConfig());
export const getTenantRents = async (tenantId) => await axios.get(BASE_TENANT_RENTS_URL.replace(":tenantId", tenantId), getConfig());
export const getUserAnnouncements = async (userId) => await axios.get(BASE_USER_ANNOUNCEMENTS_URL.replace(":userId", userId), getConfig());
export const getUserDetails = async (userId) => await axios.get(BASE_USER_DETAILS_URL.replace(":userId", userId), getConfig());
export const loginUser = (data) => async (dispatch) => {
    const response = await axios.post(BASE_LOGIN_URL.replace(":email", data.email).replace(":password", data.password));
    dispatch({
        type: 'LOGIN',
        payload: response
    });
};
export const logoutUser = (data) => async (dispatch) => {
    const response = await axios.put(BASE_LOGOUT_URL.replace(":userId", data.userId), null, getConfig()).catch((error)=>{
        alert(error);
    });
    dispatch({
        type: 'LOGOUT',
        payload: response
    });
};
export const registerUser = (data) => async (dispatch) =>await axios.post(BASE_REGISTER_URL, data);
export const registerRent = (announcementId, tenantId) => async (dispatch) => axios.post(BASE_RENT_REGISTER_URL.replace(":tenantId", tenantId).replace(":announcementId", announcementId), null, getConfig());
export const saveUser = async (data) => await axios.put(BASE_EDIT_USER_URL.replace(":userId", localStorage.getItem('userId')), data, getConfig());
export const unlockAnnouncement = (announcementId) => async (dispatch) => await axios.put(BASE_UNLOCK_ANNOUNCEMENT_URL.replace(":announcementId", announcementId), null, getConfig());
