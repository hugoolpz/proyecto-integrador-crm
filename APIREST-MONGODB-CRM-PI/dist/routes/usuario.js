"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const usuario_1 = require("../controllers/usuario");
const router = (0, express_1.Router)();
//Establecemos los endpoints de cada solicitud
router.get("/", usuario_1.getUsuarios);
router.get("/:correo/:contra", usuario_1.getUsuarioByCorreoAndContra);
router.post("/", usuario_1.postUsuario);
router.put("/:id", usuario_1.putUsuario);
router.delete("/:id", usuario_1.deleteUsuario);
exports.default = router;
//# sourceMappingURL=usuario.js.map