# Operadores Lógicos en Java

> **Tema 2 del curso** | Anterior: [1- Variables](./1-variables_java.md) | Siguiente: [3- If con Operadores Lógicos](./3-if_operadores_logicos_java.md)

---

## ¿Qué son los operadores lógicos?

Los **operadores lógicos** se usan para combinar o negar condiciones (expresiones que producen `true` o `false`). Son fundamentales en las estructuras de control como `if`, `while` y `for`.

---

## Los 3 operadores lógicos principales

| Operador | Símbolo | Nombre    | Descripción                                         |
|----------|---------|-----------|-----------------------------------------------------|
| AND      | `&&`    | Y lógico  | `true` **solo si ambas** condiciones son verdaderas |
| OR       | `\|\|`  | O lógico  | `true` si **al menos una** condición es verdadera   |
| NOT      | `!`     | Negación  | Invierte el valor: `true` → `false` y viceversa     |

---

## 1. Operador AND (`&&`)

Devuelve `true` **únicamente** cuando **todas** las condiciones son `true`.

### Tabla de verdad

| A       | B       | A `&&` B |
|---------|---------|----------|
| `true`  | `true`  | ✅ `true`  |
| `true`  | `false` | ❌ `false` |
| `false` | `true`  | ❌ `false` |
| `false` | `false` | ❌ `false` |

### Ejemplo en código

```java
int edad = 20;
boolean tieneCarnet = true;

// Solo puede conducir si tiene 18+ Y tiene carnet
boolean puedeConducir = (edad >= 18) && (tieneCarnet == true);

System.out.println(puedeConducir); // true
```

```java
int edad = 16;
boolean tieneCarnet = true;

boolean puedeConducir = (edad >= 18) && (tieneCarnet == true);
System.out.println(puedeConducir); // false  ← edad no cumple
```

---

## 2. Operador OR (`||`)

Devuelve `true` cuando **al menos una** condición es `true`.

### Tabla de verdad

| A       | B       | A `\|\|` B |
|---------|---------|------------|
| `true`  | `true`  | ✅ `true`   |
| `true`  | `false` | ✅ `true`   |
| `false` | `true`  | ✅ `true`   |
| `false` | `false` | ❌ `false`  |

### Ejemplo en código

```java
boolean esEstudiante = true;
boolean esAdultoMayor = false;

// Descuento si ES estudiante O ES adulto mayor
boolean tieneDescuento = esEstudiante || esAdultoMayor;

System.out.println(tieneDescuento); // true
```

```java
boolean esEstudiante = false;
boolean esAdultoMayor = false;

boolean tieneDescuento = esEstudiante || esAdultoMayor;
System.out.println(tieneDescuento); // false ← ninguna cumple
```

---

## 3. Operador NOT (`!`)

Invierte el valor de una condición.

### Tabla de verdad

| A       | `!A`    |
|---------|---------|
| `true`  | ❌ `false` |
| `false` | ✅ `true`  |

### Ejemplo en código

```java
boolean puertaAbierta = false;

// La puerta NO está abierta → está cerrada
System.out.println(!puertaAbierta); // true (está cerrada)
```

```java
boolean usuarioActivo = true;

if (!usuarioActivo) {
    System.out.println("Usuario inactivo");
} else {
    System.out.println("Usuario activo"); // ← se ejecuta esto
}
```

---

## Combinando operadores

Se pueden combinar varios operadores en una misma expresión. Usa **paréntesis** para mayor claridad.

```java
int edad = 25;
boolean tieneCarnet = true;
boolean alcoholEnSangre = false;

// Puede conducir si: (tiene 18+ Y tiene carnet) Y NO tiene alcohol
boolean puedeConducir = (edad >= 18 && tieneCarnet) && !alcoholEnSangre;

System.out.println(puedeConducir); // true
```

---

## Orden de precedencia (prioridad)

Cuando hay varios operadores sin paréntesis, Java los evalúa en este orden:

| Prioridad | Operador | Descripción     |
|-----------|----------|-----------------|
| 1 (mayor) | `!`      | NOT (primero)   |
| 2         | `&&`     | AND             |
| 3 (menor) | `\|\|`   | OR              |

> 💡 **Recomendación:** usa paréntesis siempre que combines operadores. Evita confusiones y hace el código más legible.

```java
// Sin paréntesis (puede confundir)
boolean r1 = true || false && false;  // true (&&se evalúa antes que||)

// Con paréntesis (claro y explícito)
boolean r2 = true || (false && false); // true
```

---

## Cortocircuito (Short-circuit)

Java es inteligente: si con el primer operando ya puede determinar el resultado, **no evalúa el segundo**.

- `&&` → si el primero es `false`, el resultado ya es `false` → no evalúa el segundo.
- `||` → si el primero es `true`, el resultado ya es `true` → no evalúa el segundo.

```java
int x = 0;

// Con &&: si (x != 0) es false, no evalúa (10/x > 1) → evita error de división por cero
if (x != 0 && (10 / x > 1)) {
    System.out.println("Mayor que 1");
} else {
    System.out.println("x es cero, no se divide"); // ← se ejecuta esto
}
```

---

## Resumen rápido

| Operador | Símbolo | Resultado `true` cuando...              |
|----------|---------|-----------------------------------------|
| AND      | `&&`    | **Ambas** condiciones son verdaderas    |
| OR       | `\|\|`  | **Al menos una** condición es verdadera |
| NOT      | `!`     | La condición original es **falsa**      |

---

## ¿Qué sigue?

➡️ **Siguiente tema:** [Condiciones `if` usando Operadores Lógicos](./3-if_operadores_logicos_java.md)
