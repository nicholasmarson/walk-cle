<template>
  <div class="map-container">
    <button @click="getUserLocation">Get My Location</button>
    <input v-model="searchTerm" @input="handleInput" placeholder="Discover CLE"/>
    <button @click="Search">Search</button>
    <div id="map"></div>
  </div>
</template>

<script>
import mapboxgl from "mapbox-gl";

export default {
  data() {
    return {
      map: null,
      searchTerm: ""
    };
  },
  mounted() {
    mapboxgl.accessToken =
      "pk.eyJ1Ijoid2Fsa2NsZTIxNiIsImEiOiJjbG16MGVvdWkxM2QzMm9wNjNobm9hZGQyIn0.5r382ZeMc0zOhHpiAd9D2A";

    // Declare latitude and longitude variables
    let latitude, longitude;

    // Initialize the map at the user's location
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          latitude = position.coords.latitude;
          longitude = position.coords.longitude;

          this.map = new mapboxgl.Map({
            container: "map",
            style: "mapbox://styles/mapbox/streets-v12",
            center: [longitude, latitude], // Center the map at the user's location
            zoom: 15, // Set an initial zoom level
          });

          // Add map controls, markers, and other customizations here
          this.map.dragPan.enable();
          
         

          // Add GeolocateControl
          const geolocate = new mapboxgl.GeolocateControl({
            positionOptions: {
              enableHighAccuracy: true,
            },
            trackUserLocation: true,
            showUserLocation: true,
          });

          // Add the GeolocateControl to the map
          this.map.addControl(geolocate);

          // Event listener for geolocation update
          this.map.on("load", () => {
            geolocate.on("geolocate", (event) => {
              const { coords } = event;
              latitude = coords.latitude;
              longitude = coords.longitude;

              // Update the map's center to the user's location
              this.map.setCenter([longitude, latitude]);
            });
          });

          // Add a marker for the user's location
          new mapboxgl.Marker()
            .setLngLat([longitude, latitude])
            .addTo(this.map);
        },
        (error) => {
          if (error.code === error.PERMISSION_DENIED) {
            alert(
              "You denied the request for geolocation. Please enable location services in your browser settings."
            );
          } else {
            alert(`Geolocation error: ${error.message}`);
          }
        }
      );
    } else {
      alert("Geolocation is not available in your browser.");
    }
    
  },
  methods: {
  
    search() {
    const url = `https://api.mapbox.com/geocoding/v5/mapbox.places/${this.searchTerm}.json?access_token='pk.eyJ1Ijoid2Fsa2NsZTIxNiIsImEiOiJjbG16MGVvdWkxM2QzMm9wNjNobm9hZGQyIn0.5r382ZeMc0zOhHpiAd9D2A'`;

    fetch(url)
      .then(response => response.json())
      .then(data => {
        const [longitude, latitude] = data.features[0].center;

        // Center the map on the location
        this.map.setCenter([longitude, latitude]);

        // Add a marker to the location (optional)
        new mapboxgl.Marker()
          .setLngLat([longitude, latitude])
          .addTo(this.map);
      })
      .catch(error => {
        console.error(error);
      });
  }
  },




  
};
</script>

<style>
.map-container {
  width: 100%;
  height: 100vh;
}

#map {
  width: 100%;
  height: 90%;
}
</style>