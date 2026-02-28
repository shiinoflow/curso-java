# Ciclo `for` en Java

> **Tema 4 del curso** | Anterior: [3- If con Operadores Lógicos](./3-if_operadores_logicos_java.md) | Siguiente: [5- Ciclo While](./5-ciclo_while_java.md)

---

## 1.- ¿Qué es un ciclo `for`?

Un **ciclo** (o bucle) permite ejecutar un bloque de código **varias veces** sin repetir el mismo código manualmente.

El ciclo `for` se usa cuando **sabes de antemano cuántas veces** se debe repetir algo.

> 💡 Ejemplo real: "Imprimir los números del 1 al 10" → sabes que son exactamente 10 repeticiones.

---

## 2.- Sintaxis básica

```java
for (inicio; condicion; actualización) {
    // código que se repite
}
```

| Parte          | Descripción                                         |
|----------------|-----------------------------------------------------|
| `inicio`       | Se ejecuta **una sola vez** al comienzo             |
| `condicion`    | Se evalúa **antes de cada vuelta** (si es `false`, el ciclo termina) |
| `actualización`| Se ejecuta **al final de cada vuelta**              |

---

## 3.- Ejemplo básico — Contar del 1 al 5

```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Número: " + i);
}
```

### Salida:
```
Número: 1
Número: 2
Número: 3
Número: 4
Número: 5
```

### ¿Cómo funciona paso a paso?

| Vuelta | `i` | `i <= 5` | Acción             |
|--------|-----|----------|--------------------|
| 1      | 1   | ✅ true  | Imprime "Número: 1"|
| 2      | 2   | ✅ true  | Imprime "Número: 2"|
| 3      | 3   | ✅ true  | Imprime "Número: 3"|
| 4      | 4   | ✅ true  | Imprime "Número: 4"|
| 5      | 5   | ✅ true  | Imprime "Número: 5"|
| —      | 6   | ❌ false | El ciclo termina   |

---

## 4.- Contar hacia atrás (decremento)

En lugar de `i++` (sumar 1), usamos `i--` (restar 1):

```java
for (int i = 5; i >= 1; i--) {
    System.out.println("Cuenta regresiva: " + i);
}
System.out.println("¡Despegue! 🚀");
```

### Salida:
```
Cuenta regresiva: 5
Cuenta regresiva: 4
Cuenta regresiva: 3
Cuenta regresiva: 2
Cuenta regresiva: 1
¡Despegue! 🚀
```

---

## 5.- Saltar de 2 en 2 (incremento personalizado)

```java
// Números pares del 2 al 10
for (int i = 2; i <= 10; i += 2) {
    System.out.print(i + " ");
}
```

### Salida:
```
2 4 6 8 10
```

---

## 6.- Ciclo `for` con `String`

```java
String[] frutas = {"Manzana", "Pera", "Mango", "Uva"};

for (int i = 0; i < frutas.length; i++) {
    System.out.println(i + ". " + frutas[i]);
}
```

### Salida:
```
0. Manzana
1. Pera
2. Mango
3. Uva
```

---

## 7.- `for-each` (versión simplificada para listas)

Cuando solo necesitas recorrer todos los elementos sin necesitar el índice:

```java
String[] colores = {"Rojo", "Verde", "Azul"};

for (String color : colores) {
    System.out.println("Color: " + color);
}
```

### Salida:
```
Color: Rojo
Color: Verde
Color: Azul
```

> 💡 Se lee como: "para cada `color` en `colores`, haz...".

---

## 8.- Ciclos `for` anidados

Un `for` dentro de otro `for`. Útil para tablas o matrices:

```java
// Tabla de multiplicar del 1 al 3
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.println(i + " x " + j + " = " + (i * j));
    }
    System.out.println("---");
}
```

### Salida:
```
1 x 1 = 1
1 x 2 = 2
1 x 3 = 3
---
2 x 1 = 2
2 x 2 = 4
2 x 3 = 6
---
3 x 1 = 3
3 x 2 = 6
3 x 3 = 9
---
```

---

## 9.- `break` y `continue`

### `break` → detiene el ciclo inmediatamente

```java
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        System.out.println("¡Paré en " + i + "!");
        break;
    }
    System.out.println(i);
}
```
```
1
2
3
4
¡Paré en 5!
```

### `continue` → salta esa vuelta y continúa con la siguiente

```java
// Imprimir del 1 al 7, saltando el 4
for (int i = 1; i <= 7; i++) {
    if (i == 4) continue;
    System.out.print(i + " ");
}
```
```
1 2 3 5 6 7
```

---

## 10.- Ejemplo completo

```java
public class EjemploFor {

    public static void main(String[] args) {

        int suma = 0;

        System.out.println("=== Números del 1 al 10 ===");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
            suma += i; // suma = suma + i
        }

        System.out.println("\nSuma total: " + suma);

        System.out.println("\n=== Solo los pares ===");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }

        System.out.println("\n\n=== Tabla del 5 ===");
        for (int i = 1; i <= 10; i++) {
            System.out.println("5 x " + i + " = " + (5 * i));
        }
    }
}
```

### Salida:
```
=== Números del 1 al 10 ===
1 2 3 4 5 6 7 8 9 10
Suma total: 55

=== Solo los pares ===
2 4 6 8 10

=== Tabla del 5 ===
5 x 1 = 5
5 x 2 = 10
5 x 3 = 15
5 x 4 = 20
5 x 5 = 25
5 x 6 = 30
5 x 7 = 35
5 x 8 = 40
5 x 9 = 45
5 x 10 = 50
```

---

## Resumen rápido

| Concepto          | Descripción                                      |
|-------------------|--------------------------------------------------|
| `for`             | Ciclo con inicio, condición y actualización      |
| `i++`             | Incremento: suma 1 por vuelta                    |
| `i--`             | Decremento: resta 1 por vuelta                   |
| `i += 2`          | Salta de 2 en 2                                  |
| `for-each`        | Recorre colecciones sin usar índice              |
| `break`           | Detiene el ciclo                                 |
| `continue`        | Salta la vuelta actual                           |
| Ciclos anidados   | Un `for` dentro de otro `for`                    |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [Ciclo `while` en Java](./5-ciclo_while_java.md)
