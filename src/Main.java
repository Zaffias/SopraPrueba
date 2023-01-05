import java.sql.Array;
import java.util.*;

public class Main {
    // Entiendo LEER NUMERO como coger un numero desde el STDIN.

    public static void main(String[] args) {
        /**
         * EJERCICIO NÚMERO PAR O IMPAR
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("**************** EJERCICIO PAR E IMPAR ****************");
        int number;
        // Si lo introducido no es numérico entramos en el catch
        try {
            // Leemos el número desde el STDIN
            System.out.println("Introduce un número");
            number = scanner.nextInt();
            // Printamos el numero con el metodo printNumbers de la clase Impares.
            Impares impares = new Impares();
            impares.printNumbers(number);
        }catch (InputMismatchException inputMismatchException) {
            System.out.println(inputMismatchException.getMessage());
        }

        /**
         * EJERCICIO PERSONAS
         */
        System.out.println("**************** EJERCICIO PERSONAS ****************");
        Persona[] personas = new Persona[50];
        Random random = new Random();
        for(int i = 0; i < personas.length; i++){
            personas[i] = new Persona(random.nextInt(100), random.nextBoolean());
        }

        Persona.clasificacionPersonas(personas);

        /**
         * EJERCICIO SUELDO
         */
        System.out.println("********** EJERCICIO CALCULAR SUELDO **********");
        double tarifa;
        int horas;
        try {
            // Leemos el número desde el STDIN
            System.out.println("Introduce la tarifa");
            tarifa = scanner.nextDouble();
            System.out.println("Introduce las horas");
            horas = scanner.nextInt();
            System.out.println("El sueldo total es: " + CalculadoraSueldo.calcularSueldoTotal(tarifa, horas) + "€");
        }catch (InputMismatchException inputMismatchException) {
            System.out.println(inputMismatchException.getMessage());
        }



    }
}

/**
 * Clase para ejercicio de números pares e impares
 */
class Impares {
    // Función que devuelve un booleano dependiendo de si el número es par o impar
    private boolean isEven(int num){
        if(num % 2 == 0)
            return true;
        return false;
    }

    // Supongo que el cero está incluido dentro de los pares
    public void printNumbers(int num){
        // Solo manejamos los números positivos.
        if (num < 0) return;

        String oddOrEven = isEven(num) ? "Es par" : "Es impar";
        System.out.println(oddOrEven);
        while (num >= 0){
            System.out.println(num);
            num -= 2;
        }
    }
}


/**
 * Clase para ejercicio de contar personas segun sexo y edad
 */
class Persona{
    private int edad;
    // False corresponde a hombre, true a mujer
    private boolean sexo;

    public Persona(int edad, boolean sexo){
        if(edad < 0) throw new RuntimeException("La edad tiene que ser 0 o más");
        this.edad = edad;
        this.sexo = sexo;
    }

    public static void clasificacionPersonas(Persona[] personas){
        int mayoresEdad = 0;
        int menoresEdad = 0;
        int totalHombres = 0;
        int totalMujeres = 0;
        int mayoresEdadMasculino = 0;
        int menoresEdadFemenina = 0;
        float porcentajeMayoresDeEdad;
        float porcentajeMujeres;

        for(Persona persona : personas){
            if (persona.edad >= 18){
                mayoresEdad++;
                if(!persona.sexo)
                    mayoresEdadMasculino++;
            }
            if(persona.edad < 18){
                menoresEdad++;
                if (persona.sexo)
                    menoresEdadFemenina++;
            }

            if (persona.sexo)
                totalMujeres++;

            if (!persona.sexo)
                totalHombres++;
        }
        porcentajeMayoresDeEdad = (float) mayoresEdad / (mayoresEdad + menoresEdad) * 100;
        porcentajeMujeres = (float) totalMujeres / (totalMujeres + totalHombres) * 100;

        System.out.println("Hay un total de " + mayoresEdad + " mayores de edad" +
                "\n" + "Hay un total de " + menoresEdad + " menores de edad" +
                "\n" + "De todas las mujeres " + menoresEdadFemenina + " son menores de edad" +
                "\n" + "De todas los hombres " + mayoresEdadMasculino + " son mayores de edad" +
                "\n" + "El porcentaje de personas mayores de edad sobre el total es de: " + porcentajeMayoresDeEdad + "%" +
                "\n" + "El porcentaje de mujeres sobre el total de personas es de: " + porcentajeMujeres +"%");
    }
}

/**
 * Clase para calcular el sueldo.
 */

class CalculadoraSueldo{

    public static double calcularSueldoTotal(double tarifa, int horas){
        if(tarifa < 0 || horas < 0) throw new RuntimeException("Horas y tarifa tienen que ser 0 o más");
        final int HORAS = 40;
        final double EXTRA = 1.5;

        int horasExtra;
        double tarifaExtra = 0;

        if(horas > 40) {
            horasExtra = horas - HORAS;
            tarifaExtra = (tarifa * EXTRA) * horasExtra;
        }
        return tarifa * horas + tarifaExtra;
    }
}