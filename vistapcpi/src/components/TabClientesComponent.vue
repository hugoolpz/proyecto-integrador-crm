<template>
    <q-table title="Tus clientes" :rows="filas" :columns="columnas" row-key="_id" separator="cell" :loading="cargando"
        :no-data-label="error" />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'
import api from 'src/boot/httpSingleton';
const urlApi = api
const router = useRouter()
const $q = useQuasar();

const columnas = [
    { name: '_id', align: 'center', label: 'UUID', field: '_id', align: 'center', sortable: true },
    { name: 'nombre', align: 'center', label: 'Nombre', field: 'nombre', align: 'center', sortable: true },
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
</script>