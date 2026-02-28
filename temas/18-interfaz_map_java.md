# La Interfaz `Map<K, V>` en Java

> **Tema 18 del curso** | Anterior: [17- TreeMap y LinkedHashMap](./17-maps_colecciones_java.md)

---

## 1.- ¿Qué es la interfaz `Map<K, V>`?

`Map<K, V>` es una **interfaz genérica** de Java que define el contrato que deben cumplir todas las clases de tipo mapa. No se puede instanciar directamente, pero sirve como **tipo común** para trabajar con `HashMap`, `LinkedHashMap` y `TreeMap` de forma intercambiable.

```java
// K = tipo de la clave (Key)
// V = tipo del valor (Value)
Map<K, V>
```

| Símbolo | Nombre  | Representa          | Ejemplo            |
|---------|---------|---------------------|--------------------|
| `K`     | Key     | Tipo de la clave    | `String`, `Integer`|
| `V`     | Value   | Tipo del valor      | `String`, `Double` |

---

## 2.- Declarar con la interfaz `Map`

```java
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

// Variable tipo Map → puede apuntar a cualquier implementación
Map<String, Integer> mapa1 = new HashMap<>();
Map<String, Integer> mapa2 = new LinkedHashMap<>();
Map<String, Integer> mapa3 = new TreeMap<>();
```

> ✅ **Buena práctica**: declarar el tipo como `Map` en lugar de `HashMap`, así puedes cambiar la implementación fácilmente sin modificar el resto del código.

```java
// ✅ Flexible — fácil cambiar de HashMap a TreeMap
Map<String, String> catalogo = new HashMap<>();

// ❌ Menos flexible — atado a HashMap
HashMap<String, String> catalogo = new HashMap<>();
```

---

## 3.- Métodos de la interfaz `Map`

Todos los mapas comparten estos métodos por contrato:

| Método                        | Descripción                                         |
|-------------------------------|-----------------------------------------------------|
| `put(K key, V value)`         | Agrega o sobreescribe una entrada                   |
| `get(K key)`                  | Retorna el valor de la clave, o `null` si no existe |
| `getOrDefault(K key, V def)`  | Retorna el valor o `def` si la clave no existe      |
| `remove(K key)`               | Elimina la entrada por clave                        |
| `containsKey(K key)`          | `true` si la clave existe                           |
| `containsValue(V value)`      | `true` si el valor existe                           |
| `size()`                      | Número de entradas                                  |
| `isEmpty()`                   | `true` si el mapa está vacío                        |
| `clear()`                     | Elimina todas las entradas                          |
| `keySet()`                    | `Set<K>` con todas las claves                       |
| `values()`                    | `Collection<V>` con todos los valores               |
| `entrySet()`                  | `Set<Map.Entry<K,V>>` con todos los pares           |
| `putIfAbsent(K key, V val)`   | Agrega solo si la clave NO existe                   |
| `replace(K key, V val)`       | Actualiza solo si la clave ya existe                |
| `forEach((k, v) -> ...)`      | Recorre con expresión lambda                        |
| `merge(K, V, BiFunction)`     | Combina valores existentes con uno nuevo            |

---

## 4.- `Map.Entry<K, V>` — Recorrer pares clave-valor

Cada par clave-valor se representa como un `Map.Entry`:

```java
Map<String, Integer> notas = new HashMap<>();
notas.put("Java",      92);
notas.put("BD",        85);
notas.put("Redes",     78);
notas.put("Algoritmos",88);

// Recorrer con entrySet()
for (Map.Entry<String, Integer> entry : notas.entrySet()) {
    System.out.printf("%-12s → %d%n", entry.getKey(), entry.getValue());
}
```

```
Java         → 92
BD           → 85
Redes        → 78
Algoritmos   → 88
```

---

## 5.- Recorrer con `forEach` y lambda

La forma más moderna y concisa:

```java
Map<String, Double> precios = new LinkedHashMap<>();
precios.put("Laptop",   899.99);
precios.put("Mouse",     19.99);
precios.put("Teclado",   45.00);

// forEach con lambda
precios.forEach((producto, precio) ->
    System.out.printf("%-10s → $%.2f%n", producto, precio)
);
```

```
Laptop     → $899.99
Mouse      → $19.99
Teclado    → $45.00
```

---

## 6.- `putIfAbsent()` — No sobreescribir si ya existe

```java
Map<String, String> config = new HashMap<>();
config.put("idioma", "es");
config.put("tema",   "oscuro");

// Intenta agregar "idioma" → no hace nada porque ya existe
config.putIfAbsent("idioma", "en");
// Agrega "fuente" porque no existe
config.putIfAbsent("fuente", "Inter");

System.out.println(config);
// {idioma=es, tema=oscuro, fuente=Inter}
```

---

## 7.- `merge()` — Combinar o actualizar valores

