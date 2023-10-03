<template>
  <div class="container">
    <div class="form-search">
          <!-- <input type="text" name="location" :value="location.coordinates" disabled /> -->
    <form @submit.prevent="filterNameSearch" class="name-search">
      <label for="location">Location:</label>
      <input type="text" id="location" v-model="searchQuery" />
      <button type="submit">Search</button>
    </form>
      <!-- Dropdown Menu -->
    <form @submit.prevent="filterTypeSearch" class="type-search">
      <label for="locationType">Location Type:</label>
      <select id="locationType" v-model="selectedLocationType">

        <option value="all">All</option>
        <option value="stadiums">Stadiums</option>
        <option value="parks">Parks</option>
        <option value="bars">Bars</option>
      </select>
      <button type="submit">Search</button>
    </form>
    </div>

    <div id="map"></div>
    <!-- <button class="btn" @click="requestLocation">Get Current Location</button>-->
    
  </div>
</template>
  
<script>
import mapboxgl from "mapbox-gl";
// import { MapboxSearchBox } from "@mapbox/search-js-web";
// import * as turf from "@turf/turf";
import MapboxDirections from "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions";
import "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions.css";
import service from '../services/locationService.js'
import axios from 'axios';
// const mapboxgl = require("mapbox-gl/dist/mapbox-gl.js");
// Retrieve API key from environment variables
mapboxgl.accessToken = process.env.VUE_APP_MAPBOX_KEY;

