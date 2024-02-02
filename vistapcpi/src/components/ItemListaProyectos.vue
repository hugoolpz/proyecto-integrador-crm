<template>
  <q-card-section
    @click="model = !model"
    v-ripple.center
    class="cursor-pointer"
  >
    <div class="row">
      <div class="text-subtitle1">{{ tituloItem }}</div>
      <q-space />
      <q-btn
        color="azul-menta"
        round
        flat
        dense
        :icon="model ? 'fas fa-angle-up' : 'fas fa-angle-down'"
        @click="model = !model"
      />
    </div>

    <q-slide-transition>
      <div v-show="model" class="text-subtitle2 text-azul-oscuro">
        {{ contenidoItem }}
      </div>
    </q-slide-transition>
  </q-card-section>
</template>

<script setup>
import { defineModel } from "vue";
const model = defineModel({ type: Boolean });
const props = defineProps({
  tituloItem: String,
  contenidoItem: String,
});
</script>
