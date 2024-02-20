<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <q-card bordered class="tarjeta-factura" style="width: 100%">
    <q-card-section class="bg-azul-menta">
      <div class="text-h6">{{ nombre }}</div>
      <div class="text-subtitle1 text-weight-light text-azul-oscuro">
        {{ subtitulo }}
      </div>
      <q-checkbox
        color="azul-oscuro"
        keep-color
        left-label
        v-model="completada"
        :label="'Completado'"
      />
      <q-btn
        fab
        color="red"
        icon="fas fa-xmark"
        class="absolute"
        @click="$emit('abrirElim')"
        style="top: 5px; right: -20px; transform: translateY(-50%)"
      />
      <q-btn
        fab
        color="green"
        icon="fas fa-plus"
        class="absolute"
        @click="$emit('abrirTarea')"
        style="top: 70px; right: -20px; transform: translateY(-50%)"
      />
    </q-card-section>

    <q-separator color="azul-oscuro" />
    <q-card-section>
      <div class="text-subtitle1 text-azul-oscuro">{{ descripcion }}</div>
    </q-card-section>
    <q-card-section class="no-padding">
      <q-list bordered>
       <div
         v-for="(a, s) in props.nombreTarea"
         :key="s"
       >
         <ItemTarea :titulo-tarea="'('+ (s+1) +') ' + a" :es-imp="importante" :estado="props.estado"></ItemTarea>
       </div>
      </q-list>
    </q-card-section>
  </q-card>
</template>
<script setup>
import ItemTarea from "./ItemTarea.vue";
import { ref } from "vue";

const completada = ref(false);

const props = defineProps({
  nombre: String,
  descripcion: String,
  estado: Boolean,
  subtitulo: String,
  nombreTarea:String,
  numeroTareas:Number,

});


const emits = defineEmits(["abrirElim"]["abrirTarea"]);

</script>
