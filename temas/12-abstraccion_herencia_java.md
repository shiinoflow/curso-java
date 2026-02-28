# Abstracción y Herencia en Java

> **Tema 12 del curso** | Anterior: [11- Clases y Objetos](./11-clases_objetos_java.md) | Siguiente: [13- Polimorfismo](./13-polimorfismo_java.md)

---

## 1.- ¿Qué es la Abstracción?

La **abstracción** consiste en mostrar solo lo **esencial** de un objeto y ocultar los detalles internos de implementación.

> 💡 Cuando conduces un carro, usas el volante y el acelerador. No necesitas saber cómo funciona el motor por dentro. Eso es abstracción.

En Java la abstracción se logra con:
- **Clases abstractas** (`abstract class`)
- **Interfaces** (`interface`)

---

## 2.- Clase abstracta

Una **clase abstracta** es una clase que **no se puede instanciar directamente** (no se puede crear un objeto de ella). Define una estructura base que las clases hijas deben completar.

```java
public abstract class Figura {

    String color;

    public Figura(String color) {
        this.color = color;
    }

    // Método abstracto: no tiene cuerpo, las subclases DEBEN implementarlo
    public abstract double calcularArea();

    // Método concreto: sí tiene cuerpo, lo heredan todas las subclases
    public void mostrarColor() {
        System.out.println("Color: " + color);
    }
}
```

> ❌ `Figura figura = new Figura("Rojo");` → **ERROR**, no se puede instanciar.  
> ✅ Solo se puede instanciar una subclase que implemente `calcularArea()`.

---

## 3.- ¿Qué es la Herencia?

La **herencia** permite que una clase (`hija`) herede los atributos y métodos de otra clase (`padre`), evitando duplicar código.

```
         Figura (padre / abstracta)
        /        \
   Circulo      Rectangulo      (hijas / concretas)
```

---

## 4.- Herencia con `extends`

```java
// Clase hija: Circulo
public class Circulo extends Figura {

    double radio;

    public Circulo(String color, double radio) {
        super(color); // llama al constructor del padre
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public String toString() {
        return String.format("Círculo | Radio: %.1f | Área: %.2f | Color: %s",
                              radio, calcularArea(), color);
    }
}
```

```java
// Clase hija: Rectangulo
public class Rectangulo extends Figura {

    double ancho, alto;

    public Rectangulo(String color, double ancho, double alto) {
        super(color);
        this.ancho = ancho;
        this.alto  = alto;
    }

    @Override
    public double calcularArea() {
        return ancho * alto;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo | %,.1f x %,.1f | Área: %.2f | Color: %s",
                              ancho, alto, calcularArea(), color);
    }
}
```

```java
// Uso
Circulo    c = new Circulo("Rojo", 5);
Rectangulo r = new Rectangulo("Azul", 4, 6);

System.out.println(c); // Círculo | Radio: 5.0 | Área: 78.54 | Color: Rojo
System.out.println(r); // Rectángulo | 4.0 x 6.0 | Área: 24.00 | Color: Azul

c.mostrarColor(); // Color: Rojo  ← heredado del padre
```

---

## 5.- La palabra clave `super`

`super` se usa para acceder a los miembros de la clase padre:

```java
// Llamar al constructor del padre
super(parametros);

// Llamar a un método del padre (desde la subclase)
super.nombreMetodo();
```

---

## 6.- Herencia en cadena (multinivel)

```
Animal → Mamifero → Perro
```

```java
public class Animal {
    String nombre;
    public Animal(String nombre) { this.nombre = nombre; }
    public void respirar() { System.out.println(nombre + " está respirando."); }
}

public class Mamifero extends Animal {
    public Mamifero(String nombre) { super(nombre); }
    public void amamantar() { System.out.println(nombre + " amamanta a sus crías."); }
}

public class Perro extends Mamifero {
    String raza;
    public Perro(String nombre, String raza) {
        super(nombre);
        this.raza = raza;
    }
    public void ladrar() { System.out.println(nombre + " ¡Guau guau!"); }
}
```

```java
Perro p = new Perro("Rex", "Labrador");
p.respirar();   // heredado de Animal
p.amamantar();  // heredado de Mamifero
p.ladrar();     // propio de Perro
```

```
Rex está respirando.
Rex amamanta a sus crías.
Rex ¡Guau guau!
```

---

## 7.- Interfaz (`interface`)