export default {
  
  data() {
    return {
      map: null,
      query: "",
      location: {
        type: "Point",
        coordinates: [],
      },
      markers: [],
      stadiums: [],
      Bars: [],
      all: [],
      parks: [],
      poi: [],
      coffee: [],
       popupTexts: [
        "This is the first marker's www.google.com",
        "This is the second marker's custom text.",
        // Add more custom texts here, one for each marker
      ],
      searchBox: null,
      userLocation: {
        lat: 0,
        lng: 0,
      },
      // created(){
      //   this.stadiums = service.getAllStadiums().then(
      //     (rep) =>{ 
      //       this.stadiums = rep.data;
      //     }
      //   )
      // }
    };
  },
  methods: {
    initMap() {
      // Create map object
      this.map = new mapboxgl.Map({
        container: "map",
        style: 'mapbox://styles/mapbox/streets-v12',
        center: [this.userLocation.lng, this.userLocation.lat],
        zoom: 15,
      });

      this.addMapClickListener();
    },
    addMapClickListener() {
      this.map.on("click", (e) => {
        this.setLocation(e.lngLat);
      });
    },
    setLocationCoordinates(lngLat) {
      this.setLocationCoordinates.coordinates = [
        Math.round(lngLat.lng * 10000) / 10000,
        Math.round(lngLat.lat * 10000) / 10000,
      ];
    },
    addMapMarker(lngLat) {
      const marker = new mapboxgl.Marker({ color: "red" })
        .setLngLat(lngLat)
        .addTo(this.map);
      this.markers.push(marker);
    },
    removeMapMarkers() {
      this.markers.forEach((marker) => marker.remove());
      this.markers = [];
    },
    setLocation(lngLat) {
      this.removeMapMarkers();
      this.addMapMarker(lngLat);
      this.setLocationCoordinates(lngLat);
    },
    requestLocation() {
      // Request to get the user's current location
      navigator.geolocation.getCurrentPosition((position) => {
        // get the latitude and longitude returned
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;

        // set location data
        this.setLocation({ lng, lat });

        // move the ap to show the location
        this.map.flyTo({ center: [lng, lat], zoom: 15 });

        // Store user location
        this.userLocation.lat = lat;
        this.userLocation.lng = lng;

        // Add a marker for the current location
        this.addMapMarker({ lng, lat });
      });
    },
    getDirections() {
      // Set up Mapbox Directions control
      const directions = new MapboxDirections({
        accessToken: mapboxgl.accessToken,
        unit: "imperial",
        profile: "mapbox/walking",
      });
      directions.setOrigin([this.userLocation.lng, this.userLocation.lat]);
      this.map.addControl(directions, "bottom-left");
    },
    // search() {
    //   // Set up Mapbox Search Box
    //   const point = turf.point([this.userLocation.lng, this.userLocation.lat]);
    //   const options = { units: "miles" };
    //   const radius = 5; // 1 mile
    //   const bbox = turf.bbox(turf.buffer(point, radius, options));

    //   const searchBox = new MapboxSearchBox();
    //   searchBox.accessToken = mapboxgl.accessToken;
    //   searchBox.options = {
    //     language: "en",
    //     country: "us",
    //     bbox: bbox,
    //   };

    //   // searchBox.on('result', (result) => {
    //   //     const { lng, lat } = result.result.geometry.coordinates;
    //   //     this.addMapMarker({ lng, lat });
    //   //     this.getDirections({ lng, lat });
    //   // });

    //   this.map.addControl(searchBox);
    // },
    
    geoLocate() {
      // Adds Location control
      const geolocateControl = new mapboxgl.GeolocateControl({
        positionOptions: {
          enableHighAccuracy: true,
        },
        trackUserLocation: true,
        showUserLocation: true,
      });
      geolocateControl.on("geolocate", (e) => {
        const lat = e.coords.latitude;
        const lng = e.coords.longitude;
        this.addMapMarker({ lng, lat });
      });
      this.map.addControl(geolocateControl);
    },
     
    fetchDataFromAPI() {
      // Assuming 'service.getAllLocations()' is an asynchronous function that returns a promise
      service.getAllLocations()
        .then((response) => {
          // Assuming the response contains an array of locations
          this.locations = response.data;
        })
        .catch((error) => {
          console.error('Error fetching locations:', error);
        });
    },

 filterNameSearch(){
   // Define the API endpoint based on the selected location type
  let apiEndpoint = "http://localhost:9000/locations/";

  switch (true) {
  case this.searchQuery === "stadiums":
    apiEndpoint += "Stadiums";
    break;
  case  this.searchQuery === "parks":
    apiEndpoint += "Parks";
    break;
  case this.searchQuery === "bars":
    apiEndpoint += "Bars";
    break;
  default:
    // Handle the default case if needed
    break;
}
  // Make an API request with the searchQuery and selected location type
  axios
    .get(apiEndpoint, {
      params: { query: this.searchQuery },
    })
    .then((response) => {
      const locations = response.data;

      // Clear existing markers and popups
      this.removeMarkersAndPopups();

      // Add markers for each location
      locations.forEach((location) => {
        const { locationId, locationLatitude, locationLongitude, locationName } = location;
        const marker = new mapboxgl.Marker({ color: "blue" })
          .setLngLat([locationLongitude, locationLatitude])
          .addTo(this.map);

        // Create a popup with custom content
        const popupContent = `
          <div>
            <p>${locationName}</p>
            <p>${locationLongitude} " " ${locationLatitude}</p>
            <button id="checkInBtn${locationId}" class="check-in-button">Check-In</button>
          </div>
        `;
        //41.497257, -81.698738
        this.map.flyTo({ center: [-81.698738, 41.497257], zoom: 14 });
        const popup = new mapboxgl.Popup({ offset: 25 })
          .setHTML(popupContent);

        // Attach the popup to the marker
        marker.setPopup(popup);

        // Add the marker to the corresponding category array
        if (this.selectedLocationType === "stadiums") {
          this.stadiums.push(marker);
        } else if (this.selectedLocationType === "parks") {
          this.parks.push(marker);
        } else if (this.selectedLocationType === "bars") {
          this.Bars.push(marker);
        }else if (this.selectedLocationType === "all"){
          this.all.push(marker)}
        
      });
    })
    .catch((error) => {
      console.error("Error fetching locations:", error);
    });
  },
filterTypeSearch(){
   // Define the API endpoint based on the selected location type
  let apiEndpoint = "http://localhost:9000/locations/";

  switch (true) {
  case this.selectedLocationType === "stadiums" :
    apiEndpoint += "Stadiums";
    break;
  case this.selectedLocationType === "parks" :
    apiEndpoint += "Parks";
    break;
  case this.selectedLocationType === "bars" :
    apiEndpoint += "Bars";
    break;
  default:

    break;
}

  // Make an API request with the searchQuery and selected location type
  axios
    .get(apiEndpoint, {
      params: { query: this.selectedLocationType },
    })
    .then((response) => {
      const locations = response.data;

      // Clear existing markers and popups
      this.removeMarkersAndPopups();

      // Add markers for each location
      locations.forEach((location) => {
        const { locationId, locationLatitude, locationLongitude, locationName } = location;
        const marker = new mapboxgl.Marker({ color: "blue" })
          .setLngLat([locationLongitude, locationLatitude])
          .addTo(this.map);

        // Create a popup with custom content
        const popupContent = `
          <div>
            <p>${locationName}</p>
            <p>${locationLongitude} " " ${locationLatitude}</p>
            <button id="checkInBtn${locationId}" class="check-in-button">Check-In</button>
          </div>
        `;
        //41.497257, -81.698738
        this.map.flyTo({ center: [-81.698738, 41.497257], zoom: 14 });
        const popup = new mapboxgl.Popup({ offset: 25 })
          .setHTML(popupContent);

        // Attach the popup to the marker
        marker.setPopup(popup);
        

        // Add the marker to the corresponding category array
        if (this.selectedLocationType === "stadiums") {
          this.stadiums.push(marker);
        } else if (this.selectedLocationType === "parks") {
          this.parks.push(marker);
        } else if (this.selectedLocationType === "bars") {
          this.Bars.push(marker);
        }else if (this.selectedLocationType === "all"){
          this.all.push(marker)}
        
      });
    })
    .catch((error) => {
      console.error("Error fetching locations:", error);
    });
  },
    removeMarkersAndPopups() {
  this.markers.forEach((marker) => {
    marker.remove();
    
  });
  this.all.forEach((poi) =>
  poi.remove())
  this.stadiums.forEach((poi) =>{
    poi.remove();
  })
  this.Bars.forEach((poi) =>{
    poi.remove();
  })
  this.parks.forEach((poi) =>{
    poi.remove();
  })
  this.poi.forEach((poi) =>{
    poi.remove();
  })
  this.coffee.forEach((poi) =>{
    poi.remove();
  })
  this.parks = [];
  this.Bars = [];
  this.poi = [];
  this.markers = [];
  this.coffee = [];
  this.all =[];
  
    },
  },
  mounted() {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lng = position.coords.longitude;
      this.userLocation = { lat, lng };
      this.initMap();
      this.map.flyTo({ center: [lng, lat], zoom: 15 });
      this.addMapMarker({ lng, lat });

      this.getDirections();
      this.search();
      this.navigation();
      this.geoLocate();
      this.fetchDataFromAPI(); 
      this.fetchDataFroStadiums()
    });
  },
};
</script>
  
<style scoped>
#map {
  grid-area: map;
  width: 100vw;
  height: 100%;
}

.name-search {
  grid-area: name
}

.type-search {
  grid-area: type
}

.container {
  display: flex;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "name search"
    "map map";
}

.mapbox-directions-instructions {
  
  font-family: "Urbanist", sans-serif;
  font-size: 14px;
  color: #333;
}

.mapbox-directions-route-summary {
  height: 1vh;
  width: 1vw;
  font-family: "Urbanist", sans-serif;
}

.mapbox-directions-step {
  height: 1vh;
  width: 1vw;
  margin-bottom: 5px;
}

.mapbox-directions-step-number {
  height: 1vh;
  width: 1vw;
  font-weight: bold;
  color: #0078d4;
}

.mapbox-directions-step-text {
  height: 1vh;
  width: 1vw;
  color: #000000;
}
</style>