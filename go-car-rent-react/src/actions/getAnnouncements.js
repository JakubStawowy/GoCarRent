import axios from "axios";

export const getAnnouncements = async (body = []) => {

    let url = 'http://localhost:8080/api/announcements';
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
        url = url + '/filter?criteria=' + criteria;
    }
    return axios.get(url, config)
}