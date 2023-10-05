import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000/rewards'
})
export default{
    trophyStatus: {
        defender: false,
        bar: false,
        sport: false,
        tree: false,
    },
    checkTrophyStatus() {
        return Promise.all([
        this.checkDefenderTrophyStatus(),
        this.checkBarTrophyStatus(),
        this.checkParkTrophyStatus(),
        this.checkStadiumTrophyStatus()
        ]);
    },
    checkDefenderTrophyStatus() {
        http.get('places')
            .then(response => {
                this.trophyStatus.defender = response.data;
            })
            .catch(error => {
                console.error(error);
            });
    },
    checkBarTrophyStatus() {
        http.get('bars')
            .then(response => {
                this.trophyStatus.bar = response.data;
            })
            .catch(error => {
                console.error(error);
            });
    },
    checkParkTrophyStatus() {
        http.get('parks')
            .then(response => {
                this.trophyStatus.tree = response.data;
            })
            .catch(error => {
                console.error(error);
            });
    },
    checkStadiumTrophyStatus() {
        http.get('stadiums')
            .then(response => {
                this.trophyStatus.sport = response.data;
            })
            .catch(error => {
                console.error(error);
            });
    },
    getPlacesCount() {
        return http.get('/placesvisited')
    },
    getStadiumCount() {
        return http.get('/stadiumsvisited')
    },
    getParkCount() {
        return http.get('parksvisited')
    },
    getBarCount() {
        return http.get('/barsvisited')
    }
}