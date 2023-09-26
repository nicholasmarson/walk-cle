<template>
  <div>
      <input v-model="searchTerm" @input="handleInput" placeholder="Discover CLE" />
      <button @click="search">Search</button>
      </div>
</template>

<script>
export default {
    data() {
        return {
            searchTerm: ''
        };
    },
    methods: {
        handleInput() {

        },
        search() {
            this.$emit('search', this.searchTerm)
        }
    }
};
</script>

<style>

</style>