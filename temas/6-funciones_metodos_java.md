# Funciones y Métodos en Java

> **Tema 6 del curso** | Anterior: [5- Ciclo While](./5-ciclo_while_java.md)

---

## 1.- ¿Qué es un método?

Un **método** es un bloque de código con nombre que realiza una tarea específica. Se define una vez y se puede **reutilizar** todas las veces que se necesite.

> 💡 En Java, las funciones se llaman **métodos** porque siempre pertenecen a una clase.

### ¿Por qué usar métodos?
- ✅ Evitar repetir el mismo código
- ✅ Organizar el programa en partes más pequeñas
- ✅ Hacer el código más fácil de leer y mantener

---

## 2.- Sintaxis básica

```java
tipoDeRetorno nombreDelMetodo(parametros) {
    // código del método
    return valor; // solo si retorna algo
}
```

| Parte            | Descripción                                                 |
|------------------|-------------------------------------------------------------|
| `tipoDeRetorno`  | Tipo del valor que devuelve (`int`, `String`, `void`, etc.) |
| `nombreDelMetodo`| Nombre del método (usa camelCase)                           |
| `parametros`     | Datos que recibe el método (puede no tener ninguno)         |
| `return`         | Devuelve el resultado (se omite si es `void`)               |

---

## 3.- Método sin parámetros y sin retorno (`void`)

`void` significa que el método **no devuelve ningún valor**.

```java
public static void saludar() {
    System.out.println("¡Hola! Bienvenido al curso de Java.");
}

public static void main(String[] args) {
    saludar(); // llamar al método
    saludar(); // se puede llamar cuantas veces quieras
}
```

### Salida:
```
¡Hola! Bienvenido al curso de Java.
¡Hola! Bienvenido al curso de Java.
```

---

## 4.- Método con parámetros

Los **parámetros** son los datos que el método recibe para trabajar con ellos.

```java
public static void saludarPersona(String nombre) {
    System.out.println("¡Hola, " + nombre + "!");
}

public static void main(String[] args) {
    saludarPersona("Ana");
    saludarPersona("Carlos");
    saludarPersona("Kevin");
}
```

### Salida:
```
¡Hola, Ana!
¡Hola, Carlos!
¡Hola, Kevin!
```

---

## 5.- Método con retorno (`return`)

Un método puede **calcular algo y devolver el resultado** usando `return`.

```java
public static int sumar(int a, int b) {
    return a + b;
}

public static void main(String[] args) {
    int resultado = sumar(5, 3);
    System.out.println("5 + 3 = " + resultado); // 8

    System.out.println("10 + 20 = " + sumar(10, 20)); // 30
}
```

### Salida:
```
5 + 3 = 8
10 + 20 = 30
```

---

## 6.- Múltiples parámetros y tipos de retorno

```java
// Retorna double
public static double calcularPromedio(double n1, double n2, double n3) {
    return (n1 + n2 + n3) / 3;
}

// Retorna String
public static String obtenerCalificacion(double promedio) {
    if (promedio >= 90) return "A - Excelente";
    else if (promedio >= 80) return "B - Muy bueno";
    else if (promedio >= 70) return "C - Bueno";
    else if (promedio >= 60) return "D - Suficiente";
    else return "F - Reprobado";
}

public static void main(String[] args) {
    double promedio = calcularPromedio(85, 92, 78);
    System.out.println("Promedio: " + promedio);
    System.out.println("Calificación: " + obtenerCalificacion(promedio));
}
```

### Salida:
```
Promedio: 85.0
Calificación: B - Muy bueno
```

---

## 7.- Método con `boolean` de retorno

Muy útil para validaciones:

```java
public static boolean esMayorDeEdad(int edad) {
    return edad >= 18;
}

public static void main(String[] args) {
    int edad = 20;

    if (esMayorDeEdad(edad)) {
        System.out.println("✅ Puede ingresar");
    } else {
        System.out.println("❌ No puede ingresar");
    }
}
```

