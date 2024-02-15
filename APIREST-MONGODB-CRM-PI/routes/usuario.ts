import { Router } from "express";
import {
  deleteUsuario,
  getUsuario,
  getUsuarioByCorreoAndContra,
  getUsuarios,
  postUsuario,
  putUsuario,
} from "../controllers/usuario";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/", getUsuarios);
router.get("/:correo/:contra", getUsuarioByCorreoAndContra);
router.get("/:id", getUsuario);
router.post("/", postUsuario);
router.put("/:id", putUsuario);
router.delete("/:id", deleteUsuario);

export default router;
