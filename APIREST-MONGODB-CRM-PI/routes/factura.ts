import { Router } from "express";
import {
  getFacturas,
  getFactura,
  postFactura,
  putFactura,
  deleteFactura,
} from "../controllers/factura";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/tuyas/:id", getFacturas);
router.get("/:id", getFactura);
router.post("/", postFactura);
router.put("/:id", putFactura);
router.delete("/:id", deleteFactura);

export default router;
