<template>
  <div class="column flex-center">
    <h5>¡Inicia sesión!</h5>

    <div class="row" style="width: 320px; height: 3px">
      <div
        class="col"
        style="width: 100%; height: 100%; background-color: #89bdbb"
      ></div>
      <div
        class="col"
        style="width: 100%; height: 100%; background-color: rgb(192, 192, 192)"
      ></div>
    </div>

    <div class="q-pa-md" style="max-width: 500px">
      <q-form @submit="iniciarSesion" class="q-gutter-md">
        <q-input
          color="azul-oscuro"
          class="q-mt-xl"
          v-model="email"
          type="text"
          label="Correo electrónico"
          lazy-rules
          :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']"
        >
          <template v-slot:prepend>
            <q-icon name="email" />
          </template>
        </q-input>

        <q-input
          color="azul-oscuro"
          v-model="contrasena"
          :type="isPwd ? 'password' : 'text'"
          label="Contraseña"
          lazy-rules
          :rules="[(val) => (val && val.length > 0) || 'Campo obligatorio']"
        >
          <template v-slot:prepend>
            <q-icon name="lock" />
          </template>
          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>

        <div class="q-mt-xl">
          <q-btn
            label="INICIAR SESIÓN"
            type="submit"
            class="bg-azul-menta text-white"
            style="width: 312px; height: 5px"
          />
        </div>

        <div class="row flex-center">
          <div class="q-mr-xs" style="color: rgb(150, 150, 150)">
            ¿No tienes una cuenta?
          </div>
          <div class="text-weight-bold cursor-pointer" @click="registro()">
            Regístrate
          </div>
        </div>
      </q-form>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import api from "src/boot/httpSingleton";
import { useQuasar } from "quasar";
import { useRouter } from "vue-router";

const email = ref("");
const contrasena = ref("");
const isPwd = ref(true);
const emit = defineEmits(["registro"]);
const urlApi = api;
const $q = useQuasar();
const router = useRouter();
const localStorage = window.localStorage;

function registro() {
  emit("registro");
}

async function iniciarSesion() {
  await fetch(`${urlApi}/usuarios/auth`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      "correo": email.value,
      "contra": contrasena.value
    })
  })
    .then((res) => res.json())
    .then((datos) => {
      if (!datos.exito) {
        $q.notify({
          progress: true,
          message: "Inicio de sesión incorrecto",
          color: "negative",
          timeout: 1000,
        });
      } else {
        $q.notify({
          progress: true,
          message: "Inicio de sesión correcto",
          color: "positive",
          timeout: 1000,
        });

        localStorage.clear();
        localStorage.setItem("usuario", JSON.stringify(datos));

        console.log(datos);

        setTimeout(function () {
          router.push({ path: "/funciones" });
        }, 1100);
      }
    });
}
</script>
