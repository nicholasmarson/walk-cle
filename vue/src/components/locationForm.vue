<template>
  <div id = "container">
    <div class="form-container">
    <label for="locationType">Location Type:</label>
    <select v-model="locationType" id="locationType">
      <option value="stadiums">Stadiums</option>
      <option value="parks">Parks</option>
      <option value="bars">Bars</option>
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
export default {
  name: "AddPointView",
  data() {
    return {
      locationType: '',
      locationName: '',
      latitude: '',
      longitude: ''
    };
  },
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
</style>
