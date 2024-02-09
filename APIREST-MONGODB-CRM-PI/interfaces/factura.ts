import { Document, Schema } from "mongoose";

export default interface IFactura extends Document {
  concepto: Schema.Types.String;
  completada: Schema.Types.Boolean;
  descripcion: Schema.Types.String;
  datosEmisor: Schema.Types.ObjectId;
  numero: Schema.Types.ObjectId;
  fecha: Schema.Types.String;
  baseImp: Schema.Types.Number;
  datosReceptor: Schema.Types.ObjectId;
}
