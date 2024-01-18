<h1 align="center"> Aplicación de Gestión de Biblioteca</h1>
<div align="center">
<img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" />
<img alt="java shield" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" />
<img alt="mysql shield" src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" />
<img alt="intelliJ Idea shield" src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white" />
</div>
<br>
<p align="center">
  <a href="#-descripción-del-proyecto">Descripción</a> •
  <a href="#-correspondencia-objeto-relacional">ORM</a> •
  <a href="#-patrón-observer">Observer</a> •
  <a href="#-modelo-vista-controlador">MVC</a> •
  <a href="#-dificultades-encontradas">Problemas</a> •
  <a href="./collaboration.md">Trabajo en equipo</a>
</p>

## 📜 Descripción del proyecto

> ### ⌨ Integrantes del grupo
>
> - :feelsgood: [Red One](https://github.com/Vtsfactory16) 
> - ⏰ [Iridescent1010](https://github.com/Iridescent100) 
> - 🖤 [CakeNeka](https://github.com/cakeneka) 

El proyecto consiste en una aplicación de gestión de una biblioteca. 
Hay que adaptar una aplicación construida previamente para que utilice el framework
**Hibernate** en lugar de JDBC. También hay que implementar el patrón
**Observer**.

### Conceptos estudiados

- Sistema ORM **Hibernate**
- Patrón **Observer**
- **Modelo-vista-controlador**
- **Java Swing**

## 🗺 Correspondencia Objeto Relacional

## 🔭 Patrón Observer

## 🕹 Modelo-vista-controlador

## 🏔 Dificultades encontradas

1. Problemas al intentar **pasar el proyecto a maven**
2. De repente no va
    - Solución: `Rebuild Project` ([StackOverflow](https://stackoverflow.com/questions/12132003/getting-cannot-find-symbol-in-java-project-in-intellij))
    - ![evidencia visual](./media/build_failed.jpeg)
3. Eliminar archivos .jar de git ([StackOverflow](https://stackoverflow.com/questions/1274057/how-do-i-make-git-forget-about-a-file-that-was-tracked-but-is-now-in-gitignore))
    - `git rm --cached <file>`
4. Merge conflicts
5. `@Transient`


#### Al cambiar interfaces DAO JDBC por interfaces DAO Hibernate

1. No aparecen nombres de categorías al listar libros
    - ![captura](./media/unknown_categories.png)
    - código:
    ```java
    // Old:
    public String getCategoriaDescr() {
       Categoria oCategoria = getObjCategoria();
       if (oCategoria!=null)
           return oCategoria.getCategoria();
       else return String.format("Categoria %d desconocida", categoriaId);
    }

    // New:    
    public String getCategoriaDescr() {
        if (categoria != null)
            return categoria.getCategoria();
        return "0. Categoría desconocida";
    }
    ```
    - ![captura](./media/known_categories.png)
2. Problemas en la búsqueda de libros y usuarios para crear un nuevo préstamo

---

```yaml
Módulo: Acceso a datos
Lenguaje: Java
Tema: Tema 3. Herramientas de Mapeo Objeto-Relacional
```
