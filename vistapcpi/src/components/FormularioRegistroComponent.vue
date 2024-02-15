<template>
  <div class="column flex-center">
    <h5>¡Regístrate!</h5>

    <div class="row" style="width: 320px; height: 3px">
      <div class="col" style="width: 100%; height: 100%; background-color: rgb(192, 192, 192)"></div>
      <div class="col" style="width: 100%; height: 100%; background-color: #89bdbb"></div>
    </div>

    <div style="margin-top: 20px">
      <q-form @submit="registrarse" class="q-gutter-md">
        <div class="row">
          <div class="col">
            <q-input color="azul-oscuro" v-model="nombre" rounded outlined label="Nombre" type="text" lazy-rules
              :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']" />
          </div>
          <div class="col q-ml-md">
            <q-input color="azul-oscuro" v-model="apellidos" rounded outlined label="Apellidos" type="text" lazy-rules
              :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']" />
          </div>
          <div class="col q-ml-md">
            <q-input color="azul-oscuro" v-model="correo" rounded outlined label="Correo electrónico" type="text"
              lazy-rules :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']" />
          </div>
        </div>

        <div class="row q-mt-md">
          <div class="col">
            <q-input color="azul-oscuro" v-model="telefono" rounded outlined label="Teléfono" type="tel" lazy-rules
              :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']" />
          </div>
          <div class="col q-ml-md">
            <q-input color="azul-oscuro" v-model="direccion" rounded outlined label="Dirección" type="text" lazy-rules
              :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']" />
          </div>
          <div class="col q-ml-md">
            <q-input color="azul-oscuro" v-model="nif" rounded outlined label="NIF" type="text" lazy-rules
              :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']" />
          </div>
        </div>

        <div class="row q-mt-md">
          <div class="col q-ml-md">
            <q-input color="azul-oscuro" v-model="contrasena" rounded outlined label="Contraseña" type="password"
              lazy-rules :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']" />
          </div>
        </div>

        <div class="q-mt-xm flex flex-center">
          <q-btn label="REGISTRAR" type="submit" class="bg-azul-menta text-white" style="width: 312px; height: 5px" />
        </div>

        <div class="row flex-center q-mt-md">
          <div class="q-mr-xs" style="color: rgb(150, 150, 150)">
            ¿Ya tienes una cuenta?
          </div>
          <div class="text-weight-bold cursor-pointer" @click="login()">
            Inicia sesión
          </div>
        </div>
      </q-form>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import api from 'src/boot/httpSingleton';
import { useQuasar } from 'quasar'

const urlApi = api
const nombre = ref("");
const apellidos = ref("");
const correo = ref("");
const telefono = ref("");
const direccion = ref("");
const nif = ref("");
const contrasena = ref("");
const $q = useQuasar()

const emit = defineEmits(["login"]);

function login() {
  emit("login");
}

async function registrarse() {
  await fetch(`${urlApi}/usuarios`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      "nombre": nombre.value,
      "apellidos": apellidos.value,
      "correo": correo.value,
      "contra": contrasena.value,
      "nif": nif.value,
      "telefono": telefono.value,
      "direccion": direccion.value
    })
  })
    .then(res => res.json())
    .then(datos => {
      console.log(datos)
      if (!datos.exito) {
        $q.notify({
          progress: true,
          message: 'Registro incorrecto',
          color: 'negative',
          timeout: 1000
        })
      } else {
        $q.notify({
          progress: true,
          message: 'Registro correcto',
          color: 'positive',
          timeout: 1000
        })

        login()
      }
    })
}
</script>
