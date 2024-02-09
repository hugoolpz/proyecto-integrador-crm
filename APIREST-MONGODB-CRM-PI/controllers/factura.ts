import { Request, Response } from "express";
import Factura from "../models/factura";
import mongoose from "mongoose";

const getFacturas = async (req: Request, res: Response) => {
  const { id } = req.params;

  await Factura.find()
    .or([{ datosEmisor: id }, { datosReceptor: id }])
    .populate("datosEmisor")
    .populate("datosReceptor")
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
};

const getFactura = async (req: Request, res: Response) => {
  const { id } = req.params;

  await Factura.findById(id)
    .populate("datosEmisor")
    .populate("datosReceptor")
    .exec()
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
};

const postFactura = async (req: Request, res: Response) => {
  const { concepto, descripcion, datosEmisor, fecha, baseImp, datosReceptor } =
    req.body;

  const factura = new Factura({
    _id: new mongoose.Types.ObjectId(),
    concepto,
    descripcion,
    datosEmisor,
    numero: new mongoose.Types.ObjectId(),
    fecha,
    baseImp,
    datosReceptor,
  });

  await factura
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
};

const putFactura = async (req: Request, res: Response) => {
  const { id } = req.params;

  const {
    tipo,
    concepto,
    completada,
    descripcion,
    datosEmisor,
    fecha,
    baseImp,
    datosReceptor,
  } = req.body;

  await Factura.findByIdAndUpdate(
    { _id: id },
    {
      tipo,
      concepto,
      completada,
      descripcion,
      datosEmisor,
      fecha,
      baseImp,
      datosReceptor,
    }
  )
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
};

const deleteFactura = async (req: Request, res: Response) => {
  const { id } = req.params;

  await Factura.findByIdAndDelete({ _id: id })
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
};

export { getFacturas, getFactura, postFactura, putFactura, deleteFactura };
