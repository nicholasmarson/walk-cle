<template>
  <div class="container">
    <ul>
      <li v-for="location in locations" :key="location.id">
        <!-- {{ location.locationId }} |  -->
        <!-- {{ location.locationTypeName }} |  -->
        {{ location.locationName }} |
        <!-- {{ location.locationLatitude }} |  -->
        <!-- {{ location.locationLongitude }} |  -->
        <button @click="handleCheckIn(location.locationId)">Checkin</button>
      </li>
    </ul>
  </div>
</template>

<script>
import locationService from "../services/locationService.js";
import CheckInService from "../services/CheckInService.js";

export default {
  name: "locations-list",
  data() {
    return {
      locations: [],
      locationTypes: [],
      checkins: [],
    };
  },
  methods: {
    handleCheckIn(locationId) {
      // Call the barCheckIn function from the imported service
      if (locationId === 1 || locationId === 2) {
        CheckInService.parkCheckIn(locationId)

          .then((response) => {
            // Handle the response if needed
            console.log("Check-in successful", response);
          })
          .catch((error) => {
            // Handle any errors
            console.error("Error during check-in", error);
          });
      } else if (locationId === 3 || locationId === 4 || locationId === 5 || locationId === 6 || locationId === 7) {
        CheckInService.barCheckIn(locationId)
          .then((response) => {
            // Handle the response if needed
            console.log("Check-in successful", response);
          })
          .catch((error) => {
            // Handle any errors
            console.error("Error during check-in", error);
          });
      } else if (locationId === 8 || locationId === 9 || locationId === 10) {
        CheckInService.stadiumCheckIn(locationId)
          .then((response) => {
            // Handle the response if needed
            console.log("Check-in successful", response);
          })
          .catch((error) => {
            // Handle any errors
            console.error("Error during check-in", error);
          });
      }
    },
  },
  created() {
    locationService.getAllLocations().then((response) => {
      this.locations = response.data;
    });
  },
};
</script>

<style scoped>
</style>