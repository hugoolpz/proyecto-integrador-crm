<template>
  <q-header elevated class="q-py-md bg-azul-oscuro text-white">
    <q-toolbar>
      <q-toolbar-title class="blanco-claro"> Nombre de la APP </q-toolbar-title>
      <q-btn class="bg-azul-menta" icon="login" label="INICIAR SESIÓN" @click="showDialogLogin = true" />
    </q-toolbar>
  </q-header>

  <q-dialog
    v-model="showDialogLogin"
    transition-hide="slide-down"
    transition-show="slide-up"
  >
    <q-card class="overflow-hidden-y" style="width: 800px; height: 650px;">
      <q-card-section>
        <FormularioLoginComponent @registro="closeDialogLogin()"></FormularioLoginComponent>
      </q-card-section>

      <q-img
        :src="url"
      />
    </q-card>
  </q-dialog>

  <q-dialog
    v-model="showDialogRegistro"
    transition-hide="slide-down"
    transition-show="slide-up"
  >
    <q-card class="overflow-hidden-y" style="width: 700px; max-width: 80vw;">
      <q-card-section>
        <FormularioRegistroComponent @login="closeDialogRegistro()"></FormularioRegistroComponent>
      </q-card-section>

      <q-img
        :src="url"
      />
    </q-card>
  </q-dialog>
</template>

<script setup>
import { ref } from "vue";
import FormularioLoginComponent from "src/components/FormularioLoginComponent.vue";
import FormularioRegistroComponent from "src/components/FormularioRegistroComponent.vue";

const showDialogLogin = ref(false);
const showDialogRegistro = ref(false);
const url = ref("src/assets/circulos.png")

function closeDialogLogin() {
  showDialogLogin.value = false;
  showDialogRegistro.value = true;
}

function closeDialogRegistro() {
  showDialogLogin.value = true;
  showDialogRegistro.value = false;
}
</script>
