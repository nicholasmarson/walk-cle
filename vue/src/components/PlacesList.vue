<template>
    <div class="places-list">
        <PlaceCard v-for="result in results" :key="result.place_id" :result="result" />
        {{ results }}
    </div>
</template>

<script>
import PlacesService from '../services/PlacesService.js';
import PlaceCard from '../components/PlaceCard.vue';

export default {
    components: {
        PlaceCard
    },
    data() {
        return {
            results: []
        }
    },
    created() {
        PlacesService.getNearbyPlaces().then( (response) => {
            this.results = response.data;
        })
    }
}
</script>

<style scoped>
.places-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
</style>