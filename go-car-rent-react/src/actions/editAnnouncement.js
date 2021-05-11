import axios from "axios";

export const editAnnouncement = (data, announcementId) => async (dispatch) => {
    const url = "http://localhost:8080/api/announcements/"+announcementId+"/edit";
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    await axios.put(url, data, config);
}