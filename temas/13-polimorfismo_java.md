# Polimorfismo en Java

> **Tema 13 del curso** | Anterior: [12- Abstracción y Herencia](./12-abstraccion_herencia_java.md)

---

## 1.- ¿Qué es el Polimorfismo?

**Polimorfismo** significa "muchas formas". En Java, permite que **un mismo método o referencia se comporte de distinta manera** según el objeto real que lo ejecuta.

> 💡 Un botón "Pagar" en una app puede procesar el pago con tarjeta, PayPal o efectivo. El mismo botón → distintos comportamientos. Eso es polimorfismo.

Hay dos tipos principales:

| Tipo                      | ¿Cuándo ocurre?                          |
|---------------------------|------------------------------------------|
| **Polimorfismo en tiempo de compilación** | Sobrecarga de métodos (`overloading`) |
| **Polimorfismo en tiempo de ejecución**   | Sobreescritura de métodos (`overriding`) |

---

## 2.- Polimorfismo por sobreescritura (`@Override`)

El tipo más importante. Una referencia del tipo **padre** puede apuntar a un objeto **hijo**, y ejecutar el método correcto según el objeto real.

```java
abstract class Animal {
    String nombre;

    public Animal(String nombre) { this.nombre = nombre; }

    public abstract void hacerSonido(); // cada animal lo implementa distinto
}

class Perro extends Animal {
    public Perro(String nombre) { super(nombre); }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + ": ¡Guau guau! 🐕");
    }
}

class Gato extends Animal {
    public Gato(String nombre) { super(nombre); }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + ": ¡Miau! 🐈");
    }
}

class Vaca extends Animal {
    public Vaca(String nombre) { super(nombre); }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + ": ¡Muuu! 🐄");
    }
}
```

---

## 3.- Referencia polimórfica

```java
public static void main(String[] args) {

    // Variable tipo PADRE, objeto tipo HIJO → polimorfismo
    Animal a1 = new Perro("Rex");
    Animal a2 = new Gato("Whiskers");
    Animal a3 = new Vaca("Lola");

    a1.hacerSonido(); // Rex: ¡Guau guau! 🐕
    a2.hacerSonido(); // Whiskers: ¡Miau! 🐈
    a3.hacerSonido(); // Lola: ¡Muuu! 🐄
}
```

> Aunque las tres variables son de tipo `Animal`, cada una ejecuta la versión correcta de `hacerSonido()` según el objeto real. Eso es polimorfismo en tiempo de ejecución.

---

## 4.- Polimorfismo con arrays y listas

El poder real del polimorfismo se ve cuando usas colecciones del tipo padre:

```java
// Array de tipo Animal que almacena distintos animales
Animal[] granja = {
    new Perro("Rex"),
    new Gato("Misi"),
    new Vaca("Luna"),
    new Perro("Thor"),
    new Gato("Felix")
};

System.out.println("=== Sonidos de la granja ===");
for (Animal animal : granja) {
    animal.hacerSonido(); // cada uno llama su propia versión
}
```

### Salida:
```
=== Sonidos de la granja ===
Rex: ¡Guau guau! 🐕
Misi: ¡Miau! 🐈
Luna: ¡Muuu! 🐄
Thor: ¡Guau guau! 🐕
Felix: ¡Miau! 🐈
```

---

## 5.- Polimorfismo por sobrecarga (`overloading`)

Mismo nombre de método, distintos parámetros. El compilador decide cuál usar:

```java
public class Calculadora {

    // Versión 1: dos enteros
    public int sumar(int a, int b) {
        return a + b;
    }

    // Versión 2: tres enteros
    public int sumar(int a, int b, int c) {
        return a + b + c;
    }

    // Versión 3: dos doubles
    public double sumar(double a, double b) {
        return a + b;
    }

    // Versión 4: String (concatenar)
    public String sumar(String a, String b) {
        return a + b;
    }
}
```

```java
Calculadora calc = new Calculadora();

System.out.println(calc.sumar(2, 3));          // 5
System.out.println(calc.sumar(1, 2, 3));       // 6
System.out.println(calc.sumar(1.5, 2.5));      // 4.0
System.out.println(calc.sumar("Hola ", "Java")); // Hola Java
```

---

## 6.- `instanceof` — Verificar el tipo real del objeto

```java
Animal a = new Perro("Rex");

if (a instanceof Perro) {
    System.out.println(a.nombre + " es un Perro 🐕");
} else if (a instanceof Gato) {
    System.out.println(a.nombre + " es un Gato 🐈");
}
```

Útil cuando necesitas acceder a métodos **específicos** de la subclase:

```java
Animal a = new Perro("Rex");

if (a instanceof Perro perro) {      // Java 16+: pattern matching
    perro.buscarHueso();             // método solo de Perro
}
```

---

## 7.- Polimorfismo con interfaces

Las interfaces también permiten polimorfismo. Distintos objetos pueden implementar la misma interfaz y comportarse diferente:

