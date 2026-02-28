# Ordenamiento Burbuja en Java

> **Tema 8 del curso** | Anterior: [7- Arreglos](./7-arreglos_java.md)

---

## 1.- ¿Qué es el ordenamiento burbuja?

El **ordenamiento burbuja** (Bubble Sort) es uno de los algoritmos de ordenamiento más simples. Compara pares de elementos **adyacentes** y los intercambia si están en el orden incorrecto. Esto se repite varias veces hasta que el arreglo queda ordenado.

> 💡 El nombre "burbuja" viene de que los valores más grandes "burbujean" hacia el final del arreglo en cada pasada.

---

## 2.- ¿Cómo funciona? (paso a paso visual)

Arreglo inicial: `[5, 3, 8, 1, 4]`

### Pasada 1 — El número más grande llega al final:

```
[5, 3, 8, 1, 4]  → compara 5 y 3  → intercambia → [3, 5, 8, 1, 4]
[3, 5, 8, 1, 4]  → compara 5 y 8  → no cambia   → [3, 5, 8, 1, 4]
[3, 5, 8, 1, 4]  → compara 8 y 1  → intercambia → [3, 5, 1, 8, 4]
[3, 5, 1, 8, 4]  → compara 8 y 4  → intercambia → [3, 5, 1, 4, 8] ✅ 8 en su lugar
```

### Pasada 2:
```
[3, 5, 1, 4, 8]  → compara 3 y 5  → no cambia
[3, 5, 1, 4, 8]  → compara 5 y 1  → intercambia → [3, 1, 5, 4, 8]
[3, 1, 5, 4, 8]  → compara 5 y 4  → intercambia → [3, 1, 4, 5, 8] ✅ 5 en su lugar
```

### Pasada 3:
```
[3, 1, 4, 5, 8]  → compara 3 y 1  → intercambia → [1, 3, 4, 5, 8]
[1, 3, 4, 5, 8]  → compara 3 y 4  → no cambia          ✅ 4 en su lugar
```

### Resultado final: `[1, 3, 4, 5, 8]` 🎉

---

## 3.- Código básico del algoritmo burbuja

```java
public class OrdenamientoBurbuja {

    public static void main(String[] args) {

        int[] numeros = {5, 3, 8, 1, 4};
        int n = numeros.length;

        // Algoritmo burbuja
        for (int i = 0; i < n - 1; i++) {           // pasadas
            for (int j = 0; j < n - 1 - i; j++) {   // comparaciones
                if (numeros[j] > numeros[j + 1]) {
                    // Intercambiar
                    int temp    = numeros[j];
                    numeros[j]  = numeros[j + 1];
                    numeros[j + 1] = temp;
                }
            }
        }

        // Mostrar resultado
        System.out.print("Ordenado: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
    }
}
```

### Salida:
```
Ordenado: 1 3 4 5 8
```

---

## 4.- Explicación del código

```java
for (int i = 0; i < n - 1; i++)
```
→ Controla las **pasadas**. Con cada pasada, el elemento más grande ya está en su posición, por eso reducimos la zona de comparación.

```java
for (int j = 0; j < n - 1 - i; j++)
```
→ Controla las **comparaciones** dentro de cada pasada. El `- i` evita volver a revisar los elementos ya ordenados al final.

```java
int temp    = numeros[j];
numeros[j]  = numeros[j + 1];
numeros[j + 1] = temp;
```
→ El **truco del intercambio**: usa una variable temporal `temp` para no perder el valor al sobreescribirlo.

---

## 5.- Burbuja mejorada (con bandera `swapped`)

Si en una pasada no se realizó ningún intercambio, significa que el arreglo **ya está ordenado** y podemos salir antes:

```java
public static void burbujaMejorada(int[] arr) {
    int n = arr.length;

    for (int i = 0; i < n - 1; i++) {
        boolean swapped = false;

        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp  = arr[j];
                arr[j]    = arr[j + 1];
                arr[j + 1] = temp;
                swapped = true;
            }
        }

        if (!swapped) {
            System.out.println("✅ Ya estaba ordenado, saliendo en pasada " + (i + 1));
            break; // no hubo intercambios → ya está ordenado
        }
    }
}
```

---

## 6.- Ordenar de mayor a menor

Solo cambia el `>` por `<` en la condición:

