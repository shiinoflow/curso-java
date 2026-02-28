# CRUD en Arreglos (Arrays) — Java

> **Tema 9 del curso** | Anterior: [7- Arreglos](./7-arreglos_java.md) | Siguiente: [10- Listas ArrayList](./10-listas_arraylist_java.md)

---

## 1.- ¿Qué es CRUD?

**CRUD** son las 4 operaciones básicas sobre cualquier colección de datos:

| Letra | Operación | Descripción                         |
|-------|-----------|-------------------------------------|
| **C** | Create    | Agregar un nuevo elemento           |
| **R** | Read      | Leer / mostrar elementos            |
| **U** | Update    | Modificar un elemento existente     |
| **D** | Delete    | Eliminar un elemento                |

> ⚠️ Los arrays en Java tienen **tamaño fijo**. Por eso simulamos el CRUD usando un contador de elementos válidos (`size`) en lugar del tamaño total del array.

---

## 2.- C — Create (Agregar)

Agregamos un nuevo elemento en la siguiente posición disponible:

```java
String[] productos = new String[10]; // capacidad máxima: 10
int size = 0; // cuántos elementos tenemos actualmente

// Agregar elementos
productos[size] = "Laptop";   size++;
productos[size] = "Mouse";    size++;
productos[size] = "Teclado";  size++;

System.out.println("Total de productos: " + size);
```

```
Total de productos: 3
```

```java
// Validar que no se pase de la capacidad
public static boolean agregar(String[] arr, int[] size, String valor) {
    if (size[0] >= arr.length) {
        System.out.println("⚠️ Array lleno, no se puede agregar.");
        return false;
    }
    arr[size[0]] = valor;
    size[0]++;
    System.out.println("✅ '" + valor + "' agregado en posición " + (size[0] - 1));
    return true;
}
```

---

## 3.- R — Read (Leer / Mostrar)

### Mostrar todos los elementos:

```java
public static void mostrarTodos(String[] arr, int size) {
    if (size == 0) {
        System.out.println("El arreglo está vacío.");
        return;
    }
    System.out.println("=== Lista de elementos ===");
    for (int i = 0; i < size; i++) {
        System.out.println("[" + i + "] " + arr[i]);
    }
}
```

### Leer por índice:

```java
public static String leerPorIndice(String[] arr, int size, int indice) {
    if (indice < 0 || indice >= size) {
        System.out.println("⚠️ Índice fuera de rango.");
        return null;
    }
    return arr[indice];
}
```

---

## 4.- U — Update (Actualizar)

Modificamos el valor en una posición específica:

```java
public static boolean actualizar(String[] arr, int size, int indice, String nuevoValor) {
    if (indice < 0 || indice >= size) {
        System.out.println("⚠️ Índice fuera de rango.");
        return false;
    }
    String anterior = arr[indice];
    arr[indice] = nuevoValor;
    System.out.println("✅ Actualizado: '" + anterior + "' → '" + nuevoValor + "'");
    return true;
}
```

```java
// Uso:
actualizar(productos, size, 1, "Mouse Inalámbrico");
// ✅ Actualizado: 'Mouse' → 'Mouse Inalámbrico'
```

---

## 5.- D — Delete (Eliminar)

Como el array tiene tamaño fijo, eliminamos "corriendo" los elementos hacia la izquierda para llenar el hueco:

```java
public static boolean eliminar(String[] arr, int[] size, int indice) {
    if (indice < 0 || indice >= size[0]) {
        System.out.println("⚠️ Índice fuera de rango.");
        return false;
    }
    String eliminado = arr[indice];

    // Correr elementos a la izquierda
    for (int i = indice; i < size[0] - 1; i++) {
        arr[i] = arr[i + 1];
    }
    arr[size[0] - 1] = null; // limpiar última posición
    size[0]--;

    System.out.println("🗑️ Eliminado: '" + eliminado + "'");
    return true;
}
```

### Visualización del proceso:

```
Antes:   ["Laptop", "Mouse", "Teclado", null]    → eliminar índice 1
         → corre Teclado a índice 1
Después: ["Laptop", "Teclado", null, null]
```

---

## 6.- Búsqueda lineal (Search)

Recorre el array comparando cada elemento hasta encontrar el buscado:

```java
public static int buscar(String[] arr, int size, String valor) {
    for (int i = 0; i < size; i++) {
        if (arr[i].equalsIgnoreCase(valor)) {
            return i; // retorna el índice donde se encontró
        }
    }
    return -1; // no encontrado
}
```

