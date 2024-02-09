"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.UsuarioModel = void 0;
const mongoose_1 = __importStar(require("mongoose"));
const SchemaUsuario = new mongoose_1.Schema({
    nombre: { type: mongoose_1.Schema.Types.String, required: true },
    apellidos: { type: mongoose_1.Schema.Types.String, required: true },
    correo: { type: mongoose_1.Schema.Types.String, required: true },
    contra: { type: mongoose_1.Schema.Types.String, required: true },
    nif: { type: mongoose_1.Schema.Types.String, required: true },
    telefono: { type: mongoose_1.Schema.Types.String, required: true },
    direccion: { type: mongoose_1.Schema.Types.String, required: true },
}, { timestamps: false, versionKey: false });
exports.UsuarioModel = mongoose_1.default.model("usuarios", SchemaUsuario);
const SchemaFactura = new mongoose_1.Schema({
    concepto: { type: mongoose_1.Schema.Types.String, required: true },
    completada: { type: mongoose_1.Schema.Types.Boolean },
    descripcion: { type: mongoose_1.Schema.Types.String, required: true },
    datosEmisor: {
        type: mongoose_1.Schema.Types.ObjectId,
        ref: "usuarios",
        required: true,
    },
    numero: { type: mongoose_1.Schema.Types.ObjectId, required: true },
    fecha: { type: mongoose_1.Schema.Types.String, required: true },
    baseImp: { type: mongoose_1.Schema.Types.Number, required: true },
    datosReceptor: {
        type: mongoose_1.Schema.Types.ObjectId,
        ref: "usuarios",
        required: true,
    },
}, { timestamps: false, versionKey: false });
exports.default = mongoose_1.default.model("facturas", SchemaFactura);
//# sourceMappingURL=factura.js.map