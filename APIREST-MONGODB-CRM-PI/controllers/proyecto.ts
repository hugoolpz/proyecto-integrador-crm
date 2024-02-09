import { Request, Response } from "express";
import mongoose from "mongoose";
import proyecto from "../models/proyecto";

const getProyectos = async (req: Request, res: Response) => {
  await proyecto
    .find()
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

const getProyecto = async (req: Request, res: Response) => {
  const { id } = req.params;

  await proyecto
    .findById(id)
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

const postProyecto = async (req: Request, res: Response) => {
  const { nombre, subtitulo, descripcion, estado, tareas } = req.body;

  const nuevoProyecto = new proyecto({
    _id: new mongoose.Types.ObjectId(),
    nombre,
    subtitulo,
    descripcion,
    estado,
    tareas,
  });

  await nuevoProyecto
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

const putProyecto = async (req: Request, res: Response) => {
  const { id } = req.params;

  const { nombre, subtitulo, descripcion, estado, tareas } = req.body;

  await proyecto
    .findByIdAndUpdate(
      { _id: id },
      {
        nombre,
        subtitulo,
        descripcion,
        estado,
        tareas,
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

const deleteProyecto = async (req: Request, res: Response) => {
  const { id } = req.params;

  await proyecto
    .findByIdAndDelete({ _id: id })
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

export { getProyectos, getProyecto, postProyecto, putProyecto, deleteProyecto };
