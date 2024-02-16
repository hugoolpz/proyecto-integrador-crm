<template>
  <q-item clickable v-ripple @click="completado = !completado">
    <q-item-section>
      <q-item-label class="text-rojo-tomate" overline>Importante*</q-item-label>
      <q-item-label>{{ tituloTarea }}</q-item-label>
    </q-item-section>
    <q-item-section avatar>
      <q-checkbox left-label :v-model="props.estado" color="azul-oscuro" />
    </q-item-section>
  </q-item>
</template>
<script setup>
import { ref } from "vue";

const props = defineProps({
  tituloTarea: String,
  subtitulo: String,
  estado: Boolean,
  esImp: String,
});
</script>
