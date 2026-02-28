# TreeMap, LinkedHashMap y Colecciones Map en Java

> **Tema 17 del curso** | Anterior: [16- HashMap](./16-hashmap_java.md)

---

## 1.- La interfaz `Map`

Todas las clases de esta lección implementan la interfaz `Map<K, V>`. Son similares al `HashMap` pero con **distintas características de orden**:

| Clase            | Orden                          | Permite clave `null` | Velocidad   |
|------------------|--------------------------------|----------------------|-------------|
| `HashMap`        | ❌ Sin orden garantizado       | ✅ Sí (una)          | ⚡ Rápido   |
| `LinkedHashMap`  | ✅ Orden de inserción          | ✅ Sí (una)          | 🟡 Medio    |
| `TreeMap`        | ✅ Orden alfabético/natural    | ❌ No                | 🐢 Más lento|

---

## 2.- `LinkedHashMap` — Orden de inserción

Mantiene el **orden en que se insertaron** los elementos.

```java
import java.util.LinkedHashMap;

LinkedHashMap<String, Integer> ranking = new LinkedHashMap<>();
ranking.put("Kevin",  1);
ranking.put("Ana",    2);
ranking.put("Luis",   3);
ranking.put("María",  4);

// Los imprime en el mismo orden en que se insertaron
for (var entry : ranking.entrySet()) {
    System.out.println(entry.getKey() + " → Posición " + entry.getValue());
}
```

```
Kevin → Posición 1
Ana → Posición 2
Luis → Posición 3
María → Posición 4
```

> Usa `LinkedHashMap` cuando el orden de inserción importa, por ejemplo para un historial o menú.

---

## 3.- `TreeMap` — Orden alfabético (natural)

Ordena automáticamente las claves en **orden ascendente** (natural: A→Z, 1→9).

```java
import java.util.TreeMap;

TreeMap<String, String> paises = new TreeMap<>();
paises.put("Honduras",    "Tegucigalpa");
paises.put("México",      "Ciudad de México");
paises.put("Argentina",   "Buenos Aires");
paises.put("Costa Rica",  "San José");
paises.put("Colombia",    "Bogotá");

for (var entry : paises.entrySet()) {
    System.out.printf("%-15s → %s%n", entry.getKey(), entry.getValue());
}
```

```
Argentina       → Buenos Aires
Colombia        → Bogotá
Costa Rica      → San José
Honduras        → Tegucigalpa
México          → Ciudad de México
```

> Ordenó automáticamente por nombre de país (A → Z).

---

## 4.- Métodos especiales de `TreeMap`

```java
TreeMap<Integer, String> notas = new TreeMap<>();
notas.put(85, "Matematicas");
notas.put(92, "Java");
notas.put(71, "Base de Datos");
notas.put(88, "Redes");
notas.put(65, "Inglés");

// Primera clave (menor)
System.out.println("Primera: " + notas.firstKey());  // 65

// Última clave (mayor)
System.out.println("Última:  " + notas.lastKey());   // 92

// Claves menores que 85
System.out.println("Menores a 85: " + notas.headMap(85));
// {65=Inglés, 71=Base de Datos}

// Claves mayores o iguales a 85
System.out.println("Mayores o = 85: " + notas.tailMap(85));
// {85=Matematicas, 88=Redes, 92=Java}

// Rango de claves (entre 70 y 90)
System.out.println("Entre 70 y 90: " + notas.subMap(70, 90));
// {71=Base de Datos, 85=Matematicas, 88=Redes}
```

---

## 5.- `TreeMap` con orden descendente

```java
import java.util.Collections;

TreeMap<String, Integer> puntos = new TreeMap<>(Collections.reverseOrder());
puntos.put("Ana",   150);
puntos.put("Luis",  200);
puntos.put("Kevin", 175);

// Imprime en orden Z → A
for (var e : puntos.entrySet()) {
    System.out.println(e.getKey() + ": " + e.getValue());
}
```

```
Luis: 200
Kevin: 175
Ana: 150
```

---

## 6.- Comparativa práctica: los 3 mapas

```java
import java.util.*;

Map<String, Integer> hashMap    = new HashMap<>();
Map<String, Integer> linkedMap  = new LinkedHashMap<>();
Map<String, Integer> treeMap    = new TreeMap<>();

String[] claves = {"Banana", "Manzana", "Cereza", "Durazno"};
int[]    valores = {3, 1, 4, 2};

for (int i = 0; i < claves.length; i++) {
    hashMap.put(claves[i],   valores[i]);
    linkedMap.put(claves[i], valores[i]);
    treeMap.put(claves[i],   valores[i]);
}

System.out.println("HashMap:       " + hashMap);
System.out.println("LinkedHashMap: " + linkedMap);
System.out.println("TreeMap:       " + treeMap);
```

```
HashMap:       {Manzana=1, Banana=3, Cereza=4, Durazno=2}  ← sin orden fijo
LinkedHashMap: {Banana=3, Manzana=1, Cereza=4, Durazno=2}  ← orden de inserción
TreeMap:       {Banana=3, Cereza=4, Durazno=2, Manzana=1}  ← orden A→Z
```

---

## 7.- Map como parámetro de método

Se puede usar la interfaz `Map` como tipo para recibir cualquiera de los tres:

