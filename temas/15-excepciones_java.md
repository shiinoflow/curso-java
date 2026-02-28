# Excepciones en Java

> **Tema 15 del curso** | Anterior: [14- Switch](./14-switch_java.md) | Siguiente: [16- HashMap](./16-hashmap_java.md)

---

## 1.- ¿Qué es una excepción?

Una **excepción** es un error que ocurre **en tiempo de ejecución** y que interrumpe el flujo normal del programa. Java permite **atraparlas** y manejarlas para que el programa no se caiga.

> 💡 Sin manejo de excepciones: el programa lanza un error y termina abruptamente. Con manejo: el programa detecta el error, lo gestiona y continúa funcionando.

---

## 2.- Jerarquía de excepciones en Java

```
Throwable
├── Error              → problemas graves del sistema (no se deben atrapar)
│   └── OutOfMemoryError, StackOverflowError...
└── Exception          → errores que SÍ podemos manejar
    ├── RuntimeException (unchecked — no obligan try-catch)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── ArithmeticException
    │   └── NumberFormatException
    └── IOException (checked — obligan try-catch)
        ├── FileNotFoundException
        └── SQLException
```

---

## 3.- Sintaxis: `try-catch`

```java
try {
    // código que puede lanzar una excepción
} catch (TipoDeExcepcion e) {
    // código que se ejecuta si ocurre esa excepción
}
```

---

## 4.- Excepciones comunes con ejemplos

### `ArithmeticException` — División por cero

```java
try {
    int resultado = 10 / 0; // lanza excepción
    System.out.println(resultado);
} catch (ArithmeticException e) {
    System.out.println("❌ Error: " + e.getMessage());
}
System.out.println("El programa continúa...");
```

```
❌ Error: / by zero
El programa continúa...
```

---

### `NumberFormatException` — Convertir texto inválido

```java
try {
    String texto = "abc";
    int numero = Integer.parseInt(texto); // falla
} catch (NumberFormatException e) {
    System.out.println("❌ No se puede convertir: " + e.getMessage());
}
```

```
❌ No se puede convertir: For input string: "abc"
```

---

### `ArrayIndexOutOfBoundsException` — Índice fuera del array

```java
int[] nums = {1, 2, 3};

try {
    System.out.println(nums[5]); // índice inválido
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("❌ Índice fuera de rango: " + e.getMessage());
}
```

---

### `NullPointerException` — Objeto nulo

```java
String texto = null;

try {
    System.out.println(texto.length()); // texto es null
} catch (NullPointerException e) {
    System.out.println("❌ El objeto es null");
}
```

---

## 5.- Múltiples `catch`

Se puede atrapar distintos tipos de excepción en el mismo `try`:

```java
try {
    String s = null;
    int[] a  = new int[3];

    System.out.println(s.length()); // NullPointerException
    System.out.println(a[10]);      // ArrayIndexOutOfBoundsException

} catch (NullPointerException e) {
    System.out.println("❌ Objeto nulo: " + e.getMessage());
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("❌ Índice inválido: " + e.getMessage());
} catch (Exception e) {
    // Atrapar cualquier otra excepción (siempre al final)
    System.out.println("❌ Error general: " + e.getMessage());
}
```

> ⚠️ El orden importa: los `catch` más específicos van primero, `Exception` siempre al final.

---

## 6.- Bloque `finally`

El bloque `finally` se ejecuta **siempre**, ocurra o no una excepción. Útil para liberar recursos:

```java
try {
    System.out.println("Intentando...");
    int r = 10 / 2;
    System.out.println("Resultado: " + r);
} catch (ArithmeticException e) {
    System.out.println("Error: " + e.getMessage());
} finally {
    System.out.println("✅ Esto siempre se ejecuta.");
}
```

```
Intentando...
Resultado: 5
✅ Esto siempre se ejecuta.
```

---

## 7.- Lanzar excepciones con `throw`

Puedes lanzar una excepción manualmente:

```java
public static void validarEdad(int edad) {
    if (edad < 0 || edad > 120) {
        throw new IllegalArgumentException("Edad inválida: " + edad);
    }
    System.out.println("Edad válida: " + edad);
}

public static void main(String[] args) {
    try {
        validarEdad(25);   // OK
        validarEdad(-5);   // lanza excepción
    } catch (IllegalArgumentException e) {
        System.out.println("❌ " + e.getMessage());
    }
}
```

