<template>
  <q-page>
    <div>
      <div class="q-pl-xl q-pb-xl q-pt-lg" style="min-width: 250px; max-width: 395px">
        <q-select color="azul-menta" outlined popup-content-class="bg-white" v-model="filtroMultiple" :options="options"
          stack-label label="Filtrar por:">
          <template v-slot:selected-item="scope">
            <q-chip removable @remove="scope.removeAtIndex(scope.index)" :tabindex="scope.tabindex" color="white"
              text-color="white" class="q-ml-none bg-azul-oscuro">
              {{ scope.opt.label }}
            </q-chip>
          </template>
        </q-select>
      </div>
      <div class="column items-center">
        <div class="col">
          <div class="row">
            <div class="col-4 q-px-xl q-pb-xl" v-for="n in 10" :key="n">
              <q-card bordered class="tarjeta-factura">
                <q-card-section class="bg-azul-menta">
                  <div class="text-h5">Concepto</div>
                  <q-checkbox color="azul-oscuro" keep-color left-label v-model="completada"
                    :label="filtroMultiple.label == 'Solo emitidas' ? '¿Está cobrada?' : '¿Está pagada?'" />
                  <q-btn fab color="rojo-tomate" icon="fas fa-xmark" class="absolute"
                    style="top: 5px; right: -20px; transform: translateY(-50%);" />
                </q-card-section>

                <q-separator color="azul-oscuro" />

                <q-card-section>
                  <div class="text-subtitle1 text-azul-oscuro">Descripción</div>
                </q-card-section>

                <q-separator color="azul-menta" />

                <ItemListaFactura v-model="datosEmisor" titulo-item="Datos del emisor de la factura"
                  contenido-item="Nombre, apellidos, NIF, domicilio...">
                </ItemListaFactura>

                <ItemListaFactura v-model="nFactura" titulo-item="Número de la factura"
                  contenido-item="Número de la factura...">
                </ItemListaFactura>

                <ItemListaFactura v-model="fechaFactura" titulo-item="Fecha de la factura"
                  contenido-item="Fecha de la factura...">
                </ItemListaFactura>

                <ItemListaFactura v-model="baseImp" titulo-item="Base imponible de la factura"
                  contenido-item="Base imponible (€)">
                </ItemListaFactura>

                <ItemListaFactura v-model="retencionIRPF" titulo-item="Retención de IRPF de la factura"
                  contenido-item="Cantidad...">
                </ItemListaFactura>

                <ItemListaFactura v-model="ivaFactura" titulo-item="IVA de la factura"
                  contenido-item="Porcentaje de IVA (%)">
                </ItemListaFactura>

                <ItemListaFactura v-model="datosRemitente" titulo-item="Datos del remitente de la factura"
                  contenido-item="Nombre, apellidos, NIF, domicilio...">
                </ItemListaFactura>

                <ItemListaFactura v-model="metodo" titulo-item="Método de pago de la factura" contenido-item="Método...">
                </ItemListaFactura>

              </q-card>
            </div>
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script setup>
import { ref } from 'vue';
import ItemListaFactura from 'src/components/ItemListaFactura.vue';
const datosEmisor = ref(false)
const nFactura = ref(false)
const fechaFactura = ref(false)
const baseImp = ref(false)
const retencionIRPF = ref(false)
const ivaFactura = ref(false)
const datosRemitente = ref(false)
const metodo = ref(false)
const completada = ref(false)

const options = [
  { label: 'Solo emitidas' }, { label: 'Solo recibidas' }
]

const filtroMultiple = ref({ label: 'Solo emitidas' })
</script>
