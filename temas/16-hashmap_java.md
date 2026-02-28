# HashMap en Java

> **Tema 16 del curso** | Anterior: [15- Excepciones](./15-excepciones_java.md) | Siguiente: [17- TreeMap y LinkedHashMap](./17-maps_colecciones_java.md)

---

## 1.- ¿Qué es un `HashMap`?

Un `HashMap` almacena datos en pares **clave → valor** (`key → value`). En lugar de acceder a los elementos por índice (como en arrays o listas), se accede por su **clave única**.

> 💡 Piénsalo como un diccionario: buscas una *palabra* (clave) y obtienes su *definición* (valor).

```
Clave       → Valor
"nombre"    → "Ana"
"edad"      → 22
"correo"    → "ana@mail.com"
```

| Característica   | ArrayList             | HashMap                      |
|------------------|-----------------------|------------------------------|
| Acceso           | Por índice `get(0)`   | Por clave `get("nombre")`    |
| Claves           | No aplica             | Únicas, cualquier tipo       |
| Orden            | Mantiene el orden     | **No garantiza orden**       |
| Valores nulos    | Permite               | Permite un `null` como clave |

---

## 2.- Importar y crear un `HashMap`

```java
import java.util.HashMap;

// HashMap<TipoClave, TipoValor>
HashMap<String, String>  contactos  = new HashMap<>();
HashMap<String, Integer> inventario = new HashMap<>();
HashMap<Integer, String> codigos    = new HashMap<>();
```

---

## 3.- C — Create: `put()`

```java
HashMap<String, String> contactos = new HashMap<>();

contactos.put("Ana",    "9999-1111");
contactos.put("Luis",   "8888-2222");
contactos.put("Kevin",  "7777-3333");
contactos.put("María",  "6666-4444");

System.out.println(contactos);
// {Ana=9999-1111, Luis=8888-2222, Kevin=7777-3333, María=6666-4444}
```

> Si usas `put()` con una clave que ya existe, **sobreescribe** el valor anterior.

---

## 4.- R — Read: `get()`, `size()`, recorrer

```java
// Obtener por clave
String tel = contactos.get("Ana");
System.out.println("Tel de Ana: " + tel); // 9999-1111

// Tamaño
System.out.println("Total: " + contactos.size()); // 4

// ¿Existe la clave?
System.out.println(contactos.containsKey("Luis"));    // true
System.out.println(contactos.containsKey("Lucía"));   // false

// ¿Existe el valor?
System.out.println(contactos.containsValue("7777-3333")); // true
```

### Recorrer con `for-each` (entrySet):

```java
for (var entry : contactos.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}
```

```
Ana → 9999-1111
Luis → 8888-2222
Kevin → 7777-3333
María → 6666-4444
```

### Recorrer solo claves o solo valores:

```java
// Solo claves
for (String clave : contactos.keySet()) {
    System.out.println("Clave: " + clave);
}

// Solo valores
for (String valor : contactos.values()) {
    System.out.println("Valor: " + valor);
}
```

---

## 5.- U — Update: `put()` / `replace()`

```java
// Actualizar con put (sobreescribe si la clave existe)
contactos.put("Ana", "9999-9999");

// Actualizar con replace (solo si la clave existe)
contactos.replace("Luis", "1111-0000");

System.out.println(contactos.get("Ana"));  // 9999-9999
System.out.println(contactos.get("Luis")); // 1111-0000
```

---

## 6.- D — Delete: `remove()`, `clear()`

```java
// Eliminar por clave
contactos.remove("María");
System.out.println(contactos.containsKey("María")); // false

// Vaciar todo
contactos.clear();
System.out.println(contactos.isEmpty()); // true
```

---

## 7.- Búsqueda

```java
HashMap<String, Integer> edades = new HashMap<>();
edades.put("Ana",   22);
edades.put("Luis",  30);
edades.put("Kevin", 25);

// Buscar por clave (directo)
if (edades.containsKey("Kevin")) {
    System.out.println("Kevin tiene " + edades.get("Kevin") + " años");
}

// Buscar por valor (recorrer)
String nombreBuscado = "";
int edadBuscada = 30;

for (var entry : edades.entrySet()) {
    if (entry.getValue() == edadBuscada) {
        nombreBuscado = entry.getKey();
        break;
    }
}
System.out.println("Persona con " + edadBuscada + " años: " + nombreBuscado);
```

```
Kevin tiene 25 años
Persona con 30 años: Luis
```

---

## 8.- `getOrDefault()` — Valor por defecto si no existe la clave

