import axios from "axios";

export default async function getUserById(id) {
    const url = 'http://localhost:8080/api'
    const result = await axios.get(url+'/users/'+id);
    return result.data;
}