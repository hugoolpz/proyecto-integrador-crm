import { Document, Schema } from "mongoose";

export default interface IUsuario extends Document {
  nombre: Schema.Types.String;
  apellidos: Schema.Types.String;
  correo: Schema.Types.String;
  contra: Schema.Types.String;
  nif: Schema.Types.String;
  telefono: Schema.Types.String;
  direccion: Schema.Types.String;
  clientes: [Schema.Types.ObjectId];
}
