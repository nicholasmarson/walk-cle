<template>
    <div class="container">
        <div class="trophies">
            <img v-if="trophyStatus.defender" src="../assets/trophies/defender.jpg" id="defender" />
            <img v-else src="../assets/trophies/defenderbw.jpg" id="defender" />
            <img v-if="trophyStatus.bar" src="../assets/trophies/bar.jpg" id="bar" />
            <img v-else src="../assets/trophies/barbw.jpg" id="bar" />
            <img v-if="trophyStatus.sport" src="../assets/trophies/sport.jpg" id="sport" />
            <img v-else src="../assets/trophies/sportbw.jpg" id="sport" />
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