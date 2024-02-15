<template>
    <div>
        <div class="q-pl-xl q-pb-xl q-pt-lg" style="min-width: 250px; max-width: 395px"></div>
        <div class="column items-center">
            <div class="col">
                <div class="row">
                    <div class="col-4 q-px-xl q-pb-xl" v-for="n in 10" :key="n">
                        <q-card bordered class="tarjeta-factura" style="width: 100%">
                            <q-card-section class="bg-azul-menta">
                                <div class="text-h6">Proyecto N</div>
                                <div class="text-subtitle1 text-weight-light text-azul-oscuro">
                                    Subtítulo proyecto
                                </div>
                                <q-checkbox color="azul-oscuro" keep-color left-label v-model="completada"
                                    :label="'Completado'" />
                                <q-btn fab color="red" icon="fas fa-xmark" class="absolute" @click="confirmar = true"
                                    style="top: 5px; right: -20px; transform: translateY(-50%)" />
                            </q-card-section>

                            <q-separator color="azul-oscuro" />
                            <q-card-section>
                                <div class="text-subtitle1 text-azul-oscuro">
                                    Descripción proyecto...
                                </div>
                            </q-card-section>
                            <q-card-section class="no-padding">
                                <q-list bordered>
                                    <ItemTarea titulo-tarea="Tarea 1"></ItemTarea>
                                    <ItemTarea titulo-tarea="Tarea 2"></ItemTarea>
                                    <ItemTarea titulo-tarea="Tarea 3"></ItemTarea>
                                    <ItemTarea titulo-tarea="Tarea 4"></ItemTarea>
                                </q-list>
                            </q-card-section>
                        </q-card>

                        <q-dialog v-model="confirmarElimProy" persistent>
                            <q-card>
                                <q-card-section class="row items-center">
                                    <q-avatar icon="warning" color="warning" text-color="white" />
                                    <span class="q-ml-sm" v-html="`¿Estas seguro de que quieres borrarlo?`"></span>
                                </q-card-section>

                                <q-card-actions align="right">
                                    <q-btn flat label="Sí, eliminar" color="positive" v-close-popup
                                        @click="eliminarElegido(id)" />
                                    <q-btn flat label="No, conservar" color="negative" v-close-popup />
                                </q-card-actions>
                            </q-card>
                        </q-dialog>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>