<template>
  <div class="container">
    <div class="form-search">
          <!-- <input type="text" name="location" :value="location.coordinates" disabled /> -->
    <form @submit.prevent="popupContent" class="name-search">
      <label for="location">Location:</label>
      <input type="text" id="location" v-model="searchQuery" />
      <button type="submit">Search</button>
    </form><form @submit.prevent="filterTypeSearch" class="type-search">
    <label for="locationType">Location Type:</label>
   <select id="locationType" v-model="Type">
  <option value="all">All</option>
  <option value="stadiums">Stadiums</option>
  <option value="parks">Parks</option>
  <option value="bars">Bars</option>
</select><button type="submit">Search</button>
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
      Type: "all",
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
    steps: 3,
  });
  
  directions.setOrigin([this.userLocation.lng, this.userLocation.lat]);
  this.map.addControl(directions, "bottom-left");

  // Create a button to toggle directions
  const toggleDirectionsButton = document.createElement("button");
  toggleDirectionsButton.innerText = " Directions";
  toggleDirectionsButton.style.position = "absolute";
  toggleDirectionsButton.style.top = "10px";
  toggleDirectionsButton.style.left = "10px";
  
  toggleDirectionsButton.addEventListener("click", () => {
    const directionsContainer = document.querySelector(".mapboxgl-ctrl-bottom-left");
    if (directionsContainer) {
      if (directionsContainer.style.display === "none" || !directionsContainer.style.display) {
        directionsContainer.style.display = "block"; // Show directions
      } else {
        directionsContainer.style.display = "none"; // Hide directions
      }
    } else {
      console.error("Directions control element not found in the DOM.");
    }
  });

  // Append the button to the map container
  const mapContainer = this.map.getContainer();
  mapContainer.appendChild(toggleDirectionsButton);
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
    
    popupContent() {
  // Define the API endpoint based on the selected location type
  let apiEndpoint = "http://localhost:9000/locations/name/";

  switch (true) {
    case this.searchQuery === "wendy park":
      apiEndpoint += "Wendy%20Park";
      break;
    case this.searchQuery === "steelers park":
      apiEndpoint += "Settlers%20Park";
      break;
    case this.searchQuery === "collision bend brewing company":
      apiEndpoint += "Collision%20Bend%20Brewing%20Company";
      break;
    case this.searchQuery === "butcher and the brewer":
      apiEndpoint += "Butcher%20and%20the%20Brewer";
      break;
    case this.searchQuery === "brewDog cleveland outpost":
      apiEndpoint += "BrewDog%20Cleveland%20Outpost";
      break;
    case this.searchQuery === "barley house":
      apiEndpoint += "Barley%20House";
      break;
    case this.searchQuery === "great lakes brewing":
      apiEndpoint += "Great%20Lakes%20Brewing%20Company";
      break;
    case this.searchQuery === "progressive field":
      apiEndpoint += "Progressive%20Field";
      break;
    case this.searchQuery === "cleveland browns stadium":
      apiEndpoint += "Cleveland%20Browns%20Stadium";
      break;
    case this.searchQuery === "rocket mortgage fieldHouse":
      apiEndpoint += "Rocket%20Mortgage%20FieldHouse";
      break;
    default:
      break;
  }

  // Make an API request with the searchQuery and selected location type
  axios
    .get(apiEndpoint, {
      params: { query: this.searchQuery },
    })
    .then((response) => {
      const location = response.data;
      // Clear existing markers and popups
      this.clearMarkersAndPopups();

      const {
        locationId,
        // locationTypeName,
        locationName,
        locationLatitude,
        locationLongitude,
        locationDescription,
        locationSunOpen,
        locationSunClose,
        locationMonOpen,
        locationMonClose,
        locationTueOpen,
        locationTueClose,
        locationWedOpen,
        locationWedClose,
        locationThuOpen,
        locationThuClose,
        locationFriOpen,
        locationFriClose,
        locationSatOpen,
        locationSatClose,
        locationImgUrl,
        locationInfoUrl,
      } = location;

      // Format the days and opening/closing times
      // const daysOfWeek = locationDays.join(", ");
      // const openingTimes = locationOpeningTimes.join(", ");
      // const closingTimes = locationClosingTimes.join(", ");
      const marker = new mapboxgl.Marker({ color: "blue" })
          .setLngLat([locationLongitude, locationLatitude ])
          .addTo(this.map);

      // Create the HTML content for the popup
      const popupContent = `
        <div>
          <h2>${locationName}</h2>
          <p>${locationDescription}</p>
          <p><strong>Sunday:</strong> ${locationSunOpen} - ${locationSunClose}</p>
          <p><strong>Monday:</strong> ${locationMonOpen} - ${locationMonClose}</p>
          <p><strong>Tuesday:</strong> ${locationTueOpen} - ${locationTueClose}</p>
          <p><strong>Wednesday:</strong> ${locationWedOpen} - ${locationWedClose}</p>
          <p><strong>Thursday:</strong> ${locationThuOpen} - ${locationThuClose}</p>
          <p><strong>Friday:</strong> ${locationFriOpen} - ${locationFriClose}</p>
          <p><strong>Saturday:</strong> ${locationSatOpen} - ${locationSatClose}</p>
          <img src="${locationImgUrl}" alt="${locationName}" width="200" height=auto>
          <a href="${locationInfoUrl}" target="_blank">More Info</a>
          <button v-on:click="addCheckin(1)" id="checkInBtn${locationId}" class="check-in-button">Check-In</button>
        </div>
      `;
      this.map.flyTo({ center: [-81.698738, 41.497257], zoom: 14 });
const popup = new mapboxgl.Popup({ offset: 25 })
          .setHTML(popupContent);

        // Attach the popup to the marker
        marker.setPopup(popup);
        if (this.Type === "stadiums") {
          this.stadiums.push(marker);
        } else if (this.Type === "parks") {
          this.parks.push(marker);
        } else if (this.Type === "bars") {
          this.Bars.push(marker);
        }else if (this.Type === "all"){
          this.all.push(marker)} 
      marker.remove
          
    })
    .catch((error) => {
      console.error("Error fetching location data:", error);
      // Handle the error and set an appropriate message in the popup
    });
    this.searchQuery = '';
},
    clearMarkersAndPopups() {
  // Remove existing markers and popups from the map
  for (const marker of this.stadiums) {
    marker.remove();
  }
  for (const marker of this.parks) {
    marker.remove();
  }
  for (const marker of this.Bars) {
    marker.remove();
  }
  for (const marker of this.all) {
    marker.remove();
  }

  // Clear marker arrays
  this.stadiums = [];
  this.parks = [];
  this.Bars = [];
  this.all = [];
},
//  filterNameSearch(){
//    // Define the API endpoint based on the selected location type
//   let apiEndpoint = "http://localhost:9000/locations/name/";

