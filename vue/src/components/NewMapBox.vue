<template>
  <div>
    
    <!-- <input type="text" name="location" :value="location.coordinates" disabled /> -->
    <div id="map"></div>
    <!-- <button class="btn" @click="requestLocation">Get Current Location</button>
    <button class="btn" @click="removeMapMarkers">Remove Markers</button> -->
  </div>
</template>
  
<script>
import mapboxgl from "mapbox-gl";
import { MapboxSearchBox } from "@mapbox/search-js-web";
import * as turf from "@turf/turf";
import MapboxDirections from "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions";
import "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions.css";

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
        style: "mapbox://styles/mapbox/streets-v12",
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
      const marker = new mapboxgl.Marker({ color: "blue" })
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
      navigator.geolocation.getCurrentPosition((position) => {
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;

        this.setLocation({ lng, lat });
        this.map.flyTo({ center: [lng, lat], zoom: 15 });

        this.userLocation.lat = lat;
        this.userLocation.lng = lng;
        
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
    search() {
      // Set up Mapbox Search Box
      const point = turf.point([this.userLocation.lng, this.userLocation.lat]);
      const options = { units: "miles" };
      const radius = 5; // 1 mile
      const bbox = turf.bbox(turf.buffer(point, radius, options));

      const searchBox = new MapboxSearchBox();
      searchBox.accessToken = mapboxgl.accessToken;
      searchBox.options = {
        language: "en",
        country: "us",
        bbox: bbox,
      };

      this.map.addControl(searchBox);
    },
    navigation() {
      // Adds basic zoom and rotation control
      this.map.addControl(new mapboxgl.NavigationControl());
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
    });
  },
};
</script>
  
<style scoped>
#map {
  width: 100vw;
  height: 100%;
}

.mapbox-directions-instructions {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  font-family: "Urbanist", sans-serif;
  font-size: 14px;
  color: #333;
}

.mapbox-directions-route-summary {
  font-family: "Urbanist", sans-serif;
}

.mapbox-directions-step {
  margin-bottom: 5px;
}

.mapbox-directions-step-number {
  font-weight: bold;
  color: #0078d4;
}

.mapbox-directions-step-text {
  color: #000000;
}
</style>