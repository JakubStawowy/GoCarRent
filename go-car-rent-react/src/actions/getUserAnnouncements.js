import axios from 'axios';

export const getUserAnnouncements = async (id) => {
    const url = "http://localhost:8080/api/announcements/user/"+id;
    const config = {
        headers: {
            "Authorization" : "Bearer " + localStorage.getItem("token")
        }
    }

    return await axios.get(url, config);
}