```java
int[] numeros = {5, 3, 8, 1, 4};
int n = numeros.length;

for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < n - 1 - i; j++) {
        if (numeros[j] < numeros[j + 1]) { // ← cambia el signo
            int temp       = numeros[j];
            numeros[j]     = numeros[j + 1];
            numeros[j + 1] = temp;
        }
    }
}

System.out.print("Mayor a menor: ");
for (int n2 : numeros) System.out.print(n2 + " ");
```

### Salida:
```
Mayor a menor: 8 5 4 3 1
```

---

## 7.- Burbuja como método reutilizable

```java
public static void burbuja(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp   = arr[j];
                arr[j]     = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

public static void imprimirArray(int[] arr) {
    for (int num : arr) System.out.print(num + " ");
    System.out.println();
}

public static void main(String[] args) {
    int[] a = {64, 34, 25, 12, 22, 11, 90};
    int[] b = {1, 2, 3, 4, 5}; // ya ordenado

    System.out.print("Antes: "); imprimirArray(a);
    burbuja(a);
    System.out.print("Después: "); imprimirArray(a);

    System.out.print("\nAntes: "); imprimirArray(b);
    burbuja(b);
    System.out.print("Después: "); imprimirArray(b);
}
```

### Salida:
```
Antes:   64 34 25 12 22 11 90
Después: 11 12 22 25 34 64 90

Antes:   1 2 3 4 5
Después: 1 2 3 4 5
```

---

## 8.- Ejemplo completo con traza de pasadas

```java
public class BurbujaConTraza {

    public static void main(String[] args) {

        int[] numeros = {5, 3, 8, 1, 4};
        int n = numeros.length;
        int intercambios = 0;

        System.out.println("Array inicial: " + java.util.Arrays.toString(numeros));
        System.out.println();

        for (int i = 0; i < n - 1; i++) {
            System.out.println("--- Pasada " + (i + 1) + " ---");

            for (int j = 0; j < n - 1 - i; j++) {
                System.out.print("  Comparando " + numeros[j] + " y " + numeros[j + 1]);

                if (numeros[j] > numeros[j + 1]) {
                    int temp       = numeros[j];
                    numeros[j]     = numeros[j + 1];
                    numeros[j + 1] = temp;
                    intercambios++;
                    System.out.println(" → INTERCAMBIO → " + java.util.Arrays.toString(numeros));
                } else {
                    System.out.println(" → sin cambio");
                }
            }
        }

        System.out.println("\nArray ordenado: " + java.util.Arrays.toString(numeros));
        System.out.println("Total de intercambios: " + intercambios);
    }
}
```

### Salida:
```
Array inicial: [5, 3, 8, 1, 4]

--- Pasada 1 ---
  Comparando 5 y 3 → INTERCAMBIO → [3, 5, 8, 1, 4]
  Comparando 5 y 8 → sin cambio
  Comparando 8 y 1 → INTERCAMBIO → [3, 5, 1, 8, 4]
  Comparando 8 y 4 → INTERCAMBIO → [3, 5, 1, 4, 8]
--- Pasada 2 ---
  Comparando 3 y 5 → sin cambio
  Comparando 5 y 1 → INTERCAMBIO → [3, 1, 5, 4, 8]
  Comparando 5 y 4 → INTERCAMBIO → [3, 1, 4, 5, 8]
--- Pasada 3 ---
  Comparando 3 y 1 → INTERCAMBIO → [1, 3, 4, 5, 8]
  Comparando 3 y 4 → sin cambio
--- Pasada 4 ---
  Comparando 1 y 3 → sin cambio

Array ordenado: [1, 3, 4, 5, 8]
Total de intercambios: 6
```

---

## Resumen rápido

| Concepto               | Descripción                                              |
|------------------------|----------------------------------------------------------|
| Algoritmo burbuja      | Compara pares adyacentes e intercambia si están mal ordenados |
| Variable `temp`        | Necesaria para intercambiar dos valores sin perderlos    |
| `n - 1 - i`            | Reduce las comparaciones en cada pasada                  |
| Bandera `swapped`      | Optimización para salir temprano si ya está ordenado     |
| Orden ascendente       | Condición `arr[j] > arr[j+1]`                           |
| Orden descendente      | Condición `arr[j] < arr[j+1]`                           |
