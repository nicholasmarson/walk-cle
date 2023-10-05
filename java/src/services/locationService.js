import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000/locations'
})
export default{
    getAllLocations() {
        return http.get()
    },
    getLocationById(locationId) {
        return http.get(`/id/${locationId}`)
    },
    getLocationByName(locationName) {
        return http.get(`/name/${locationName}`)
    },
    getAllLocationsByLocationTypeName(locationTypeName) {
        return http.get(`/${locationTypeName}`)
    },
    createLocation() {
        return http.post('/create')
    },
    updateLocation(locationId) {
        return http.put(`/update/${locationId}`)
    },
    deleteLocation(locationId) {
        return http.delete(`delete/${locationId}`)
    },
}