import { Document, Schema } from "mongoose";

export default interface ITarea extends Document {
  nombre: Schema.Types.String;
  estado: Schema.Types.Boolean;
  esImp: Schema.Types.Boolean;
}
