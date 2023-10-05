<template>
    <div class="container">
        <div class="trophies">
            <p>Defender of the Land (for visiting every location)</p>
            <img v-if="trophyStatus.defender" src="../assets/trophies/defender.jpg" id="defender" />
            <img v-else src="../assets/trophies/defenderbw.jpg" id="defender" />
            <p>Bar Hopper (for visiting 5 bars within a small location)</p>
            <img v-if="trophyStatus.bar" src="../assets/trophies/bar.jpg" id="bar" />
            <img v-else src="../assets/trophies/barbw.jpg" id="bar" />
            <p>Sports Enthusiast (for visiting all of the sports stadiums)</p>
            <img v-if="trophyStatus.sport" src="../assets/trophies/sport.jpg" id="sport" />
            <img v-else src="../assets/trophies/sportbw.jpg" id="sport" />
            <p>Tree Hugger (for visiting the CLE parks)</p>
            <img v-if="trophyStatus.tree" src="../assets/trophies/tree.jpg" id="tree" />
            <img v-else src="../assets/trophies/treebw.jpg" id="tree" />
        </div>
    </div>
</template>

<script>
import trophyService from "../services/rewards.js"

export default {
    name: 'rewards-comp',
    data() {
        return {
            trophyStatus: {
                defender: false,
                bar: false,
                sport: false,
                tree: false,
            }
        };
    },
    created() {
         trophyService.checkTrophyStatus()
            .then(() => {
                this.trophyStatus = trophyService.trophyStatus;
            })
            .catch(error => {
                console.error(error);
            });
    }
}
</script>


<style scoped>
.container {
    display: grid;
    grid-template-columns:repeat(auto-fill, minmax(250px, 1fr));
    grid-gap: 10px;
}

img {
    max-width: 150px;
    height: auto;
    width: 100%;
}

.trophies {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 99%
}

@media screen and (max-width: 400px) {
    img {
        width: 110px;
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