Una **interfaz** es una forma más estricta de abstracción: define un **contrato** que las clases deben cumplir. Solo declara métodos (sin cuerpo), y la clase que la implementa los define.

```java
public interface Volador {
    void volar();           // sin cuerpo → obligatorio implementar
    void aterrizar();
}

public interface Nadador {
    void nadar();
}
```

```java
// Pato puede volar Y nadar (implementa 2 interfaces)
public class Pato extends Animal implements Volador, Nadador {

    public Pato(String nombre) { super(nombre); }

    @Override
    public void volar() {
        System.out.println(nombre + " está volando 🦆");
    }

    @Override
    public void aterrizar() {
        System.out.println(nombre + " aterrizó.");
    }

    @Override
    public void nadar() {
        System.out.println(nombre + " está nadando 🌊");
    }
}
```

```java
Pato pato = new Pato("Donald");
pato.volar();     // Donald está volando 🦆
pato.nadar();     // Donald está nadando 🌊
pato.aterrizar(); // Donald aterrizó.
```

---

## 8.- Clase abstracta vs Interfaz

| Característica           | Clase Abstracta           | Interfaz                       |
|--------------------------|---------------------------|--------------------------------|
| Instanciar              | ❌ No                     | ❌ No                          |
| Métodos con cuerpo      | ✅ Sí                     | ✅ (desde Java 8, con `default`)|
| Atributos               | ✅ Sí                     | Solo constantes (`final static`)|
| Herencia múltiple       | ❌ Solo una clase padre   | ✅ Implementa varias interfaces |
| Uso ideal               | Compartir código base     | Definir un contrato/capacidad  |

---

## 9.- `@Override`

Cuando una subclase redefine un método del padre, se usa `@Override` para indicarlo explícitamente:

```java
@Override
public double calcularArea() {
    return Math.PI * radio * radio;
}
```

> ✅ Ventajas: el compilador verifica que realmente estás sobreescribiendo un método del padre. Si te equivocas en el nombre, da error.

---

## 10.- Ejemplo completo — Jerarquía de vehículos

```java
// Clase abstracta base
abstract class Vehiculo {
    protected String marca;
    protected int    anio;

    public Vehiculo(String marca, int anio) {
        this.marca = marca;
        this.anio  = anio;
    }

    public abstract void descripcion();

    public void mostrarInfo() {
        System.out.println("Marca: " + marca + " | Año: " + anio);
    }
}

// Subclase Auto
class Auto extends Vehiculo {
    private int puertas;

    public Auto(String marca, int anio, int puertas) {
        super(marca, anio);
        this.puertas = puertas;
    }

    @Override
    public void descripcion() {
        System.out.println("🚗 Auto: " + marca + " (" + anio + ") - " + puertas + " puertas");
    }
}

// Subclase Moto
class Moto extends Vehiculo {
    private String tipo; // deportiva, clásica, etc.

    public Moto(String marca, int anio, String tipo) {
        super(marca, anio);
        this.tipo = tipo;
    }

    @Override
    public void descripcion() {
        System.out.println("🏍️ Moto: " + marca + " (" + anio + ") - Tipo: " + tipo);
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        Auto a = new Auto("Toyota", 2022, 4);
        Moto m = new Moto("Yamaha", 2021, "Deportiva");

        a.descripcion();
        a.mostrarInfo(); // heredado de Vehiculo

        System.out.println();

        m.descripcion();
        m.mostrarInfo(); // heredado de Vehiculo
    }
}
```

### Salida:
```
🚗 Auto: Toyota (2022) - 4 puertas
Marca: Toyota | Año: 2022

🏍️ Moto: Yamaha (2021) - Tipo: Deportiva
Marca: Yamaha | Año: 2021
```

---

## Resumen rápido

| Concepto          | Descripción                                               |
|-------------------|-----------------------------------------------------------|
| Abstracción       | Mostrar solo lo esencial, ocultar detalles internos       |
| `abstract class`  | Clase que no se puede instanciar, puede tener métodos abstractos |
| Herencia          | Una clase hija hereda de una clase padre con `extends`    |
| `super`           | Accede al constructor o métodos del padre                 |
| `@Override`       | Indica que se sobreescribe un método del padre            |
| `interface`       | Contrato que obliga a implementar ciertos métodos         |
| `implements`      | Una clase cumple el contrato de una interfaz              |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [Polimorfismo](./13-polimorfismo_java.md)
