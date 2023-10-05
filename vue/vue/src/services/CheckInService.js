import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000/'
})
export default{
    barCheckIn(locationId) {
        return http.post(`rewards/barcheckin/${locationId}`)
    },

    parkCheckIn(locationId) {
        return http.post(`rewards/parkcheckin/${locationId}`)
    },

    stadiumCheckIn(locationId) {
        return http.post(`rewards/stadiumcheckin/${locationId}`)
    },

    checkInRecords() {
        return http.get('/checkinrecords')
    }
}