```java
// Uso:
int pos = buscar(productos, size, "Teclado");
if (pos != -1) {
    System.out.println("✅ Encontrado en posición: " + pos);
} else {
    System.out.println("❌ No encontrado.");
}
```

---

## 7.- Búsqueda binaria (array ordenado)

Más eficiente que la lineal, pero **requiere que el array esté ordenado**:

```java
public static int busquedaBinaria(int[] arr, int objetivo) {
    int inicio = 0;
    int fin    = arr.length - 1;

    while (inicio <= fin) {
        int medio = (inicio + fin) / 2;

        if (arr[medio] == objetivo) {
            return medio;               // encontrado
        } else if (arr[medio] < objetivo) {
            inicio = medio + 1;         // buscar en la mitad derecha
        } else {
            fin = medio - 1;            // buscar en la mitad izquierda
        }
    }
    return -1; // no encontrado
}
```

```java
int[] nums = {1, 3, 5, 7, 9, 11, 13};
System.out.println(busquedaBinaria(nums, 7));  //  3
System.out.println(busquedaBinaria(nums, 6));  // -1
```

---

## 8.- Ejemplo completo — CRUD de productos

```java
public class CrudArray {

    static String[] productos = new String[10];
    static int size = 0;

    // CREATE
    static void agregar(String p) {
        if (size < productos.length) {
            productos[size++] = p;
            System.out.println("✅ Agregado: " + p);
        } else System.out.println("⚠️ Array lleno.");
    }

    // READ
    static void mostrar() {
        System.out.println("--- Productos ---");
        for (int i = 0; i < size; i++)
            System.out.println("[" + i + "] " + productos[i]);
    }

    // UPDATE
    static void actualizar(int idx, String nuevo) {
        if (idx >= 0 && idx < size) {
            System.out.println("✏️ '" + productos[idx] + "' → '" + nuevo + "'");
            productos[idx] = nuevo;
        } else System.out.println("⚠️ Índice inválido.");
    }

    // DELETE
    static void eliminar(int idx) {
        if (idx >= 0 && idx < size) {
            System.out.println("🗑️ Eliminado: " + productos[idx]);
            for (int i = idx; i < size - 1; i++)
                productos[i] = productos[i + 1];
            productos[--size] = null;
        } else System.out.println("⚠️ Índice inválido.");
    }

    // SEARCH
    static int buscar(String nombre) {
        for (int i = 0; i < size; i++)
            if (productos[i].equalsIgnoreCase(nombre)) return i;
        return -1;
    }

    public static void main(String[] args) {

        agregar("Laptop");
        agregar("Mouse");
        agregar("Teclado");
        agregar("Monitor");
        mostrar();

        System.out.println();
        actualizar(1, "Mouse Inalámbrico");
        mostrar();

        System.out.println();
        eliminar(2);
        mostrar();

        System.out.println();
        int pos = buscar("Monitor");
        System.out.println(pos != -1 ? "🔍 'Monitor' en posición " + pos : "❌ No encontrado");
    }
}
```

### Salida:
```
✅ Agregado: Laptop
✅ Agregado: Mouse
✅ Agregado: Teclado
✅ Agregado: Monitor
--- Productos ---
[0] Laptop
[1] Mouse
[2] Teclado
[3] Monitor

✏️ 'Mouse' → 'Mouse Inalámbrico'
--- Productos ---
[0] Laptop
[1] Mouse Inalámbrico
[2] Teclado
[3] Monitor

🗑️ Eliminado: Teclado
--- Productos ---
[0] Laptop
[1] Mouse Inalámbrico
[2] Monitor

🔍 'Monitor' en posición 2
```

---

## Resumen rápido

| Operación      | Cómo se hace en Array                              |
|----------------|----------------------------------------------------|
| Create         | `arr[size] = valor; size++;`                       |
| Read (todos)   | Recorrer con `for` hasta `size`                    |
| Read (índice)  | `arr[indice]`                                      |
| Update         | `arr[indice] = nuevoValor;`                        |
| Delete         | Correr elementos y hacer `size--`                  |
| Búsqueda lineal| Recorrer comparando con `equals()`                 |
| Búsqueda binaria| Divide y conquista (requiere array ordenado)      |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [Listas ArrayList con CRUD](./10-listas_arraylist_java.md)
