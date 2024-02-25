import { Request, Response } from "express";
import mongoose from "mongoose";
import {ProyectoModel} from "../models/proyecto";
import {TareaModel} from "../models/proyecto";

const getProyectos = async (req: Request, res: Response) => {
  await ProyectoModel
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

  await ProyectoModel
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
  const { nombre, subtitulo, descripcion, estado, tareas,integrantes } = req.body;

  const nuevoProyecto = new ProyectoModel({
    _id: new mongoose.Types.ObjectId(),
      nombre,
      subtitulo,
      descripcion,
      estado,
      tareas,
      integrantes
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
  const { nombre, importante, estado } = req.body; 

  await ProyectoModel
    .findByIdAndUpdate(
      id,
      {
        $push: {
          tareas: {
            nombre,
            importante,
            estado,
          },
        },
      },
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
const putTareaE = async (req: Request, res: Response) => {
  const { id ,tareaid} = req.params;
  const { estado} = req.body; 

  try {
    const resultado = await ProyectoModel.findOneAndUpdate(
      { _id: id, 'tareas._id': tareaid },
      { $set: { 'tareas.$.estado': estado } },
      { new: true }
    );

    if (!resultado) {
      return res.status(404).json({
        exito: false,
        mensaje: 'Proyecto o tarea no encontrada',
      });
    }

    return res.status(200).json({
      exito: true,
      datos: resultado,
    });
  } catch (error) {
    console.error('Error en la actualización de la tarea:', error);
    return res.status(500).json({
      exito: false,
      error: 'Error interno del servidor',
    });
  }
};


const putProyectoE = async (req: Request, res: Response) => {
  const { id } = req.params;

  const {
    estado
  } = req.body;

  await ProyectoModel.findByIdAndUpdate(
    { _id: id },
    {
      estado,
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

  await ProyectoModel
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

export { getProyectos, getProyecto, putProyectoE,putTareaE, postProyecto, putProyecto, deleteProyecto };
