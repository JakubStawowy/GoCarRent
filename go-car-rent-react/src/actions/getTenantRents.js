import axios from 'axios';

export const getTenantRents = async (id) => {
    const url = "http://localhost:8080/api/rents/tenant/"+id;
    const config = {
        headers: {
            "Authorization" : "Bearer " + localStorage.getItem("token")
        }
    }

    return await axios.get(url, config);
}