import { Router } from "express";
import {
  getProyectos,
  getProyecto,
  postProyecto,
  putProyecto,
  putProyectoE,
  deleteProyecto,
  putTareaE
} from "../controllers/proyecto";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/", getProyectos);
router.get("/:id", getProyecto);
router.put("/:id", putProyectoE);
router.put("/agregarTarea/:id", putProyecto);
router.put("/:id/tareas/:tareaid", putTareaE);
router.post("/", postProyecto);
router.delete("/:id", deleteProyecto);

export default router;
