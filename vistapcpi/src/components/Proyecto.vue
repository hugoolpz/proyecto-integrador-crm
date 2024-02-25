<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <q-card bordered class="tarjeta-factura" style="width: 100%">
    <q-card-section class="bg-azul-menta">
      <div class="text-h6">{{ nombre }}</div>
      <div class="text-subtitle1 text-weight-light text-azul-oscuro">
        {{ subtitulo }}
      </div>
      <q-checkbox
        @click="$emit('cambiarEstado')"
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
         v-for="(tarea, indice) in props.tareas"
         :key="tarea"
       >
         <ItemTarea :id="tarea._id" :id-t="props._id" :titulo-tarea="'('+ (indice+1) +') ' + tarea.nombre" :esImp="tarea.importante" :estado="tarea.completada"    @cambiar-estado="cambiarEstado(props._id,tarea._id)"></ItemTarea>
       </div>
      </q-list>
    </q-card-section>
  </q-card>
</template>
<script setup>
import ItemTarea from "./ItemTarea.vue";
import {onMounted, ref} from "vue";
import api from "boot/httpSingleton";
import {useQuasar} from "quasar";

const completada = ref(false);
const props = defineProps(["_id","nombre", "descripcion", "estado", "subtitulo", "tareas",])
const emits = defineEmits(["abrirElim","abrirTarea","cambiarEstado"]);
const urlApi = api;
const $q = useQuasar();

onMounted(() => {
  obtenerEstado(props._id)
})
async function cambiarEstado(idProyecto,idElegida) {
  let datosObt = await obtenerUnaTarea(idProyecto)
  let compTarea

  for(let x = 0 ;x<datosObt.tareas.length;x++){

    if(datosObt.tareas[x]._id === idElegida){

      console.log("dsadasd = " + datosObt.tareas[x]._id)
      compTarea = x

    }

  }
 console.log(datosObt.tareas[compTarea].estado)
  if (datosObt.tareas[compTarea].estado === false){
    await fetch(`${urlApi}/proyectos/${idProyecto}/tareas/${idElegida}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        estado: true
      }),
    })
      .then((res) => res.json())
      .then((datos) => {
        if (!datos.exito) {
          $q.notify({
            progress: true,
            message: "Error",
            color: "negative",
            timeout: 1000,
          });
        } else {
          $q.notify({
            progress: true,
            message: "Tarea clasificada como completa",
            color: "positive",
            timeout: 1000,
          });
        }
      });
  } else {
    await fetch(`${urlApi}/proyectos/${idProyecto}/tareas/${idElegida}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        estado: false
      }),
    })
      .then((res) => res.json())
      .then((datos) => {
        if (!datos.exito) {
          $q.notify({
            progress: true,
            message: "Error",
            color: "negative",
            timeout: 1000,
          });
        } else {
          $q.notify({
            progress: true,
            message: "Tarea clasificada como incompletada",
            color: "positive",
            timeout: 1000,
          });
        }
      });
  }
}
async function obtenerUnaTarea(idElegida){
  let datosObtenidos = null
  await fetch(`${urlApi}/proyectos/${idElegida}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      datosObtenidos = datos.datos
    });
  return datosObtenidos
}
async function obtenerEstado(idElegida){
  await fetch(`${urlApi}/proyectos/${idElegida}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      console.log("esto = " + datos)
      completada.value = datos.datos.estado
    });
}
</script>
