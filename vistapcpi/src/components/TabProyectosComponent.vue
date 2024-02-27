<template>
  <div>
    <div class="column items-center q-pt-xl" >
      <div class="row">
          <div
            class="col-4 q-px-xl q-pb-xl"
            v-for="(proy) in proyectosExistentes"
            :key="proy"
          >
            <div>
              <Proyecto
                :_id="proy._id"
                :nombre="proy.nombre"
                :descripcion="proy.descripcion"
                :estado="proy.estado"
                :subtitulo="proy.subtitulo"
                :tareas="proy.tareas"
                @abrir-elim="abrirElim(proy._id)"
                @abrir-tarea="abrirTarea(proy._id)"
                @cambiar-estado="cambiarEstado(proy._id)"
              ></Proyecto>
            </div>
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
            <q-dialog v-model="abrirAgregarTarea" persistent>
              <q-card class="q-pa-md" style="width: 600px">
                <q-card-section>
                  <q-form class="q-gutter-lg q-pa-md" @reset="onReset" @submit="emitirTarea(idAnadir)">
                    <span class="text-azul-oscuro text-h6">Añadir nueva tarea</span>
                    <q-input
                      v-model="nombre"
                      color="azul-menta"
                      label="Nombre"
                      type="text"
                    />
                    <q-select  v-model="model" label="Importancia" :options="options" />
                    <div align="right">
                      <q-btn color="azul-menta" label="Emitir" type="submit"/>
                      <q-btn
                        class="q-ml-sm"
                        color="azul-menta"
                        flat
                        label="Cancelar"
                        @click="abrirAgregarTarea = false"
                      />
                    </div>
                  </q-form>
                </q-card-section>
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
const urlApi = api;
const tareasLength = ref()
const importante =  ref()
const confirmarElimProy = ref(false)
const abrirAgregarTarea = ref(false)
const $q = useQuasar();
const emitirProy = defineModel();
const idElim = ref(0);
const idAnadir = ref(0);
const nombre = ref("");
const subtitulo = ref("");
const descripcion = ref("");
const importantes = ref(false);
const tareaExistente = ref(false);
const model= ref("")
const  options = [
  'Estandar', 'Importante'
]


onMounted(() => {
  obtenerPro();
});

function abrirElim(idElegido) {
  confirmarElimProy.value = true;
  idElim.value = idElegido;

}
function abrirTarea(idElegido){
  abrirAgregarTarea.value = true;
  idAnadir.value = idElegido
}
function predeterminado(){

  proyectosExistentes.value = []
  tareasLength.value = 0
  importante.value = false
  nombre.value = ""
  subtitulo.value = ""
  descripcion.value = ""
  tareaExistente.value = false
  importantes.value = false

}
async function emitirTarea(idElegido){

  if ( model.value === "Importante" ){

    importantes.value = true

  }

  await fetch(`${urlApi}/proyectos/agregarTarea/${idElegido}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },

    body: JSON.stringify({
      nombre: nombre.value,
      importante: importantes.value,
      estado: false,
    }),
  })
    .then((res) => res.json())
    .then((datos) => {
      console.log(datos)

      obtenerPro();
      abrirAgregarTarea.value = false
    });
}
async function obtenerPro() {
  predeterminado()
  emitirProy.value = false;
  await fetch(`${urlApi}/proyectos`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      console.log(datos)

      let data

      for (let x = 0 ; x<3;x++){

        data = datos.datos[x]
        console.log(datos.datos[x])

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
          obtenerPro();

        }
      });
}
async function cambiarEstado(idElegida) {
  let datosObt = await obtenerUna(idElegida)
  if (datosObt.estado !== false){
    await fetch(`${urlApi}/proyectos/${idElegida}`, {
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
            message: "Proyecto clasificado como incompleto",
            color: "positive",
            timeout: 1000,
          });
        }
      });
  } else {
    await fetch(`${urlApi}/proyectos/${idElegida}`, {
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
            message: "Proyecto clasificado como completado",
            color: "positive",
            timeout: 1000,
          });
        }
      });
  }
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
