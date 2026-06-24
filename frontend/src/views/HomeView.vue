<script setup>
  import BottomNav from "@/components/BottomNav.vue";
</script>

<template>
  <main>
    <!-- header, cards, button CTA -->
    <header>
      <div>
        <h2>Salut Karine !</h2>
        <h3>Où s'envole-t-on aujourd'hui ?</h3>
      </div>
      <img src="@/assets/mascotte.png" alt="La mascotte nommée Hira parle">
    </header>
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
  }
  header > div {
    text-align: center;
    background-color: var(--color-soie-orientale);
    border-radius: 50% 50% 57% 43% / 66% 69% 31% 34% ;
    padding: 20px;
    position: relative;
  }
  header > div::after {
    content: '';
    position: absolute;
    bottom: -10px;
    left: 40%;
    border-left: 10px solid transparent;
    border-right: 20px solid transparent;
    border-top: 20px solid var(--color-soie-orientale);
  }
  header > img {
    width: 125px;
    transform: scaleX(-1);
    margin-left: -150px;
  }
</style>
