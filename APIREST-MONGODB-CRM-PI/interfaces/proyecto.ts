import { Document, Schema } from "mongoose";
import IUsuario from "./usuario";
import ITarea from "./tarea";

export default interface IProyecto extends Document {
  nombre: Schema.Types.String;
  subtitulo: Schema.Types.String;
  descripcion: Schema.Types.String;
  estado: Schema.Types.Boolean;
  tareas: [ITarea];
  integrantes: [IUsuario];

}
