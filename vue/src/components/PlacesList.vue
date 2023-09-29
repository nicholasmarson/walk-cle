<template>
    <div class="places-list">
        <PlaceCard v-for="candidate in candidates" :key="candidate.place_id" :candidate="candidate" />
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
            candidates: []
        }
    },
    created() {
        PlacesService.getPlace().then( (response) => {
            this.candidates = response.data.candidates;
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