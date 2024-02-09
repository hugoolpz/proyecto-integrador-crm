<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <q-card bordered class="tarjeta-factura">
    <q-card-section class="bg-azul-menta" style="min-height: 150px;">
      <div class="text-h5">{{ concepto }}</div>
      <q-checkbox color="azul-oscuro" keep-color left-label v-model="completada" :label="filtroMultiple.label == 'Solo emitidas'
        ? '¿Está cobrada?'
        : '¿Está pagada?'
        " />
      <q-btn fab color="rojo-tomate" icon="fas fa-xmark" class="absolute"
        style="top: 5px; right: -20px; transform: translateY(-50%)" @click="$emit('abrirElim')" />
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
import { ref } from "vue";
import ItemListaFactura from "src/components/ItemListaFactura.vue";
const datosEmisor = ref(false);
const nFactura = ref(false);
const fechaFactura = ref(false);
const baseImp = ref(false);
const retencionIRPF = ref(false);
const ivaFactura = ref(false);
const datosRemitente = ref(false);
const completada = ref(false);

const props = defineProps(['concepto',
  'descripcion', 'filtroMultiple', 'emisor', 'nFact', 'fecha', 'base', 'retencion', 'iva', 'remitente'])

const emits = defineEmits(['abrirElim'])
</script>
