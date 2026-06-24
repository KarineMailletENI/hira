<script setup>
  import { Compass, Luggage, ListTodo, UserRound } from '@lucide/vue'
  import {ref} from 'vue'

  const activeTab = ref('')
</script>

<template>
    <nav>
      <div :class="{active: activeTab === 'explorer'}" @click="activeTab = 'explorer'">
        <Compass />
        <p>Explorer</p>
      </div>
      <div :class="{active: activeTab === 'voyages'}" @click="activeTab = 'voyages'">
        <Luggage />
        <p>Voyages</p>
      </div>
      <div :class="{active: activeTab === 'listes'}" @click="activeTab = 'listes'">
        <ListTodo />
        <p>Listes</p>
      </div>
      <div :class="{active: activeTab === 'profil'}" @click="activeTab = 'profil'">
        <UserRound />
        <p>Profil</p>
      </div>
    </nav>
</template>

<style scoped>
  nav {
    display: flex;
    flex-direction: row;
    gap: 1rem;
    align-items: center;
    justify-content: space-evenly;
    border-top: 1px solid var(--color-soie-orientale);
    position: fixed;
    bottom: 0;
    width: 100%;
    padding-block: 1rem;
    background-color: var(--color-brillance);
  }
  div {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    cursor: pointer;
  }
  .active {
    color: var(--color-mer-mediterranee);
  }
  .active::after {
    position: absolute;
    content: '';
    width: 50px;
    height: 2px;
    background-color: var(--color-aube-scintillante);
    top: 0;
  }
</style>
