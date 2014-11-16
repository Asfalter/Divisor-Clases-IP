package Ensayo;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Solucion: Divisor de direcciones IP segun su clase (A, B o C).
 * Autor: Pablo Castro
 * Fecha de ultima revision: 16/11/2014
 */

public class ClasesIP {
    public static void main(String[] args) {

        Scanner lector = new Scanner(System.in);

        //Los 3 arrays para las clases A,B y C respectivamente.
        String[] arrayClaseA = new String[500];
        String[] arrayClaseB = new String[500];
        String[] arrayClaseC = new String[500];

        //Contadores para la asignacion de valores en cada clase.
        int contadorA = 0;
        int contadorB = 0;
        int contadorC = 0;

        //Variable usada para guardar la direccion IP ingresada por el usuario.
        String direccionIP = "";

        //Array usado para dividir la direccion IP ingresada en 4 octetos, usando el metodo "split".
        String[] octetos;

        //Variables tipo int para guardar cada octeto con valores numericos y poder realizar las restricciones con mas facilidad.
        int primerOcteto = 0;
        int segundoOcteto = 0;
        int tercerOcteto = 0;
        int cuartoOcteto= 0;

        //Variable tipo boolean para el bucle while, el cual nos permite ingresar varias direcciones IP hasta que el usuario confirme que ha terminado.
        boolean finalizar = false;

        //Variable tipo boolean que para el segundo bucle while, el cual ejecuta las revisiones a la direccion IP ingresada por el usuario, para asegurarnos de que es una direccion IP valida.
        boolean correcto;

        while (finalizar == false)
        {
            correcto = false;
            while (correcto == false)
            {
                correcto = true;

                System.out.println("Ingrese una direccion IP: ");
                direccionIP = lector.next();

                //Dividimos la direccion IP en 4 octetos (Usamos los puntos dentro de la direccion para dividirla.).
                octetos = direccionIP.split("\\.");

                //Comprobamos que la direccion IP tenga efectivamente 4 octetos. En caso de que no los tenga, pedimos el reingreso de la misma.
                if (octetos.length == 4)
                {
                    //Ingresamos el bloque de codigo a intentar ejecutar
                    try {
                        //Convertimos los 4 octetos dentro del array a valores tipo int
                        primerOcteto = Integer.parseInt(octetos[0]);
                        segundoOcteto = Integer.parseInt(octetos[1]);
                        tercerOcteto = Integer.parseInt(octetos[2]);
                        cuartoOcteto = Integer.parseInt(octetos[3]);

                        //Realizamos todas las comprobaciones para asegurarnos que es una direccion IP valida.
                        if (primerOcteto == 127)
                        {
                            System.out.println("El valor del primer octeto no puede ser 127, este ID esta reservado para pruebas de conexion");
                            correcto = false;
                        }

                        if (primerOcteto > 223 || primerOcteto < 0)
                        {
                            System.out.println("El valor del primer octeto no puede ser mayor que 223 ni menor a 0");
                            System.out.println("Las unicas clases validas de IP son A, B y C");
                            correcto = false;
                        }

                        if (segundoOcteto > 255 || segundoOcteto < 0)
                        {
                            System.out.println("El valor del segundo octeto no puede ser mayor que 255 ni menor a 0");
                            correcto = false;
                        }

                        if (tercerOcteto > 255 || tercerOcteto < 0)
                        {
                            System.out.println("El valor del tercer octeto no puede ser mayor que 255 ni menor a 0");
                            correcto = false;
                        }

                        if (cuartoOcteto > 255 || cuartoOcteto < 0)
                        {
                            System.out.println("El valor del cuarto octeto no puede ser mayor que 255 ni menor a 0");
                            correcto = false;
                        }

                        if (primerOcteto == 0 && segundoOcteto == 0 && tercerOcteto == 0 && cuartoOcteto == 0)
                        {
                            System.out.println("La direccion IP no puede ser 0.0.0.0");
                            correcto = false;
                        }

                        if (primerOcteto == 255 && segundoOcteto == 255 && tercerOcteto == 255 && cuartoOcteto == 255)
                        {
                            System.out.println("La direccion IP no puede ser 255.255.255.255");
                            correcto = false;
                        }

                        //Restriccion personal, segun requerimientos del ensayo
                        if (primerOcteto == 192 && segundoOcteto == 168 && tercerOcteto == 0 && cuartoOcteto == 1)
                        {
                            System.out.println("La direccion IP no puede ser el gateway usado por su router");
                            correcto = false;
                        }
                        //Manejamos la unica excepcion posible dentro del bloque de codigo, que es causada por el ingreso de valores no numericos dentro de la direccion IP
                    } catch (NumberFormatException e){
                        System.out.println("Los octetos deben ser numericos");
                        correcto = false;
                    }
                }
                //En caso de que la direccion IP no contenga 4 octetos, repetimos el bucle para el reingreso de la misma.
                else{

                    System.out.println("Su direccion IP debe contener 4 octetos");
                    correcto = false;
                }

            }

            //Asignamos la direccion IP ingresada y validada a los distintos arrays. segun su clase.
            //NOTA: Las unicas clases que pueden ser ingresadas son las A, B y C. En caso de que sea una direccion tipo D o E, el validador nos lo dira y pedira el reingreso de la misma
            if (primerOcteto > 0 && primerOcteto < 127 )
            {
                arrayClaseA[contadorA] = direccionIP;
                contadorA++;
            }

            if (primerOcteto > 127 && primerOcteto < 192 )
            {
                arrayClaseB[contadorB] = direccionIP;
                contadorB++;
            }

            if (primerOcteto > 191 && primerOcteto < 224 )
            {
                arrayClaseC[contadorC] = direccionIP;
                contadorC++;
            }

            //Variable tipo boolean usada para el bucle while, el cual nos permite preguntar al usuario si desea continuar con el proceso de ingresar direcciones, o si ya ha termiado.
            boolean confirmacionFinalizar = true;

            while (confirmacionFinalizar == true)
            {
                System.out.println("¿Desea ingresar otra direccion? (S = Si, N = No)");
                String confirmacion = lector.next();

                if (confirmacion.equals("S") || confirmacion.equals("N"))
                {

                }
                else
                {
                    System.out.println("Confirmacion no valida, por favor ingrese una respuesta valida (S = Si, N = No)");
                }

                if (confirmacion.equals("S"))
                {
                    confirmacionFinalizar = false;

                }

                if (confirmacion.equals("N"))
                {
                    confirmacionFinalizar = false;
                    finalizar = true;

                }
            }
        }

        lector.close();



        //Imprimimos las direcciones subdivididas por clase en pantalla.
        System.out.println("Direcciones IP");
        System.out.println("----------------");
        System.out.println("Clase A");
        System.out.println("");

        for (int i = 0; i < contadorA; i++)
        {
            System.out.println(arrayClaseA[i]);
        }

        System.out.println("----------------");
        System.out.println("Clase B");
        System.out.println("");

        for (int i = 0; i < contadorB; i++)
        {
            System.out.println(arrayClaseB[i]);
        }

        System.out.println("----------------");
        System.out.println("Clase C");
        System.out.println("");

        for (int i = 0; i < contadorC; i++)
        {
            System.out.println(arrayClaseC[i]);
        }

        System.out.println("----------------");

    }
}

