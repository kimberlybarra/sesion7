package Juego;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void modificarAtributoPersonaje(String nombre, String atributo, int valor) {
        Personaje personaje = obtenerPersonaje(nombre);
        if (personaje != null) {
            switch (atributo.toLowerCase()) {
                case "vida":
                    personaje.setVida(valor);
                    break;
                case "ataque":
                    personaje.setAtaque(valor);
                    break;
                case "defensa":
                    personaje.setDefensa(valor);
                    break;
                case "alcance":
                    personaje.setAlcance(valor);
                    break;
                default:
                    System.out.println("Atributo no válido, ingresa uno de los siguientes: vida, ataque, defensa, alcance.");
                    return;  
            }
            guardarArchivo();
            System.out.println("Atributo actualizado.");
        } else {
            System.out.println("El personaje no existe.");
        }
    }
    
    public void filtrarPersonajesPorAtributo(String atributo) {
        List<Personaje> personajesOrdenados = personajes.stream()
            .sorted((p1, p2) -> {
                switch (atributo.toLowerCase()) {
                    case "vida":
                        return Integer.compare(p2.getVida(), p1.getVida());
                    case "ataque":
                        return Integer.compare(p2.getAtaque(), p1.getAtaque());
                    case "defensa":
                        return Integer.compare(p2.getDefensa(), p1.getDefensa());
                    case "alcance":
                        return Integer.compare(p2.getAlcance(), p1.getAlcance());
                    default:
                        System.out.println("Atributo no válido.");
                        return 0;
                }
            })
            .collect(Collectors.toList());

        for (Personaje p : personajesOrdenados) {
            System.out.println(p);
        }
    }
    
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para mostrar estadísticas.");
            return;
        }

        int totalPersonajes = personajes.size();
        int sumaVida = 0, sumaAtaque = 0, sumaDefensa = 0, sumaAlcance = 0;

        for (Personaje p : personajes) {
            sumaVida += p.getVida();
            sumaAtaque += p.getAtaque();
            sumaDefensa += p.getDefensa();
            sumaAlcance += p.getAlcance();
        }

        double promedioVida = (double) sumaVida / totalPersonajes;
        double promedioAtaque = (double) sumaAtaque / totalPersonajes;
        double promedioDefensa = (double) sumaDefensa / totalPersonajes;
        double promedioAlcance = (double) sumaAlcance / totalPersonajes;

        System.out.println("\n--- Estadísticas de Personajes ---");
        System.out.println("Total de personajes: " + totalPersonajes);
        System.out.printf("Promedio de Vida: %.2f%n", promedioVida);
        System.out.printf("Promedio de Ataque: %.2f%n", promedioAtaque);
        System.out.printf("Promedio de Defensa: %.2f%n", promedioDefensa);
        System.out.printf("Promedio de Alcance: %.2f%n", promedioAlcance);
    }

	public boolean personajesVacios() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
