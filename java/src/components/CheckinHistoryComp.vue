<template>
    <div>
        <ul>
            <li v-for="checkin in checkins" :key="checkin.checkinId">
                {{ checkin.locationName }} |
                {{ checkin.timestamp }}
            </li>
        </ul>
    </div>
</template>

<script>
import checkInService from '../services/CheckInService.js';

export default {
    name: 'checkin-history-comp',
    data() {
        return {
            checkins: [],
        }
    },
    created() {
        checkInService.checkInRecords().then (response => {
            this.checkins = response.data;
        });
    },
}

</script>

<style scoped>

</style>