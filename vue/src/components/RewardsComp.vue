<template>
    <div class="container">
        <div class="trophies">
            <img src="../assets/trophies/defender.jpg" id="defender" />
            <img src="../assets/trophies/bar.jpg" id="bar" />
            <img src="../assets/trophies/sport.jpg" id="sport" />
            <img src="../assets/trophies/tree.jpg" id="tree" />
        </div>
    </div>
</template>

<script>

export default {
name: 'rewards-comp',
}
</script>


<style scoped>
.container {
    display: grid;
    grid-template-columns:repeat(auto-fill, minmax(250px, 1fr));
    grid-gap: 10px;
}

img {
    max-width: 250px;
    height: auto;
    width: 100%;
}

@media screen and (max-width: 400px) {
    .container {
        grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
        grid-gap: 5px;
    }
    img {
        max-width: 40%;
    }
}

#defender {
    grid-area: defender;
}

#bar {
    grid-area: bar;
}

#sport {
    grid-area: sport;
}

#tree {
    grid-area: tree;
}
</style>