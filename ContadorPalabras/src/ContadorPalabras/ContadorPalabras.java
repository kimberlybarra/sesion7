package ContadorPalabras;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class ContadorPalabras {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar un archivo de texto");

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToRead = fileChooser.getSelectedFile();
            try {
                procesarArchivo(fileToRead);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún archivo.");
        }
    }

    private static void procesarArchivo(File archivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String line;
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;
        Map<String, Integer> conteoPalabras = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            totalLineas++;
            totalCaracteres += line.length(); // Contar caracteres de la línea

            String[] palabras = line.split("\\W+"); // Divide la línea en palabras usando expresiones regulares
            totalPalabras += palabras.length;

            for (String palabra : palabras) {
                if (Character.isLetterOrDigit(palabra.charAt(0))) { // Verifica si es una palabra válida
                    conteoPalabras.put(palabra.toLowerCase(), conteoPalabras.getOrDefault(palabra.toLowerCase(), 0) + 1);
                }
            }
        }
        reader.close();

        mostrarResultados(totalLineas, totalPalabras, totalCaracteres, conteoPalabras);
    }

    private static void mostrarResultados(int totalLineas, int totalPalabras, int totalCaracteres, Map<String, Integer> conteoPalabras) {
        System.out.println("Total de líneas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Total de caracteres (sin contar el final de línea): " + totalCaracteres);

        if (totalLineas > 0) {
            double promedioPalabrasPorLinea = (double) totalPalabras / totalLineas;
            System.out.println("Promedio de palabras por línea: " + promedioPalabrasPorLinea);
        } else {
            System.out.println("No hay líneas para calcular el promedio.");
        }

        // Encontrar las palabras más frecuentes
        int maxFrecuencia = Collections.max(conteoPalabras.values());
        System.out.println("Palabras más frecuentes:");
        for (Map.Entry<String, Integer> entrada : conteoPalabras.entrySet()) {
            if (entrada.getValue() == maxFrecuencia) {
                System.out.println(entrada.getKey() + ": " + entrada.getValue());
            }
        }
    }
}
