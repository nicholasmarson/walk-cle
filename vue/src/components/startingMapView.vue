<template>
  <div class="map-container">
    <div id="map"></div>
  </div>
</template>

<script>
import mapboxgl from 'mapbox-gl';

export default {
  data() {
    return {
      map: null,
    };
  },
  mounted() {
    mapboxgl.accessToken = 'pk.eyJ1Ijoid2Fsa2NsZTIxNiIsImEiOiJjbG16MGVvdWkxM2QzMm9wNjNobm9hZGQyIn0.5r382ZeMc0zOhHpiAd9D2A';

    this.map = new mapboxgl.Map({
      container: 'map',
      style: 'mapbox://styles/mapbox/streets-v11',
      center: [-81.6934, 41.4993], // Cleveland, Ohio coordinates
      zoom: 12,
    });

    // Add map controls, markers, and other customizations here

    // Enable map dragging/panning
    this.map.dragPan.enable();

     // Add custom tile source and layer
  this.map.addSource('custom-tiles', {
    type: 'vector', // or 'vector' if you're using vector tiles
    tiles: ['URL_TO_YOUR_http://a.tiles.mapbox.com/v4/mapbox.mapbox-streets-v8/14/4823/6160.mvt?access_token=pk.eyJ1Ijoid2Fsa2NsZTIxNiIsImEiOiJjbG16MGVvdWkxM2QzMm9wNjNobm9hZGQyIn0.5r382ZeMc0zOhHpiAd9D2A'], // Replace with the URL to your custom tiles
    tileSize: 256, // The tile size of your custom tiles
  });

  this.map.addLayer({
    id: 'custom-tiles-layer',
    type: 'vector',
    source: 'custom-tiles',
    minzoom: 0,
    maxzoom: 22, // Adjust the maxzoom according to your custom tiles
  });
  },
};
</script>

<style>
.map-container {
  width: 100%;
  height: 400px;
  overflow: auto; /* Add a scrollbar when the map exceeds the container's height */
}

#map {
  width: 100%;
  height: 100%;
}
</style>