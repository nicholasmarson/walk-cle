<template>
  <div class="container">
    <div class="form-search">
      <form @submit.prevent="popupContent" class="name-search">
        <label for="location" id="location-label">Location:</label>
        <input type="text" id="location-input" v-model="searchQuery" />
        <button type="submit" id="location-button">Search</button>
      </form>
      <form @submit.prevent="filterTypeSearch" class="type-search">
        <label for="locationType" id="locationType-label">Category:</label>
        <select id="locationType-select" v-model="Type">
          <option value="all">All</option>
          <option value="stadiums">Stadiums</option>
          <option value="parks">Parks</option>
          <option value="bars">Bars</option>
        </select>
        <button type="submit" id="locationType-button">Search</button>
      </form>
    </div>
    <div id="map"></div>    
  </div>
</template>
  
<script>
import mapboxgl from "mapbox-gl";
import MapboxDirections from "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions";
import "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions.css";
import service from '../services/locationService.js'
import axios from 'axios';

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
      searchBox: null,
      userLocation: {
        lat: 0,
        lng: 0,
      },
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
    getDirections() {
  // Set up Mapbox Directions control
  const directions = new MapboxDirections({
    accessToken: mapboxgl.accessToken,
    unit: "imperial",
    profile: "mapbox/walking",
    steps: 2,
  });

  directions.setOrigin([this.userLocation.lng, this.userLocation.lat]);
  this.map.addControl(directions, "bottom-left");

  // Create a button to toggle directions
  const toggleDirectionsButton = document.createElement("button");
  toggleDirectionsButton.innerText = " Directions";
  toggleDirectionsButton.style.background = "#407F7F"
  toggleDirectionsButton.style.fontFamily = "Urbanist"
  toggleDirectionsButton.style.color = "#fff"
  toggleDirectionsButton.style.borderRadius = "5px";
  toggleDirectionsButton.style.padding = "10px 20px"
toggleDirectionsButton.style.bottom = "132px"
 toggleDirectionsButton.style.position = "fixed";
  toggleDirectionsButton.style.right = "20px"
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
    navigation() {
            // Adds basic zoom and rotation control
            this.map.addControl(new mapboxgl.NavigationControl());
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
        </div>
      `;
      this.map.flyTo({ center: [-81.698738, 41.497257], zoom: 13 });
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
        </div>
      `;
        this.map.flyTo({ center: [-81.698738, 41.497257], zoom: 13 });
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
  this.parks = [];
  this.Bars = [];
  this.poi = [];
  this.markers = [];
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
      this.clearMarkersAndPopups(); 

      this.getDirections();
      this.navigation();
      this.geoLocate();
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

.form-search {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 30%;
  grid-template-areas:
    "name-search type-search";
  justify-content: space-evenly;
  align-items: center;
}

@media screen and (max-width: 550px) {
    .form-search {
      display: grid;
      grid-template-columns: 1fr;
      gap: 0;
      grid-template-areas:
        "name-search"
        "type-search";
      justify-content: space-between;
      align-items: center;
    }
}

.name-search {
  grid-area: name-search;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  /* gap: 20px; */
  grid-template-areas:
    "location-label location-input location-button";
  justify-content: space-evenly;
  align-items: center;
  text-align: center;
}

.type-search {
  grid-area: type-search;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  /* gap: 20px; */
  grid-template-areas:
    "locationType-label locationType-select locationType-button";
  justify-content: space-evenly;
  align-items: center;
  text-align: center;
}

.name-search, .type-search {
  padding: 10px;
}

#location-label {
  display: flex;
  grid-area: location-label;
}
#location-input {
  display: flex;
  grid-area: location-input;
  width: 150px;
}
#location-button {
  display: flex;
  grid-area: location-button;
}
#locationType-label {
  display: flex;
  grid-area: locationType-label;
}
#locationType-select {
  display: flex;
  grid-area: locationType-select;
  width: 150px;
}
#locationType-button {
  display: flex;
  grid-area: locationType-button;
}

button[type="submit"] {
  background-color: #407F7F;
  color: white;
  padding: 5px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  margin-left: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

button[type="submit"]:hover {
  background-color: white;
  color: black;
}

</style>