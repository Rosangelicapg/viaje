// importar librerias
import java.io.BufferedWriter;// espacio memoria escriba en otros archivos como word y ayuda a pasarlo al archicvo txt
import java.io.FileWriter; //crear archivos
import java.io.IOException; //alertass
import java.text.NumberFormat; // estados financieros
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale; // configurar a pesos colombianos
import java.util.Scanner; // teclado

public class viaje {


    public static void main (String[] args) {

        Scanner teclado = new Scanner(System.in);
          // crear una variable para configurarla en pesos colombianos
        NumberFormat formatoPesos = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-CO"));
        formatoPesos.setMinimumFractionDigits(0);
        formatoPesos.setMaximumFractionDigits(0);

        // variables constantes
        final int precio_MB = 285325; // precio de la mateta basica
        final int precio_MP = 325650; // precio maleta premiun

        // guardar datos del cliente 
        String nombre= "";
        String cedula="";
        String fechaViaje= "";
        String destino ="";
        String tipoMaleta = "";

        // variable paraa almacenar la cantidad de acompañantes o personas
        int acompañantes= 0;
        int totalPersonas= 0;
       
        //variable para guardar el valor de la maleta 
        // y para el calculo del viaje
        double valorMaleta=0;
        double valorViaje=0;

        // variable para guardar la fecha en que se registra el viaje
        String fechaRegistro="";

        // terminar el programa con la palabra vacaciones
        String palabraCierre="";

         // mensaje de bienvenida al sistema 
        System.out.println("BIENVENIDO A AVENTURA SOÑADA VIAJES");
        
        
        // COMENZAR EL CICLO REPETITIVO  WHILE 
         while (!palabraCierre.equalsIgnoreCase("vacaciones")){
            // menu principal del sistema 

            System.out.println("======AVENTURA SOÑADA VIAJES====");
            System.out.println("1.Registrar al cliente");
            System.out.println("2. Tipo de maleta");
            System.out.println("3.Acompañantes");
            System.out.println("4.Valor del viaje ");
            System.out.println("5.Visualizar toda la informacion");
            System.out.println("6. Respaldo TXT");
            System.out.println("7. Salir");
           
            // solicitar al vendedor seleccionar una opcion
             System.out.println("Seleccione una opcion (1-7)");
              
            
            // mostrar alerta si el usuario selecciona una opcion diferente a 1,2,3,4,5,6,7
            int opcion = teclado.nextInt();
            if (opcion!=1 && opcion!=2 && opcion!=3 && opcion!=4 && opcion!=5 && opcion!=6 && opcion!=7){
                System.out.println("OPCION INVALIDA, POR FAVOR ESCOJA UNA OPCION VALIDA");
            }
            
            // variable para controlar si la opción es válida
            boolean opcionValida = false; 

            // crear el switch case para las opciones del menu
            switch (opcion){

                case 1:
                    System.out.println("Ingrese el nombre del cliente:");
                    // ayuda de la IA
                    nombre = teclado.next();
                    
                    System.out.println("Ingrese la cédula del cliente:");
                    cedula= teclado.next();
                    // voy a hacer una validacion para que la cedula tenga 10 digitos
                    // y si el vendedor no cumple la validaccion voy a hacer un bucle
                    // para que el vendedor ingrese la cedula nuevamente

                    while (true) {
                    
                        if (cedula.length() == 10) { // el length me ayuda a comprobar que la cedula tenga 10 digitos
                            opcionValida = true; // la opción es válida si la cédula tiene 10 dígitos
                            break; // salir del bucle si la cédula tiene 10 dígitos
                        } 
                        // si la cedula no tiene 10 digitos le envio al cliente un mensaje
                        else{
                            System.out.println("Cédula inválida. Por favor ingrese una cédula de 10 dígitos:");
                            cedula=teclado.next();
                        } 
                    }
        
                     // sigo pidiendo los datos del cliente
                        System.out.println("Ingrese la fecha del viaje (dd/mm/aaaa):");
                        fechaViaje = teclado.next();
                    
                        System.out.println("Ingrese el destino del viaje:");
                        destino = teclado.next();
                    
                         // capturar fecha y hora
                         fechaRegistro = LocalDate.now() + " " + LocalTime.now();
                     break; // salir del switch case

                   case 2:

                    tipoMaleta = ingresarTipoMaleta(teclado);
                    valorMaleta = tipoMaleta.equalsIgnoreCase("MB") ? precio_MB : precio_MP;
                    break; // salir del switch case

                    case 3:

                        acompañantes = ingresarAcompanantes(teclado);
                        break;

                    case 4:
                        if (tipoMaleta.isEmpty()) { // el isEmpty me ayda a combrobar que el vendedor ya haya ingresado el tipo de maleta 
                                                    // para poder hacer el calculo
                            System.out.println("Primero seleccione el tipo de maleta en la opción 2.");
                            break;
                        }

                        // voy a darle un menesaje al vendedor por si no registra una cantidad de acompañantes
                        if (acompañantes <= 0) {
                            System.out.println("Primero registre la cantidad de acompañantes en la opción 3.");
                            break;
                        }

                        // El cliente también cuenta como una persona.
                        totalPersonas = acompañantes;
                        valorViaje = totalPersonas * valorMaleta;
                        System.out.println("Total de personas: " + totalPersonas);
                        System.out.println("Valor total del viaje: $"
                                + formatoPesos.format(valorViaje) + " COP");
                        break;
                    
                    case 5: // VISUALIZAR TODA LA INF
                    mostrarFactura(nombre, cedula, fechaViaje, destino, tipoMaleta,
                            acompañantes, totalPersonas, valorViaje, fechaRegistro, formatoPesos);
                    System.out.println("Escriba vacaciones para finalizar o seguir para ingresar nuevos datos:");
                     palabraCierre = teclado.next();
                    while (!palabraCierre.equalsIgnoreCase("vacaciones")
                            && !palabraCierre.equalsIgnoreCase("seguir")) {
                        System.out.println("Opción inválida. Escriba vacaciones o seguir:");
                        palabraCierre = teclado.next();
                    }
                    if (palabraCierre.equalsIgnoreCase("seguir")) {
                        tipoMaleta = ingresarTipoMaleta(teclado);
                        valorMaleta = tipoMaleta.equalsIgnoreCase("MB") ? precio_MB : precio_MP;
                        acompañantes = ingresarAcompanantes(teclado);
                        palabraCierre = "";
                    }
                    break;
                     

                     case 6: // crear archivo TXT
                      // definir la ruta de mi computador
                     try (BufferedWriter bw = new BufferedWriter(
                             new FileWriter("C:\\Users\\USUARIO\\Desktop\\archivo.txt", true))) {
        
                      bw.newLine();
                      bw.write ("\\");
                      bw.write("========== FACTURA ==========" + "\n");
                      bw.write("nombre:" + "\n" + nombre+ "\n" );
                      bw.write ("cedula:" + "\n"+ cedula + "\n");
                      bw.write ("fecha del viaje:" + "\n"+ fechaViaje + "\n");
                      bw.write("destino:" +"\n" + destino + "\n");
                      bw.write ("tipo de maleta seleccionada:" + "\n" + tipoMaleta+ "\n");
                      bw.write("acompañnantes:" + "\n" + acompañantes + "\n");
                      bw.write ("Valor total del viaje: $" + "\n"
                                + formatoPesos.format(valorViaje) + " COP" + "\n");
                      bw.write("Fecha de registro: " + "\n" + fechaRegistro + "\n");
                     }
                    catch (IOException Error) {
                      System.out.println("alerta varificar respaldo" + Error.getMessage());
                      }
                      break;                                                                                                                        

                      case 7:
                      mostrarFactura(nombre, cedula, fechaViaje, destino, tipoMaleta,
                              acompañantes, totalPersonas, valorViaje, fechaRegistro, formatoPesos);
                      palabraCierre = "vacaciones";
                      break;
                

         }
         }
         
         teclado.close();
                  
}

