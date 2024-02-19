<template>
    <q-table title="Tus clientes" :rows="filas" :columns="columnas" row-key="_id" separator="cell" :loading="cargando"
        :no-data-label="error" />
  <q-dialog v-model="registrarCliente">
    <q-card>
      <q-card-section>
        <div class="text-h6 text-center" style="width: 450px">¿Quieres añadir un cliente a tu lista? ¡Introduce su código de cliente aquí!</div>
        <q-space class="q-pa-sm"></q-space>
        Tu código de cliente:
        <q-chip
          class="q-ml-none bg-azul-oscuro cursor-pointer"
          color="white"
          text-color="white"
          clickable
          @click="copiarId"
        >
          {{tuInfo.datos._id}}
          <q-tooltip class="bg-azul-menta" anchor="center right" self="center left" :offset="[10, 10]">Copiar al portapapeles</q-tooltip>
        </q-chip>
        <q-form class="q-gutter-lg" @reset="onReset" @submit="intentarRegistro()">
          <q-input v-model="codigo" label="Código de cliente" color="azul-oscuro"></q-input>
          <div align="right">
            <q-btn color="azul-menta" label="Registrar" type="submit"/>
            <q-btn
              class="q-ml-sm"
              color="azul-menta"
              flat
              label="Cancelar"
              @click="registrarCliente = false"
            />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'
import api from 'src/boot/httpSingleton';
const $q = useQuasar();
const urlApi = api;
const registrarCliente = defineModel();
const codigo = ref("")

const columnas = [
    { name: '_id', label: 'UUID', field: '_id', align: 'center', sortable: true },
    { name: 'nombre', label: 'Nombre', field: 'nombre', align: 'center', sortable: true },
    { name: 'apellidos', label: 'Apellidos', field: 'apellidos', align: 'center', sortable: true },
    { name: 'correo', label: 'Correo', field: 'correo', align: 'center', sortable: true },
    { name: 'nif', label: 'NIF', field: 'nif', align: 'center', sortable: true },
    { name: 'telefono', label: 'Teléfono', field: 'telefono', align: 'center', sortable: true },
    { name: 'direccion', label: 'Dirección', field: 'direccion', align: 'center', sortable: true }
];

const cargando = ref(false)
const error = ref("")
const tuInfo = ref(JSON.parse(window.localStorage.getItem("usuario")))
const filas = ref([])

onMounted(() => {
    obtenerTodos()
})

async function intentarRegistro() {
  try {
    const respuesta = await fetch(`${urlApi}/usuarios/${codigo.value}`);
    const datos = await respuesta.json();

    if (!datos.exito) {
      throw new Error("¡El código de cliente es erróneo!");
    }

    const infoCliente = datos.datos;
    const infocl = {
      clientes: [{ "_id": infoCliente._id }]
    };

    const respuestaPut = await fetch(`${urlApi}/usuarios/${tuInfo.value.datos._id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(infocl)
    });

    const data = await respuestaPut.json();

    if (data.exito) {
      console.log("Cliente añadido correctamente:", data.datos);
      $q.notify({
        message: `¡Cliente ${infoCliente.nombre} ${infoCliente.apellidos} añadido correctamente!`,
        color: "positive",
        position: "top",
        icon: "check"
      });
      obtenerTodos();
    } else {
      throw new Error("Error al añadir el cliente");
    }
  } catch (error) {
    console.log(error);
    $q.notify({
      message: error.message || "Error desconocido",
      color: "negative",
      position: "top",
      icon: "warning"
    });
  }
}


async function obtenerTodos() {
    cargando.value = true
    await fetch(`${urlApi}/usuarios/${tuInfo.value.datos._id}`)
        .then(respuesta => respuesta.json())
        .then(datos => {
            cargando.value = false
            if (datos != null) {
                filas.value = datos.datos.clientes
            } else {
                error.value = "No hay registros en esta tabla de la base de datos"
            }
        })
        .catch(err => {
            cargando.value = false
            error.value = "No se pudieron encontrar registros, ¿base de datos inactiva o peticiones rechazadas?"
            $q.notify({
                message: "¡No se pudo obtener respuesta de la base de datos!",
                color: "warning",
                position: "top",
                icon: "warning",
                actions: [
                    { label: 'Reintentar', color: 'positive', handler: () => { router.go() } },
                    { label: 'Volver al inicio', color: 'negative', handler: () => { router.push({ path: '/' }) } }
                ]
            });
            console.error(err)
        });
}

function copiarId(){
  navigator.clipboard.writeText(tuInfo.value.datos._id)
}
</script>
