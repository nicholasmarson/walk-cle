<template>
    <div class="place-card">
        <!-- <div v-if="place" class="place-card-address">{{ place.formatted_address }}</div>
        <div v-if="place" class="place-card-name">{{ place.name }}</div>
        <div v-if="place" class="place-card-id">{{ place.place_id }}</div>
        <div v-if="place" class="place-card-rating">{{ place.rating }}</div> -->
        
        <!-- {{ candidates }} -->
    
        <div v-if="candidate" class="place-card-id">{{ candidate.place_id }}</div>

    </div>
</template>

<script>
export default {
    props: ['candidate']
}
</script>

<style scoped>

</style>