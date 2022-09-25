package co.uniquindio.edu.model;

import co.uniquindio.edu.controller.Controller;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;

@Data
public class Archivo {


    private Controller controller;


    private File file;

    public Archivo(String ruta){
        file = new File(ruta);
    }

    public void cambiarRuta(String ruta){
        file = new File(ruta);
    }

    public ArrayList<MyFile> getDirectories(){
        ArrayList<MyFile> archivos = new ArrayList<>();
        String[] objetos = file.list();

        for (String i: objetos) {
            archivos.add(new MyFile(i));
        }
        return archivos;
    }


    public String obtenerRuta() {
        return file.getPath();
    }

    public boolean verificarDirectory(String ruta){
        File fileAux = new File(file.getPath()+ruta);
        return fileAux.isDirectory();
    }

}
