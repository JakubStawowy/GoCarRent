import axios from "axios";

export const getAnnouncements = async (body = []) => {

    let url;
    let config;
    console.log(body);
    if (body.length === 0) {
        url = 'http://localhost:8080/api/announcements';
        config = {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        }
    }
    else {
        let criteria = '';
        body.forEach(specification => {
            criteria = criteria + specification.key + specification.operation + specification.value + ";";
        });
        criteria = criteria.substr(0, criteria.length - 1);
        url = 'http://localhost:8080/api/announcements/filter?criteria=' + criteria;

        config = {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        }
    }
    return axios.get(url, config)
}