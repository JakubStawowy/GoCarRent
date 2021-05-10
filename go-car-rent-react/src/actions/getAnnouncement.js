import axios from 'axios';

export const getAnnouncement = (id) => async () => {
    const url = "http://localhost:8080/api/announcements/"+id;
    const config = {
        headers: {
            "Authorization" : "Bearer " + localStorage.getItem("token")
        }
    }

    return axios.get(url, config);
}