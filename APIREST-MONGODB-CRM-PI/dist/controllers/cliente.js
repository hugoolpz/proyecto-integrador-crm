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
Object.defineProperty(exports, "__esModule", { value: true });
exports.getClientes = void 0;
const factura_1 = require("../models/factura");
const getClientes = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield factura_1.UsuarioModel.findById(id)
        .select("clientes")
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
exports.getClientes = getClientes;
//# sourceMappingURL=cliente.js.map