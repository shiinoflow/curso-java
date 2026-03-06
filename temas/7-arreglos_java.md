# Arreglos (Arrays) en Java

> **Tema 7 del curso** | Anterior: [6- Funciones y Métodos](./6-funciones_metodos_java.md) | Siguiente: [8- Ordenamiento Burbuja](./8-burbuja_java.md)

---

## 1.- ¿Qué es un arreglo?

Un **arreglo** (array) es una estructura que permite guardar **varios valores del mismo tipo** en una sola variable, organizados en posiciones numeradas.

> 💡 Imagínalo como una fila de cajitas numeradas, cada una guarda un valor.

```
Posición →   [0]     [1]     [2]     [3]     [4]
Valores  →   "Ana"  "Luis"  "Kevin"  "María"  "Pedro"
```

---

## 2.- Array Unidimensional — Declaración y creación

### Forma 1: Declarar y luego asignar valores

```java
int[] numeros = new int[5]; // arreglo de 5 enteros (todos inician en 0)

numeros[0] = 10;
numeros[1] = 20;
numeros[2] = 30;
numeros[3] = 40;
numeros[4] = 50;
```

### Forma 2: Declarar e inicializar en una sola línea

```java
int[] numeros = {10, 20, 30, 40, 50};

String[] nombres = {"Ana", "Luis", "Kevin", "María"};

double[] precios = {9.99, 14.50, 3.25};
```

> ⚠️ Los índices **empiezan en 0**, no en 1.

---

## 3.- Acceder y modificar elementos

```java
int[] numeros = {10, 20, 30, 40, 50};

// Leer un elemento
System.out.println(numeros[0]); // 10
System.out.println(numeros[3]); // 40

// Modificar un elemento
numeros[2] = 99;
System.out.println(numeros[2]); // 99

// Tamaño del arreglo
System.out.println(numeros.length); // 5
```

---

## 4.- Recorrer un array con `for`

```java
int[] edades = {22, 18, 30, 25, 28};

System.out.println("=== Edades ===");
for (int i = 0; i < edades.length; i++) {
    System.out.println("Posición " + i + ": " + edades[i]);
}
```

### Salida:
```
=== Edades ===
Posición 0: 22
Posición 1: 18
Posición 2: 30
Posición 3: 25
Posición 4: 28
```

---

## 5.- Recorrer un array con `for-each`

```java
String[] frutas = {"Manzana", "Pera", "Mango", "Uva"};

for (String fruta : frutas) {
    System.out.println("🍎 " + fruta);
}
```

### Salida:
```
🍎 Manzana
🍎 Pera
🍎 Mango
🍎 Uva
```

---

## 6.- Operaciones comunes con arrays

```java
int[] nums = {5, 2, 8, 1, 9, 3};

// Suma total
int suma = 0;
for (int n : nums) suma += n;
System.out.println("Suma: " + suma); // 28

// Valor máximo
int max = nums[0];
for (int n : nums) {
    if (n > max) max = n;
}
System.out.println("Máximo: " + max); // 9

// Valor mínimo
int min = nums[0];
for (int n : nums) {
    if (n < min) min = n;
}
System.out.println("Mínimo: " + min); // 1

// Promedio
double promedio = (double) suma / nums.length;
System.out.println("Promedio: " + promedio); // 4.666...
```

---

## 7.- Array Bidimensional — Introducción

Un array bidimensional es como una **tabla con filas y columnas** (una matriz).

```
         Col 0   Col 1   Col 2
Fila 0 |   1   |   2   |   3   |
Fila 1 |   4   |   5   |   6   |
Fila 2 |   7   |   8   |   9   |
```

---

## 8.- Array Bidimensional — Declaración

### Forma 1: con `new`

```java
int[][] matriz = new int[3][3]; // 3 filas, 3 columnas
```

### Forma 2: con valores directos

```java
int[][] matriz = {
    {1, 2, 3},   // fila 0
    {4, 5, 6},   // fila 1
    {7, 8, 9}    // fila 2
};
```

### Acceder a un elemento: `array[fila][columna]`

```java
System.out.println(matriz[0][0]); // 1 (fila 0, col 0)
System.out.println(matriz[1][2]); // 6 (fila 1, col 2)
System.out.println(matriz[2][1]); // 8 (fila 2, col 1)
```

