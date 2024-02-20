<template>
  <div class="column q-gutter-y-md">
    <q-knob
      v-model="ingresos"
      size="350px"
      :thickness=".4"
      color="azul-menta"
      track-color="rojo-tomate"
      class="q-ma-md text-azul-oscuro"
      :max="maximo"
      readonly
    />
    <div class="row flex-center">
      <div><q-chip color="azul-menta" class="text-subtitle1 text-white">Ingresos: {{ingresos}}€</q-chip></div>
      <div><q-chip color="rojo-tomate" class="text-subtitle1 text-white">Gastos: {{gastos}}€</q-chip></div>
    </div>
    <div class="row flex-center text-h6">Beneficios: <q-chip color="azul-oscuro" class="text-white text-h6">{{beneficios}}€</q-chip></div>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import api from "src/boot/httpSingleton";
import {useQuasar} from "quasar";
const $q = useQuasar();
const urlApi = api;
const tuInfo = ref(JSON.parse(window.localStorage.getItem("usuario")));
const beneficios = ref(0)
const ingresos = ref(0)
const gastos = ref(0)
const maximo = ref(0)

onMounted(() => {
  obtenerFacts()
})
async function obtenerFacts() {
  await fetch(`${urlApi}/facturas/tuyas/${tuInfo.value.datos._id}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      datos.datos.forEach((factura) => {
        if (factura.datosEmisor._id === tuInfo.value.datos._id) {
          let nuevoIngreso = (factura.baseImp * 0.21) + factura.baseImp - (factura.baseImp * 0.15)
          ingresos.value += nuevoIngreso
          maximo.value += nuevoIngreso
        } else {
          let nuevoGasto = (factura.baseImp * 0.21) + factura.baseImp - (factura.baseImp * 0.15)
          gastos.value += nuevoGasto
          maximo.value += nuevoGasto
        }
      });
    });

  ingresos.value = ingresos.value.toFixed(3)
  gastos.value = gastos.value.toFixed(3)
  beneficios.value = ingresos.value - gastos.value
  beneficios.value = beneficios.value.toFixed(3)
  console.log("Máximo: " + maximo.value)
  console.log("Beneficios: " + beneficios.value)
}
</script>
