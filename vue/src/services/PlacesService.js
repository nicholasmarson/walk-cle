import axios from 'axios';

// const http = axios.create({
//     baseURL: "localhost:9000"
// });



export default {
    
    getNearbyPlaces() {
        return axios.get('/nearbyplaces');
    }
}