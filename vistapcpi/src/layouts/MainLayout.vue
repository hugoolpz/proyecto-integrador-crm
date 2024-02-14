<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-azul-oscuro text-white">
      <q-toolbar class="text-white shadow-2">
        <q-btn flat icon="fas fa-list" class="q-mr-sm" @click="menuIzq = !menuIzq" />
        <q-separator dark vertical inset />
        <q-btn stretch flat label="Inicio" @click="router.push({ path: '/' })" />
        <q-separator dark vertical inset class="q-mr-md" />
        <q-btn flat icon="fas fa-plus" @click="emitirFact = true" label="Emitir factura" class="bg-azul-menta" />

        <q-space />


        <q-input dark dense standout v-model="busqueda" class="q-mr-md" style="width: 300px;">
          <template v-slot:append>
            <q-icon v-if="busqueda === ''" name="fas fa-search" />
            <q-icon v-else name="fas fa-xmark" class="cursor-pointer" @click="busqueda = ''" />
          </template>
        </q-input>
        <q-tabs v-model="tab" class="text-white" active-class="text-rojo-tomate">
          <q-tab name="facturas" icon="fas fa-receipt" label="Facturas" />
          <q-tab name="proyectos" icon="fas fa-list-check" label="Proyectos" />
        </q-tabs>
      </q-toolbar>
    </q-header>
    <q-drawer v-model="menuIzq" behavior="mobile" :width="250" :breakpoint="400">
      <q-img class="" src="https://cdn.quasar.dev/img/material.png" style="height: 80px">
        <div class="bg-transparent">
          <div class="text-weight-bold">{{ tuInfo.datos.nombre + ' ' + tuInfo.datos.apellidos }}</div>
        </div>
      </q-img>

      <q-scroll-area style="height: calc(100% - 150px); border-right: 1px solid #ddd">
        <q-list padding>
          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="fas fa-user-edit" />
            </q-item-section>

            <q-item-section> Cambiar datos de perfil </q-item-section>
          </q-item>

          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="fas fa-right-from-bracket" />
            </q-item-section>

            <q-item-section> Cerrar sesión </q-item-section>
          </q-item>

          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="fas fa-trash" />
            </q-item-section>

            <q-item-section> Eliminar cuenta </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <q-page>
        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="facturas">
            <div>
              <div class="row q-pl-xl q-pb-xl q-pt-lg q-gutter-x-xl">
                <div class="col">
                  <q-select color="azul-menta" outlined popup-content-class="bg-white" v-model="filtroMultiple"
                    :options="options" stack-label label="Filtrar por:" style="width: 315px;">
                    <template v-slot:selected-item="scope">
                      <q-chip removable @remove="scope.removeAtIndex(scope.index)" :tabindex="scope.tabindex"
                        color="white" text-color="white" class="q-ml-none bg-azul-oscuro">
                        {{ scope.opt.label }}
                      </q-chip>
                    </template>
                  </q-select>
                </div>
              </div>
              <div class="column">
                <div class="col">
                  <div class="row" v-if="filtroMultiple.label == 'Solo emitidas'">
                    <div class="col-4 q-px-xl q-pb-xl" v-for="factE in facturasEmitidas" :key="factE._id">
                      <Factura :concepto="factE.concepto" :descripcion="factE.descripcion"
                        :filtro-multiple="filtroMultiple"
                        :emisor="factE.datosEmisor.nombre + ' ' + factE.datosEmisor.apellidos" :n-fact="factE.numero"
                        :fecha="factE.fecha" :base="factE.baseImp + '€'"
                        :remitente="factE.datosReceptor.nombre + ' ' + factE.datosReceptor.apellidos" iva="21%"
                        retencion="15%" @abrir-elim="abrirElim(factE._id)">
                      </Factura>
                    </div>
                  </div>
                  <div class="row" v-else>
                    <div class="col-4 q-px-xl q-pb-xl" v-for="factR in facturasRecibidas" :key="factR._id">
                      <Factura :concepto="factR.concepto" :descripcion="factR.descripcion"
                        :filtro-multiple="filtroMultiple"
                        :emisor="factR.datosEmisor.nombre + ' ' + factR.datosEmisor.apellidos" :n-fact="factR.numero"
                        :fecha="factR.fecha" :base="factR.baseImp + '€'"
                        :remitente="factR.datosReceptor.nombre + ' ' + factR.datosReceptor.apellidos" iva="21%"
                        retencion="15%" @abrir-elim="abrirElim(factR._id)">
                      </Factura>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <q-dialog v-model="emitirFact" persistent>
              <q-card style="width: 600px;" class="q-pa-md">
                <q-card-section>
                  <q-form @submit="emitirFactura()" @reset="onReset" class="q-gutter-lg">
                    <q-input v-model="concepto" type="text" label="Concepto" />
                    <q-input v-model="descripcion" type="text" label="Descripción" />
                    <q-input v-model="datosEmisor" type="hidden" label="Emisor (Id de momento)" />
                    <q-input v-model="fechaFactura" type="date" label="Fecha" />
                    <q-input v-model="baseImp" type="number" label="Base imponible" />
                    <q-input v-model="datosRemitente" type="text" label="Remitente (Id de momento)" />
                    <div align="right">
                      <q-btn label="Emitir" type="submit" color="azul-menta" />
                      <q-btn label="Cancelar" @click="emitirFact = false" color="azul-menta" flat class="q-ml-sm" />
                    </div>
                  </q-form>
                </q-card-section>
              </q-card>
            </q-dialog>

            <q-dialog v-model="confirmarElimFact" persistent>
              <q-card>
                <q-card-section class="row items-center">
                  <q-avatar icon="warning" color="warning" text-color="white" />
                  <span class="q-ml-sm" v-html="`¿Estas seguro de que quieres borrar la factura ${idElim}?`"></span>
                </q-card-section>

                <q-card-actions align="right">
                  <q-btn flat label="Sí, eliminar" color="positive" v-close-popup @click="eliminarElegido(idElim)" />
                  <q-btn flat label="No, conservar" color="negative" v-close-popup />
                </q-card-actions>
              </q-card>
            </q-dialog>
          </q-tab-panel>
          <q-tab-panel name="proyectos">
            <div>
              <div class="q-pl-xl q-pb-xl q-pt-lg" style="min-width: 250px; max-width: 395px"></div>
              <div class="column items-center">
                <div class="col">
                  <div class="row">
                    <div class="col-4 q-px-xl q-pb-xl" v-for="n in 10" :key="n">
                      <q-card bordered class="tarjeta-factura" style="width: 100%">
                        <q-card-section class="bg-azul-menta">
                          <div class="text-h6">Proyecto N</div>
                          <div class="text-subtitle1 text-weight-light text-azul-oscuro">
                            Subtítulo proyecto
                          </div>
                          <q-checkbox color="azul-oscuro" keep-color left-label v-model="completada"
                            :label="'Completado'" />
                          <q-btn fab color="red" icon="fas fa-xmark" class="absolute" @click="confirmar = true"
                            style="top: 5px; right: -20px; transform: translateY(-50%)" />
                        </q-card-section>

                        <q-separator color="azul-oscuro" />
                        <q-card-section>
                          <div class="text-subtitle1 text-azul-oscuro">
                            Descripción proyecto...
                          </div>
                        </q-card-section>
                        <q-card-section class="no-padding">
                          <q-list bordered>
                            <ItemTarea titulo-tarea="Tarea 1"></ItemTarea>
                            <ItemTarea titulo-tarea="Tarea 2"></ItemTarea>
                            <ItemTarea titulo-tarea="Tarea 3"></ItemTarea>
                            <ItemTarea titulo-tarea="Tarea 4"></ItemTarea>
                          </q-list>
                        </q-card-section>
                      </q-card>

                      <q-dialog v-model="confirmarElimProy" persistent>
                        <q-card>
                          <q-card-section class="row items-center">
                            <q-avatar icon="warning" color="warning" text-color="white" />
                            <span class="q-ml-sm" v-html="`¿Estas seguro de que quieres borrarlo?`"></span>
                          </q-card-section>

                          <q-card-actions align="right">
                            <q-btn flat label="Sí, eliminar" color="positive" v-close-popup
                              @click="eliminarElegido(id)" />
                            <q-btn flat label="No, conservar" color="negative" v-close-popup />
                          </q-card-actions>
                        </q-card>
                      </q-dialog>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </q-tab-panel>
        </q-tab-panels>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref, onMounted } from "vue";
