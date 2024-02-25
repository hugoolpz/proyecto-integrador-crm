import { Router } from "express";
import {
  authUsuario,
  deleteUsuario,
  getUsuario,
  getUsuarios,
  postUsuario,
  putUsuario,
  removeCliente,
} from "../controllers/usuario";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/", getUsuarios);
router.post("/auth", authUsuario);
router.get("/:id", getUsuario);
router.post("/", postUsuario);
router.put("/:id", putUsuario);
router.put("/cliente/:id", removeCliente);
router.delete("/:id", deleteUsuario);

export default router;
