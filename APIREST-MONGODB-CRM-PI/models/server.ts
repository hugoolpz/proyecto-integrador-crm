import express, { Application } from "express";
import mongoose from "mongoose";
import cors from "cors";
import rutasFacturas from "../routes/factura";
import rutasUsuarios from "../routes/usuario";
import rutasProyectos from "../routes/proyecto";

class Servidor {
  private app: Application;
  private port: string;
  private rutasApi = {
    facturas: "/api/facturas/",
    usuarios: "/api/usuarios/",
    proyectos: "/api/proyectos/",
  };

  constructor() {
    this.app = express();
    //Usamos la variable de entorno PORT y si es null, ponemos 8080
    this.port = "3000";
    //Nos intentamos conectar a la BD
    this.establecerConexionBD();
    //Activamos los middlewares
    this.middlewares();
    //Definimos rutas
    this.routes();
  }

  async establecerConexionBD() {
    mongoose
      .connect(process.env.MONGO_URL!)
      .then(() => {
        console.log("Éxito al conectar a Mongo");
      })
      .catch((error) => {
        console.log("Error al conectar a Mongo" + error);
      });

    mongoose.set("debug", true);
  }

  middlewares() {
    //CORS
    this.app.use(cors());
    //Parseo del body
    this.app.use(express.json());
    //Codificar url
    this.app.use(express.urlencoded({ extended: true }));
  }

  routes() {
    this.app.use(this.rutasApi.facturas, rutasFacturas);
    this.app.use(this.rutasApi.usuarios, rutasUsuarios);
    this.app.use(this.rutasApi.proyectos, rutasProyectos);
  }

  listen() {
    this.app.listen(this.port, () => {
      console.log("El servidor activo en puerto " + this.port);
    });
  }
}

//Exportamos la clase
export default Servidor;
