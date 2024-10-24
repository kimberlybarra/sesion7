package Juego;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n----- Menú -----");
            System.out.println("1. Agregar personaje");
            System.out.println("2. Modificar personaje");
            System.out.println("3. Eliminar personaje");
            System.out.println("4. Mostrar personajes");
            System.out.println("5. Mostrar estadísticas");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            // Validación para asegurarse de que la opción ingresada sea un número entero.
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número.");
                scanner.nextLine(); // Limpiar el scanner
                continue;
            }

            switch (opcion) {
            case 1:
                String nombre;
                while (true) {
                    System.out.print("Ingresa el nombre del personaje (solo letras): ");
                    nombre = scanner.nextLine();

                    // Valida que el nombre solo contenga letras y espacios
                    if (Pattern.matches("[a-zA-Z ]+", nombre)) {
                        break;  // Sale del bucle si el nombre es válido
                    } else {
                        System.out.println("Error: El nombre solo puede contener letras y espacios.");
                    }
                }

                if (gestor.existePersonaje(nombre)) {
                    System.out.println("El personaje ya existe.");
                } else {
                    int vida = ingresarAtributoNumerico(scanner, "vida");
                    int ataque = ingresarAtributoNumerico(scanner, "ataque");
                    int defensa = ingresarAtributoNumerico(scanner, "defensa");
                    int alcance = ingresarAtributoNumerico(scanner, "alcance");

                    Personaje personaje = new Personaje(nombre, vida, ataque, defensa, alcance);
                    gestor.agregarPersonaje(personaje);

                    System.out.println("\nPersonaje agregado:");
                    System.out.println("Nombre del personaje: " + personaje.getNombre());
                    System.out.println("Vida: " + personaje.getVida());
                    System.out.println("Ataque: " + personaje.getAtaque());
                    System.out.println("Defensa: " + personaje.getDefensa());
                    System.out.println("Alcance: " + personaje.getAlcance());
                }
                break;

                case 2:
                    if (gestor.personajesVacios()) {
                        System.out.println("No hay personajes para modificar.");
                    } else {
                        System.out.println("Personajes disponibles:");
                        gestor.mostrarPersonajes();  // Muestra los personajes existentes

                        System.out.print("Ingresa el nombre del personaje a modificar: ");
                        String nombreModificar = scanner.nextLine();

                        if (gestor.existePersonaje(nombreModificar)) {
                            String atributo;
                            while (true) {
                                System.out.print("Ingresa el atributo a modificar (vida, ataque, defensa, alcance): ");
                                atributo = scanner.nextLine();

                                if (atributo.equals("vida") || atributo.equals("ataque") ||
                                    atributo.equals("defensa") || atributo.equals("alcance")) {
                                    break;
                                } else {
                                    System.out.println("Atributo no válido. Por favor, ingresa uno de los siguientes: vida, ataque, defensa, alcance.");
                                }
                            }

                            int nuevoValor = ingresarAtributoNumerico(scanner, atributo);
                            gestor.modificarAtributoPersonaje(nombreModificar, atributo, nuevoValor);

                        } else {
                            System.out.println("El personaje no existe.");
                        }
                    }
                    break;

                case 3:
                    if (gestor.personajesVacios()) {
                        System.out.println("No hay personajes para eliminar.");
                    } else {
                        System.out.println("Personajes disponibles:");
                        gestor.mostrarPersonajes(); // Muestra los personajes existentes

                        System.out.print("Ingresa el nombre del personaje a eliminar: ");
                        String nombreEliminar = scanner.nextLine();
                        gestor.eliminarPersonaje(nombreEliminar);
                    }
                    break;

                case 4:
                    if (gestor.personajesVacios()) {
                        System.out.println("No hay personajes para mostrar.");
                    } else {
                        String atributoFiltrar;
                        while (true) {
                            System.out.print("Ingresa el atributo por el que quieres filtrar (vida, ataque, defensa, alcance): ");
                            atributoFiltrar = scanner.nextLine().toLowerCase(); // Convertir a minúsculas para una comparación flexible

                            if (atributoFiltrar.equals("vida") || atributoFiltrar.equals("ataque") ||
                                atributoFiltrar.equals("defensa") || atributoFiltrar.equals("alcance")) {
                                break;
                            } else {
                                System.out.println("Atributo no válido. Por favor, ingresa un atributo correcto.");
                            }
                        }

                        System.out.println("Personajes ordenados por " + atributoFiltrar + ":");
                        gestor.filtrarPersonajesPorAtributo(atributoFiltrar);
                    }
                    break;

                case 5:
                    if (gestor.personajesVacios()) {
                        System.out.println("No hay personajes para mostrar estadísticas.");
                    } else {
                        gestor.mostrarEstadisticas();  // Llama al método para mostrar estadísticas
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del programa");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }

    // Método auxiliar para pedir y validar atributos numéricos
    private static int ingresarAtributoNumerico(Scanner scanner, String atributo) {
        int valor = 0;
        while (true) {
            System.out.print("Ingresa el valor para " + atributo + ": ");
            try {
                valor = scanner.nextInt();
                if (valor < 0) {
                    System.out.println("Error: El valor no puede ser negativo.");
                } else {
                    scanner.nextLine(); // Limpia el buffer
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número.");
                scanner.nextLine(); // Limpiar el scanner
            }
        }
        return valor;
    }
}
