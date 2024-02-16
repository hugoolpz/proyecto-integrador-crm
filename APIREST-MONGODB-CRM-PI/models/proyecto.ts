import mongoose, { Schema, Document } from "mongoose";
import IProyecto from "../interfaces/proyecto";
import ITarea from "../interfaces/tarea";

const SchemaTarea: Schema = new Schema<ITarea>(
  {
    nombre: { type: Schema.Types.String, required: true },
    subtitulo: { type: Schema.Types.String, required: true },
    tiempoEsp: { type: Schema.Types.Number, required: true },
    tiempoReal: { type: Schema.Types.Number, required: true },
    estado: { type: Schema.Types.Boolean, required: true },
    esImp: { type: Schema.Types.Boolean, required: true },

  },
  { timestamps: false, versionKey: false }
);

const SchemaProyecto: Schema = new Schema<IProyecto>(
  {
    nombre: { type: Schema.Types.String, required: true },
    subtitulo: { type: Schema.Types.String, required: true },
    descripcion: { type: Schema.Types.String, required: true },
    estado: { type: Schema.Types.Boolean, required: true },
    tareas: [SchemaTarea],
  },
  { timestamps: false, versionKey: false }
);

export const ProyectoModel =  mongoose.model<IProyecto & Document>(
  "proyectos",
  SchemaProyecto
);
