import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000/locationtypes'
})
export default{
    getAllLocationTypes() {
        return http.get()
    },
    getLocationTypesById(locationTypeId) {
        return http.get(`/id/${locationTypeId}`)
    },
    getLocationTypesByName(locationTypeName) {
        return http.get(`/name/${locationTypeName}`)
    },
    createLocationType() {
        return http.post('/create')
    },
    updateLocationType(locationTypeId) {
        return http.put(`/update/${locationTypeId}`)
    },
    deleteLocationType(locationTypeId) {
        return http.delete(`delete/${locationTypeId}`)
    },
}