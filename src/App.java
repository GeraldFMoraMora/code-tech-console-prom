import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;

import model.entity.Customer;
import model.service.ConnectionService;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenido al administrador de clientes!");

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
                    System.out.print("\nDigite el número de cédula: ");
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
                    System.out.print("\nDigite el número de cédula: ");
                    String idDelUser = scanner.nextLine();

                    connectionService.delClient(idDelUser);
                    
                    break;
                case 3:
                    System.out.print("\nDigite el número de cédula del usuario a actualizar: ");
                    String idUpdateUser = scanner.nextLine();

                    JSONObject jsonRes = new JSONObject(connectionService.getClientById(idUpdateUser));

                    System.out.println("Estos son los datos actuales del usuario:\n"
                    +"Identificación: "+jsonRes.getString("id")
                    +",Nombre: "+jsonRes.getString("firstname")
                    +", Apellido: "+jsonRes.getString("lastname")
                    +", Celular: "+jsonRes.getString("phone")
                    +", Fecha de nacimiento: "+jsonRes.getString("birthdate")+"\n"); 

                    System.out.println("A continuación se le consultará los nuevos datos.");
                    System.out.println("NOTA: Si desea mantener el dato solo presiona Enter para continuar");

                    System.out.print("Digite el número de cédula nuevo: ");
                    String idUpdate = scanner.nextLine();
                    if(idUpdate.equals("")){idUpdate=jsonRes.getString("id");}

                    System.out.print("Digite el nuevo nombre a actualizar: ");
                    String firstNameUpdate = scanner.nextLine();
                    if(firstNameUpdate.equals("")){idUpdate=jsonRes.getString("firstname");}

                    System.out.print("Digite el apellido a actualizar: ");
                    String lastNameUpdate = scanner.nextLine();
                    if(lastNameUpdate.equals("")){idUpdate=jsonRes.getString("lastname");}

                    System.out.print("Digite el número telefónico a actualizar: ");
                    String phoneUpdate = scanner.nextLine();
                    if(phoneUpdate.equals("")){idUpdate=jsonRes.getString("phone");}

                    System.out.print("Digite la fecha de nacimiento en el siguiente formato Día-Mes-Año para actualizar: ");
                    String birthdateStringUpdate = scanner.nextLine();
                    if(birthdateStringUpdate.equals("")){idUpdate=jsonRes.getString("birthdate");}

                    SimpleDateFormat dateFormatUpdate = new SimpleDateFormat("dd-MM-yyyy");
                    Date birthdateUpdate;

                    try {
                        birthdateUpdate = dateFormatUpdate.parse(birthdateStringUpdate);
                    } catch (ParseException e) {
                        System.out.println("Error al convertir la fecha. Asegúrese de seguir el formato especificado.");
                        continue;
                    }

                    Customer customerUpdate = new Customer(idUpdate, firstNameUpdate, lastNameUpdate, phoneUpdate, birthdateUpdate);

                    connectionService.updateClient(customerUpdate);
                    
                    break;
                case 4:
                    System.out.print("\nDigite el número de cédula: ");
                    String idGetUser = scanner.nextLine();

                    connectionService.getClientById(idGetUser);
                    
                    break;
                case 5:
                    int optionFilter;
                    System.out.println("\nComo desea filtrar su busqueda:");
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
                    System.out.println("Error: Opción invalida, ingrese una correcta.");
                    
                    break;
            }

        } while (option !=0);
    }
}
