import { Document, Schema } from "mongoose";

export default interface ITarea extends Document {
  nombre: Schema.Types.String;
  importante: Schema.Types.Boolean;
  estado: Schema.Types.Boolean;
}
