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
exports.deleteUsuario = exports.putUsuario = exports.postUsuario = exports.authUsuario = exports.getUsuario = exports.getUsuarios = void 0;
const mongoose_1 = __importDefault(require("mongoose"));
const factura_1 = require("../models/factura");
const bcrypt_1 = __importDefault(require("bcrypt"));
const getUsuarios = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    yield factura_1.UsuarioModel.find()
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
exports.getUsuarios = getUsuarios;
const getUsuario = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield factura_1.UsuarioModel.findById(id)
        .populate("clientes")
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
exports.getUsuario = getUsuario;
const authUsuario = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { correo, contra } = req.body;
    console.log(req.body);
    yield factura_1.UsuarioModel.findOne({
        correo: correo,
    })
        .populate("clientes")
        .exec()
        .then((resultado) => {
        if (bcrypt_1.default.compareSync(contra, resultado.contra.toString())) {
            console.log("Contra perfe " + resultado);
            return res.json({
                exito: true,
                datos: resultado,
            });
        }
        else {
            console.log("Contra no perfe: " + resultado);
            return res.status(500).json({
                exito: false,
                error: "Contraseña incorrecta",
            });
        }
    })
        .catch((error) => {
        console.log("Error: " + error);
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.authUsuario = authUsuario;
const postUsuario = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { nombre, apellidos, correo, contra, nif, telefono, direccion, clientes, } = req.body;
    let hashContra = bcrypt_1.default.hashSync(contra, 10);
    console.log(req.body);
    const nuevoUsuario = new factura_1.UsuarioModel({
        _id: new mongoose_1.default.Types.ObjectId(),
        nombre,
        apellidos,
        correo,
        contra: hashContra,
        nif,
        telefono,
        direccion,
        clientes,
    });
    yield nuevoUsuario
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
exports.postUsuario = postUsuario;
const putUsuario = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    const { nombre, apellidos, correo, nif, telefono, direccion, clientes } = req.body;
    yield factura_1.UsuarioModel.findOneAndUpdate({ _id: id }, {
        nombre,
        apellidos,
        correo,
        nif,
        telefono,
        direccion,
        $push: { clientes: clientes },
    })
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
exports.putUsuario = putUsuario;
const deleteUsuario = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield factura_1.UsuarioModel.findByIdAndDelete({ _id: id })
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
exports.deleteUsuario = deleteUsuario;
//# sourceMappingURL=usuario.js.map