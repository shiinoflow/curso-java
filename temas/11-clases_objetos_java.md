# Clases y Objetos en Java

> **Tema 11 del curso** | Anterior: [10- ArrayList](./10-listas_arraylist_java.md) | Siguiente: [12- Abstracción y Herencia](./12-abstraccion_herencia_java.md)

---

## 1.- ¿Qué es la Programación Orientada a Objetos (POO)?

La **POO** es un paradigma de programación que organiza el código en **objetos**, que combinan datos (atributos) y comportamientos (métodos).

| Concepto   | Descripción                                         |
|------------|-----------------------------------------------------|
| **Clase**  | El molde o plantilla para crear objetos             |
| **Objeto** | Una instancia concreta creada a partir de una clase |
| **Atributo**| Datos/propiedades que tiene el objeto              |
| **Método** | Acciones que puede realizar el objeto               |

> 💡 Una **clase** es como el plano de una casa. Un **objeto** es la casa construida a partir de ese plano.

---

## 2.- Declarar una clase

```java
public class Persona {

    // Atributos (datos)
    String nombre;
    int edad;
    String correo;

    // Método (comportamiento)
    void saludar() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años.");
    }
}
```

---

## 3.- Crear objetos (`new`)

```java
public class Main {
    public static void main(String[] args) {

        // Crear objeto de la clase Persona
        Persona p1 = new Persona();

        // Asignar valores a sus atributos
        p1.nombre = "Ana";
        p1.edad   = 22;
        p1.correo = "ana@mail.com";

        // Llamar a un método
        p1.saludar(); // Hola, soy Ana y tengo 22 años.

        // Crear otro objeto distinto
        Persona p2 = new Persona();
        p2.nombre = "Carlos";
        p2.edad   = 30;
        p2.saludar(); // Hola, soy Carlos y tengo 30 años.
    }
}
```

> Cada objeto es **independiente**: cambiar `p1` no afecta a `p2`.

---

## 4.- Constructor

El **constructor** es un método especial que se ejecuta automáticamente al crear un objeto. Sirve para inicializar sus atributos desde el principio.

```java
public class Persona {

    String nombre;
    int    edad;
    String correo;

    // Constructor
    public Persona(String nombre, int edad, String correo) {
        this.nombre = nombre; // this.nombre = atributo, nombre = parámetro
        this.edad   = edad;
        this.correo = correo;
    }

    void saludar() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años.");
    }
}
```

```java
// Ahora al crear el objeto se pasan los valores directamente
Persona p1 = new Persona("Ana", 22, "ana@mail.com");
Persona p2 = new Persona("Carlos", 30, "carlos@mail.com");

p1.saludar(); // Hola, soy Ana y tengo 22 años.
p2.saludar(); // Hola, soy Carlos y tengo 30 años.
```

---

## 5.- Encapsulamiento: `private` + Getters/Setters

El **encapsulamiento** protege los atributos de acceso directo desde fuera de la clase, usando `private`, y se exponen mediante métodos `get` y `set`.

```java
public class CuentaBancaria {

    private String titular;
    private double saldo;     // ← privado: no se puede acceder directamente

    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo   = saldoInicial;
    }

    // Getter — leer el saldo
    public double getSaldo() {
        return saldo;
    }

    // Setter — depositar (con validación)
    public void depositar(double monto) {
        if (monto > 0) saldo += monto;
        else System.out.println("⚠️ Monto inválido.");
    }

    // Retirar con validación
    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) saldo -= monto;
        else System.out.println("⚠️ Fondos insuficientes o monto inválido.");
    }

    public void mostrarInfo() {
        System.out.printf("Cuenta de %s → Saldo: $%.2f%n", titular, saldo);
    }
}
```

```java
CuentaBancaria cuenta = new CuentaBancaria("Kevin", 1000.0);
cuenta.mostrarInfo();       // Cuenta de Kevin → Saldo: $1000.00

cuenta.depositar(500);
cuenta.mostrarInfo();       // Cuenta de Kevin → Saldo: $1500.00

cuenta.retirar(200);
cuenta.mostrarInfo();       // Cuenta de Kevin → Saldo: $1300.00

// cuenta.saldo = -999;     ← ❌ ERROR: saldo es privado
```

---

## 6.- Múltiples constructores (sobrecarga)

Una clase puede tener **varios constructores** con distintos parámetros:

