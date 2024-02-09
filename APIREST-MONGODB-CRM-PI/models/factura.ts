import mongoose, { Schema, Document } from "mongoose";
import IUsuario from "../interfaces/usuario";
import IFactura from "../interfaces/factura";

const SchemaUsuario: Schema = new Schema<IUsuario>(
  {
    nombre: { type: Schema.Types.String, required: true },
    apellidos: { type: Schema.Types.String, required: true },
    correo: { type: Schema.Types.String, required: true },
    contra: { type: Schema.Types.String, required: true },
    nif: { type: Schema.Types.String, required: true },
    telefono: { type: Schema.Types.String, required: true },
    direccion: { type: Schema.Types.String, required: true },
  },
  { timestamps: false, versionKey: false }
);

export const UsuarioModel = mongoose.model<IUsuario & Document>(
  "usuarios",
  SchemaUsuario
);

const SchemaFactura: Schema = new Schema<IFactura>(
  {
    concepto: { type: Schema.Types.String, required: true },
    completada: { type: Schema.Types.Boolean },
    descripcion: { type: Schema.Types.String, required: true },
    datosEmisor: {
      type: Schema.Types.ObjectId,
      ref: "usuarios",
      required: true,
    },
    numero: { type: Schema.Types.ObjectId, required: true },
    fecha: { type: Schema.Types.String, required: true },
    baseImp: { type: Schema.Types.Number, required: true },
    datosReceptor: {
      type: Schema.Types.ObjectId,
      ref: "usuarios",
      required: true,
    },
  },
  { timestamps: false, versionKey: false }
);

export default mongoose.model<IFactura & Document>("facturas", SchemaFactura);
