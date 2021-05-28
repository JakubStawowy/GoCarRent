import axios from 'axios';
import {BASE_EDIT_USER_URL} from "./urlRepository";

export const saveUser = async (data) => {
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }

    return await axios.put(BASE_EDIT_USER_URL.replace(":userId", localStorage.getItem('userId')), data, config);
}