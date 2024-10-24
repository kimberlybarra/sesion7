package controlador;

import modelo.Empleado;
import java.io.*;
import java.util.*;

public class ControladorEmpleado {
    private String archivo = "empleados.txt";
    private List<Empleado> empleados;

    public ControladorEmpleado() {
        empleados = new ArrayList<>();
        leerEmpleados();
    }

    // Leer empleados desde el archivo
    public void leerEmpleados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int numero = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double sueldo = Double.parseDouble(datos[2]);
                empleados.add(new Empleado(numero, nombre, sueldo));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de los datos en el archivo: " + e.getMessage());
        }
    }

    // Guardar empleados en el archivo
    private void guardarEmpleados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Empleado emp : empleados) {
                writer.println(emp.getNumero() + "," + emp.getNombre() + "," + emp.getSueldo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Listar empleados
    public void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados.");
        } else {
            for (Empleado emp : empleados) {
                System.out.println(emp);
            }
        }
    }

    // Agregar un nuevo empleado con validación de número duplicado y sueldo válido
    public void agregarEmpleado(Empleado nuevoEmpleado) {
        if (buscarEmpleado(nuevoEmpleado.getNumero()) == null) {
            if (nuevoEmpleado.getSueldo() > 0) {
                empleados.add(nuevoEmpleado);
                guardarEmpleados();
            } else {
                System.out.println("Error: El sueldo debe ser positivo.");
            }
        } else {
            System.out.println("Error: Ya existe un empleado con ese número.");
        }
    }

    // Buscar empleado por número
    public Empleado buscarEmpleado(int numero) {
        for (Empleado emp : empleados) {
            if (emp.getNumero() == numero) {
                return emp;
            }
        }
        return null;
    }

 // Eliminar empleado por número
    public boolean eliminarEmpleado(int numero) {
        Empleado emp = buscarEmpleado(numero);
        if (emp != null) {
            empleados.remove(emp);
            guardarEmpleados();
            return true; // Retornar true si se eliminó correctamente
        } else {
            System.out.println("Empleado no encontrado.");
            return false; // Retornar false si no se encontró
        }
    }

}
