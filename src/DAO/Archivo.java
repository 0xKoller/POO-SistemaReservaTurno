package DAO;

import Negocio.Paciente;

import java.io.*;
import java.util.ArrayList;

public class Archivo {

    String path;

    public Archivo(String path){
        this.path = path;
    }

    public void guardar(ArrayList objeto){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objeto);
            fileOutputStream.close();
        } catch (FileNotFoundException e){
            System.err.println("No se encontro el archivo");
        } catch (IOException e) {
            System.err.println("Error de IO: "+e.getMessage());
        }
    }

    public ArrayList listar() {
        //Estas leyendo el objeto (para el sistema es una unica coleccion)
        Object objeto = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objeto = objectInputStream.readObject();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //System.err.println("El archivo estaba vacio");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if(objeto == null){
                objeto = new ArrayList();
            }
            return (ArrayList) objeto;
        }
    }

    public void eliminar(int indice){
        try {
            ArrayList lista = this.listar();
            lista.remove(indice);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lista);
            fileOutputStream.close();
        } catch (FileNotFoundException e){
            System.err.println("No se encontro el archivo");
        } catch (IOException e) {
            System.err.println("Error de IO: "+e.getMessage());
        }
    }

}
