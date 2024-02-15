<template>
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
                    <q-input v-model="concepto" type="text" label="Concepto" color="azul-menta" />
                    <q-input v-model="descripcion" type="text" label="Descripción" color="azul-menta" />
                    <q-input v-model="fechaFactura" type="date" label="Fecha" color="azul-menta" />
                    <q-input v-model="baseImp" type="number" label="Base imponible" color="azul-menta" />
                    <q-select v-model="datosRemitente" :options="remitentes" option-label="nombreCliente"
                        option-value="idCliente" color="azul-menta" placeholder="Remitente" />
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
</template>

<script setup>
import { ref, onMounted } from "vue";
import Factura from "src/components/Factura.vue";
import api from 'src/boot/httpSingleton';
import { useQuasar } from 'quasar'

const tuInfo = ref(JSON.parse(window.localStorage.getItem("usuario")))
const concepto = ref("")
const descripcion = ref("")
const datosEmisor = ref(tuInfo.value.datos._id);
const fechaFactura = ref("");
const baseImp = ref("");
const datosRemitente = ref("");
const remitentes = ref([])

const options = [{ label: "Solo emitidas" }, { label: "Solo recibidas" }];
const filtroMultiple = ref({ label: "Solo emitidas" })
const facturasEmitidas = ref([])
const facturasRecibidas = ref([])
const idElim = ref(0)
const confirmarElimFact = ref(false);
const $q = useQuasar()
const urlApi = api
const emitirFact = defineModel()

onMounted(() => {
    obtenerFacts()
    reunirClientes()
})

function reunirClientes() {
    remitentes.value = tuInfo.value.datos.clientes.map((val) => {
        return {
            nombreCliente: val.nombre + " " + val.apellidos,
            idCliente: val._id
        }
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
</script>