import ItemTarea from "src/components/ItemTarea.vue";
import Factura from "src/components/Factura.vue";
import api from 'src/boot/httpSingleton';
import { useQuasar } from 'quasar'
import { useRouter } from "vue-router";

const $q = useQuasar()
const urlApi = api
const menuIzq = ref(false);
const completada = ref(false);
const tab = ref('facturas')
const confirmarElimFact = ref(false);
const confirmarElimProy = ref(false);
const options = [{ label: "Solo emitidas" }, { label: "Solo recibidas" }];
const filtroMultiple = ref({ label: "Solo emitidas" })
const busqueda = ref("")
const emitirFact = ref(false)
const facturasEmitidas = ref([])
const facturasRecibidas = ref([])
const idElim = ref(0)
const router = useRouter()

const tuInfo = ref(JSON.parse(window.localStorage.getItem("usuario")))
const concepto = ref("")
const descripcion = ref("")
const datosEmisor = ref(tuInfo.value.datos._id);
const fechaFactura = ref("");
const baseImp = ref("");
const datosRemitente = ref("");

onMounted(() => {
  obtenerFacts()
})

function abrirElim(idElegido) {
  confirmarElimFact.value = true
  idElim.value = idElegido
}

async function eliminarElegido(idEliminar) {
  await fetch(`${urlApi}/facturas/${idEliminar}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    },
  })
    .then(res => res.json())
    .then(datos => {
      if (!datos.exito) {
        $q.notify({
          progress: true,
          message: 'Eliminación incorrecta',
          color: 'negative',
          timeout: 1000
        })
      } else {
        $q.notify({
          progress: true,
          message: 'Eliminación correcta de factura ' + idEliminar,
          color: 'positive',
          timeout: 1000
        })

        obtenerFacts()
      }
    })
}

async function obtenerFacts() {
  await fetch(`${urlApi}/facturas/tuyas/${tuInfo.value.datos._id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    },
  })
    .then(res => res.json())
    .then(datos => {
      facturasEmitidas.value = []
      facturasRecibidas.value = []
      datos.datos.forEach(factura => {
        if (factura.datosEmisor._id === tuInfo.value.datos._id) {
          // Si la factura es emitida por ti, la agregamos a facturasEmitidas
          facturasEmitidas.value.push(factura);
        } else {
          // Si no, la agregamos a facturasRecibidas
          facturasRecibidas.value.push(factura);
        }
      });
    })
}

async function emitirFactura() {
  if (baseImp.value < 0) {
    $q.notify({
      progress: true,
      message: 'Importe negativo',
      color: 'negative',
      timeout: 1000
    })
  } else {
    await fetch(`${urlApi}/facturas`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        "concepto": concepto.value,
        "descripcion": descripcion.value,
        "datosEmisor": datosEmisor.value,
        "fecha": fechaFactura.value,
        "baseImp": baseImp.value,
        "datosReceptor": datosRemitente.value,
      })
    })
      .then(res => res.json())
      .then(datos => {
        console.log(datos)
        if (!datos.exito) {
          $q.notify({
            progress: true,
            message: 'Emisión incorrecta',
            color: 'negative',
            timeout: 1000
          })
        } else {
          $q.notify({
            progress: true,
            message: 'Emisión correcta',
            color: 'positive',
            timeout: 1000
          })

          emitirFact.value = false
          obtenerFacts()
        }
      })
  }
}
</script>