```java
public class Producto {

    String nombre;
    double precio;
    int    stock;

    // Constructor completo
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock  = stock;
    }

    // Constructor sin stock (valor por defecto 0)
    public Producto(String nombre, double precio) {
        this(nombre, precio, 0); // llama al constructor completo
    }

    void mostrar() {
        System.out.printf("%-12s $%.2f  stock: %d%n", nombre, precio, stock);
    }
}
```

```java
Producto p1 = new Producto("Laptop",  899.99, 10);
Producto p2 = new Producto("USB Hub",  19.99);    // stock = 0

p1.mostrar(); // Laptop       $899.99  stock: 10
p2.mostrar(); // USB Hub      $19.99   stock: 0
```

---

## 7.- Método `toString()`

Sirve para definir cómo se representa el objeto como texto:

```java
public class Persona {

    String nombre;
    int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad   = edad;
    }

    @Override
    public String toString() {
        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
    }
}
```

```java
Persona p = new Persona("Ana", 22);
System.out.println(p); // Persona{nombre='Ana', edad=22}
```

---

## 8.- Clase con ArrayList de objetos

```java
import java.util.ArrayList;

public class Salon {

    ArrayList<Persona> estudiantes = new ArrayList<>();

    void agregar(Persona p) {
        estudiantes.add(p);
        System.out.println("✅ Agregado: " + p.nombre);
    }

    void mostrarTodos() {
        System.out.println("=== Lista del salón ===");
        for (Persona p : estudiantes) {
            System.out.println("• " + p.nombre + " (" + p.edad + " años)");
        }
    }
}
```

```java
Salon salon = new Salon();
salon.agregar(new Persona("Ana",   22));
salon.agregar(new Persona("Luis",  19));
salon.agregar(new Persona("Kevin", 23));
salon.mostrarTodos();
```

```
✅ Agregado: Ana
✅ Agregado: Luis
✅ Agregado: Kevin
=== Lista del salón ===
• Ana (22 años)
• Luis (19 años)
• Kevin (23 años)
```

---

## 9.- Ejemplo completo — Sistema de empleados

```java
public class Empleado {

    private String nombre;
    private String puesto;
    private double salario;

    public Empleado(String nombre, String puesto, double salario) {
        this.nombre  = nombre;
        this.puesto  = puesto;
        this.salario = salario;
    }

    public void aumentarSalario(double porcentaje) {
        salario += salario * (porcentaje / 100);
        System.out.printf("💰 Aumento del %.0f%% → nuevo salario: $%.2f%n", porcentaje, salario);
    }

    public void mostrar() {
        System.out.printf("%-12s | %-15s | $%.2f%n", nombre, puesto, salario);
    }

    public static void main(String[] args) {
        Empleado e1 = new Empleado("Ana García",  "Desarrolladora", 2500.00);
        Empleado e2 = new Empleado("Luis Pérez",  "Diseñador",      2000.00);
        Empleado e3 = new Empleado("Kevin Arauz", "DBA",            2200.00);

        System.out.println("=== Empleados ===");
        e1.mostrar();
        e2.mostrar();
        e3.mostrar();

        System.out.println();
        e1.aumentarSalario(10); // 10% de aumento
        e3.aumentarSalario(15); // 15% de aumento
    }
}
```

### Salida:
```
=== Empleados ===
Ana García   | Desarrolladora  | $2500.00
Luis Pérez   | Diseñador       | $2000.00
Kevin Arauz  | DBA             | $2200.00

💰 Aumento del 10% → nuevo salario: $2750.00
💰 Aumento del 15% → nuevo salario: $2530.00
```

---

## Resumen rápido

| Concepto          | Descripción                                        |
|-------------------|----------------------------------------------------|
| Clase             | Plantilla/molde que define atributos y métodos     |
| Objeto            | Instancia creada con `new NombreClase()`           |
| Atributo          | Variable que pertenece a la clase                  |
| Método            | Función que pertenece a la clase                   |
| Constructor       | Inicializa el objeto al crearlo                    |
| `this`            | Referencia al objeto actual                        |
| `private`         | Atributo solo accesible dentro de la clase         |
| Getter/Setter     | Métodos para leer/escribir atributos privados      |
| `toString()`      | Define cómo se muestra el objeto como texto        |
| Encapsulamiento   | Ocultar datos y exponer solo lo necesario          |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [Abstracción y Herencia](./12-abstraccion_herencia_java.md)
