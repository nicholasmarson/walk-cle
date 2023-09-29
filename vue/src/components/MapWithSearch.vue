<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import mapboxgl from "mapbox-gl";
import { MapboxSearchBox } from "@mapbox/search-js-web";
import * as turf from "@turf/turf";
import MapboxDirections from "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions";
import "@mapbox/mapbox-gl-directions/dist/mapbox-gl-directions.css";

export default {
  data() {
    return {
      // make environment variable
      ACCESS_TOKEN:
        "pk.eyJ1Ijoid2Fsa2NsZTIxNiIsImEiOiJjbG16MGVvdWkxM2QzMm9wNjNobm9hZGQyIn0.5r382ZeMc0zOhHpiAd9D2A",
    };
  },
  mounted() {
    mapboxgl.accessToken = this.ACCESS_TOKEN;

    const stadium = [-81.700058, 41.506035];

    // Initialize the map at the user's location
    navigator.geolocation.getCurrentPosition((position) => {
      const { latitude, longitude } = position.coords;

      this.map = new mapboxgl.Map({
        container: "map",
        style: "mapbox://styles/mapbox/streets-v12",
        center: [longitude, latitude], // Center the map at the user's location
        zoom: 15, // Set an initial zoom level
      });

      const directions = new MapboxDirections({
        accessToken: mapboxgl.accessToken,
        unit: "imperial",
        profile: "mapbox/walking",
        steps: 1,
      });
      this.map.addControl(directions, "bottom-left");
      directions.setOrigin([longitude, latitude]);

      const popup = new mapboxgl.Popup({ offset: 25 }).setText(
        "Factory of sadness"
      );

      // Create a marker and add it to the map
      new mapboxgl.Marker().setLngLat(stadium).setPopup(popup).addTo(this.map);

      const point = turf.point([longitude, latitude]);
      const options = { units: "miles" };
      const radius = 5; // 1 mile
      const bbox = turf.bbox(turf.buffer(point, radius, options));

      const searchBox = new MapboxSearchBox();
      searchBox.accessToken = this.ACCESS_TOKEN;
      searchBox.options = {
        language: "en",
        country: "us",
        bbox: bbox, // Set the bounding box in the search options
      };
      this.map.addControl(searchBox, "top-right");

      const geolocate = new mapboxgl.GeolocateControl({
        positionOptions: {
          enableHighAccuracy: true,
        },
        trackUserLocation: true,
        showUserLocation: true,
      });
      this.map.addControl(geolocate);

      const nav = new mapboxgl.NavigationControl();
      this.map.addControl(nav);
    });
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

#map canvas {
  width: 80vw;
  height: 80vh;
}

.mapboxgl-canvas {
  width: 100%;
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

/* Customize the individual step */
.mapbox-directions-step {
  margin-bottom: 5px;
}

/* Customize the step number */
.mapbox-directions-step-number {
  font-weight: bold;
  color: #0078d4; /* Custom color for step numbers */
}

/* Customize the step instruction text */
.mapbox-directions-step-text {
  color: #000000;
}
</style>