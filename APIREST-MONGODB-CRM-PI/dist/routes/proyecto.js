"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const proyecto_1 = require("../controllers/proyecto");
const router = (0, express_1.Router)();
//Establecemos los endpoints de cada solicitud
router.get("/", proyecto_1.getProyectos);
router.get("/:id", proyecto_1.getProyecto);
router.put("/:id", proyecto_1.putProyectoE);
router.put("/agregarTarea/:id", proyecto_1.putProyecto);
router.put("/:id/tareas/:tareaid", proyecto_1.putTareaE);
router.post("/", proyecto_1.postProyecto);
router.delete("/:id", proyecto_1.deleteProyecto);
exports.default = router;
//# sourceMappingURL=proyecto.js.map