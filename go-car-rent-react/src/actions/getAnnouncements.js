import axios from "axios";

export const getAnnouncements = async () => {
    const url = 'http://localhost:8080/api/announcements';
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    return axios.get(url, config);
}