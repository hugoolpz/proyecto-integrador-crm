<template>
  <div>
    <div
      class="q-pl-xl q-pb-xl q-pt-lg"
      style="min-width: 250px; max-width: 395px"
    ></div>
    <div class="column items-center">
      <div class="col">
        <div class="row">
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
              @abrir-elim="abrirElim(n._id)"
            ></Proyecto>
            <q-dialog v-model="confirmarElimProy" persistent>
              <q-card>
                <q-card-section class="row items-center">
                  <q-avatar icon="warning" color="warning" text-color="white" />
                  <span
                    class="q-ml-sm"
                    v-html="`¿Estas seguro de que quieres borrarlo?`"
                  ></span>
                </q-card-section>

                <q-card-actions align="right">
                  <q-btn
                    flat
                    label="Sí, eliminar"
                    color="positive"
                    v-close-popup
                    @click="eliminarElegido(id)"
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
          </div>
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
// const tareasExistentes = ref([]);
const tuInfo = ref(JSON.parse(window.localStorage.getItem("usuario")));
const idEmisor = ref(tuInfo.value.datos._id);
const urlApi = api;

onMounted(() => {
  obtenerPro();
});

async function obtenerPro() {
  await fetch(`${urlApi}/proyectos`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((datos) => {
      datos.datos.forEach((proyecto) => {
        console.log(proyecto);

        proyectosExistentes.value.push(proyecto);
      });
    });
}
</script>
