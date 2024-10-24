package Juego;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n----- Menú -----");
            System.out.println("1. Agregar personaje");
            System.out.println("2. Modificar personaje");
            System.out.println("3. Eliminar personaje");
            System.out.println("4. Mostrar personajes");
            System.out.println("5. Mostrar estadísticas");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
            case 1:
                System.out.print("Ingresa el nombre del personaje: ");
                String nombre = scanner.nextLine();

                if (gestor.existePersonaje(nombre)) {
                    System.out.println("El personaje ya existe.");
                } else {
                    System.out.print("Ingresa la vida del personaje: ");
                    int vida = scanner.nextInt();
                    System.out.print("Ingresa el ataque del personaje: ");
                    int ataque = scanner.nextInt();
                    System.out.print("Ingresa la defensa del personaje: ");
                    int defensa = scanner.nextInt();
                    System.out.print("Ingresa el alcance del personaje: ");
                    int alcance = scanner.nextInt();
                    scanner.nextLine();  

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
                    System.out.print("Ingresa el nombre del personaje a modificar: ");
                    String nombreModificar = scanner.nextLine();
                    if (gestor.existePersonaje(nombreModificar)) {
                        String atributo;
                        while (true) {
                            System.out.print("Ingresa el atributo a modificar (vida, ataque, defensa, alcance): ");
                            atributo = scanner.nextLine();
                            
                            if (atributo.equals("vida") || atributo.equals("ataque") || atributo.equals("defensa") || atributo.equals("alcance")) {
                                break;  
                            } else {
                                System.out.println(" ");
                            }
                        }
                        
                        System.out.print("Ingresa el nuevo valor de " + atributo + ": ");
                        int nuevoValor = scanner.nextInt();
                        scanner.nextLine(); 

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
        } while (opcion != 5);

        scanner.close();
    }
}
