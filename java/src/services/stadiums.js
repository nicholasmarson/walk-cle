import axios from 'axios';
export default{
    getAllLocations(){
        return axios.get('http://localhost:9000/locations/Stadiums')
    },}