    private static void mostrarFactura(String nombre, String cedula, String fechaViaje,
            String destino, String tipoMaleta, int acompañantes, int totalPersonas,
            double valorViaje, String fechaRegistro, NumberFormat formatoPesos) {
        System.out.println("======= INFORMACIÓN DEL VIAJE =======");
        System.out.println("Nombre del cliente: " + nombre);
        System.out.println("Cédula: " + cedula);
        System.out.println("Fecha del viaje: " + fechaViaje);
        System.out.println("Destino: " + destino);
        System.out.println("Tipo de maleta: " + tipoMaleta);
        System.out.println("Acompañantes: " + acompañantes);
        System.out.println("Total de personas: " + totalPersonas);
        System.out.println("Valor total del viaje: $"
                + formatoPesos.format(valorViaje) + " COP");
        System.out.println("Fecha de registro: " + fechaRegistro);
    }

    private static String ingresarTipoMaleta(Scanner teclado) {
        System.out.println("MB maleta basica: $285325 COP");
        System.out.println("MP maleta premium: $325650 COP");
        System.out.println("Seleccione el tipo de maleta MB o MP:");
        String tipoMaleta = teclado.next();

        while (!tipoMaleta.equalsIgnoreCase("MB") && !tipoMaleta.equalsIgnoreCase("MP")) {
            System.out.println("Tipo de maleta inválido. Por favor ingrese MB o MP:");
            tipoMaleta = teclado.next();
        }
        return tipoMaleta;
    }

    private static int ingresarAcompanantes(Scanner teclado) {
        System.out.println("Ingrese la cantidad de acompañantes:");
        int acompañantes = teclado.nextInt();

        while (acompañantes <= 0) {
            System.out.println("Cantidad inválida. Ingrese un número mayor a cero:");
            acompañantes = teclado.nextInt();
        }
        return acompañantes;
    }
}
