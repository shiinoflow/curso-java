public class BusquedaNumeros {
    public static void main(String[] args) {
        System.out.println("=== Método 1: Variables individuales (Ineficiente) ===");
        metodoIndividual();
        
        System.out.println("\n=== Método 2: Arreglos (Eficiente) ===");
        metodoArreglo();
    }

    /*
     * Enunciado 1 (Variables Independientes):
     * Declarar 20 variables enteras con valores incrementales de 5 en 5 (5, 10, 15...).
     * Buscar si alguna variable contiene el valor objetivo (60) utilizando estructuras 
     * condicionales if-else if secuenciales para validar cada variable una por una.
     */
    public static void metodoIndividual() {
        int numero = 5;
        int numero2 = 10;
        int numero3 = 15;
        int numero4 = 20;
        int numero5 = 25;
        int numero6 = 30;
        int numero7 = 35;
        int numero8 = 40;
        int numero9 = 45;
        int numero10 = 50;
        int numero11 = 55;
        int numero12 = 60;
        int numero13 = 65;
        int numero14 = 70;
        int numero15 = 75;
        int numero16 = 80;
        int numero17 = 85;
        int numero18 = 90;
        int numero19 = 95;
        int numero20 = 100;

        int objetivo = 60;

        if (numero == objetivo) {
            System.out.println("Encontrado en numero: " + numero);
        } else if (numero2 == objetivo) {
            System.out.println("Encontrado en numero2: " + numero2);
        } else if (numero3 == objetivo) {
            System.out.println("Encontrado en numero3: " + numero3);
        } else if (numero4 == objetivo) {
            System.out.println("Encontrado en numero4: " + numero4);
        } else if (numero5 == objetivo) {
            System.out.println("Encontrado en numero5: " + numero5);
        } else if (numero6 == objetivo) {
            System.out.println("Encontrado en numero6: " + numero6);
        } else if (numero7 == objetivo) {
            System.out.println("Encontrado en numero7: " + numero7);
        } else if (numero8 == objetivo) {
            System.out.println("Encontrado en numero8: " + numero8);
        } else if (numero9 == objetivo) {
            System.out.println("Encontrado en numero9: " + numero9);
        } else if (numero10 == objetivo) {
            System.out.println("Encontrado en numero10: " + numero10);
        } else if (numero11 == objetivo) {
            System.out.println("Encontrado en numero11: " + numero11);
        } else if (numero12 == objetivo) {
            System.out.println("Encontrado en numero12: " + numero12);
        } else if (numero13 == objetivo) {
            System.out.println("Encontrado en numero13: " + numero13);
        } else if (numero14 == objetivo) {
            System.out.println("Encontrado en numero14: " + numero14);
        } else if (numero15 == objetivo) {
            System.out.println("Encontrado en numero15: " + numero15);
        } else if (numero16 == objetivo) {
            System.out.println("Encontrado en numero16: " + numero16);
        } else if (numero17 == objetivo) {
            System.out.println("Encontrado en numero17: " + numero17);
        } else if (numero18 == objetivo) {
            System.out.println("Encontrado en numero18: " + numero18);
        } else if (numero19 == objetivo) {
            System.out.println("Encontrado en numero19: " + numero19);
        } else if (numero20 == objetivo) {
            System.out.println("Encontrado en numero20: " + numero20);
        } else {
            System.out.println("El número " + objetivo + " no fue encontrado en las variables.");
        }
    }

    /*
     * Enunciado 2 (Uso de Arreglos):
     * Crear un arreglo de 100,000 enteros y llenarlo automáticamente con múltiplos de 5.
     * Utilizar un ciclo for para recorrer el arreglo y buscar el número objetivo (60).
     * Mostrar la posición donde se encontró y medir el tiempo de ejecución en nanosegundos.
     */
    public static void metodoArreglo() {
        int[] numeros = new int[100000];
        int objetivo = 60;

        // Asignación de valores
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (i + 1) * 5;
        }

        // Búsqueda del número objetivo
        boolean encontrado = false;
        long inicio = System.nanoTime();
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == objetivo) {
                System.out.println("Encontrado en el índice " + i + ": " + numeros[i]);
                encontrado = true;
                break;
            }
        }
        long fin = System.nanoTime();

        if (!encontrado) {
            System.out.println("El número " + objetivo + " no fue encontrado en el arreglo.");
        }

        System.out.println("Tiempo de búsqueda (nanosegundos): " + (fin - inicio));
    }
}
