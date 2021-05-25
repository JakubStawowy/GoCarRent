import axios from "axios";

export const getUserDetails = async (userId) => {
    const url = "http://localhost:8080/api/users/" + userId;
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    return await axios.get(url, config);
}