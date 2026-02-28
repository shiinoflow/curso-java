# Variables en Java

## 1.- ¿Qué es una variable?

Una **variable** es un espacio en memoria que reservamos para guardar un dato. Tiene:

- **Un nombre** (identificador) → para referirnos a ella en el código.
- **Un tipo de dato** → indica qué clase de valor puede almacenar.
- **Un valor** → el dato que contiene en un momento dado.

> 💡 Piénsalo como una caja: el tipo define el tamaño de la caja, el nombre es la etiqueta, y el valor es lo que metes dentro.

---

## 2.- Sintaxis básica

```java
tipo nombreVariable = valor;
```

### Ejemplos:

```java
int edad = 25;
double precio = 19.99;
char letra = 'A';
boolean activo = true;
String nombre = "Carlos";
```

---

## 3.- Tipos de datos primitivos

| Tipo      | Tamaño   | Ejemplo de valor    | Descripción                      |
|-----------|----------|---------------------|----------------------------------|
| `byte`    | 8 bits   | `100`               | Entero muy pequeño               |
| `short`   | 16 bits  | `32000`             | Entero pequeño                   |
| `int`     | 32 bits  | `1_000_000`         | Entero estándar (más común)      |
| `long`    | 64 bits  | `9876543210L`       | Entero grande (lleva `L` al final)|
| `float`   | 32 bits  | `3.14f`             | Decimal simple (lleva `f`)        |
| `double`  | 64 bits  | `3.141592653`       | Decimal de doble precisión        |
| `char`    | 16 bits  | `'Z'`               | Un solo carácter (comillas simples)|
| `boolean` | 1 bit    | `true` / `false`    | Verdadero o Falso                |

---

## 4.- Tipo de dato especial: `String`

`String` **no es un tipo primitivo**, es una **clase**. Representa cadenas de texto (texto con varios caracteres).

```java
String saludo = "¡Hola, Mundo!";
String ciudad = "Tegucigalpa";
```

> Nota: `String` va con mayúscula inicial porque es una clase.

---

## 5.- Declaración vs Inicialización

Puedes hacer ambas cosas en un solo paso o por separado:

```java
// Declarar e inicializar al mismo tiempo
int puntaje = 100;

// Declarar primero, asignar después
int nivel;
nivel = 5;
```

---

## 6.- Cambiar el valor de una variable

Las variables pueden cambiar su valor durante la ejecución del programa:

```java
int contador = 0;
contador = 1;
contador = contador + 1; // Ahora vale 2
```

---

## 7.- Variables constantes con `final`

Si no quieres que el valor cambie, usa la palabra clave `final`:

```java
final double PI = 3.14159;
final int MAX_INTENTOS = 3;
```

> Por convención, las constantes se escriben en **MAYÚSCULAS_CON_GUION_BAJO**.

---

## 8.- Reglas para nombrar variables

✅ Debe empezar con una **letra**, `_` o `$`  
✅ Puede tener letras, números, `_` o `$`  
✅ Usa **camelCase**: `miVariable`, `nombreCompleto`  
❌ No puede empezar con número: ~~`1variable`~~  
❌ No puede usar palabras reservadas: ~~`int`, `class`, `return`~~  
❌ Java distingue mayúsculas: `edad` ≠ `Edad`  

---

## 9.- Ejemplo completo

```java
public class EjemploVariables {

    public static void main(String[] args) {

        // Variables de diferentes tipos
        String nombre    = "Ana";
        int    edad      = 22;
        double altura    = 1.65;
        boolean estudia  = true;
        char   inicial   = 'A';

        // Constante
        final String PAIS = "Honduras";

        // Mostrar los valores
        System.out.println("Nombre:  " + nombre);
        System.out.println("Edad:    " + edad);
        System.out.println("Altura:  " + altura + " m");
        System.out.println("Estudia: " + estudia);
        System.out.println("Inicial: " + inicial);
        System.out.println("País:    " + PAIS);

        // Cambiar el valor de una variable
        edad = 23;
        System.out.println("Nuevo cumpleaños: " + edad + " años");
    }
}
```

### Salida esperada:
```
Nombre:  Ana
Edad:    22
Altura:  1.65 m
Estudia: true
Inicial: A
País:    Honduras
Nuevo cumpleaños: 23 años
```

---

## 10.- Resumen rápido

| Concepto        | Descripción                                   |
|-----------------|-----------------------------------------------|
| Variable        | Espacio en memoria para guardar un dato       |
| Tipo            | Define qué clase de dato puede almacenar      |
| `final`         | Hace que el valor no pueda cambiar (constante)|
| `camelCase`     | Convención de nombres: `miVariable`           |
| `String`        | Clase para texto (no es tipo primitivo)       |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [Operadores Lógicos en Java](./2-operadores_logicos_java.md)
