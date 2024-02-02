<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <q-header elevated class="bg-azul-oscuro text-white">
    <q-toolbar>
      <q-btn flat @click="menuIzq = !menuIzq" round dense icon="fas fa-list" />
      <q-toolbar-title class="blanco-claro"> Nombre de la APP </q-toolbar-title>
      <q-space />
      <q-input
        dark
        dense
        standout
        v-model="busqueda"
        class="q-ml-md"
        style="width: 450px"
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
      <q-space />

      <q-btn
        name="facturas"
        icon="fas fa-receipt"
        label="Facturas"
        to="/facturas"
        flat
      >
      </q-btn>
      <q-btn
        name="proyectos"
        icon="fas fa-list-check"
        to="/proyectos"
        label="Proyectos"
        flat
      >
      </q-btn>
    </q-toolbar>
  </q-header>
  <q-drawer v-model="menuIzq" behavior="mobile" :width="250" :breakpoint="400">
    <q-img
      class=""
      src="https://cdn.quasar.dev/img/material.png"
      style="height: 80px"
    >
      <div class="bg-transparent">
        <div class="text-weight-bold">Razvan Stoenescu</div>
        <div>@rstoenescu</div>
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
</template>
<script setup>
import { ref } from "vue";
const menuIzq = ref(false);
const tab = ref("facturas");
const busqueda = ref("");
</script>
