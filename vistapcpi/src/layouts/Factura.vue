<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <q-layout view="hHh lpR fFf">
    <Headers></Headers>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>
<script setup>
import Headers from "src/components/Headers.vue";
</script>
