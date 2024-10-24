package vista;

import controlador.ControladorEmpleado;
import modelo.Empleado;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VistaEmpleado {
    private ControladorEmpleado controlador;
    private Scanner scanner;

    public VistaEmpleado() {
        controlador = new ControladorEmpleado();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Menú Empleados ---");
            System.out.println("1. Listar todos los empleados");
            System.out.println("2. Agregar un nuevo empleado");
            System.out.println("3. Buscar un empleado por número");
            System.out.println("4. Eliminar un empleado por número");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    listarEmpleados();
                    break;
                case 2:
                    agregarEmpleado();
                    break;
                case 3:
                    buscarEmpleado();
                    break;
                case 4:
                    eliminarEmpleado();  // Eliminamos la lista aquí para evitar duplicados
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void listarEmpleados() {
        controlador.listarEmpleados(); 
    }

    private void agregarEmpleado() {
        try {
            System.out.print("Ingrese el número del empleado: ");
            int numero = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el nombre del empleado: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el sueldo del empleado: ");
            double sueldo = scanner.nextDouble();
            scanner.nextLine();

            if (sueldo <= 0) {
                System.out.println("Error: El sueldo debe ser mayor que cero.");
                return;
            }

            Empleado nuevoEmpleado = new Empleado(numero, nombre, sueldo);
            controlador.agregarEmpleado(nuevoEmpleado);

            System.out.println("Empleado agregado exitosamente. Datos del nuevo empleado:");
            System.out.println(nuevoEmpleado.toString());
            listarEmpleados(); // Mostrar la lista después de agregar
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida, por favor ingrese datos correctos.");
            scanner.nextLine();
        }
    }

    private void buscarEmpleado() {
        try {
            System.out.print("Ingrese el número del empleado que desea buscar: ");
            int numero = scanner.nextInt();
            scanner.nextLine();
            Empleado emp = controlador.buscarEmpleado(numero);
            if (emp != null) {
                System.out.println(emp);
            } else {
                System.out.println("Empleado no encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: El número de empleado debe ser un número entero.");
            scanner.nextLine();  // Limpiar el buffer
        }
    }

    private void eliminarEmpleado() {
        try {
            System.out.print("Ingrese el número del empleado que desea eliminar: ");
            int numero = scanner.nextInt();
            scanner.nextLine();
            
            // Guardamos el resultado de la eliminación
            boolean eliminado = controlador.eliminarEmpleado(numero);
            if (eliminado) {
                System.out.println("Empleado eliminado.");
                listarEmpleados(); // Mostrar la lista después de eliminar
            } else {
                System.out.println("No se encontró un empleado con ese número.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: El número de empleado debe ser un número entero.");
            scanner.nextLine();  // Limpiar el buffer
        }
    }
}
