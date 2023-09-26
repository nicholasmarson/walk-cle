<template>
  <div>
    <div id="map"></div>
  </div>
</template>
  
<script>
import mapboxgl from "mapbox-gl";
import { MapboxSearchBox } from "@mapbox/search-js-web";

export default {
  data() {
    return {
      ACCESS_TOKEN:
        "pk.eyJ1Ijoid2Fsa2NsZTIxNiIsImEiOiJjbG16MGVvdWkxM2QzMm9wNjNobm9hZGQyIn0.5r382ZeMc0zOhHpiAd9D2A",
    };
  },
  mounted() {
    // make environment variable
    mapboxgl.accessToken =
      "pk.eyJ1Ijoid2Fsa2NsZTIxNiIsImEiOiJjbG16MGVvdWkxM2QzMm9wNjNobm9hZGQyIn0.5r382ZeMc0zOhHpiAd9D2A";

    let latitude, longitude;

    // Initialize the map at the user's location
    navigator.geolocation.getCurrentPosition((position) => {
      latitude = position.coords.latitude;
      longitude = position.coords.longitude;

      this.map = new mapboxgl.Map({
        container: "map",
        style: "mapbox://styles/mapbox/streets-v12",
        center: [longitude, latitude], // Center the map at the user's location
        zoom: 15, // Set an initial zoom level
      });

      const searchBox = new MapboxSearchBox();
      searchBox.accessToken = this.ACCESS_TOKEN;
      searchBox.options = {
        language: "en",
        country: "us",
      };
      this.map.addControl(searchBox);

      // Add Geolocate Control
      const geolocate = new mapboxgl.GeolocateControl({
        positionOptions: {
          enableHighAccuracy: true,
        },
        trackUserLocation: true,
        showUserLocation: true,
      });
      this.map.addControl(geolocate);

      // Event listener for geolocation update
      // this.map.on("load", () => {
      //   geolocate.on("geolocate", (event) => {
      //     const { coords } = event;
      //     const { longitude, latitude } = coords;

      //     // Update the map's center to the user's location
      //     this.map.setCenter([longitude, latitude]);

      //     // Remove the previous user location marker if it exists
      //     if (this.userLocationMarker) {
      //       this.userLocationMarker.remove();
      //     }
      //     // Create a custom user location marker (blue arrow)
      //     this.userLocationMarker = new mapboxgl.Marker({
      //       color: "#007bff", // Blue color for the arrow
      //       rotation: event.coords.heading, // Use the heading to set the arrow's rotation
      //     })
      //       .setLngLat([longitude, latitude])
      //       .addTo(this.map);
      //   });
      // });
    });
  },
  methods: {
    // getUserLocation() {
    //   // Trigger geolocation
    //   const geolocateButton = document.querySelector(
    //     ".mapboxgl-ctrl-geolocate"
    //   );
    //   if (geolocateButton) {
    //     geolocateButton.click();
    //   }
    // },
  },
};
</script>

<style scoped>
#map {
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
  width: 100%;
  height: 90%;
  position: absolute;
  display: flex;
  align-items: center;
  flex-grow: 1;
}

#map canvas{
    width: 80vw;
    height: 80vh;
}

.mapboxgl-canvas {
    width: 100%;
    height: 100%;
}
</style>
