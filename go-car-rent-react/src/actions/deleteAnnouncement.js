import axios from 'axios';

export const deleteAnnouncement = (data) => async (dispatch) => {
    const url = "http://localhost:8080/api/announcements/" + data.announcementId + "/remove?userId=" + localStorage.getItem('userId') + "&password=" + data.password;
    console.log(url);
    const config = {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('token')
        }
    }
    await axios.delete(url, config);
}