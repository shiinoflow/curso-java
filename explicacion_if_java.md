# La sentencia `if` en Java

La sentencia `if` es una de las estructuras de control más importantes en Java. Permite que tu programa tome decisiones basadas en condiciones. Si la condición se evalúa como `true` (verdadera), se ejecuta un bloque de código específico; si es `false` (falsa), el programa salta ese bloque.

## Operadores Lógicos

Antes de usar `if`, es importante entender cómo construir condiciones complejas usando operadores lógicos. Estos nos permiten evaluar múltiples expresiones booleanas (`true`/`false`) a la vez.

| Operador | Nombre | Descripción | Ejemplo (`a = true`, `b = false`) | Resultado |
| :---: | :---: | :--- | :--- | :--- |
| `&&` | **AND** (Y) | Devuelve `true` solo si **ambas** condiciones son verdaderas. | `a && b` | `false` |
| `\|\|` | **OR** (O) | Devuelve `true` si **al menos una** condición es verdadera. | `a \|\| b` | `true` |
| `!` | **NOT** (NO) | Invierte el valor booleano. Si es `true` pasa a `false` y viceversa. | `!a` | `false` |

Estos operadores se combinan frecuentemente con los **operadores de comparación** (`==`, `!=`, `>`, `<`, `>=`, `<=`) para formar las condiciones dentro del `if`.

## Sintaxis

La estructura básica es la siguiente:

```java
if (condicion) {
    // Código que se ejecuta si la condición es verdadera
}
```

También es común usarla junto con `else` (si no) o `else if` (si no, si):

```java
if (condicion) {
    // Se ejecuta si es verdadero
} else {
    // Se ejecuta si es falso
}
```

## Ejemplo Práctico

Imagina que queremos verificar si una persona es mayor de edad para votar.

```java
public class EjemploIf {
    public static void main(String[] args) {
        int edad = 16; // Cambia este valor para probar diferentes resultados

        // Verificamos la condición
        if (edad >= 18) {
            System.out.println("Es mayor de edad, puede votar.");
        } else {
            System.out.println("Es menor de edad, aún no puede votar.");
        }
        
        System.out.println("Fin del programa.");
    }
}
```

### Explicación del ejemplo:
1. Definimos una variable `int edad = 16`.
2. La sentencia `if (edad >= 18)` evalúa si el valor de `edad` es mayor o igual a 18.
3. Como 16 no es mayor o igual a 18, la condición es **falsa**.
4. El programa salta al bloque `else` y ejecuta: `System.out.println("Es menor de edad, aún no puede votar.");`.
