import { Document, Schema } from "mongoose";

export default interface ITarea extends Document {
  nombre: Schema.Types.String;
  subtitulo: Schema.Types.String;
  tiempoEsp: Schema.Types.Number;
  tiempoReal: Schema.Types.Number;
  estado: Schema.Types.Boolean;
  esImp: Schema.Types.Boolean;
}
