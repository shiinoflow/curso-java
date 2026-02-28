# Listas `ArrayList` en Java — CRUD, Búsqueda y Ordenamiento

> **Tema 10 del curso** | Anterior: [9- CRUD en Arrays](./9-arreglos_crud_java.md)

---

## 1.- ¿Qué es un `ArrayList`?

`ArrayList` es una **lista dinámica** que, a diferencia del array normal, **crece y decrece automáticamente** según los elementos que agregas o eliminas.

| Característica     | Array          | ArrayList              |
|--------------------|----------------|------------------------|
| Tamaño             | Fijo           | **Dinámico**           |
| CRUD               | Manual         | **Métodos integrados** |
| Requiere import    | No             | **Sí**                  |
| Tipos primitivos   | ✅ `int`, etc. | ❌ Usa `Integer`, `Double`, etc. |

---

## 2.- Importar y crear un `ArrayList`

```java
import java.util.ArrayList;
import java.util.Collections; // para ordenar

public class EjemploLista {
    public static void main(String[] args) {

        // ArrayList vacío
        ArrayList<String> nombres = new ArrayList<>();

        // ArrayList con capacidad inicial (opcional, solo optimización)
        ArrayList<Integer> numeros = new ArrayList<>(20);
    }
}
```

> 💡 Entre `<>` va el **tipo de dato** que guardará la lista. Siempre con mayúscula: `String`, `Integer`, `Double`, `Boolean`.

---

## 3.- C — Create: `add()`

```java
ArrayList<String> productos = new ArrayList<>();

// Agregar al final
productos.add("Laptop");
productos.add("Mouse");
productos.add("Teclado");
productos.add("Monitor");

// Agregar en posición específica
productos.add(1, "Audífonos"); // inserta en índice 1, corre los demás

System.out.println(productos);
// [Laptop, Audífonos, Mouse, Teclado, Monitor]
```

---

## 4.- R — Read: `get()`, `size()`

```java
// Obtener un elemento por índice
String primero = productos.get(0);
System.out.println("Primero: " + primero); // Laptop

// Tamaño de la lista
System.out.println("Total: " + productos.size()); // 5

// Mostrar todos con for-each
System.out.println("=== Todos los productos ===");
for (String p : productos) {
    System.out.println("• " + p);
}

// Mostrar con índice
for (int i = 0; i < productos.size(); i++) {
    System.out.println("[" + i + "] " + productos.get(i));
}
```

---

## 5.- U — Update: `set()`

```java
System.out.println("Antes: " + productos.get(2)); // Mouse

productos.set(2, "Mouse Inalámbrico");

System.out.println("Después: " + productos.get(2)); // Mouse Inalámbrico
```

---

## 6.- D — Delete: `remove()`

```java
// Eliminar por índice
productos.remove(1);                        // elimina el índice 1
System.out.println(productos);

// Eliminar por valor
productos.remove("Monitor");               // elimina la primera coincidencia
System.out.println(productos);

// Vaciar toda la lista
productos.clear();
System.out.println("Lista vacía: " + productos.isEmpty()); // true
```

---

## 7.- Búsqueda: `contains()`, `indexOf()`, `lastIndexOf()`

```java
ArrayList<String> frutas = new ArrayList<>();
frutas.add("Manzana");
frutas.add("Pera");
frutas.add("Mango");
frutas.add("Manzana"); // duplicado

// ¿Existe el elemento?
System.out.println(frutas.contains("Pera"));     // true
System.out.println(frutas.contains("Uva"));      // false

// Primera posición donde aparece
System.out.println(frutas.indexOf("Manzana"));   // 0

// Última posición donde aparece
System.out.println(frutas.lastIndexOf("Manzana")); // 3

// Búsqueda manual (ignorando mayúsculas)
public static int buscar(ArrayList<String> lista, String valor) {
    for (int i = 0; i < lista.size(); i++) {
        if (lista.get(i).equalsIgnoreCase(valor)) return i;
    }
    return -1;
}
```

---

## 8.- Ordenamiento con `Collections`

### Orden ascendente (A → Z / menor → mayor)

```java
import java.util.Collections;

ArrayList<String> nombres = new ArrayList<>();
nombres.add("Carlos");
nombres.add("Ana");
nombres.add("Kevin");
nombres.add("Beatriz");

Collections.sort(nombres);
System.out.println(nombres);
// [Ana, Beatriz, Carlos, Kevin]
```

```java
ArrayList<Integer> numeros = new ArrayList<>();
numeros.add(5); numeros.add(1); numeros.add(8); numeros.add(3);

Collections.sort(numeros);
System.out.println(numeros); // [1, 3, 5, 8]
```

### Orden descendente (Z → A / mayor → menor)

```java
Collections.sort(nombres, Collections.reverseOrder());
System.out.println(nombres);
// [Kevin, Carlos, Beatriz, Ana]
```

### Ordenar por criterio personalizado (`Comparator`)

```java
ArrayList<String> palabras = new ArrayList<>();
palabras.add("Carro");
palabras.add("Auto");
palabras.add("Motocicleta");
palabras.add("Bus");

// Ordenar por longitud de la palabra (más corta primero)
palabras.sort((a, b) -> a.length() - b.length());
System.out.println(palabras);
// [Bus, Auto, Carro, Motocicleta]
```

### Valor máximo y mínimo

