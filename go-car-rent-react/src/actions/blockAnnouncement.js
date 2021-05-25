import axios from 'axios';

export const blockAnnouncement = (announcementID) => async (dispatch) => {
    const url = "http://localhost:8080/api/announcements/" + announcementID + "/block";
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }

    return await axios.put(url, null, config);
}