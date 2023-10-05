<template>
  <div>
    <button id="btn" @click="addSports">Add Markers</button>
    <div id="map"></div>
  </div>
</template>

<script>
import mapboxgl from "mapbox-gl";
import MapboxDirections from "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions";
import "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions.css";

mapboxgl.accessToken = process.env.VUE_APP_MAPBOX_KEY;
export default {
  data() {
    return {
      map: null,
      stadiums: [],
      popupTexts: [
        "Stadium 1",
        "Stadium 2",
        "Stadium 3",
        // Add more popup texts here
      ],
      currentPopupIndex: null,
      popups: [],
    };
  },
  methods: {
    initMap() {
      // Create map object
      this.map = new mapboxgl.Map({
        container: "map",
        style: "mapbox://styles/walkcle216/cln4qqvn306wt01qi05mi9h14",
        center: [-81.681290, 41.505493],
        zoom: 15,
      });
    },
     getDirections() {
      // Set up Mapbox Directions control
      const directions = new MapboxDirections({
        accessToken: mapboxgl.accessToken,
        unit: "imperial",
        profile: "mapbox/walking",
      });
      directions.setOrigin();
      this.map.addControl(directions, "bottom-left");
    },
    navigation() {
      // Adds basic zoom and rotation control
      this.map.addControl(new mapboxgl.NavigationControl());
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

    addSports() {
      const coordinates = [
        [-81.6852949, 41.4958921],
        [-81.6995481, 41.5060535],
        [-81.6880574, 41.4965474],
        // Add more coordinates here
      ];

      console.log("Coordinates:", coordinates);

      // Remove existing markers and popups
      this.removeMarkersAndPopups();
      this.map.flyTo({ center: [-81.6852949, 41.490645], zoom: 15 });

      // Add markers and popups for each coordinate
      coordinates.forEach((coord, index) => {
        const marker = new mapboxgl.Marker({ color: "blue" })
          .setLngLat(coord)
          .addTo(this.map);

        // Create a popup with custom content
        const popupContent = `
          <div>
            <p>${this.popupTexts[index]}</p>
            <img src="https://example.com/your-image-url.jpg" alt="Image" width="200"/>
          </div>
        `;

        const popup = new mapboxgl.Popup({ offset: 25 })
          .setHTML(popupContent);

        // Attach the popup to the marker
        marker.setPopup(popup);
        this.popups.push(popup);

        // Attach a click event handler to the marker
        marker.getElement().addEventListener("click", () => {
          // Close the currently open popup if there is one
          if (this.currentPopupIndex !== null) {
            this.popups[this.currentPopupIndex].remove();
          }
          // Open the clicked popup
          popup.addTo(this.map);
          this.currentPopupIndex = index;
        });

        this.stadiums.push(marker);
      });
    },
    removeMarkersAndPopups() {
      this.stadiums.forEach((marker) => {
        marker.remove();
      });
      this.stadiums = [];
      this.popups.forEach((popup) => {
        popup.remove();
      });
      this.popups = [];
      this.currentPopupIndex = null;
    },
   
    
  },
  mounted() {
    this.initMap();
    this.getDirections();
    this.navigation();
    this.requestLocation();
  },
};
</script>

<style scoped>
#map {
  height: 80vh;
  width: 100vw;
}
.btn {
  float: inline-end;
}
</style>
