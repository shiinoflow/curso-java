# Switch en Java

> **Tema 14 del curso** | Anterior: [3- If con Operadores Lógicos](./3-if_operadores_logicos_java.md) | Siguiente: [15- Excepciones](./15-excepciones_java.md)

---

## 1.- ¿Qué es el `switch`?

El `switch` es una estructura de control que evalúa una variable y ejecuta el bloque de código que corresponda a su valor. Es una alternativa más limpia al `if-else if` cuando tienes **múltiples opciones fijas**.

| Característica      | `if-else`                        | `switch`                      |
|---------------------|----------------------------------|-------------------------------|
| Evalúa              | Cualquier condición booleana     | Un valor puntual              |
| Legibilidad         | Puede volverse confuso con muchos casos | Más claro con muchos casos |
| Tipos aceptados     | Cualquier tipo                   | `int`, `char`, `String`, `enum` |

---

## 2.- Sintaxis básica

```java
switch (variable) {
    case valor1:
        // código
        break;
    case valor2:
        // código
        break;
    default:
        // código si ningún caso coincide
}
```

> ⚠️ `break` es obligatorio al final de cada `case`. Sin él, el código "cae" al siguiente caso (*fall-through*).

---

## 3.- Ejemplo con `int` — Días de la semana

```java
int dia = 3;

switch (dia) {
    case 1:
        System.out.println("Lunes");
        break;
    case 2:
        System.out.println("Martes");
        break;
    case 3:
        System.out.println("Miércoles");
        break;
    case 4:
        System.out.println("Jueves");
        break;
    case 5:
        System.out.println("Viernes");
        break;
    case 6:
        System.out.println("Sábado");
        break;
    case 7:
        System.out.println("Domingo");
        break;
    default:
        System.out.println("Número de día inválido");
}
```

```
Miércoles
```

---

## 4.- Ejemplo con `String` — Menú de opciones

```java
String opcion = "B";

switch (opcion) {
    case "A":
        System.out.println("Seleccionaste: Agregar");
        break;
    case "B":
        System.out.println("Seleccionaste: Buscar");
        break;
    case "C":
        System.out.println("Seleccionaste: Eliminar");
        break;
    case "S":
        System.out.println("Saliendo del programa...");
        break;
    default:
        System.out.println("⚠️ Opción no válida");
}
```

```
Seleccionaste: Buscar
```

---

## 5.- Múltiples casos juntos (agrupación)

Cuando varios casos ejecutan lo mismo se pueden agrupar:

```java
int mes = 4;

switch (mes) {
    case 1: case 3: case 5:
    case 7: case 8: case 10: case 12:
        System.out.println("31 días");
        break;
    case 4: case 6: case 9: case 11:
        System.out.println("30 días");
        break;
    case 2:
        System.out.println("28 o 29 días");
        break;
    default:
        System.out.println("Mes inválido");
}
```

```
30 días
```

---

## 6.- `fall-through` — Caída sin `break`

Sin `break`, el código continúa ejecutando los casos siguientes:

```java
int nivel = 2;

switch (nivel) {
    case 1:
        System.out.println("Acceso básico");
    case 2:
        System.out.println("Acceso intermedio");   // ← se ejecuta
    case 3:
        System.out.println("Acceso avanzado");      // ← también se ejecuta!
        break;
    default:
        System.out.println("Sin acceso");
}
```

```
Acceso intermedio
Acceso avanzado
```

> 💡 En general el fall-through es un error. Úsalo solo cuando sea intencional y comentado.

---

## 7.- Switch Expression (Java 14+) — Forma moderna

La forma moderna usa `->` y devuelve un valor directamente. No necesita `break`:

```java
int dia = 5;

String nombre = switch (dia) {
    case 1 -> "Lunes";
    case 2 -> "Martes";
    case 3 -> "Miércoles";
    case 4 -> "Jueves";
    case 5 -> "Viernes";
    case 6 -> "Sábado";
    case 7 -> "Domingo";
    default -> "Día inválido";
};

System.out.println(nombre); // Viernes
```

---

## 8.- Switch Expression con bloques y `yield`

Cuando necesitas varias líneas en un caso con la forma moderna, usas `yield` para devolver el valor:

```java
int puntos = 85;

String calificacion = switch (puntos / 10) {
    case 10, 9 -> "A - Excelente";
    case 8     -> "B - Muy bueno";
    case 7     -> "C - Bueno";
    case 6     -> {
        System.out.println("Aprobado justo");
        yield "D - Suficiente";
    }
    default -> {
        System.out.println("Reprobado");
        yield "F - Insuficiente";
    }
};

System.out.println("Calificación: " + calificacion);
```

```
Calificación: B - Muy bueno
```

---

## 9.- Switch con `enum`

Los `enum` son perfectos para usar con `switch`:

```java
enum Estacion { PRIMAVERA, VERANO, OTONO, INVIERNO }

Estacion estacion = Estacion.VERANO;

switch (estacion) {
    case PRIMAVERA:
        System.out.println("🌸 Clima templado");
        break;
    case VERANO:
        System.out.println("☀️ Mucho calor");
        break;
    case OTONO:
        System.out.println("🍂 Hojas caen");
        break;
    case INVIERNO:
        System.out.println("❄️ Mucho frío");
        break;
}
```

```
☀️ Mucho calor
```

---

## 10.- Ejemplo completo — Calculadora con switch

```java
import java.util.Scanner;

public class CalculadoraSwitch {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Número 1: ");
        double a = sc.nextDouble();

        System.out.print("Operador (+, -, *, /): ");
        String op = sc.next();

        System.out.print("Número 2: ");
        double b = sc.nextDouble();

        double resultado = switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    System.out.println("⚠️ No se puede dividir entre 0");
                    yield 0;
                }
                yield a / b;
            }
            default -> {
                System.out.println("⚠️ Operador no reconocido");
                yield 0;
            }
        };

        System.out.printf("%.2f %s %.2f = %.2f%n", a, op, b, resultado);
    }
}
```

```
Número 1: 10
Operador (+, -, *, /): *
Número 2: 5
10.00 * 5.00 = 50.00
```

---

## Resumen rápido

| Concepto             | Descripción                                       |
|----------------------|---------------------------------------------------|
| `switch (var)`       | Evalúa el valor de la variable                    |
| `case valor:`        | Bloque a ejecutar si el valor coincide            |
| `break`              | Termina el case actual (obligatorio)              |
| `default:`           | Se ejecuta si ningún caso coincide                |
| Agrupación           | `case 1: case 2:` → misma acción                 |
| Switch Expression    | Forma moderna con `->`, sin `break`, retorna valor|
| `yield`              | Retorna un valor en switch expression con bloque  |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [Excepciones](./15-excepciones_java.md)
