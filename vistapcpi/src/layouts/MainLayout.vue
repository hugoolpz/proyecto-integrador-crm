<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-azul-oscuro text-white">
      <q-toolbar class="text-white shadow-2">
        <q-btn
          flat
          icon="fas fa-list"
          class="q-mr-sm"
          @click="menuIzq = !menuIzq"
        />
        <q-separator dark vertical inset />
        <q-btn
          stretch
          flat
          label="Inicio"
          @click="router.push({ path: '/' })"
        />
        <q-separator dark vertical inset class="q-mr-md" />
        <q-btn
          v-if="tab === 'facturas'"
          flat
          icon="fas fa-plus"
          @click="emitirFact = true"
          label="Emitir factura"
          class="bg-azul-menta"
        />
        <q-btn
          v-else-if="tab === 'proyectos'"
          flat
          icon="fas fa-plus"
          @click="crearProyecto = true"
          label="Crear proyecto"
          class="bg-azul-menta"
        />
        <q-btn
          v-else
          flat
          icon="fas fa-plus"
          @click="registrarCliente = true"
          label="Registrar cliente"
          class="bg-azul-menta"
        />

        <q-space />

        <q-input
          dark
          dense
          standout
          v-model="busqueda"
          class="q-mr-md"
          style="width: 300px"
        >
          <template v-slot:append>
            <q-icon v-if="busqueda === ''" name="fas fa-search" />
            <q-icon
              v-else
              name="fas fa-xmark"
              class="cursor-pointer"
              @click="busqueda = ''"
            />
          </template>
        </q-input>
        <q-tabs
          v-model="tab"
          class="text-white"
          active-class="text-rojo-tomate"
        >
          <q-tab name="clientes" icon="fas fa-user" label="Clientes" />
          <q-tab name="facturas" icon="fas fa-receipt" label="Facturas" />
          <q-tab name="proyectos" icon="fas fa-list-check" label="Proyectos" />
          <q-tab name="graficos" icon="fas fa-chart-pie" label="Beneficios" />
        </q-tabs>
      </q-toolbar>
    </q-header>
    <q-drawer
      v-model="menuIzq"
      behavior="mobile"
      :width="250"
      :breakpoint="400"
    >
      <q-img
        class=""
        src="https://cdn.quasar.dev/img/material.png"
        style="height: 80px"
      >
        <div class="bg-transparent">
          <div class="text-weight-bold">
            {{ tuInfo.datos.nombre + " " + tuInfo.datos.apellidos }}
          </div>
        </div>
      </q-img>

      <q-scroll-area
        style="height: calc(100% - 150px); border-right: 1px solid #ddd"
      >
        <q-list padding>
          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="fas fa-user-edit" />
            </q-item-section>

            <q-item-section> Cambiar datos de perfil </q-item-section>
          </q-item>

          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="fas fa-right-from-bracket" />
            </q-item-section>

            <q-item-section> Cerrar sesión </q-item-section>
          </q-item>

          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="fas fa-trash" />
            </q-item-section>

            <q-item-section> Eliminar cuenta </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <q-page>
        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="facturas">
            <tab-facturas v-model="emitirFact"></tab-facturas>
          </q-tab-panel>
          <q-tab-panel name="proyectos">
            <tab-proyectos v-model="crearProyecto"></tab-proyectos>
          </q-tab-panel>
          <q-tab-panel name="clientes">
            <tab-clientes v-model="registrarCliente"></tab-clientes>
          </q-tab-panel>
          <q-tab-panel name="graficos" class="flex flex-center">
            <tab-graficos></tab-graficos>
          </q-tab-panel>
        </q-tab-panels>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref } from "vue";
import TabClientes from "src/components/TabClientesComponent.vue";
import TabProyectos from "src/components/TabProyectosComponent.vue";
import TabFacturas from "src/components/TabFacturasComponent.vue";
import { useRouter } from "vue-router";
import TabGraficos from "components/TabGraficosComponent.vue";

const menuIzq = ref(false);
const completada = ref(false);
const tab = ref("facturas");
const confirmarElimProy = ref(false);
const busqueda = ref("");
const emitirFact = ref(false);
const router = useRouter();
const crearProyecto = ref(false);
const registrarCliente = ref(false)

const tuInfo = ref(JSON.parse(window.localStorage.getItem("usuario")));
</script>
