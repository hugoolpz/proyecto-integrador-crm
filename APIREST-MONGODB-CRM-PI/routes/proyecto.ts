import { Router } from "express";
import {
  getProyectos,
  getProyecto,
  postProyecto,
  putProyecto,
  deleteProyecto,
} from "../controllers/proyecto";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/", getProyectos);
router.get("/:id", getProyecto);
router.post("/", postProyecto);
router.put("/agregarTarea/:id", putProyecto);
router.delete("/:id", deleteProyecto);

export default router;
