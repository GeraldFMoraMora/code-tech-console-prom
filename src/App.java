import java.util.Scanner;

import model.service.CustomerService;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        CustomerService customerService = new CustomerService();

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nMenú de navegación:");
            System.out.println("1. Crear cliente");
            System.out.println("2. Eliminar cliente");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Obtener cliente por ID");
            System.out.println("5. Listar clientes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                System.out.println("Opción 1: Crear cliente");
                    
                    break;
                case 2:
                System.out.println("Opción 2: Eliminar cliente");
                    
                    break;
                case 3:
                System.out.println("Opción 3: Actualizar cliente");
                    
                    break;
                case 4:
                System.out.println("Opción 4: Obtener cliente por ID");
                    
                    break;
                case 5:
                System.out.println("Opción 5: Listar clientes");
                    
                    break;
                case 0:
                System.out.println("Saliendo del programa. ¡Hasta luego!");
                    
                    break;
                default:
                    break;
            }

        } while (option !=0);
    }
}