```java
ArrayList<Integer> nums = new ArrayList<>();
nums.add(3); nums.add(9); nums.add(1); nums.add(7);

System.out.println("Máximo: " + Collections.max(nums)); // 9
System.out.println("Mínimo: " + Collections.min(nums)); // 1
```

---

## 9.- Métodos útiles de `ArrayList`

| Método                  | Descripción                                      |
|-------------------------|--------------------------------------------------|
| `add(valor)`            | Agrega al final                                  |
| `add(indice, valor)`    | Inserta en posición específica                   |
| `get(indice)`           | Obtiene el elemento en esa posición              |
| `set(indice, valor)`    | Reemplaza el elemento en esa posición            |
| `remove(indice)`        | Elimina por índice                               |
| `remove(obj)`           | Elimina la primera ocurrencia del objeto         |
| `size()`                | Retorna el número de elementos                   |
| `isEmpty()`             | `true` si la lista está vacía                    |
| `contains(valor)`       | `true` si el elemento existe                     |
| `indexOf(valor)`        | Posición de la primera ocurrencia (-1 si no hay) |
| `lastIndexOf(valor)`    | Posición de la última ocurrencia                 |
| `clear()`               | Vacía toda la lista                              |
| `Collections.sort()`   | Ordena ascendentemente                           |
| `Collections.max()`    | Retorna el valor máximo                          |
| `Collections.min()`    | Retorna el valor mínimo                          |

---

## 10.- Ejemplo completo — CRUD de estudiantes

```java
import java.util.ArrayList;
import java.util.Collections;

public class CrudLista {

    static ArrayList<String> estudiantes = new ArrayList<>();

    // CREATE
    static void agregar(String nombre) {
        estudiantes.add(nombre);
        System.out.println("✅ Agregado: " + nombre);
    }

    // READ
    static void mostrar() {
        if (estudiantes.isEmpty()) {
            System.out.println("Lista vacía.");
            return;
        }
        System.out.println("--- Estudiantes (" + estudiantes.size() + ") ---");
        for (int i = 0; i < estudiantes.size(); i++)
            System.out.println("[" + i + "] " + estudiantes.get(i));
    }

    // UPDATE
    static void actualizar(int idx, String nuevo) {
        if (idx >= 0 && idx < estudiantes.size()) {
            System.out.println("✏️ '" + estudiantes.get(idx) + "' → '" + nuevo + "'");
            estudiantes.set(idx, nuevo);
        } else System.out.println("⚠️ Índice inválido.");
    }

    // DELETE
    static void eliminar(String nombre) {
        if (estudiantes.remove(nombre))
            System.out.println("🗑️ Eliminado: " + nombre);
        else
            System.out.println("❌ No encontrado: " + nombre);
    }

    // SEARCH
    static void buscar(String nombre) {
        int idx = -1;
        for (int i = 0; i < estudiantes.size(); i++)
            if (estudiantes.get(i).equalsIgnoreCase(nombre)) { idx = i; break; }

        if (idx != -1)
            System.out.println("🔍 '" + nombre + "' encontrado en posición " + idx);
        else
            System.out.println("🔍 '" + nombre + "' NO encontrado.");
    }

    // SORT
    static void ordenar() {
        Collections.sort(estudiantes);
        System.out.println("🔤 Lista ordenada: " + estudiantes);
    }

    public static void main(String[] args) {

        agregar("Kevin");
        agregar("Ana");
        agregar("Luis");
        agregar("María");
        agregar("Carlos");
        mostrar();

        System.out.println();
        actualizar(2, "Luis Pérez");
        mostrar();

        System.out.println();
        eliminar("María");
        mostrar();

        System.out.println();
        buscar("Ana");
        buscar("Lucía");

        System.out.println();
        ordenar();
    }
}
```

### Salida:
```
✅ Agregado: Kevin
✅ Agregado: Ana
✅ Agregado: Luis
✅ Agregado: María
✅ Agregado: Carlos
--- Estudiantes (5) ---
[0] Kevin
[1] Ana
[2] Luis
[3] María
[4] Carlos

✏️ 'Luis' → 'Luis Pérez'
--- Estudiantes (5) ---
[0] Kevin
[1] Ana
[2] Luis Pérez
[3] María
[4] Carlos

🗑️ Eliminado: María
--- Estudiantes (4) ---
[0] Kevin
[1] Ana
[2] Luis Pérez
[3] Carlos

🔍 'Ana' encontrado en posición 1
🔍 'Lucía' NO encontrado.

🔤 Lista ordenada: [Ana, Carlos, Kevin, Luis Pérez]
```

---

## Resumen rápido

| Operación   | Array (manual)                  | ArrayList (integrado)          |
|-------------|----------------------------------|--------------------------------|
| Create      | `arr[size++] = val`             | `lista.add(val)`               |
| Read        | `arr[i]`                        | `lista.get(i)`                 |
| Update      | `arr[i] = nuevoVal`             | `lista.set(i, nuevoVal)`       |
| Delete      | Correr + `size--`               | `lista.remove(i)` o `.remove(obj)` |
| Buscar      | Bucle + `equals()`              | `.contains()` o `.indexOf()`   |
| Ordenar     | Implementar algoritmo           | `Collections.sort(lista)`      |
| Tamaño      | `.length`                       | `.size()`                      |
