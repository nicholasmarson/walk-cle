<template>
  <div id = "container">
    <div class="form-container">
    <label for="locationType">Location Type:</label>
    <select v-model="locationType" id="locationType">
      <option value="Stadiums">Stadiums</option>
      <option value="Parks">Parks</option>
      <option value="Bars">Bars</option>
      <option value="Other">Other</option>
    </select>
    <br />
    <label for="locationName">Location Name:</label>
    <input v-model="locationName" id="locationName" />
    <br />
    <label for="latitude">Latitude:</label>
    <input v-model="latitude" id="latitude" />
    <br />
    <label for="longitude">Longitude:</label>
    <input v-model="longitude" id="longitude" />
    <br />
      <button class="btn" @click="handleSubmit">Submit</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "AddPointView",
  data() {
    return {
      locationType: "",
      locationName: "",
      latitude: "",
      longitude: ""
    };
  },
  methods: {
    handleSubmit() {
      const locationData = {
        locationType: parseInt(this.locationType),
        locationName: this.locationName,
        latitude: this.latitude,
        longitude: this.longitude
      };
      axios.post('http://localhost:9000/locations/createlocation', locationData)
    }
  }
}
</script>

<style scoped>

.btn {
    font-family: "Urbanist", sans-serif;
    display: inline-block;
    background: #407F7F;
    color: #fff;
    border: none;
    padding: 10px 20px;
    margin: 5px;
    border-radius: 5px;
    cursor: pointer;
    text-decoration: none;
    font-size: 15px;
    width: auto;
}

.form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}


label {
  margin-bottom: 0.5rem;
}

input,
select {
  margin-bottom: 1rem;
}

.footer-container {
  background-color: #92b9c5;
  text-align: center;
  width: 100%;
}

#container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.form-container {
  flex-grow: 1;
  max-height: 80vh;
  overflow-y: auto;
}

/* Adjustments for input sizes */
input[type="time"],
textarea {
  width: 10%; /* Make the input elements fill their container */
  padding: 8px; /* Add some padding for better appearance */
  font-size: 14px; /* Adjust font size for readability */
}
</style>