//   switch (true) {
//   case this.searchQuery === "wendy park":
//     apiEndpoint += "Wendy%20Park";
//     break;
//     case this.searchQuery === "steelers park":
//     apiEndpoint += "Settlers%20Park";
//     break;
//     case this.searchQuery === "collision bend brewing company":
//     apiEndpoint += "Collision%20Bend%20Brewing%20Company";
//     break;
//     case this.searchQuery === "butcher and the brewer":
//     apiEndpoint += "Butcher%20and%20the%20Brewer";
//     break;
//   case  this.searchQuery === "brewDog cleveland outpost":
//     apiEndpoint += "BrewDog%20Cleveland%20Outpost";
//     break;
//     case  this.searchQuery === "barley house":
//     apiEndpoint += "Barley%20House";
//     break;
//   case this.searchQuery === "great lakes brewing":
//     apiEndpoint += "Great%20Lakes%20Brewing%20Company";
//     break;
//     case this.searchQuery === "progressive field":
//     apiEndpoint += "Progressive%20Field";
//     break;
//     case this.searchQuery === "cleveland browns stadium":
//     apiEndpoint += "Cleveland%20Browns%20Stadium";
//     break;
//     case this.searchQuery === "rocket mortgage fieldHouse":
//     apiEndpoint += "Rocket%20Mortgage%20FieldHouse";
//     break;
//   default:
  
//     break;
// }
//   // Make an API request with the searchQuery and selected location type
//   axios
//     .get(apiEndpoint, {
//       params: { query: this.searchQuery },
//     })
//     .then((response) => {
//       const location = response.data;
// console.log(apiEndpoint)
//       // Clear existing markers and popups
//       this.removeMarkersAndPopups();

//       // Add markers for each location
      
//         const { locationId, locationLatitude, locationLongitude, locationName } = location;
//         const marker = new mapboxgl.Marker({ color: "blue" })
//           .setLngLat([locationLongitude, locationLatitude])
//           .addTo(this.map);

//         // Create a popup with custom content
//         const popupContent = `
//           <div>
//             <p>${locationName}</p>
//             <p>${locationLongitude} " " ${locationLatitude}</p>
//             <button id="checkInBtn${locationId}" class="check-in-button">Check-In</button>
//           </div>
//         `;
//         //41.497257, -81.698738
//         this.map.flyTo({ center: [-81.698738, 41.497257], zoom: 14 });
//         const popup = new mapboxgl.Popup({ offset: 25 })
//           .setHTML(popupContent);

//         // Attach the popup to the marker
//         marker.setPopup(popup);

//         // Add the marker to the corresponding category array
//         // add all of the names as or statments
//         if (this.searchQuery === "progressive field" || this.searchQuery ===  "cleveland browns stadium"
//         || this.searchQuery === "rocket mortgage fieldhouse" ) {
//           this.stadiums.push(marker);
//         } else if (this.searchQuery === "wendy park" || this.searchQuery === "steelers park") {
//           this.parks.push(marker);
//         } else if (this.searchQuery === "collision bend brewing company" || this.searchQuery === "butcher and the brewer"
//         || this.searchQuery === "brewDog cleveland outpost" || this.searchQuery === "barley house" || this.searchQuery === "great lakes brewing") {
//           this.Bars.push(marker);
//         }else if (this.searchQuery === "all"){
//           this.all.push(marker)}

