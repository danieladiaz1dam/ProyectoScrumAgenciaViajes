package agencia;

import java.util.Scanner;

import exceptions.InvalidFechaException;
import exceptions.InvalidLugarException;
import exceptions.InvalidPrecioException;

public class App {

    static final String RUTA = "src/Agencia.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Agencia agencia = new Agencia(RUTA);

        // Variables
        // Entero para llamar a la opcion que quiere
        int opcion;
        // variable que guarda el precio del viaje
        double precio = 0;
        // variable que gusarda el lugar del viaje
        String lugar = "";
        // variable que guarda la fecha del viaje
        String fecha = "";

        // bucle para el menu de opciones que se sale cuando la opcion sea distinta a 6
        do {
            // funcion que muestra el menu de opciones
            mostrarMenu();
            // Scanner de la variable opciones
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar el buffer

            // Switch donde se ven las opciones del menu
            switch (opcion) {
                case 1:
                    System.out.println("Esto son los viajes disponibles que tenemos --> ");
                    System.out.println();
                    agencia.verViajesDisponibles();
                    break;
                case 2:
                    System.out.print("Introduce el lugar donde te gustaría viajar: ");
                    lugar = sc.nextLine();
                    System.out.print("Introduce la fecha, (en formato DD/MM/YYYY): ");
                    fecha = sc.nextLine();
                    System.out.print("Introduce el precio del viaje: ");
                    precio = sc.nextDouble();
                    sc.nextLine(); // limpiar el buffer

                    try {
                        agencia.agregarViaje(new Viaje(lugar, fecha, precio));
                        System.out.println("Viaje añadido correctamente.");
                    } catch (InvalidLugarException | InvalidFechaException | InvalidPrecioException e) {
                        System.out.println("Error al añadir el viaje: " + e);
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el lugar del viaje que desea modificar:");
                    lugar = sc.nextLine();
                    System.out.print("Introduce la nueva fecha, (en formato DD/MM/YYYY): ");
                    fecha = sc.nextLine();
                    System.out.print("Introduce el nuevo precio del viaje: ");
                    precio = sc.nextDouble();
                    sc.nextLine(); // limpiar el buffer

                    if (agencia.modificarViaje(lugar, fecha, precio)) {
                        System.out.println("Viaje modificado correctamente.");
                    } else {
                        System.out.println("No se encontró el viaje para modificar.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el lugar del viaje que desea eliminar:");
                    lugar = sc.nextLine();

                    if (agencia.eliminarViaje(lugar)) {
                        System.out.println("Viaje eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró el viaje para eliminar.");
                    }
                    break;
                case 5:
                    agencia.guardarViajes();
                    System.out.println("Cambios guardados correctamente.");
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        } while (opcion != 6);

        // Cierre del Scanner
        sc.close();
    }

    // Funcion del menu
    static void mostrarMenu() {
        System.out.println("Menú de Gestión de Viajes");
        System.out.println("1. Ver listado de todos los viajes disponibles");
        System.out.println("2. Añadir un nuevo viaje");
        System.out.println("3. Modificar el precio o la fecha de un viaje existente");
        System.out.println("4. Eliminar un viaje existente");
        System.out.println("5. Guardar los cambios en un archivo de texto");
        System.out.println("6. Salir del programa");
    }
}