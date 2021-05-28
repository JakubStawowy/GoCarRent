import axios from "axios";
import {BASE_LOGOUT_URL} from "./urlRepository";

export const logoutUser = (data) => async (dispatch) => {
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    const response = await axios.put(BASE_LOGOUT_URL.replace(":userId", data.userId), null, config).catch((error)=>{
        alert(error);
    });
    dispatch({
        type: 'LOGOUT',
        payload: response
    });
};