### Salida:
```
✅ Puede ingresar
```

---

## 8.- Sobrecarga de métodos (mismo nombre, distintos parámetros)

Java permite tener **varios métodos con el mismo nombre** siempre que los parámetros sean diferentes:

```java
public static int sumar(int a, int b) {
    return a + b;
}

public static double sumar(double a, double b) {
    return a + b;
}

public static int sumar(int a, int b, int c) {
    return a + b + c;
}

public static void main(String[] args) {
    System.out.println(sumar(2, 3));         // 5   → usa la 1ra versión
    System.out.println(sumar(1.5, 2.5));     // 4.0 → usa la 2da versión
    System.out.println(sumar(1, 2, 3));      // 6   → usa la 3ra versión
}
```

---

## 9.- Llamar un método desde otro método

Los métodos pueden llamarse entre sí:

```java
public static double calcularArea(double radio) {
    return Math.PI * radio * radio;
}

public static void mostrarArea(double radio) {
    double area = calcularArea(radio); // llamada a otro método
    System.out.printf("Radio: %.1f → Área: %.2f%n", radio, area);
}

public static void main(String[] args) {
    mostrarArea(5);
    mostrarArea(3.5);
    mostrarArea(10);
}
```

### Salida:
```
Radio: 5.0 → Área: 78.54
Radio: 3.5 → Área: 38.48
Radio: 10.0 → Área: 314.16
```

---

## 10.- Ejemplo completo — Sistema de calificaciones

```java
public class SistemaCalificaciones {

    // Calcula el promedio de tres notas
    public static double calcularPromedio(double n1, double n2, double n3) {
        return (n1 + n2 + n3) / 3;
    }

    // Determina si el estudiante aprobó
    public static boolean aprobo(double promedio) {
        return promedio >= 60;
    }

    // Devuelve la letra de calificación
    public static String obtenerLetra(double promedio) {
        if (promedio >= 90) return "A";
        else if (promedio >= 80) return "B";
        else if (promedio >= 70) return "C";
        else if (promedio >= 60) return "D";
        else return "F";
    }

    // Imprime el reporte completo del estudiante
    public static void mostrarReporte(String nombre, double n1, double n2, double n3) {
        double promedio = calcularPromedio(n1, n2, n3);
        String letra = obtenerLetra(promedio);
        boolean paso = aprobo(promedio);

        System.out.println("=============================");
        System.out.println("Estudiante: " + nombre);
        System.out.printf("Promedio:   %.1f (%s)%n", promedio, letra);
        System.out.println("Estado:     " + (paso ? "✅ Aprobado" : "❌ Reprobado"));
        System.out.println("=============================");
    }

    public static void main(String[] args) {
        mostrarReporte("Ana García",   95, 88, 92);
        mostrarReporte("Luis Pérez",   55, 60, 58);
        mostrarReporte("Kevin Arauz",  78, 82, 80);
    }
}
```

### Salida:
```
=============================
Estudiante: Ana García
Promedio:   91.7 (A)
Estado:     ✅ Aprobado
=============================
=============================
Estudiante: Luis Pérez
Promedio:   57.7 (F)
Estado:     ❌ Reprobado
=============================
=============================
Estudiante: Kevin Arauz
Promedio:   80.0 (B)
Estado:     ✅ Aprobado
=============================
```

---

## Resumen rápido

| Concepto             | Descripción                                          |
|----------------------|------------------------------------------------------|
| `void`               | El método no devuelve ningún valor                   |
| `return`             | Devuelve un valor desde el método                    |
| Parámetros           | Datos que recibe el método al ser llamado            |
| Argumentos           | Valores concretos que se pasan al llamar el método   |
| Sobrecarga           | Mismo nombre de método, distintos parámetros         |
| `public static`      | Modificadores para poder llamar el método desde main |
