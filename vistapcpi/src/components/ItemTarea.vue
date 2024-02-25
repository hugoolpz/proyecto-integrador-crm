<template>
  <q-item clickable v-ripple @click="completado = !completado">
    <q-item-section>
      <q-item-label class="text-rojo-tomate" overline>
        <span v-if="esImp">Importante*</span>
        <span v-else>Estandar</span>
      </q-item-label>
      <q-item-label>{{ tituloTarea }}</q-item-label>
    </q-item-section>
    <q-item-section avatar>
      <q-checkbox left-label v-model="estad0" color="azul-oscuro"  @click="$emit('cambiarEstado')"/>
    </q-item-section>
  </q-item>
</template>
<script setup>
import {onMounted, ref} from "vue";
import api from "boot/httpSingleton";
import {useQuasar} from "quasar";

const estad0 = ref(false)
const emits = defineEmits(["cambiarEstado"]);
const urlApi = api;

const props = defineProps({
  id:Number,
  idT:Number,
  tituloTarea: String,
  esImp: Boolean,
  estado:Boolean
});

onMounted(() => {
  obtenerEstado(props.idT,props.id)
})
async function obtenerEstado(idElegida,idTarea){
  let datosObt = await obtenerUna(idElegida)
  let compTarea

  for(let x = 0 ;x<datosObt.tareas.length;x++){

    if(datosObt.tareas[x]._id === idTarea){

      console.log("dsadasd = " + datosObt.tareas[x]._id)
      compTarea = x

    }
  }

  estad0.value = datosObt.tareas[compTarea].estado

}
async function obtenerUna(idElegida){
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
</script>