/*
 RUN
 Ingrese una direccion IP:
 0.0.0.0
 La direccion IP no puede ser 0.0.0.0
 Ingrese una direccion IP:
 127.5.3.4
 El valor del primer octeto no puede ser 127, este ID esta reservado para pruebas de conexion
 Ingrese una direccion IP:
 255.255.255.255
 El valor del primer octeto no puede ser mayor que 223 ni menor a 0
 Las unicas clases validas de IP son A, B y C
 La direccion IP no puede ser 255.255.255.255
 Ingrese una direccion IP:
 192.168.0.1
 La direccion IP no puede ser el gateway usado por su router
 Ingrese una direccion IP:
 1.2.A.4
 Los octetos deben ser numericos
 Ingrese una direccion IP:
 1.2.3.4.5
 Su direccion IP debe contener 4 octetos
 Ingrese una direccion IP:
 1.2.3
 Su direccion IP debe contener 4 octetos
 Ingrese una direccion IP:
 1.2.3.4
 ¿Desea ingresar otra direccion? (S = Si, N = No)
 S
 Ingrese una direccion IP:
 3.4.5.6
 ¿Desea ingresar otra direccion? (S = Si, N = No)
 S
 Ingrese una direccion IP:
 128.7.5.4
 ¿Desea ingresar otra direccion? (S = Si, N = No)
 S
 Ingrese una direccion IP:
 200.7.4.3
 ¿Desea ingresar otra direccion? (S = Si, N = No)
 N
 Direcciones IP
 ----------------
 Clase A

 1.2.3.4
 3.4.5.6
 ----------------
 Clase B

 128.7.5.4
 ----------------
 Clase C

 200.7.4.3
 ----------------

 Process finished with exit code 0
 */



