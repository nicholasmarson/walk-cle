<template>
    <div class="container">
        
    </div>
</template>

<script>
import locationService from '../services/locationService.js';

export default {
    name: 'location-popup-box',
    props: ['location'],
    data() {
        return {
            locations: [],
        };
    },
    created() {
        locationService.getAllLocations().then( response => {
            this.locations = response.data;
        });
    },
}
</script>

<style scoped>

</style>