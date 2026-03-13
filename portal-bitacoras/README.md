# 📓 Portal Bitácora de Estudio

Sistema web de registro y seguimiento de sesiones de aprendizaje. Funciona como una aplicación de una sola página (HTML) sin necesidad de servidor ni instalaciones.

---

## 📁 Estructura de archivos

```
portal-bitacoras/
│
├── bitacora-estudio.html   ← Aplicación principal (abrir en el navegador)
├── bitacora-db.txt         ← Base de datos local (separado por |)
└── README.md               ← Este archivo
```

---

## 🚀 Cómo usar

1. Abre **`bitacora-estudio.html`** en **Google Chrome** o **Microsoft Edge**.
2. En el banner amarillo, haz clic en **"Abrir archivo"** y selecciona **`bitacora-db.txt`**.
3. El sistema cargará los registros existentes y quedará listo para usar.
4. Haz clic en **"Nueva sesión"** para registrar una sesión de estudio.

> ⚠️ **Requiere Chrome o Edge.** La lectura y escritura de archivos locales usa la API `File System Access`, que no está disponible en Firefox ni Safari.

---

## 📋 Funcionalidades

### 🗒️ Bitácora
- Registro de sesiones con: fecha, hora inicio/fin, duración calculada automáticamente
- Tipos de sesión: Curso, Libro, Práctica, Proyecto, Revisión u Otro (personalizable)
- Editor de subtemas con viñetas (`•`), sub-viñetas (`◦`), numeración y separadores
- Evaluación de comprensión y energía (1–5 estrellas)
- Campo de observaciones y motivo de retraso
- **Agrupación por fecha** con total de horas por día
- Acciones por registro: ver detalle, editar, eliminar
- Eliminar grupo completo por fecha
- **Descarga de bitácora en TXT** por grupo de fecha

### 📊 Dashboard (KPIs)
| Métrica | Descripción |
|---|---|
| Sesiones | Total de registros ingresados |
| Horas totales | Suma de tiempo estudiado |
| Días de estudio | Días únicos con al menos una sesión |
| Comprensión prom. | Promedio de estrellas de comprensión |
| Energía prom. | Promedio de estrellas de energía |
| Racha actual | Días consecutivos de estudio |

**Gráficas:**
- 📊 Horas estudiadas por día (barras)
- 🍩 Distribución de sesiones por tipo (dona)
- 📈 Tendencia de comprensión y energía (línea)
- 📊 Top 7 cursos más estudiados (barras horizontales)
- 📋 Tabla de las últimas 10 sesiones

---

## 🗄️ Base de datos (`bitacora-db.txt`)

El archivo usa formato **texto plano separado por ` | `** (pipe). Cada línea es un registro.

### Estructura del archivo
```
# BITACORA_ESTUDIO_DB | version:1
# fecha | horaInicio | horaFin | duracion | duracionTexto | tipo | tipoSelect | fuente | curso | tema | subtema | motivoRetraso | comprension | energia | observaciones
2026-02-05 | 19:00 | 20:30 | 90 | 1h 30min (90 minutos) | CURSO | CURSO | Udemy | Java desde cero | Variables | • int\n• double\n• String | Se revisó más detalle | 4 | 3 | Se entendió bien
```

### Codificación de campos especiales
| Carácter | Codificación en archivo |
|---|---|
| Salto de línea (`\n`) | `\NL` |
| Pipe (`\|`) | `\PIPE` |
| Backslash (`\\`) | `\\` |

### Campos por registro
| Campo | Tipo | Descripción |
|---|---|---|
| `fecha` | texto | Formato `YYYY-MM-DD` |
| `horaInicio` | texto | Formato `HH:MM` |
| `horaFin` | texto | Formato `HH:MM` |
| `duracion` | número | Total en minutos |
| `duracionTexto` | texto | Ej: `1h 30min (90 minutos)` |
| `tipo` | texto | Valor final (ej: `PODCAST` si fue OTRO) |
| `tipoSelect` | texto | Valor del select (ej: `OTRO`) |
| `fuente` | texto | Plataforma o fuente |
| `curso` | texto | Nombre del curso o recurso |
| `tema` | texto | Tema principal |
| `subtema` | texto | Descripción con viñetas (multilinea codificada) |
| `motivoRetraso` | texto | Justificación de desvío |
| `comprension` | número | 1 a 5 |
| `energia` | número | 1 a 5 |
| `observaciones` | texto | Notas libres (multilinea codificada) |

---

## 💡 Flujo de trabajo recomendado

```
Abrir Chrome
    ↓
Abrir bitacora-estudio.html
    ↓
Conectar bitacora-db.txt
    ↓
Nueva sesión → Llenar formulario → Guardar
    ↓
Ver registros agrupados por fecha
    ↓
Dashboard → Revisar métricas y tendencias
    ↓
Descargar TXT del día como respaldo
```

---

## 🛠️ Tecnologías usadas

| Tecnología | Uso |
|---|---|
| HTML5 | Estructura |
| CSS3 + Bootstrap 5.3 | Estilos y layout responsive |
| Bootstrap Icons | Iconografía |
| Chart.js 4.4 | Gráficas del dashboard |
| File System Access API | Lectura y escritura del archivo DB |
| JavaScript (vanilla) | Lógica completa de la aplicación |

---

## 📌 Notas

- Los datos **se guardan automáticamente** en `bitacora-db.txt` tras cada acción.
- Al volver a abrir la app, selecciona el mismo archivo para retomar donde lo dejaste.
- El archivo `bitacora-db.txt` es legible en cualquier editor de texto.
- Para hacer respaldo, simplemente copia el archivo `bitacora-db.txt`.

---

*Desarrollado para el curso de Java — Kevin Arauz*