```java
HashMap<String, Integer> puntos = new HashMap<>();
puntos.put("Ana",   150);
puntos.put("Luis",  200);

// "Kevin" no existe → retorna 0 por defecto
int ptsKevin = puntos.getOrDefault("Kevin", 0);
System.out.println("Kevin: " + ptsKevin); // Kevin: 0
```

---

## 9.- HashMap con objetos como valor

```java
class Producto {
    String nombre;
    double precio;
    int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock  = stock;
    }

    @Override
    public String toString() {
        return String.format("%s | $%.2f | Stock: %d", nombre, precio, stock);
    }
}

HashMap<String, Producto> catalogo = new HashMap<>();
catalogo.put("P001", new Producto("Laptop",   899.99, 10));
catalogo.put("P002", new Producto("Mouse",     19.99, 50));
catalogo.put("P003", new Producto("Teclado",   45.00, 30));

// Buscar por código
String codigo = "P002";
if (catalogo.containsKey(codigo)) {
    System.out.println(catalogo.get(codigo));
}
```

```
Mouse | $19.99 | Stock: 50
```

---

## 10.- Ejemplo completo — CRUD de contactos

```java
import java.util.HashMap;

public class AgendaContactos {

    static HashMap<String, String> agenda = new HashMap<>();

    static void agregar(String nombre, String telefono) {
        agenda.put(nombre, telefono);
        System.out.println("✅ Contacto agregado: " + nombre);
    }

    static void mostrar() {
        System.out.println("--- Agenda (" + agenda.size() + ") ---");
        for (var e : agenda.entrySet())
            System.out.println("  " + e.getKey() + " → " + e.getValue());
    }

    static void actualizar(String nombre, String nuevoTel) {
        if (agenda.containsKey(nombre)) {
            agenda.replace(nombre, nuevoTel);
            System.out.println("✏️ Actualizado: " + nombre);
        } else System.out.println("⚠️ Contacto no encontrado.");
    }

    static void eliminar(String nombre) {
        if (agenda.remove(nombre) != null)
            System.out.println("🗑️ Eliminado: " + nombre);
        else
            System.out.println("⚠️ Contacto no encontrado.");
    }

    static void buscar(String nombre) {
        String tel = agenda.getOrDefault(nombre, null);
        if (tel != null)
            System.out.println("🔍 " + nombre + " → " + tel);
        else
            System.out.println("🔍 '" + nombre + "' no encontrado.");
    }

    public static void main(String[] args) {
        agregar("Ana",   "9999-1111");
        agregar("Luis",  "8888-2222");
        agregar("Kevin", "7777-3333");
        mostrar();

        System.out.println();
        actualizar("Luis", "1111-9999");
        mostrar();

        System.out.println();
        eliminar("Kevin");
        mostrar();

        System.out.println();
        buscar("Ana");
        buscar("Lucía");
    }
}
```

### Salida:
```
✅ Contacto agregado: Ana
✅ Contacto agregado: Luis
✅ Contacto agregado: Kevin
--- Agenda (3) ---
  Ana → 9999-1111
  Luis → 8888-2222
  Kevin → 7777-3333

✏️ Actualizado: Luis
--- Agenda (3) ---
  Ana → 9999-1111
  Luis → 1111-9999
  Kevin → 7777-3333

🗑️ Eliminado: Kevin
--- Agenda (2) ---
  Ana → 9999-1111
  Luis → 1111-9999

🔍 Ana → 9999-1111
🔍 'Lucía' no encontrado.
```

---

## Resumen rápido

| Método                     | Descripción                                      |
|----------------------------|--------------------------------------------------|
| `put(clave, valor)`        | Agrega o sobreescribe un par clave-valor         |
| `get(clave)`               | Retorna el valor de la clave                     |
| `remove(clave)`            | Elimina la entrada con esa clave                 |
| `replace(clave, valor)`    | Actualiza si la clave ya existe                  |
| `containsKey(clave)`       | `true` si la clave existe                        |
| `containsValue(valor)`     | `true` si el valor existe                        |
| `getOrDefault(clave, def)` | Retorna el valor o un default si no existe       |
| `keySet()`                 | Conjunto de todas las claves                     |
| `values()`                 | Colección de todos los valores                   |
| `entrySet()`               | Conjunto de pares clave-valor                    |
| `size()` / `isEmpty()`     | Tamaño / verificar si está vacío                 |
| `clear()`                  | Vacía el mapa completo                           |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [TreeMap y LinkedHashMap](./17-maps_colecciones_java.md)