```java
interface Pagable {
    void procesarPago(double monto);
}

class TarjetaCredito implements Pagable {
    @Override
    public void procesarPago(double monto) {
        System.out.printf("💳 Pago con tarjeta de crédito: $%.2f%n", monto);
    }
}

class PayPal implements Pagable {
    @Override
    public void procesarPago(double monto) {
        System.out.printf("🅿️  Pago con PayPal: $%.2f%n", monto);
    }
}

class Efectivo implements Pagable {
    @Override
    public void procesarPago(double monto) {
        System.out.printf("💵 Pago en efectivo: $%.2f%n", monto);
    }
}
```

```java
// El mismo método acepta cualquier tipo de pago
public static void realizarCompra(Pagable metodoPago, double total) {
    System.out.println("Procesando compra...");
    metodoPago.procesarPago(total);
    System.out.println("✅ Compra completada.");
}

public static void main(String[] args) {
    realizarCompra(new TarjetaCredito(), 150.00);
    System.out.println();
    realizarCompra(new PayPal(), 89.99);
    System.out.println();
    realizarCompra(new Efectivo(), 25.00);
}
```

```
Procesando compra...
💳 Pago con tarjeta de crédito: $150.00
✅ Compra completada.

Procesando compra...
🅿️  Pago con PayPal: $89.99
✅ Compra completada.

Procesando compra...
💵 Pago en efectivo: $25.00
✅ Compra completada.
```

---

## 8.- Los 4 pilares de la POO — resumen

| Pilar             | ¿Qué hace?                                              | Tema      |
|-------------------|---------------------------------------------------------|-----------|
| **Encapsulamiento**| Oculta datos con `private`, expone con getters/setters | Tema 11   |
| **Abstracción**   | Muestra solo lo esencial (`abstract`, `interface`)      | Tema 12   |
| **Herencia**      | Una clase hereda de otra con `extends`                  | Tema 12   |
| **Polimorfismo**  | Mismo método, distintos comportamientos                 | Tema 13   |

---

## 9.- Ejemplo completo — Sistema de empleados polimórfico

```java
abstract class Empleado {
    protected String nombre;

    public Empleado(String nombre) { this.nombre = nombre; }

    // Cada tipo de empleado calcula su salario de forma distinta
    public abstract double calcularSalario();

    public void mostrar() {
        System.out.printf("%-15s → $%.2f%n", nombre, calcularSalario());
    }
}

class EmpleadoFijo extends Empleado {
    private double salarioMensual;

    public EmpleadoFijo(String nombre, double salario) {
        super(nombre);
        this.salarioMensual = salario;
    }

    @Override
    public double calcularSalario() {
        return salarioMensual;
    }
}

class EmpleadoPorHoras extends Empleado {
    private double tarifaHora;
    private int    horasTrabajadas;

    public EmpleadoPorHoras(String nombre, double tarifa, int horas) {
        super(nombre);
        this.tarifaHora      = tarifa;
        this.horasTrabajadas = horas;
    }

    @Override
    public double calcularSalario() {
        return tarifaHora * horasTrabajadas;
    }
}

class EmpleadoComision extends Empleado {
    private double salarioBase;
    private double ventas;
    private double comision; // porcentaje

    public EmpleadoComision(String nombre, double base, double ventas, double comision) {
        super(nombre);
        this.salarioBase = base;
        this.ventas      = ventas;
        this.comision    = comision;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (ventas * comision / 100);
    }
}

public class Main {
    public static void main(String[] args) {

        // Array polimórfico de tipo Empleado
        Empleado[] empleados = {
            new EmpleadoFijo("Ana García",       2500.00),
            new EmpleadoPorHoras("Luis Pérez",   15.50, 160),
            new EmpleadoComision("Kevin Arauz",  1000.00, 8000.00, 5.0),
        };

        System.out.println("=== Nómina Mensual ===");
        double totalNomina = 0;
        for (Empleado e : empleados) {
            e.mostrar(); // polimorfismo: cada uno calcula distinto
            totalNomina += e.calcularSalario();
        }
        System.out.printf("%nTotal nómina: $%.2f%n", totalNomina);
    }
}
```

### Salida:
```
=== Nómina Mensual ===
Ana García      → $2500.00
Luis Pérez      → $2480.00
Kevin Arauz     → $1400.00

Total nómina: $6380.00
```

---

## Resumen rápido

| Concepto                | Descripción                                              |
|-------------------------|----------------------------------------------------------|
| Polimorfismo            | Mismo método, distintos comportamientos                  |
| Sobreescritura (`@Override`) | Subclase redefine el método del padre             |
| Sobrecarga (`overloading`)   | Mismo nombre, distintos parámetros               |
| Referencia polimórfica  | Variable tipo padre apunta a objeto hijo                 |
| `instanceof`            | Verifica el tipo real del objeto en tiempo de ejecución  |
| Polimorfismo con interfaz | Distintas clases implementan los mismos métodos        |