//           this.searchQuery = '';
        
//     })
//     .catch((error) => {
//       console.error("Error fetching locations:", error);
//     });
//   },
filterTypeSearch() {
  this.clearMarkersAndPopups();
  // Define the API endpoint based on the selected location type
  let apiEndpoint = "http://localhost:9000/locations/";

  switch (this.Type) {
    case "stadiums":
      apiEndpoint += "Stadiums";
      break;
    case "parks":
      apiEndpoint += "Parks";
      break;
    case "bars":
      apiEndpoint += "Bars";
      break;
    default:
      break;
  }

  // Make an API request with the searchQuery and selected location type
  axios
    .get(apiEndpoint, {
      params: { query: this.Type }, // Corrected to use this.Type
    })
    .then((response) => {
      const locations = response.data; // Assuming this is an array of locations
      // Clear existing markers and popups
        this.removeMarkersAndPopups();
     
      console.log(apiEndpoint);

      locations.forEach((location) => {
        const {
        locationId,
        // locationTypeName,
        locationName,
        locationLatitude,
        locationLongitude,
        locationDescription,
        locationSunOpen,
        locationSunClose,
        locationMonOpen,
        locationMonClose,
        locationTueOpen,
        locationTueClose,
        locationWedOpen,
        locationWedClose,
        locationThuOpen,
        locationThuClose,
        locationFriOpen,
        locationFriClose,
        locationSatOpen,
        locationSatClose,
        locationImgUrl,
        locationInfoUrl,
      } = location;

        // Format the days and opening/closing times
        // const daysOfWeek = locationDays.join(", ");
        // const openingTimes = locationOpeningTimes.join(", ");
        // const closingTimes = locationClosingTimes.join(", ");
        const marker = new mapboxgl.Marker({ color: "blue" })
          .setLngLat([locationLongitude, locationLatitude])
          .addTo(this.map);

        // Create the HTML content for the popup
        const popupContent = `
        <div>
          <h2>${locationName}</h2>
          <p>${locationDescription}</p>
          <p><strong>Sunday:</strong> ${locationSunOpen} - ${locationSunClose}</p>
          <p><strong>Monday:</strong> ${locationMonOpen} - ${locationMonClose}</p>
          <p><strong>Tuesday:</strong> ${locationTueOpen} - ${locationTueClose}</p>
          <p><strong>Wednesday:</strong> ${locationWedOpen} - ${locationWedClose}</p>
          <p><strong>Thursday:</strong> ${locationThuOpen} - ${locationThuClose}</p>
          <p><strong>Friday:</strong> ${locationFriOpen} - ${locationFriClose}</p>
          <p><strong>Saturday:</strong> ${locationSatOpen} - ${locationSatClose}</p>
          <img src="${locationImgUrl}" alt="${locationName}" width="200" height=auto>
          <a href="${locationInfoUrl}" target="_blank">More Info</a>
          <button v-on:click="addCheckin(1)" id="checkInBtn${locationId}" class="check-in-button">Check-In</button>
        </div>
      `;
        this.map.flyTo({ center: [-81.698738, 41.497257], zoom: 14 });
        const popup = new mapboxgl.Popup({ offset: 25 })
          .setHTML(popupContent);

        // Attach the popup to the marker
        marker.setPopup(popup);
        if (this.Type === "stadiums") {
          this.stadiums.push(marker);
        } else if (this.Type === "parks") {
          this.parks.push(marker);
        } else if (this.Type === "bars") {
          this.Bars.push(marker);
        }else if (this.Type === "all"){
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
    addCheckin(amount) {
      this.$store.commit("INCREASE_CHECKINS", amount);
    }

  },
  mounted() {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lng = position.coords.longitude;
      this.userLocation = { lat, lng };
      this.initMap();
      this.map.flyTo({ center: [lng, lat], zoom: 15 });
      this.addMapMarker({ lng, lat });
this.clearMarkersAndPopups() 
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
label{
  display: inline-flex;
}

.name-search {
  display: inline-block;
  grid-area: name;
  padding-right: 5px;
  font-family: "Urbanist", sans-serif;
  background: #407F7F;
  color: #fff;
  
  cursor: pointer;
  text-decoration: none;
  font-size: 15px;
  width: 40%;
}

.type-search {
  display: inline-block;
  grid-area: name;
  padding-right: 5px;
  font-family: "Urbanist", sans-serif;
  background: #407F7F;
  color: #fff;
  padding-top: 20px;
  text-align: center;
  cursor: pointer;
  text-decoration: none;
  font-size: 15px;
  width: 40%;
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