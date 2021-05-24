import axios from 'axios';

export const saveUser = async (data) => {
    const url = "http://localhost:8080/api/users/" + localStorage.getItem('userId') + "/edit";
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }

    return await axios.put(url, data, config);
}