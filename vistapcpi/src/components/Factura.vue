<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <q-card bordered class="tarjeta-factura">
    <q-card-section class="bg-azul-menta" style="min-height: 150px;">
      <div class="text-h5">{{ concepto }}</div>
      <q-checkbox @click="$emit('cambiarEstado')" color="azul-oscuro" keep-color left-label v-model="completada" :disable="filtroMultiple.label === 'Solo emitidas'
      ? false
      : true
      " :label="filtroMultiple.label === 'Solo emitidas'
        ? '¿Está cobrada?'
        : '¿Está pagada?'
        " />
      <q-btn fab color="rojo-tomate" icon="fas fa-xmark" class="absolute"
        style="top: 5px; right: -20px; transform: translateY(-50%)" @click="$emit('abrirElim')" v-show="(filtroMultiple.label === 'Solo emitidas')" />
    </q-card-section>

    <q-separator color="azul-oscuro" />

    <q-card-section>
      <div class="text-subtitle1 text-azul-oscuro">{{ descripcion }}</div>
    </q-card-section>

    <q-separator color="azul-menta" />

    <ItemListaFactura v-model="datosEmisor" titulo-item="Datos del emisor de la factura" :contenido-item="emisor">
    </ItemListaFactura>

    <ItemListaFactura v-model="nFactura" titulo-item="Número de la factura" :contenido-item="nFact">
    </ItemListaFactura>

    <ItemListaFactura v-model="fechaFactura" titulo-item="Fecha de la factura" :contenido-item="fecha">
    </ItemListaFactura>

    <ItemListaFactura v-model="baseImp" titulo-item="Base imponible de la factura" :contenido-item="base">
    </ItemListaFactura>

    <ItemListaFactura v-model="retencionIRPF" titulo-item="Retención de IRPF de la factura" :contenido-item="retencion">
    </ItemListaFactura>

    <ItemListaFactura v-model="ivaFactura" titulo-item="IVA de la factura" :contenido-item="iva">
    </ItemListaFactura>

    <ItemListaFactura v-model="datosRemitente" titulo-item="Datos del remitente de la factura"
      :contenido-item="remitente">
    </ItemListaFactura>
  </q-card>
</template>

<script setup>
import {onMounted, ref} from "vue";
import api from "src/boot/httpSingleton";
import ItemListaFactura from "src/components/ItemListaFactura.vue";
const datosEmisor = ref(false);
const nFactura = ref(false);
const fechaFactura = ref(false);
const baseImp = ref(false);
const retencionIRPF = ref(false);
const ivaFactura = ref(false);
const datosRemitente = ref(false);
const completada = ref(false);
const urlApi = api

const props = defineProps(['id','concepto',
  'descripcion', 'filtroMultiple', 'emisor', 'nFact', 'fecha', 'base', 'retencion', 'iva', 'remitente'])

const emits = defineEmits(['abrirElim', 'cambiarEstado'])

onMounted(() => {
  obtenerUna(props.id)
})
async function obtenerUna(idElegida){
  await fetch(`${urlApi}/facturas/${idElegida}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      completada.value = datos.datos.completada
    });
}
</script>