```java
public static void imprimirMapa(Map<String, Integer> mapa) {
    for (var entry : mapa.entrySet()) {
        System.out.printf("  %-12s → %d%n", entry.getKey(), entry.getValue());
    }
}

public static void main(String[] args) {
    TreeMap<String, Integer> notas = new TreeMap<>();
    notas.put("Java",        92);
    notas.put("Algoritmos",  85);
    notas.put("Base Datos",  78);

    System.out.println("Notas ordenadas:");
    imprimirMapa(notas); // recibe Map, acepta TreeMap
}
```

---

## 8.- Map anidado (Map dentro de Map)

Útil para estructuras tipo tabla:

```java
// Mapa de estudiantes → (materia → nota)
Map<String, Map<String, Double>> registros = new LinkedHashMap<>();

Map<String, Double> notasAna = new HashMap<>();
notasAna.put("Java",     95.0);
notasAna.put("BD",       88.0);
notasAna.put("Redes",    76.0);

Map<String, Double> notasLuis = new HashMap<>();
notasLuis.put("Java",    78.0);
notasLuis.put("BD",      82.0);
notasLuis.put("Redes",   91.0);

registros.put("Ana",  notasAna);
registros.put("Luis", notasLuis);

// Imprimir
for (var estudiante : registros.entrySet()) {
    System.out.println("Estudiante: " + estudiante.getKey());
    for (var materia : estudiante.getValue().entrySet()) {
        System.out.printf("  %-10s → %.1f%n", materia.getKey(), materia.getValue());
    }
}
```

```
Estudiante: Ana
  Java       → 95.0
  BD         → 88.0
  Redes      → 76.0
Estudiante: Luis
  Java       → 78.0
  BD         → 82.0
  Redes      → 91.0
```

---

## 9.- Frecuencia de palabras (caso de uso real)

Un uso clásico del Map: contar cuántas veces aparece cada palabra:

```java
String texto = "java es genial java es el mejor lenguaje java";
String[] palabras = texto.split(" ");

Map<String, Integer> frecuencia = new TreeMap<>();

for (String palabra : palabras) {
    frecuencia.put(palabra, frecuencia.getOrDefault(palabra, 0) + 1);
}

for (var entry : frecuencia.entrySet()) {
    System.out.printf("%-10s → %d vez(ces)%n", entry.getKey(), entry.getValue());
}
```

```
el         → 1 vez(ces)
es         → 2 vez(ces)
genial     → 1 vez(ces)
java       → 3 vez(ces)
lenguaje   → 1 vez(ces)
mejor      → 1 vez(ces)
```

---

## 10.- Ejemplo completo — Inventario con TreeMap

```java
import java.util.*;

public class InventarioOrdenado {

    static TreeMap<String, Integer> inventario = new TreeMap<>();

    static void agregar(String producto, int cantidad) {
        inventario.merge(producto, cantidad, Integer::sum); // suma si ya existe
        System.out.println("✅ " + producto + ": " + inventario.get(producto) + " unidades");
    }

    static void mostrar() {
        System.out.println("=== Inventario (orden A-Z) ===");
        inventario.forEach((p, c) ->
            System.out.printf("  %-15s → %3d uds%n", p, c));
        System.out.println("Total productos: " + inventario.size());
    }

    static void eliminar(String producto) {
        if (inventario.remove(producto) != null)
            System.out.println("🗑️ Eliminado: " + producto);
        else
            System.out.println("⚠️ No encontrado: " + producto);
    }

    public static void main(String[] args) {
        agregar("Teclado",  30);
        agregar("Monitor",  10);
        agregar("Mouse",    50);
        agregar("Audífonos", 20);
        agregar("Monitor",   5); // suma 5 más a Monitor
        System.out.println();
        mostrar();

        System.out.println();
        eliminar("Teclado");
        mostrar();
    }
}
```

### Salida:
```
✅ Teclado: 30 unidades
✅ Monitor: 10 unidades
✅ Mouse: 50 unidades
✅ Audífonos: 20 unidades
✅ Monitor: 15 unidades

=== Inventario (orden A-Z) ===
  Audífonos       →  20 uds
  Monitor         →  15 uds
  Mouse           →  50 uds
  Teclado         →  30 uds
Total productos: 4

🗑️ Eliminado: Teclado
=== Inventario (orden A-Z) ===
  Audífonos       →  20 uds
  Monitor         →  15 uds
  Mouse           →  50 uds
Total productos: 3
```

---

## Resumen rápido

| Clase            | Cuándo usarla                                                |
|------------------|--------------------------------------------------------------|
| `HashMap`        | Cuando el orden no importa y necesitas máxima velocidad      |
| `LinkedHashMap`  | Cuando necesitas recordar el orden de inserción              |
| `TreeMap`        | Cuando necesitas las claves ordenadas automáticamente        |

| Método TreeMap extra | Descripción                             |
|---------------------|-----------------------------------------|
| `firstKey()`        | Primera clave (la menor)               |
| `lastKey()`         | Última clave (la mayor)                |
| `headMap(k)`        | Claves menores a `k`                   |
| `tailMap(k)`        | Claves mayores o iguales a `k`         |
| `subMap(a, b)`      | Claves entre `a` (incl.) y `b` (excl.) |
