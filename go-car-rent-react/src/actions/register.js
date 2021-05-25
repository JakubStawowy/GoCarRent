import axios from "axios";

export const registerUser = (data) => async (dispatch) => {
    const url = 'http://localhost:8080/api/register';
    await axios.post(url, data);
};