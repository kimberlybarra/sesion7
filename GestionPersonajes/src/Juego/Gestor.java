package Juego;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gestor {
    private List<Personaje> personajes;
    private String archivo = "personajes.txt";

    public Gestor() {
        personajes = new ArrayList<>();
        leerArchivo();
    }

    public void agregarPersonaje(Personaje personaje) {
        if (!existePersonaje(personaje.getNombre())) {
            personajes.add(personaje);
            guardarArchivo();
        } else {
            System.out.println("El personaje ya existe.");
        }
    }

    public void modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje personaje = obtenerPersonaje(nombre);
        if (personaje != null) {
            personaje.setVida(vida);
            personaje.setAtaque(ataque);
            personaje.setDefensa(defensa);
            personaje.setAlcance(alcance);
            guardarArchivo();
        } else {
            System.out.println("El personaje no existe.");
        }
    }

    public void eliminarPersonaje(String nombre) {
        Personaje personaje = obtenerPersonaje(nombre);
        if (personaje != null) {
            personajes.remove(personaje);
            guardarArchivo();
            System.out.println("Personaje eliminado.");
        } else {
            System.out.println("El personaje no existe.");
        }
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes.");
        } else {
            for (Personaje p : personajes) {
                System.out.println(p);
            }
        }
    }

    boolean existePersonaje(String nombre) {
        return obtenerPersonaje(nombre) != null;
    }

    private Personaje obtenerPersonaje(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    private void guardarArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                writer.println(p); // Utiliza el método toString de la clase Personaje
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    private void leerArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int vida = Integer.parseInt(datos[1]);
                int ataque = Integer.parseInt(datos[2]);
                int defensa = Integer.parseInt(datos[3]);
                int alcance = Integer.parseInt(datos[4]);
                personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

	public boolean personajesVacios() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
