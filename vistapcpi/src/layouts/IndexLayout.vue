<template>
  <q-layout view="hHh lpR fFf">
    <HeaderIndexComponent></HeaderIndexComponent>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import HeaderIndexComponent from "src/components/HeaderIndexComponent.vue";
</script>