---

## 9.- Recorrer un array bidimensional

Usa un `for` anidado: el externo recorre **filas**, el interno recorre **columnas**.

```java
int[][] tabla = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

for (int fila = 0; fila < tabla.length; fila++) {
    for (int col = 0; col < tabla[fila].length; col++) {
        System.out.print(tabla[fila][col] + "\t");
    }
    System.out.println(); // salto de línea al terminar cada fila
}
```

### Salida:
```
1	2	3
4	5	6
7	8	9
```

---

## 10.- Ejemplo completo — Registro de calificaciones

```java
public class RegistroCalificaciones {

    public static void main(String[] args) {

        // Arreglos unidimensionales
        String[] estudiantes = {"Ana", "Luis", "Kevin", "María"};
        double[] notas       = {92.5, 75.0, 88.0, 61.5};

        System.out.println("=== Reporte de Calificaciones ===");

        double sumaTotal = 0;
        for (int i = 0; i < estudiantes.length; i++) {
            String estado = notas[i] >= 60 ? "✅ Aprobado" : "❌ Reprobado";
            System.out.printf("%-8s → %.1f  %s%n", estudiantes[i], notas[i], estado);
            sumaTotal += notas[i];
        }

        double promedio = sumaTotal / notas.length;
        System.out.printf("%nPromedio del grupo: %.1f%n", promedio);

        // Array bidimensional: tabla de notas por materia
        System.out.println("\n=== Notas por Materia ===");
        String[] materias = {"Matemáticas", "Java", "Base de Datos"};
        double[][] notasPorMateria = {
            {88, 95, 76},  // Ana
            {70, 82, 68},  // Luis
            {91, 87, 93}   // Kevin
        };

        // Encabezado
        System.out.printf("%-10s", "");
        for (String m : materias) System.out.printf("%-16s", m);
        System.out.println();

        // Filas
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-10s", estudiantes[i]);
            for (int j = 0; j < materias.length; j++) {
                System.out.printf("%-16.0f", notasPorMateria[i][j]);
            }
            System.out.println();
        }
    }
}
```

### Salida:
```
=== Reporte de Calificaciones ===
Ana      → 92.5  ✅ Aprobado
Luis     → 75.0  ✅ Aprobado
Kevin    → 88.0  ✅ Aprobado
María    → 61.5  ✅ Aprobado

Promedio del grupo: 79.3

=== Notas por Materia ===
          Matemáticas     Java            Base de Datos
Ana       88              95              76
Luis      70              82              68
Kevin     91              87              93
```

---

## 11.- Ejercicio Práctico: Comparación de Eficiencia

En la carpeta `ejercicios/` hemos añadido el archivo `BusquedaNumeros.java` para demostrar la **importancia de los arreglos**.

El ejercicio compara dos enfoques para buscar el número **60**:

1.  **❌ Sin Arreglos (Manual):**
    *   Se declaran 20 variables (`int numero1`, `int numero2`...).
    *   Se necesitan 20 condiciones `if-else if` para revisar cada una.
    *   Es **imposible de mantener** si el número de datos crece (ej. 1000 datos).

2.  **✅ Con Arreglos (Óptimo):**
    *   Se crea un arreglo `int[] numeros`.
    *   Un simple ciclo `for` recorre y busca el dato, sin importar si son 20 o 100,000 elementos.

> **Pruébalo tú mismo:** Compila y ejecuta el archivo para ver la diferencia en limpieza de código y escalabilidad.

---

## Resumen rápido

| Concepto              | Descripción                                       |
|-----------------------|---------------------------------------------------|
| `int[] arr`           | Array unidimensional de enteros                   |
| `int[][] mat`         | Array bidimensional (matriz)                      |
| `arr[i]`              | Acceder al elemento en posición `i`               |
| `mat[fila][col]`      | Acceder al elemento en fila y columna             |
| `arr.length`          | Número de elementos del array                     |
| Índice empieza en `0` | El primer elemento es `arr[0]`, no `arr[1]`       |
| `for` anidado         | Para recorrer matrices (bidimensionales)          |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [Ordenamiento Burbuja](./8-burbuja_java.md)
