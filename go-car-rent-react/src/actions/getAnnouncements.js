import axios from "axios";
import {BASE_ANNOUNCEMENTS_FILTER_URL, BASE_ANNOUNCEMENTS_URL} from "./urlRepository";

export const getAnnouncements = async (body = []) => {

    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    };

    if (body.length !== 0) {
        let criteria = '';
        body.forEach(specification => {
            criteria = criteria + specification.key + specification.operation + specification.value + ";";
        });
        criteria = criteria.substr(0, criteria.length - 1);

        return axios.get(BASE_ANNOUNCEMENTS_FILTER_URL.replace(":criteria", criteria), config);
    }
    return axios.get(BASE_ANNOUNCEMENTS_URL, config);
}