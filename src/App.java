import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entity.Customer;
import model.service.ConnectionService;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        ConnectionService connectionService = new ConnectionService();

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
                    System.out.println("\n");

                    System.out.print("Digite el número de cédula: ");
                    String id = scanner.nextLine();

                    System.out.print("Digite el nombre: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Digite el apellido: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Digite el número telefónico: ");
                    String phone = scanner.nextLine();

                    System.out.print("Digite la fecha de nacimiento en el siguiente formato Día-Mes-Año: ");
                    String birthdateString = scanner.nextLine();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date birthdate;

                    try {
                        birthdate = dateFormat.parse(birthdateString);
                    } catch (ParseException e) {
                        System.out.println("Error al convertir la fecha. Asegúrese de seguir el formato especificado.");
                        continue;
                    }

                    Customer customer = new Customer(id, firstName, lastName, phone, birthdate);

                    connectionService.addClient(customer);
                    
                    break;
                case 2:
                System.out.println("Opción 2: Eliminar cliente");
                    
                    break;
                case 3:
                System.out.println("Opción 3: Actualizar cliente");
                    
                    break;
                case 4:
                    System.out.println("\n");

                    System.out.print("Digite el número de cédula: ");
                    String idUser = scanner.nextLine();

                    connectionService.getClientById(idUser);
                    
                    break;
                case 5:
                    int optionFilter;
                    System.out.println("\n");
                    System.out.println("Como desea filtrar su busqueda:");
                    System.out.println("1. Fecha de nacimiento descendente");
                    System.out.println("2. Id");
                    System.out.println("3. Nombre de manera ascendente");
                    System.out.println("0. Regresar");
                    System.out.print("Seleccione una opción: ");

                    optionFilter = scanner.nextInt();
                    scanner.nextLine();
                    
                    if ((optionFilter == 0) || (optionFilter>3)){
                        continue;
                    } else{
                        connectionService.getClientsByFilters(optionFilter);
                    }   

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
