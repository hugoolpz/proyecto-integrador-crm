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
exports.ProyectoModel = void 0;
const mongoose_1 = __importStar(require("mongoose"));
const SchemaTarea = new mongoose_1.Schema({
    nombre: { type: mongoose_1.Schema.Types.String, required: true },
    subtitulo: { type: mongoose_1.Schema.Types.String, required: true },
    tiempoEsp: { type: mongoose_1.Schema.Types.Number, required: true },
    tiempoReal: { type: mongoose_1.Schema.Types.Number, required: true },
    estado: { type: mongoose_1.Schema.Types.Boolean, required: true },
    esImp: { type: mongoose_1.Schema.Types.Boolean, required: true },
}, { timestamps: false, versionKey: false });
const SchemaProyecto = new mongoose_1.Schema({
    nombre: { type: mongoose_1.Schema.Types.String, required: true },
    subtitulo: { type: mongoose_1.Schema.Types.String, required: true },
    descripcion: { type: mongoose_1.Schema.Types.String, required: true },
    estado: { type: mongoose_1.Schema.Types.Boolean, required: true },
    tareas: [SchemaTarea],
}, { timestamps: false, versionKey: false });
exports.ProyectoModel = mongoose_1.default.model("proyectos", SchemaProyecto);
//# sourceMappingURL=proyecto.js.map