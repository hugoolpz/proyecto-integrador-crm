"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.deleteProyecto = exports.putProyecto = exports.postProyecto = exports.getProyecto = exports.getProyectos = void 0;
const mongoose_1 = __importDefault(require("mongoose"));
const proyecto_1 = require("../models/proyecto");
const getProyectos = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    yield proyecto_1.ProyectoModel
        .find()
        .exec()
        .then((resultados) => {
        return res.status(200).json({
            exito: true,
            datos: resultados,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.getProyectos = getProyectos;
const getProyecto = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield proyecto_1.ProyectoModel
        .findById(id)
        .exec()
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.getProyecto = getProyecto;
const postProyecto = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { nombre, subtitulo, descripcion, estado, tareas, integrantes } = req.body;
    const nuevoProyecto = new proyecto_1.ProyectoModel({
        _id: new mongoose_1.default.Types.ObjectId(),
        nombre,
        subtitulo,
        descripcion,
        estado,
        tareas,
        integrantes
    });
    yield nuevoProyecto
        .save()
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.postProyecto = postProyecto;
const putProyecto = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    const { nombre, importante, estado } = req.body;
    yield proyecto_1.ProyectoModel
        .findByIdAndUpdate(id, {
        $push: {
            tareas: {
                nombre,
                importante,
                estado,
            },
        },
    }, { new: true } // Devuelve el documento actualizado
    )
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.putProyecto = putProyecto;
const deleteProyecto = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield proyecto_1.ProyectoModel
        .findByIdAndDelete({ _id: id })
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.deleteProyecto = deleteProyecto;
//# sourceMappingURL=proyecto.js.map