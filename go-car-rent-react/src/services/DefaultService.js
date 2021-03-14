import axios from 'axios'

const REST_API_URL = 'http://localhost:8080/api/user/2';

class DefaultService {
    getHelloMessage(){
        return axios.get(REST_API_URL);
    }
}

export default new DefaultService()