```
Edad válida: 25
❌ Edad inválida: -5
```

---

## 8.- Crear excepción personalizada

Puedes crear tus propias excepciones extendiendo `Exception` o `RuntimeException`:

```java
// Excepción personalizada
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(double saldo, double monto) {
        super(String.format("Saldo insuficiente. Saldo: $%.2f | Monto solicitado: $%.2f", saldo, monto));
    }
}
```

```java
public class CuentaBancaria {

    private double saldo;

    public CuentaBancaria(double saldo) { this.saldo = saldo; }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto > saldo) {
            throw new SaldoInsuficienteException(saldo, monto);
        }
        saldo -= monto;
        System.out.printf("✅ Retiro exitoso. Saldo restante: $%.2f%n", saldo);
    }
}
```

```java
public static void main(String[] args) {
    CuentaBancaria cuenta = new CuentaBancaria(500.0);

    try {
        cuenta.retirar(200);   // OK
        cuenta.retirar(400);   // lanza SaldoInsuficienteException
    } catch (SaldoInsuficienteException e) {
        System.out.println("❌ " + e.getMessage());
    }
}
```

```
✅ Retiro exitoso. Saldo restante: $300.00
❌ Saldo insuficiente. Saldo: $300.00 | Monto solicitado: $400.00
```

---

## 9.- `try-with-resources` (Java 7+)

Para recursos que deben cerrarse automáticamente (archivos, conexiones, etc.):

```java
import java.io.*;

try (BufferedReader br = new BufferedReader(new FileReader("archivo.txt"))) {
    String linea;
    while ((linea = br.readLine()) != null) {
        System.out.println(linea);
    }
} catch (FileNotFoundException e) {
    System.out.println("❌ Archivo no encontrado.");
} catch (IOException e) {
    System.out.println("❌ Error de lectura.");
}
// El BufferedReader se cierra automáticamente al salir del try
```

---

## 10.- Ejemplo completo — Validador de datos

```java
public class ValidadorDatos {

    public static int parsearEntero(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ '" + texto + "' no es un número válido. Retornando 0.");
            return 0;
        }
    }

    public static double dividir(double a, double b) {
        if (b == 0) throw new ArithmeticException("No se puede dividir entre 0");
        return a / b;
    }

    public static void main(String[] args) {

        // Parsear números
        System.out.println(parsearEntero("42"));    // 42
        System.out.println(parsearEntero("abc"));   // 0 con advertencia
        System.out.println(parsearEntero("100"));   // 100

        System.out.println();

        // Divisiones
        String[] operaciones = {"10/2", "8/0", "15/3"};

        for (String op : operaciones) {
            String[] partes = op.split("/");
            try {
                double a = Double.parseDouble(partes[0]);
                double b = Double.parseDouble(partes[1]);
                System.out.printf("%.0f / %.0f = %.2f%n", a, b, dividir(a, b));
            } catch (ArithmeticException e) {
                System.out.println("❌ " + op + " → " + e.getMessage());
            } finally {
                System.out.println("  [operación procesada]");
            }
        }
    }
}
```

### Salida:
```
42
⚠️ 'abc' no es un número válido. Retornando 0.
0
100

10 / 2 = 5.00
  [operación procesada]
❌ 8/0 → No se puede dividir entre 0
  [operación procesada]
15 / 3 = 5.00
  [operación procesada]
```

---

## Resumen rápido

| Concepto                   | Descripción                                         |
|----------------------------|-----------------------------------------------------|
| `try`                      | Bloque de código que puede lanzar excepción         |
| `catch (Tipo e)`           | Captura y maneja la excepción                       |
| `finally`                  | Se ejecuta siempre (con o sin error)                |
| `throw`                    | Lanza una excepción manualmente                     |
| `throws`                   | Declara que un método puede lanzar excepción        |
| `Exception` personalizada  | Extiende `Exception` o `RuntimeException`           |
| `try-with-resources`       | Cierra recursos automáticamente                     |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [HashMap](./16-hashmap_java.md)
