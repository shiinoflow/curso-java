# Ciclo `while` en Java

> **Tema 5 del curso** | Anterior: [4- Ciclo For](./4-ciclo_for_java.md)

---

## 1.- ¿Qué es un ciclo `while`?

El ciclo `while` repite un bloque de código **mientras una condición sea verdadera**. A diferencia del `for`, se usa cuando **no sabes exactamente cuántas veces** se va a repetir.

> 💡 Ejemplo real: "Seguir pidiendo la contraseña mientras sea incorrecta" → no sabes cuántos intentos hará el usuario.

---

## 2.- Sintaxis básica

```java
while (condicion) {
    // código que se repite
}
```

| Parte       | Descripción                                                       |
|-------------|-------------------------------------------------------------------|
| `condicion` | Se evalúa **antes de cada vuelta**. Si es `false`, el ciclo termina. |

> ⚠️ Si la condición nunca se vuelve `false`, el ciclo se repite **infinitamente** (bucle infinito).

---

## 3.- Ejemplo básico — Contar del 1 al 5

```java
int i = 1;

while (i <= 5) {
    System.out.println("Número: " + i);
    i++; // ¡Importante! Si no, bucle infinito
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

| Vuelta | `i` | `i <= 5` | Acción              |
|--------|-----|----------|---------------------|
| 1      | 1   | ✅ true  | Imprime, luego i=2  |
| 2      | 2   | ✅ true  | Imprime, luego i=3  |
| 3      | 3   | ✅ true  | Imprime, luego i=4  |
| 4      | 4   | ✅ true  | Imprime, luego i=5  |
| 5      | 5   | ✅ true  | Imprime, luego i=6  |
| —      | 6   | ❌ false | Ciclo termina       |

---

## 4.- `while` vs `for` — ¿Cuándo usar cada uno?

| Situación                              | Usa     |
|----------------------------------------|---------|
| Sabes exactamente cuántas repeticiones | `for`   |
| No sabes cuántas veces se repetirá     | `while` |
| Recorrer una lista/arreglo             | `for`   |
| Esperar una entrada del usuario        | `while` |
| Validar datos hasta que sean correctos | `while` |

---

## 5.- Ejemplo — Validar entrada del usuario

```java
import java.util.Scanner;

public class ValidarEdad {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int edad = -1;

        while (edad < 0 || edad > 120) {
            System.out.print("Ingresa tu edad (0-120): ");
            edad = scanner.nextInt();

            if (edad < 0 || edad > 120) {
                System.out.println("⚠️ Edad inválida. Intenta de nuevo.");
            }
        }

        System.out.println("✅ Edad registrada: " + edad);
    }
}
```

```
Ingresa tu edad (0-120): -5
⚠️ Edad inválida. Intenta de nuevo.
Ingresa tu edad (0-120): 200
⚠️ Edad inválida. Intenta de nuevo.
Ingresa tu edad (0-120): 22
✅ Edad registrada: 22
```

---

## 6.- Ejemplo — Adivina el número

```java
import java.util.Scanner;

public class AdivinaNumero {
    public static void main(String[] args) {

        int numeroSecreto = 7;
        int intento = 0;
        Scanner scanner = new Scanner(System.in);

        while (intento != numeroSecreto) {
            System.out.print("Adivina el número (1-10): ");
            intento = scanner.nextInt();

            if (intento < numeroSecreto) {
                System.out.println("📉 Muy bajo");
            } else if (intento > numeroSecreto) {
                System.out.println("📈 Muy alto");
            }
        }

        System.out.println("🎉 ¡Correcto! Era el " + numeroSecreto);
    }
}
```

---

## 7.- `do-while` — El primo del `while`

El `do-while` es igual que el `while`, pero la condición se evalúa **al final**. Esto garantiza que el código se ejecute **al menos una vez**.

```java
do {
    // código que se repite
} while (condicion);
```

### Comparación:

| Ciclo      | ¿Cuándo evalúa la condición? | ¿Mínimo de ejecuciones? |
|------------|------------------------------|-------------------------|
| `while`    | Antes de cada vuelta          | 0 (puede no ejecutarse) |
| `do-while` | Después de cada vuelta        | **1** (siempre corre una vez) |

### Ejemplo:

```java
int numero;
Scanner scanner = new Scanner(System.in);

do {
    System.out.print("Ingresa un número positivo: ");
    numero = scanner.nextInt();
} while (numero <= 0);

System.out.println("Número aceptado: " + numero);
```

```
Ingresa un número positivo: -3
Ingresa un número positivo: 0
Ingresa un número positivo: 8
Número aceptado: 8
```

---

## 8.- `break` y `continue` en `while`

### `break` → sale del ciclo inmediatamente

```java
int i = 0;

while (true) { // ciclo "infinito" controlado con break
    System.out.println("i = " + i);
    i++;
    if (i == 4) {
        System.out.println("Saliendo del ciclo");
        break;
    }
}
```
```
i = 0
i = 1
i = 2
i = 3
Saliendo del ciclo
```

### `continue` → salta la vuelta actual

```java
int i = 0;

while (i < 7) {
    i++;
    if (i == 4) continue; // salta el 4
    System.out.print(i + " ");
}
```
```
1 2 3 5 6 7
```

---

## 9.- ⚠️ Cuidado con el bucle infinito

Si olvidamos actualizar la variable dentro del `while`, el ciclo nunca termina:

```java
// ❌ BUCLE INFINITO — nunca actualiza i
int i = 1;
while (i <= 5) {
    System.out.println(i);
    // i++ falta aquí → el programa se cuelga
}
```

```java
// ✅ CORRECTO
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++; // actualización necesaria
}
```

---

## 10.- Ejemplo completo

```java
public class EjemploWhile {

    public static void main(String[] args) {

        // Suma de números pares del 1 al 20
        int i = 1;
        int sumaPares = 0;

        System.out.println("=== Números pares del 1 al 20 ===");
        while (i <= 20) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
                sumaPares += i;
            }
            i++;
        }

        System.out.println("\nSuma de pares: " + sumaPares);

        // Cuenta regresiva con do-while
        System.out.println("\n=== Cuenta regresiva ===");
        int cuenta = 5;
        do {
            System.out.println(cuenta + "...");
            cuenta--;
        } while (cuenta > 0);

        System.out.println("🚀 ¡Despegue!");
    }
}
```

### Salida:
```
=== Números pares del 1 al 20 ===
2 4 6 8 10 12 14 16 18 20
Suma de pares: 110

=== Cuenta regresiva ===
5...
4...
3...
2...
1...
🚀 ¡Despegue!
```

---

## Resumen rápido

| Concepto      | Descripción                                          |
|---------------|------------------------------------------------------|
| `while`       | Repite mientras la condición sea `true`              |
| `do-while`    | Igual que `while` pero ejecuta al menos 1 vez        |
| `break`       | Sale del ciclo inmediatamente                        |
| `continue`    | Salta la vuelta actual y continúa                    |
| Bucle infinito| Ocurre si la condición nunca se vuelve `false`       |
