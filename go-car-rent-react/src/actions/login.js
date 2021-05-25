import axios from "axios";

export const loginUser = (data) => async (dispatch) => {

    const url = 'http://localhost:8080/api/login?email='+data.email+'&password='+data.password;
    const response = await axios.post(url);
    dispatch({
        type: 'LOGIN',
        payload: response
    });
};