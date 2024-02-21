import mongoose, { Schema, Document } from "mongoose";
import IProyecto from "../interfaces/proyecto";
import ITarea from "../interfaces/tarea";

const SchemaTarea: Schema = new Schema<ITarea>(
  {
    nombre: { type: Schema.Types.String, required: true },
    importante: { type: Schema.Types.Boolean, required: true },
    estado: { type: Schema.Types.Boolean, required: true },

  },
  { timestamps: false, versionKey: false }
);

export const TareaModel =  mongoose.model<ITarea & Document>(
  "tareas",
  SchemaTarea
);

const SchemaProyecto: Schema = new Schema<IProyecto>(
  {
    nombre: { type: Schema.Types.String, required: true },
    subtitulo: { type: Schema.Types.String, required: true },
    descripcion: { type: Schema.Types.String, required: true },
    estado: { type: Schema.Types.Boolean, required: true },
    tareas:  { type: [SchemaTarea], required: true },
    integrantes: {
      type: [Schema.Types.ObjectId],
      ref: "usuarios", required: true 
    },
  },
  { timestamps: false, versionKey: false }
);

export const ProyectoModel =  mongoose.model<IProyecto & Document>(
  "proyectos",
  SchemaProyecto
);

