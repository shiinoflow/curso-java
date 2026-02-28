# Manejo de Archivos en Java (File I/O)

> **Tema 19 del curso** | Anterior: [18- Interfaz Map](./18-interfaz_map_java.md)

---

## 1.- ¿Qué es File I/O?

**File I/O** (Input/Output) es la capacidad de **leer y escribir archivos** desde un programa Java. Permite guardar datos de forma permanente más allá de la ejecución del programa.

| Operación | Descripción                          |
|-----------|--------------------------------------|
| **Write** | Guardar datos en un archivo           |
| **Read**  | Leer datos desde un archivo           |
| **Append**| Agregar datos al final de un archivo  |
| **Delete**| Eliminar un archivo o directorio      |

---

## 2.- Clases principales para manejo de archivos

| Clase / API            | Uso                                          |
|------------------------|----------------------------------------------|
| `File`                 | Representa un archivo o directorio           |
| `FileWriter`           | Escribir texto en un archivo                 |
| `FileReader`           | Leer texto de un archivo                     |
| `BufferedWriter`       | Escritura eficiente con buffer               |
| `BufferedReader`       | Lectura eficiente línea por línea            |
| `PrintWriter`          | Escribir con formato (`println`, `printf`)   |
| `Files` (NIO)          | API moderna para operaciones de archivos     |
| `Path` / `Paths` (NIO) | Representar rutas de archivos (Java 7+)      |

---

## 3.- Escribir en un archivo — `FileWriter` + `BufferedWriter`

```java
import java.io.*;

public class EscribirArchivo {
    public static void main(String[] args) {

        // try-with-resources cierra el archivo automáticamente
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("estudiantes.txt"))) {

            writer.write("Ana García");
            writer.newLine();            // salto de línea
            writer.write("Luis Pérez");
            writer.newLine();
            writer.write("Kevin Arauz");

            System.out.println("✅ Archivo escrito correctamente.");

        } catch (IOException e) {
            System.out.println("❌ Error al escribir: " + e.getMessage());
        }
    }
}
```

### Resultado — archivo `estudiantes.txt`:
```
Ana García
Luis Pérez
Kevin Arauz
```

---

## 4.- Escribir con `PrintWriter` (más cómodo)

```java
import java.io.*;

try (PrintWriter pw = new PrintWriter(new FileWriter("notas.txt"))) {

    pw.println("=== Notas del salón ===");
    pw.printf("%-12s → %.1f%n", "Ana García",  92.5);
    pw.printf("%-12s → %.1f%n", "Luis Pérez",  75.0);
    pw.printf("%-12s → %.1f%n", "Kevin Arauz", 88.0);
    pw.println("=======================");

    System.out.println("✅ Archivo creado.");

} catch (IOException e) {
    System.out.println("❌ Error: " + e.getMessage());
}
```

### Resultado — archivo `notas.txt`:
```
=== Notas del salón ===
Ana García   → 92.5
Luis Pérez   → 75.0
Kevin Arauz  → 88.0
=======================
```

---

## 5.- Leer un archivo — `BufferedReader`

```java
import java.io.*;

public class LeerArchivo {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("estudiantes.txt"))) {

            String linea;
            int numero = 1;

            System.out.println("=== Contenido del archivo ===");
            while ((linea = reader.readLine()) != null) {
                System.out.println(numero + ". " + linea);
                numero++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("❌ Error al leer: " + e.getMessage());
        }
    }
}
```

```
=== Contenido del archivo ===
1. Ana García
2. Luis Pérez
3. Kevin Arauz
```

---

## 6.- Agregar al final de un archivo (Append)

El segundo parámetro `true` en `FileWriter` activa el modo **append**:

```java
try (BufferedWriter writer = new BufferedWriter(
        new FileWriter("estudiantes.txt", true))) {  // ← true = append

    writer.newLine();
    writer.write("María López");
    writer.newLine();
    writer.write("Carlos Díaz");

    System.out.println("✅ Datos agregados al archivo.");

} catch (IOException e) {
    System.out.println("❌ Error: " + e.getMessage());
}
```

### Archivo después del append:
```
Ana García
Luis Pérez
Kevin Arauz
María López
Carlos Díaz
```

---

## 7.- Clase `File` — Información y operaciones

```java
import java.io.File;

File archivo = new File("estudiantes.txt");

System.out.println("Existe:      " + archivo.exists());
System.out.println("Es archivo:  " + archivo.isFile());
System.out.println("Es carpeta:  " + archivo.isDirectory());
System.out.println("Nombre:      " + archivo.getName());
System.out.println("Ruta:        " + archivo.getAbsolutePath());
System.out.println("Tamaño:      " + archivo.length() + " bytes");

// Eliminar archivo
if (archivo.delete()) {
    System.out.println("🗑️ Archivo eliminado.");
} else {
    System.out.println("⚠️ No se pudo eliminar.");
}
```

### Crear carpetas:

