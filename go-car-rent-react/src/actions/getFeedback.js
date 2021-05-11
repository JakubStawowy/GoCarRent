import axios from 'axios';

export const getFeedback = async (userId) => {
    const url = "http://localhost:8080/api/feedback/user/"+userId;
    console.log(url);
    const config = {
        headers: {
            Authorization: "Bearer " + localStorage.getItem('token')
        }
    }
    return await axios.get(url, config);
}