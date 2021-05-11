import axios from 'axios';

export const getAnnouncement = async (id) => {
    const url = "http://localhost:8080/api/announcements/"+id;
    const config = {
        headers: {
            "Authorization" : "Bearer " + localStorage.getItem("token")
        }
    }

    return await axios.get(url, config);
}