`merge(clave, valor, función)` → si la clave no existe la agrega; si existe, **combina** el valor actual con el nuevo usando la función:

```java
Map<String, Integer> votos = new HashMap<>();

String[] candidatos = {"Ana", "Luis", "Ana", "Kevin", "Ana", "Luis"};

for (String c : candidatos) {
    // Si existe: suma 1 al valor actual. Si no: pone 1
    votos.merge(c, 1, Integer::sum);
}

votos.forEach((nombre, total) ->
    System.out.println(nombre + ": " + total + " voto(s)")
);
```

```
Ana: 3 voto(s)
Luis: 2 voto(s)
Kevin: 1 voto(s)
```

---

## 8.- Usar `Map` como parámetro (polimorfismo)

La ventaja principal: un método que acepta `Map<K,V>` funciona con **cualquier implementación**:

```java
public static void imprimirMapa(Map<String, Integer> mapa) {
    mapa.forEach((k, v) ->
        System.out.printf("  %-12s → %d%n", k, v));
}

public static void main(String[] args) {
    Map<String, Integer> hash   = new HashMap<>();
    Map<String, Integer> linked = new LinkedHashMap<>();
    Map<String, Integer> tree   = new TreeMap<>();

    // Llenar los 3 mapas igual
    for (Map<String, Integer> m : new Map[]{hash, linked, tree}) {
        m.put("Banana", 3);
        m.put("Manzana", 1);
        m.put("Cereza", 4);
    }

    System.out.println("HashMap:");        imprimirMapa(hash);
    System.out.println("LinkedHashMap:"); imprimirMapa(linked);
    System.out.println("TreeMap:");        imprimirMapa(tree);
}
```

```
HashMap:
  Manzana      → 1
  Banana       → 3
  Cereza       → 4
LinkedHashMap:
  Banana       → 3
  Manzana      → 1
  Cereza       → 4
TreeMap:
  Banana       → 3
  Cereza       → 4
  Manzana      → 1
```

---

## 9.- Crear un `Map` inmutable con `Map.of()`

Desde Java 9, puedes crear mapas de solo lectura de forma muy compacta:

```java
// Map inmutable (no se puede modificar)
Map<String, Integer> dias = Map.of(
    "Lunes",     1,
    "Martes",    2,
    "Miércoles", 3,
    "Jueves",    4,
    "Viernes",   5
);

System.out.println(dias.get("Miércoles")); // 3

// dias.put("Sábado", 6); ← ❌ lanza UnsupportedOperationException
```

> Útil para constantes o configuraciones que no deben cambiar.

---

## 10.- Ejemplo completo — Encuesta de preferencias

```java
import java.util.*;

public class EncuestaPreferencias {

    public static void main(String[] args) {

        // Respuestas de los usuarios
        String[] respuestas = {
            "Java", "Python", "Java", "JavaScript",
            "Java", "Python", "Java", "C++", "Python", "Java"
        };

        // Contar frecuencia con Map
        Map<String, Integer> conteo = new LinkedHashMap<>();
        for (String r : respuestas) {
            conteo.merge(r, 1, Integer::sum);
        }

        // Mostrar resultados
        System.out.println("=== Resultados Encuesta ===");
        System.out.println("Total respuestas: " + respuestas.length);
        System.out.println();

        conteo.forEach((lenguaje, votos) -> {
            int porcentaje = (votos * 100) / respuestas.length;
            String barra   = "█".repeat(porcentaje / 5);
            System.out.printf("%-15s %s %d%%%n", lenguaje, barra, porcentaje);
        });

        // Ganador
        String ganador = Collections.max(
            conteo.entrySet(),
            Map.Entry.comparingByValue()
        ).getKey();

        System.out.println("\n🏆 Lenguaje favorito: " + ganador);
    }
}
```

### Salida:
```
=== Resultados Encuesta ===
Total respuestas: 10

Java           ████████████ 50%
Python         ██████ 30%
JavaScript     ██ 10%
C++            ██ 10%

🏆 Lenguaje favorito: Java
```

---

## Resumen rápido

| Concepto               | Descripción                                           |
|------------------------|-------------------------------------------------------|
| `Map<K, V>`            | Interfaz genérica para todos los mapas                |
| `K` → clave, `V` → valor | Tipos genéricos que se definen al declarar el mapa |
| `Map.Entry<K, V>`      | Representa un par clave-valor                         |
| `entrySet()`           | Recorrer todos los pares                              |
| `forEach((k,v) ->)`    | Recorrer con lambda (forma moderna)                   |
| `putIfAbsent()`        | Agregar solo si la clave no existe                    |
| `merge()`              | Combinar valores existentes                           |
| `Map.of()`             | Crear un mapa inmutable (Java 9+)                     |
| Polimorfismo con `Map` | Un método acepta cualquier implementación de mapa     |
