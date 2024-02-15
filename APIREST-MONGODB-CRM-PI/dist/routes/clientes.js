"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const cliente_1 = require("../controllers/cliente");
const router = (0, express_1.Router)();
//Establecemos los endpoints de cada solicitud
router.get("/:id", cliente_1.getClientes);
exports.default = router;
//# sourceMappingURL=clientes.js.map