```java
File carpeta = new File("datos/reportes");
if (carpeta.mkdirs()) {
    System.out.println("📁 Carpeta creada: " + carpeta.getPath());
}
```

---

## 8.- API moderna con `Files` y `Path` (Java NIO — Java 7+)

La forma más moderna y sencilla:

```java
import java.nio.file.*;
import java.util.List;

// ── ESCRIBIR ──────────────────────────────────────────
String contenido = "Línea 1\nLínea 2\nLínea 3";
Files.writeString(Path.of("archivo.txt"), contenido);
System.out.println("✅ Escrito con NIO");

// ── LEER TODO de una vez ──────────────────────────────
String texto = Files.readString(Path.of("archivo.txt"));
System.out.println(texto);

// ── LEER LÍNEA POR LÍNEA ─────────────────────────────
List<String> lineas = Files.readAllLines(Path.of("archivo.txt"));
for (int i = 0; i < lineas.size(); i++) {
    System.out.println((i + 1) + ": " + lineas.get(i));
}

// ── APPEND ────────────────────────────────────────────
Files.writeString(
    Path.of("archivo.txt"),
    "\nLínea 4",
    StandardOpenOption.APPEND
);

// ── COPIAR ────────────────────────────────────────────
Files.copy(Path.of("archivo.txt"), Path.of("copia.txt"),
           StandardCopyOption.REPLACE_EXISTING);

// ── ELIMINAR ──────────────────────────────────────────
Files.deleteIfExists(Path.of("copia.txt"));
```

---

## 9.- Guardar y leer una lista de objetos (CSV)

Un uso muy común: guardar datos estructurados como CSV:

```java
import java.io.*;
import java.util.*;

class Estudiante {
    String nombre;
    double nota;

    public Estudiante(String nombre, double nota) {
        this.nombre = nombre;
        this.nota   = nota;
    }

    // Convierte a línea CSV
    public String toCsv() {
        return nombre + "," + nota;
    }

    // Crea un Estudiante desde línea CSV
    public static Estudiante fromCsv(String linea) {
        String[] partes = linea.split(",");
        return new Estudiante(partes[0], Double.parseDouble(partes[1]));
    }

    @Override
    public String toString() {
        return String.format("%-12s → %.1f", nombre, nota);
    }
}

public class ArchivoCSV {

    static final String ARCHIVO = "estudiantes.csv";

    // Guardar lista en CSV
    static void guardar(List<Estudiante> lista) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            pw.println("nombre,nota"); // encabezado
            for (Estudiante e : lista) pw.println(e.toCsv());
        }
        System.out.println("✅ Guardado en " + ARCHIVO);
    }

    // Leer CSV y retornar lista
    static List<Estudiante> cargar() throws IOException {
        List<Estudiante> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            br.readLine(); // saltar encabezado
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(Estudiante.fromCsv(linea));
            }
        }
        return lista;
    }

    public static void main(String[] args) throws IOException {
        // Guardar
        List<Estudiante> estudiantes = Arrays.asList(
            new Estudiante("Ana García",   92.5),
            new Estudiante("Luis Pérez",   75.0),
            new Estudiante("Kevin Arauz",  88.0)
        );
        guardar(estudiantes);

        // Cargar y mostrar
        System.out.println("=== Cargado desde CSV ===");
        for (Estudiante e : cargar()) {
            System.out.println(e);
        }
    }
}
```

### Salida:
```
✅ Guardado en estudiantes.csv
=== Cargado desde CSV ===
Ana García   → 92.5
Luis Pérez   → 75.0
Kevin Arauz  → 88.0
```

### Archivo `estudiantes.csv`:
```
nombre,nota
Ana García,92.5
Luis Pérez,75.0
Kevin Arauz,88.0
```

---

## 10.- Resumen rápido

| Clase / Método                 | Uso                                        |
|--------------------------------|--------------------------------------------|
| `FileWriter(ruta)`             | Abrir archivo para escritura (sobreescribe)|
| `FileWriter(ruta, true)`       | Abrir en modo append                       |
| `BufferedWriter`               | Escritura eficiente con buffer             |
| `PrintWriter`                  | Escritura con `println` y `printf`         |
| `FileReader(ruta)`             | Abrir archivo para lectura                 |
| `BufferedReader`               | Leer línea por línea eficientemente        |
| `reader.readLine()`            | Lee una línea; retorna `null` al terminar  |
| `Files.writeString(path, txt)` | NIO: escribir texto de golpe               |
| `Files.readString(path)`       | NIO: leer todo el archivo como String      |
| `Files.readAllLines(path)`     | NIO: leer todas las líneas como `List`     |
| `Files.copy()`                 | Copiar archivo                             |
| `Files.deleteIfExists(path)`   | Eliminar archivo sin lanzar excepción      |
| `file.exists()`                | Verificar si el archivo existe             |
| `file.mkdirs()`                | Crear directorio y subdirectorios          |

> ⚠️ Siempre usa **try-with-resources** o cierra el archivo con `.close()` para liberar recursos.
