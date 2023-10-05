<template>
  <div class="container">
    <ul>
      <li v-for="location in locations" :key="location.id">
        <!-- {{ location.locationId }} |  -->
        <!-- {{ location.locationTypeName }} |  -->
        <div class="location-info">
          {{ location.locationName }}
        </div>
        <!-- {{ location.locationLatitude }} |  -->
        <!-- {{ location.locationLongitude }} |  -->
        <button class="list-btn" @click="handleCheckIn(location.locationId)">Check In</button>
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

.list-btn {
  font-family: "Urbanist", sans-serif;
  display: inline-block;
  background: #407F7F;
  color: #fff;
  border: none;
  padding: 12px 100px; /* Increase padding to make the button wider */
  border-radius: 5px;
  cursor: pointer;
  text-decoration: none;
  font-size: 15px;
  width: auto; /* Let the button width be determined by its content */
  margin-top: 1px; /* Reduce the space above the button */
}

ul {
  padding: 0;
  list-style: none;
}

li{
  margin-bottom: 3px;
}

.location-info {
  font-weight: bold;
  margin-bottom: 1px;
}
</style>
