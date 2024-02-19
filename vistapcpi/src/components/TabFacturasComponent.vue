<template>
  <div>
    <div class="row q-pl-xl q-pb-xl q-pt-lg q-gutter-x-xl">
      <div class="col">
        <q-select
          v-model="filtroMultiple"
          :options="options"
          color="azul-menta"
          label="Filtrar por:"
          outlined
          popup-content-class="bg-white"
          stack-label
          style="width: 315px"
        >
          <template v-slot:selected-item="scope">
            <q-chip
              :tabindex="scope.tabindex"
              class="q-ml-none bg-azul-oscuro"
              color="white"
              removable
              text-color="white"
              @remove="scope.removeAtIndex(scope.index)"
            >
              {{ scope.opt.label }}
            </q-chip>
          </template>
        </q-select>
      </div>
    </div>
    <div class="column">
      <div class="col">
        <div v-if="filtroMultiple.label == 'Solo emitidas'" class="row">
          <div
            v-for="factE in facturasEmitidas"
            :key="factE._id"
            class="col-4 q-px-xl q-pb-xl"
          >
            <Factura
              :base="factE.baseImp + '€'"
              :concepto="factE.concepto"
              :descripcion="factE.descripcion"
              :emisor="
                factE.datosEmisor.nombre + ' ' + factE.datosEmisor.apellidos
              "
              :fecha="factE.fecha"
              :filtro-multiple="filtroMultiple"
              :n-fact="factE.numero"
              :remitente="
                factE.datosReceptor.nombre + ' ' + factE.datosReceptor.apellidos
              "
              iva="21%"
              retencion="15%"
              @abrir-elim="abrirElim(factE._id)"
            >
            </Factura>
          </div>
        </div>
        <div v-else class="row">
          <div
            v-for="factR in facturasRecibidas"
            :key="factR._id"
            class="col-4 q-px-xl q-pb-xl"
          >
            <Factura
              :base="factR.baseImp + '€'"
              :concepto="factR.concepto"
              :descripcion="factR.descripcion"
              :emisor="
                factR.datosEmisor.nombre + ' ' + factR.datosEmisor.apellidos
              "
              :fecha="factR.fecha"
              :filtro-multiple="filtroMultiple"
              :n-fact="factR.numero"
              :remitente="
                factR.datosReceptor.nombre + ' ' + factR.datosReceptor.apellidos
              "
              iva="21%"
              retencion="15%"
              @abrir-elim="abrirElim(factR._id)"
            >
            </Factura>
          </div>
        </div>
      </div>
    </div>
  </div>
  <q-dialog v-model="emitirFact" persistent>
    <q-card class="q-pa-md" style="width: 600px">
      <q-card-section>
        <q-form class="q-gutter-lg" @reset="onReset" @submit="emitirFactura()">
          <q-input
            v-model="concepto"
            color="azul-menta"
            label="Concepto"
            type="text"
          />
          <q-input
            v-model="descripcion"
            color="azul-menta"
            label="Descripción"
            type="text"
          />
          <q-input
            v-model="fechaFactura"
            color="azul-menta"
            label="Fecha"
            type="date"
          />
          <q-input
            v-model="baseImp"
            color="azul-menta"
            label="Base imponible"
            type="number"
          />
          <q-select
            v-model="datosRemitente"
            :options="remitentes"
            color="azul-menta"
            option-label="nombreCliente"
            option-value="idCliente"
            placeholder="Remitente"
          />
          <div align="right">
            <q-btn color="azul-menta" label="Emitir" type="submit"/>
            <q-btn
              class="q-ml-sm"
              color="azul-menta"
              flat
              label="Cancelar"
              @click="emitirFact = false"
            />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="confirmarElimFact" persistent>
    <q-card>
      <q-card-section class="row items-center">
        <q-avatar color="warning" icon="warning" text-color="white"/>
        <span
          class="q-ml-sm"
          v-html="`¿Estas seguro de que quieres borrar la factura ${idElim}?`"
        ></span>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn
          v-close-popup
          color="positive"
          flat
          label="Sí, eliminar"
          @click="eliminarElegido(idElim)"
        />
        <q-btn v-close-popup color="negative" flat label="No, conservar"/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {onMounted, ref} from "vue";
import Factura from "src/components/Factura.vue";
import api from "src/boot/httpSingleton";
import {useQuasar} from "quasar";

const tuInfo = ref(JSON.parse(window.localStorage.getItem("usuario")));
const concepto = ref("");
const descripcion = ref("");
const datosEmisor = ref(tuInfo.value.datos._id);
const fechaFactura = ref("");
const baseImp = ref("");
const datosRemitente = ref("");
const remitentes = ref([]);

const options = [{label: "Solo emitidas"}, {label: "Solo recibidas"}];
const filtroMultiple = ref({label: "Solo emitidas"});
const facturasEmitidas = ref([]);
const facturasRecibidas = ref([]);
const idElim = ref(0);
const confirmarElimFact = ref(false);
const $q = useQuasar();
const urlApi = api;
const emitirFact = defineModel();

onMounted(() => {
  obtenerFacts();
  reunirClientes();
});

async function reunirClientes() {
  await fetch(`${urlApi}/usuarios/${tuInfo.value.datos._id}`)
    .then(respuesta => respuesta.json())
    .then(datos => {
      remitentes.value = datos.datos.clientes.map((val) => {
        return {
          nombreCliente: val.nombre + " " + val.apellidos,
          idCliente: val._id,
        };
      });
    })
}

async function emitirFactura() {
  console.log(datosRemitente.value);
  if (baseImp.value < 0) {
    $q.notify({
      progress: true,
      message: "Importe negativo",
      color: "negative",
      timeout: 1000,
    });
  } else {
    await fetch(`${urlApi}/facturas`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        concepto: concepto.value,
        descripcion: descripcion.value,
        datosEmisor: datosEmisor.value,
        fecha: fechaFactura.value,
        baseImp: baseImp.value,
        datosReceptor: datosRemitente.value.idCliente,
      }),
    })
      .then((res) => res.json())
      .then((datos) => {
        console.log(datos);
        if (!datos.exito) {
          $q.notify({
            progress: true,
            message: "Emisión incorrecta",
            color: "negative",
            timeout: 1000,
          });
        } else {
          $q.notify({
            progress: true,
            message: "Emisión correcta",
            color: "positive",
            timeout: 1000,
          });

          emitirFact.value = false;
          obtenerFacts();
        }
      });
  }
}

async function obtenerFacts() {
  await fetch(`${urlApi}/facturas/tuyas/${tuInfo.value.datos._id}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      facturasEmitidas.value = [];
      facturasRecibidas.value = [];
      datos.datos.forEach((factura) => {
        if (factura.datosEmisor._id === tuInfo.value.datos._id) {
          // Si la factura es emitida por ti, la agregamos a facturasEmitidas
          facturasEmitidas.value.push(factura);
        } else {
          // Si no, la agregamos a facturasRecibidas
          facturasRecibidas.value.push(factura);
        }
      });
    });
}

function abrirElim(idElegido) {
  confirmarElimFact.value = true;
  idElim.value = idElegido;
}

async function eliminarElegido(idEliminar) {
  await fetch(`${urlApi}/facturas/${idEliminar}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      if (!datos.exito) {
        $q.notify({
          progress: true,
          message: "Eliminación incorrecta",
          color: "negative",
          timeout: 1000,
        });
      } else {
        $q.notify({
          progress: true,
          message: "Eliminación correcta de factura " + idEliminar,
          color: "positive",
          timeout: 1000,
        });

        obtenerFacts();
      }
    });
}
</script>
