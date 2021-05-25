import axios from "axios";

export const logoutUser = (data) => async (dispatch) => {
    const url = 'http://localhost:8080/api/logout?id='+data.userId;
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    const response = await axios.put(url, null, config).catch((error)=>{
        alert(error);
    });
    dispatch({
        type: 'LOGOUT',
        payload: response
    });
};
