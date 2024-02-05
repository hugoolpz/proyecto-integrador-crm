<template>
  <q-item clickable v-ripple @click="completado = !completado">
    <q-item-section>
      <q-item-label class="text-rojo-tomate" overline>Importante*</q-item-label>
      <q-item-label>{{ tituloTarea }}</q-item-label>
    </q-item-section>
    <q-item-section avatar>
      <q-checkbox left-label v-model="completado" color="azul-oscuro" />
    </q-item-section>
  </q-item>
</template>
<script setup>
import { ref } from "vue";

const completado = ref(false);

const props = defineProps({
  tituloTarea: String,
});
</script>
