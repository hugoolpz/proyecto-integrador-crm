<template>
  <q-page>
    <q-parallax :height="550">
      <template v-slot:media>
        <video width="1920" height="1080" poster="" autoplay loop muted style="filter: brightness(30%);">
          <source :ratio="16 / 9" type="video/mp4" src="../assets/pexels-kindel-media-6774633 (2160p).mp4">
        </video>
      </template>

      <template v-slot:content="scope">
        <div class="absolute column items-center" :style="{
          opacity: 0.4 + (1 - scope.percentScrolled) * 0.55,
          top: (scope.percentScrolled * 60) + '%',
          left: 0,
          right: 0
        }">
          <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg"
            style="background-color: white; padding: 10px; border-radius: 100px;width: 150px; height: 150px">
          <div class="text-h3 text-white text-center q-mt-sm">Maneja tus facturas fácilmente</div>
          <div class="text-h6 text-grey-3 text-center q-mt-sm">Todo en la palma de tu mano</div>
        </div>
      </template>
    </q-parallax>
  </q-page>
</template>

<script setup></script>
