import axios from "axios";

export const addAnnouncement = (data) => async (dispatch) => {
    const url = "http://localhost:8080/api/announcements/add";
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    await axios.post(url, data, config);
}