import axios from 'axios';
import {BASE_TENANT_RENTS_URL} from "./urlRepository";

export const getTenantRents = async (tenantId) => {
    const config = {
        headers: {
            "Authorization" : "Bearer " + localStorage.getItem("token")
        }
    }

    return await axios.get(BASE_TENANT_RENTS_URL.replace(":tenantId", tenantId), config);
}