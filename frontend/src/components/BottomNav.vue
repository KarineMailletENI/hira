<script setup>
  import { Compass, Luggage, ListTodo, UserRound } from '@lucide/vue'
  import {ref} from 'vue'

  const activeTab = ref('explorer')
</script>

<template>
    <nav>
      <div :class="{active: activeTab === 'explorer'}" @click="activeTab = 'explorer'">
        <Compass />
        <p>Explorer</p>
      </div>
      <div :class="{active: activeTab === 'voyages'}" @click="activeTab = 'voyages'">
        <Luggage />
        <p>Mes voyages</p>
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
  }
  div {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  .active {
    color: var(--color-mer-mediterranee);
  }
</style>
