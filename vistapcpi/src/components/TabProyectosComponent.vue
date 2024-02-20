<template>
  <div>
    <div class="column items-center q-pt-xl" >
      <div class="row" style="width: 80%">
          <div
            class="col-4 q-px-xl q-pb-xl"
            v-for="n in proyectosExistentes"
            :key="n._id"
          >
            <Proyecto
              :nombre="n.nombre"
              :descripcion="n.descripcion"
              :estado="n.estado"
              :subtitulo="n.subtitulo"
              :nombreTarea="tareasNombres"
              :numero-tareas="tareasLength"
              :importante="importante"
              @abrir-elim="abrirElim(n._id)"
            ></Proyecto>
            <q-dialog v-model="confirmarElimProy" persistent>
              <q-card>
                <q-card-section class="row items-center">
                  <q-avatar icon="warning" color="warning" text-color="white" />
                  <span
                    class="q-ml-sm"
                    v-html="`¿Estas seguro de que quieres borrarlo el proyecto ${idElim}?`"
                  ></span>
                </q-card-section>

                <q-card-actions align="right">
                  <q-btn
                    flat
                    label="Sí, eliminar"
                    color="positive"
                    v-close-popup
                    @click="eliminarElegido(idElim)"
                  />
                  <q-btn
                    flat
                    label="No, conservar"
                    color="negative"
                    v-close-popup
                  />
                </q-card-actions>
              </q-card>
            </q-dialog>
            <q-dialog v-model="emitirProy" persistent>
              <q-card class="q-pa-md" style="width: 600px">
                <q-card-section>
                  <q-form class="q-gutter-lg" @reset="onReset" @submit="emitirProyecto()">
                    <q-input
                      v-model="nombre"
                      color="azul-menta"
                      label="Nombre"
                      type="text"
                    />
                    <q-input
                      v-model="subtitulo"
                      color="azul-menta"
                      label="Subtitulo"
                      type="text"
                    />
                    <q-input
                      v-model="descripcion"
                      color="azul-menta"
                      label="Descripcion"
                      type="text"
                    />
                    <div align="right">
                      <q-btn color="azul-menta" label="Emitir" type="submit"/>
                      <q-btn
                        class="q-ml-sm"
                        color="azul-menta"
                        flat
                        label="Cancelar"
                        @click="emitirProy = false"
                      />
                    </div>
                  </q-form>
                </q-card-section>
              </q-card>
            </q-dialog>
          </div>
        </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from "vue";
import Proyecto from "./Proyecto.vue";
import api from "src/boot/httpSingleton";
import { useQuasar } from "quasar";

const proyectosExistentes = ref([]);
const tareasExistentes = ref([]);
const urlApi = api;
const tareasLength = ref()
const tareasNombres = ref([]);
const importante =  ref()
const confirmarElimProy = ref(false)
const $q = useQuasar();
const emitirProy = defineModel();
const idElim = ref(0);
const nombre = ref("");
const subtitulo = ref("");
const descripcion = ref("");

onMounted(() => {
  obtenerPro();
});

function abrirElim(idElegido) {
  confirmarElimProy.value = true;
  idElim.value = idElegido;

}
function predeterminado(){

  proyectosExistentes.value = []
  tareasExistentes.value = []
  tareasNombres.value = []
  tareasLength.value = 0
  importante.value = false
  nombre.value = ""
  subtitulo.value = ""
  descripcion.value = ""

}

async function obtenerPro() {
  predeterminado()
  emitirProy.value = false;
  await fetch(`${urlApi}/tuyos/proyectos`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      console.log(datos)


      let data = datos.datos[0]
      tareasLength.value = data.tareas.length
      importante.value = data.importante


      //Tareas existentes
     for(let x=0; x<tareasLength.value;x++){

       tareasExistentes.value.push(data.tareas[x]);
       tareasNombres.value.push (data.tareas[x])

     }

     //Datos del proyecto
      datos.datos.forEach((proyecto) => {

        proyectosExistentes.value.push(proyecto);

      });
    });
}
async function eliminarElegido(idEliminar) {
  predeterminado()
  await fetch(`${urlApi}/proyectos/${idEliminar}`, {
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

        emitirProy.value = false;
        confirmarElimProy.value = false;
        obtenerPro();
      }
    });
}

async function emitirProyecto() {
    await fetch(`${urlApi}/proyectos`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre: nombre.value,
        subtitulo: subtitulo.value,
        descripcion: descripcion.value,
        estado: true,
        tareas:[],
        integrantes: [],
        datosReceptor: true,
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

          emitirProy.value = false;
          predeterminado()
          obtenerPro();

        }
      });
}
</script>
