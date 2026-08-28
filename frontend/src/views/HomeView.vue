<script setup>
  import BottomNav from "@/components/BottomNav.vue";
  import { MoveRight, CirclePlus, ChevronRight } from '@lucide/vue';
</script>

<template>
  <main>
    <header>
        <h2>Salut Karine !</h2>
        <h3>Où s'envole-t-on aujourd'hui ?</h3>
    </header>
    <div class="cards">
      <div class="departure-block">
        <img src="@/assets/mascotte-happy.png" alt="La mascotte nommée Hira parle">
        <div class="card-accent card">
          <div class="card-accent-contain">
            <h4 class="card-title">Prochain départ</h4>
            <strong>J-5</strong>
            <h5>Cap sur Lisbonne </h5>
          </div>
          <span class="button-accent">
            Voir plus <MoveRight />
          </span>
        </div>
      </div>
      <div class="card-sample card">
        <h4 class="card-title">Voyage à compléter</h4>
        <div class="card-contain">
          <svg width="36" height="36" viewBox="0 0 52 52" aria-hidden="true">
            <circle cx="26" cy="26" r="21" fill="none" stroke="#FFFDFC" stroke-width="6"></circle>
            <circle cx="26" cy="26" r="21" fill="none" stroke="#05272B" stroke-width="6" stroke-linecap="round" stroke-dasharray="105.6 131.9" transform="rotate(-90 26 26)"></circle>
          </svg>
          <div>
            <h5>Voyage au Japon</h5>
            <p>12 éléments bouclés sur 15</p>
          </div>
        </div>
        <ChevronRight />
      </div>
      <div class="card-CTA card">
        <CirclePlus />
        <h4>Organise ton nouveau voyage</h4>
      </div>
    </div>
  </main>
  <BottomNav />
</template>

<style scoped>
  main{
    padding-block: 50px;
  }
  header{
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 60px;
  }
  .departure-block {
    display: flex;
    flex-direction: row;
    position: relative;
  }
  .departure-block > img {
    width: 180px;
    transform: scaleX(-1);
    position: absolute;
    bottom: 0;
    left: 0;
    z-index: 2;
  }
  .cards > .departure-block > .card-accent{
    margin-left: 150px;
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .cards > .departure-block > .card-accent > .card-accent-contain > strong{
    font-size: 35px;
  }
  .cards > .departure-block > .card-accent > .card-accent-contain {
    margin-top: 10px;
  }
  .lucide{
    vertical-align: middle;
  }
  .lucide-move-right{
    font-size: smaller;
  }
  .card-sample{
    position: relative;
  }
  .card-sample > .card-title{
    padding-bottom: 10px;
  }
  .card-sample > .card-contain{
    display: flex;
    flex-direction: row;
    align-items: stretch;
    justify-content: left;
  }
  .card-sample > .card-contain > div {
    padding-inline-start: 10px;
  }
  .card-sample > .card-contain > div >  p{
    font-size: 12px;
  }
  .card-sample > svg {
    position : absolute;
    top: 50%;
    transform : translateY(-50%);
    right: 5px;
  }
  .cards > .card-CTA {
    display: inline-flex;
    align-items: center;
    justify-content: flex-start;
  }
  .cards > .card-CTA > svg{
    margin-inline: 7px;
  }
</style>
