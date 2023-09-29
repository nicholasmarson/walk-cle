import axios from 'axios';

export default {
    getPlace() {
        return axios.get('https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=%2B16502530000&inputtype=phonenumber&key=AIzaSyDdqqFWrwPg33CoEoUq8AacD5qyp8gb4ms');
    },
}