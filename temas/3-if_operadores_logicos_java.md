# Condiciones `if` usando Operadores Lógicos en Java

> **Tema 3 del curso** | Anterior: [2- Operadores Lógicos](./2-operadores_logicos_java.md)

---

## Repaso rápido

Antes de empezar, recuerda los tres operadores lógicos:

| Operador | Símbolo | Descripción                            |
|----------|---------|----------------------------------------|
| AND      | `&&`    | Verdadero si **ambas** condiciones lo son |
| OR       | `\|\|`  | Verdadero si **al menos una** lo es    |
| NOT      | `!`     | Invierte el valor de la condición      |

---

## Estructura del `if` con operadores lógicos

```java
if (condicion1 OPERADOR condicion2) {
    // código si se cumple
} else {
    // código si NO se cumple
}
```

---

## Ejemplos con `&&` (AND)

### Ejemplo 1 — Acceso al sistema

```java
String usuario = "admin";
String contrasena = "1234";

if (usuario.equals("admin") && contrasena.equals("1234")) {
    System.out.println("✅ Acceso concedido");
} else {
    System.out.println("❌ Usuario o contraseña incorrectos");
}
```

```
✅ Acceso concedido
```

> Ambas condiciones deben ser verdaderas para entrar.

---

### Ejemplo 2 — Rango de temperatura

```java
int temperatura = 22;

if (temperatura >= 18 && temperatura <= 30) {
    System.out.println("🌤️ Temperatura agradable");
} else {
    System.out.println("🌡️ Temperatura fuera de rango");
}
```

```
🌤️ Temperatura agradable
```

---

### Ejemplo 3 — Aprobar un examen con asistencia mínima

```java
int nota = 75;
int asistencia = 80;

if (nota >= 60 && asistencia >= 70) {
    System.out.println("✅ Estudiante aprobado");
} else {
    System.out.println("❌ Estudiante reprobado");
}
```

```
✅ Estudiante aprobado
```

---

## Ejemplos con `||` (OR)

### Ejemplo 4 — Descuento especial

```java
boolean esEstudiante = false;
boolean esAdultoMayor = true;

if (esEstudiante || esAdultoMayor) {
    System.out.println("🎟️ Tienes descuento especial");
} else {
    System.out.println("Sin descuento aplicable");
}
```

```
🎟️ Tienes descuento especial
```

---

### Ejemplo 5 — Día de descanso

```java
String dia = "Sábado";

if (dia.equals("Sábado") || dia.equals("Domingo")) {
    System.out.println("🛌 Día de descanso");
} else {
    System.out.println("💼 Día laborable");
}
```

```
🛌 Día de descanso
```

---

### Ejemplo 6 — Acceso por tipo de usuario

```java
String rol = "moderador";

if (rol.equals("admin") || rol.equals("moderador")) {
    System.out.println("🔓 Puedes acceder al panel de control");
} else {
    System.out.println("🔒 Acceso denegado");
}
```

```
🔓 Puedes acceder al panel de control
```

---

## Ejemplos con `!` (NOT)

### Ejemplo 7 — Verificar que un campo no esté vacío

```java
String nombre = "";

if (!nombre.isEmpty()) {
    System.out.println("Nombre registrado: " + nombre);
} else {
    System.out.println("⚠️ El nombre no puede estar vacío");
}
```

```
⚠️ El nombre no puede estar vacío
```

---

### Ejemplo 8 — Usuario no bloqueado

```java
boolean estaBloqueado = false;

if (!estaBloqueado) {
    System.out.println("✅ El usuario puede iniciar sesión");
} else {
    System.out.println("🚫 Usuario bloqueado");
}
```

```
✅ El usuario puede iniciar sesión
```

---

## Combinando los tres operadores

### Ejemplo 9 — Control de acceso complejo

```java
int edad = 20;
boolean tieneTicket = true;
boolean estaEnListaNegra = false;

// Puede entrar si: tiene 18+ Y tiene ticket Y NO está en lista negra
if (edad >= 18 && tieneTicket && !estaEnListaNegra) {
    System.out.println("🎉 Bienvenido al evento");
} else {
    System.out.println("🚫 No puedes entrar");
}
```

```
🎉 Bienvenido al evento
```

---

### Ejemplo 10 — Sistema de calificaciones

```java
int nota = 55;
boolean presentoRecuperacion = true;

// Aprueba si: nota >= 60  O  (nota >= 50 Y presentó recuperación)
if (nota >= 60 || (nota >= 50 && presentoRecuperacion)) {
    System.out.println("✅ Aprobado");
} else {
    System.out.println("❌ Reprobado");
}
```

```
✅ Aprobado
```

---

## Ejemplo completo — Sistema de registro

```java
public class SistemaRegistro {

    public static void main(String[] args) {

        String usuario   = "kevin";
        String password  = "java2024";
        int    intentos  = 2;
        boolean activo   = true;
        String  rol      = "admin";

        // Validar acceso
        if (!activo) {
            System.out.println("🚫 Cuenta desactivada");

        } else if (intentos >= 5) {
            System.out.println("🔒 Cuenta bloqueada por demasiados intentos");

        } else if (usuario.equals("kevin") && password.equals("java2024")) {

            System.out.println("✅ Inicio de sesión correcto. Hola, " + usuario + "!");

            // Determinar permisos según rol
            if (rol.equals("admin") || rol.equals("superadmin")) {
                System.out.println("🔧 Tienes acceso al panel de administración");
            } else {
                System.out.println("👤 Acceso estándar");
            }

        } else {
            System.out.println("❌ Credenciales incorrectas. Intento " + intentos + " de 5");
        }
    }
}
```

### Salida:

```
✅ Inicio de sesión correcto. Hola, kevin!
🔧 Tienes acceso al panel de administración
```

---

## Resumen de patrones más usados

| Patrón                      | Código ejemplo                                      |
|-----------------------------|-----------------------------------------------------|
| Rango de valores            | `valor >= min && valor <= max`                      |
| Múltiples opciones válidas  | `x.equals("A") \|\| x.equals("B")`                 |
| Negación de condición       | `!condicion`                                        |
| Condición compuesta         | `(a && b) \|\| c`                                   |
| Excluir un caso             | `activo && !bloqueado`                              |

---

> 💡 **Tip de buenas prácticas:** Usa paréntesis para agrupar condiciones complejas. Hace el código más legible y evita errores de precedencia.
