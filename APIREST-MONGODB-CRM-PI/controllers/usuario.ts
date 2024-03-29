import { Request, Response } from "express";
import mongoose from "mongoose";
import { UsuarioModel } from "../models/factura";
import bcrypt from "bcrypt";

const getUsuarios = async (req: Request, res: Response) => {
  await UsuarioModel.find()
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

const getUsuario = async (req: Request, res: Response) => {
  const { id } = req.params;

  await UsuarioModel.findById(id)
    .populate("clientes")
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

const authUsuario = async (req: Request, res: Response) => {
  const { correo, contra } = req.body;

  console.log(req.body);

  await UsuarioModel.findOne({
    correo: correo,
  })
    .populate("clientes")
    .exec()
    .then((resultado) => {
      if (bcrypt.compareSync(contra, resultado!.contra.toString())) {
        console.log("Contra perfe " + resultado);
        return res.json({
          exito: true,
          datos: resultado,
        });
      } else {
        console.log("Contra no perfe: " + resultado);
        return res.status(500).json({
          exito: false,
          error: "Contraseña incorrecta",
        });
      }
    })
    .catch((error) => {
      console.log("Error: " + error);
      return res.status(500).json({
        exito: false,
        error,
      });
    });
};

const postUsuario = async (req: Request, res: Response) => {
  const {
    nombre,
    apellidos,
    correo,
    contra,
    nif,
    telefono,
    direccion,
    clientes,
  } = req.body;

  let hashContra = bcrypt.hashSync(contra, 10);

  console.log(req.body);

  const nuevoUsuario = new UsuarioModel({
    _id: new mongoose.Types.ObjectId(),
    nombre,
    apellidos,
    correo,
    contra: hashContra,
    nif,
    telefono,
    direccion,
    clientes,
  });

  await nuevoUsuario
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

const putUsuario = async (req: Request, res: Response) => {
  const { id } = req.params;
  const { nombre, apellidos, correo, nif, telefono, direccion, clientes } =
    req.body;

  // Verificaciones de atributos nulos
  const updateData: any = {};
  if (nombre !== "" && nombre !== undefined) {
    updateData.nombre = nombre;
  }
  if (apellidos !== "" && apellidos !== undefined) {
    updateData.apellidos = apellidos;
  }
  if (correo !== "" && correo !== undefined) {
    updateData.correo = correo;
  }
  if (nif !== "" && nif !== undefined) {
    updateData.nif = nif;
  }
  if (telefono !== "" && telefono !== undefined) {
    updateData.telefono = telefono;
  }
  if (direccion !== "" && direccion !== undefined) {
    updateData.direccion = direccion;
  }

  if (clientes !== null && clientes !== undefined) {
    updateData.$push = { clientes: clientes };
  }

  await UsuarioModel.findOneAndUpdate({ _id: id }, updateData)
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

const removeCliente = async (req: Request, res: Response) => {
  const { id } = req.params;
  const { nombre, apellidos, correo, nif, telefono, direccion, clientes } =
    req.body;

  // Verificaciones de atributos nulos
  const updateData: any = {};
  if (nombre !== "" && nombre !== undefined) {
    updateData.nombre = nombre;
  }
  if (apellidos !== "" && apellidos !== undefined) {
    updateData.apellidos = apellidos;
  }
  if (correo !== "" && correo !== undefined) {
    updateData.correo = correo;
  }
  if (nif !== "" && nif !== undefined) {
    updateData.nif = nif;
  }
  if (telefono !== "" && telefono !== undefined) {
    updateData.telefono = telefono;
  }
  if (direccion !== "" && direccion !== undefined) {
    updateData.direccion = direccion;
  }

  if (clientes !== null && clientes !== undefined) {
    updateData.clientes = clientes;
  } else {
    updateData.$pull = { clientes: { $exists: true } };
  }

  await UsuarioModel.findOneAndUpdate({ _id: id }, updateData)
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

const deleteUsuario = async (req: Request, res: Response) => {
  const { id } = req.params;

  await UsuarioModel.findByIdAndDelete({ _id: id })
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

export {
  getUsuarios,
  getUsuario,
  authUsuario,
  postUsuario,
  putUsuario,
  deleteUsuario,
  removeCliente,
};
