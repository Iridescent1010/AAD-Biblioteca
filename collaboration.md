# Proyecto acceso a datos ORM

:feelsgood::feelsgood::feelsgood::feelsgood:

## ✅ Tareas

### Programa

#### 1. Implementar correspondencia con Hibernate y JPA

- [x] Añadir hibernate al proyecto (Instrucciones en la presentación)
- [x] Crear correspondencia con la base de datos (Generate Persistence Mapping)
- [x] Implementar de nuevo las clases DAO [`modelo.dao`](./Biblioteca%20MVC/src/modelo/dao/), esta parte es difícil.
    - [x] `CategoriaDAOHibernate` [Neka]
    - [x] `HistoricoDAOHibernate` [Vts]
    - [x] `LibroDAOHibernate` [Neka]
    - [x] `PrestamoDAOHibernate` [Iridescent]
    - [x] `UsuarioDAOHibernate` [Iridescent]

- [ ] **Sustituir** las clases que utilizan **JDBC** por las que utilizan **Hibernate** en el **programa principal**

- Hay que asegurarse de que **no se pierda funcionalidad** (hay que mantener el
log de los libros)
     
> [!IMPORTANT]  
> - Hay un problema con las interfaces DAO (`CategoriaDAO`, `HistoricoDAO`...)
> - Ahora mismo utilizan las clases POJO antiguas
> - Hay que cambiarlas (o crear algunas nuevas) para que trabajen con las clases POJO que tienen **anotaciones JPA**
> - Al final la solución ha sido añadir las anotaciones JPA a las clases originales =)
     
> Probablemente surjan conflictos entre las clases POJO que ya existen y las clases POJO que se generan al crear la correspondencia.

#### 2. Implementar patrón Observer

Para que la interfaz refleje las últimas modificaciones que ocurran en los datos.

> Creo que lo mejor será estudiar el patrón Observer en un ejemplo más pequeño antes de implementarlo en el proyecto.

#### 3. Revisar implementación del patrón **MVC** (Modelo Vista Controlador)

#### 4. Extra (nuevas funcionalidades)

> "Estas nuevas funcionalidades inventadas por el grupo serán tenidas en cuenta en la nota."

### Presentación

> La presentación es el [`readme.md`](./readme.md)

- Duración: **10 minutos** (2:30 por alumno)
- Explicar **correspondencia** entre clases y tablas
- Explicar implementación de **Observer**
- Explicar revisión del **MVC** (Modelo-Vista-Controlador)
- **Problemas** encontrados y **soluciones** a esos problemas 


## 🎯 Objetivos

> ### Semana 1 (19/12/2023 - 24/12/2023)
> 
> **Todos**
> - Revisar MVC (proyecto de conversión pesetas-euros)
> - MVC ya está implementado en el programa
> - Estudio y ejemplo del **Patrón observer**.

> ### Semana 2 (25/12/2023 - 31/12/2023)
>
> 🎄 🎅
> 

> ### Semana 3 (1/1/2024 - 7/1/2024) 
> 
> - Ejemplo patrón **Observer**

> ### Semana 4 (10/1/2024 - 14/1/2024)
>
> Ridu: Pasar proyecto a maven
> - `git clone https://github.com/Iridescent1010/AAD-Biblioteca.git`
> - `git -b checkout maven`
> - Creas aquí tu carpeta con el proyecto en maven =)
> - `git add -A`
> - `git commit -m "tu mensaje"`
> - `git push`
>
> Crear implementación del DAO para Hibernate

> ### Semana 5 (15/1/2024 - 21/1/2024)
>
> Patrón observer

## 📄 Registro de cambios

### 18/01/2024

- Sustituir clases DAO JDBC por clases DAO Hibernate en el programa principal
- Arreglar creación de préstamo y lista de libros

### 17/01/2024

- Implementaciones de `UsuarioDAO`, `PrestamoDAO` e `HistoricoDAO`
- Comprobado el correcto funcionamiento de las implementaciones

### 16/01/2024

Implementaciones para Hibernate de las interfaces `CategoriaDAO` y `LibroDAO`

### 14/01/2024

Añadir anotaciones JPA a las clases POJO originales y realizar pruebas con
Hibernate

### 11/01/2024

Añadida la dependencia de Hibernate y realizada la correspondencia con la
base de datos

### 1/01/2024

Añadida inserción de categorías al archivo `datos.sql`.

**Para crear la base de datos**
1. Conectar con mysql desde IntelliJ (Database \> Data Source)
2. Ejecutar `BIBLIOTECA.sql` (se puede hacer desde el propio IntelliJ)
3. Ejecutar `datos.sql`
4. A continuación el programa ya debería funcionar 

### 31/12/2023

Al intentar clonarlo daba fallos por varios archivos llamados `Aux`,
que no es un nombre válido en windows. He renombrado estos archivos a `Auxiliary`

### 19/12/2023
 
El proyecto que ha dado el profe ya implementa **Modelo-Vista-Controlador**,
tenemos que implementar el patrón **observer** y utilizar herramientas de
corresponencia objeto-relacional (**Hibernate** con **JPA**) para la persistencia en la
base de datos.
 
- Cambiar el código del botón de `nuevoPrestamo()` en `FormMain`
