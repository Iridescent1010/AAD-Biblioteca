# Proyecto acceso a datos ORM

## Resumen

- Proyecto a medias
- Implementar patrón **Observer** 
    - **Modelo Vista Controlador** ya está implementado
- Presentación
    - Correspondencia
    - Implementación de los patrones
    - Cambios en el MVC
    - Dificultades y soluciones

## ✅ Organización

> ### Semana 1 (19/12/2023 - 24/12/2023)
> 
> **Todos**
> - Revisar MVC (proyecto de conversión pesetas-euros)
> - MVC ya está implementado en el programa
> - Estudio y ejemplo del **Patrón observer**.

> ### Semana 2 (25/12/2023 - 31/12/2023)
>

> ### Semana 3(1/1/2024 - 7/1/2024) 
> 
> - Ejemplo patrón **Observer**

## 📄 Registro de cambios

> ### 19/12/2023
> 
> El proyecto que ha dado el profe ya implementa **Modelo-Vista-Controlador**,
> tenemos que implementar el patrón **observer** y utilizar herramientas de
> corresponendia objeto-relacional (**Hibernate**) para la persistencia en la
> base de datos.
> 
> - Cambiar el código del botón de `nuevoPrestamo()` en `FormMain`

> ### 31/12/2023
> 
> Al intentar clonarlo daba fallos por varios archivos llamados `Aux`,
> que no es un nombre válido en windows.
>
> He renombrado los archivos a `Auxiliary`

> ### 1/1/2024
>
> Añadida inserción de categorías al archivo `datos.sql`.
> 
> **Para crear la base de datos**
> 1. Conectar con mysql desde IntelliJ (Database \> Data Source)
> 2. Ejecutar `BIBLIOTECA.sql` (se puede hacer desde el propio IntelliJ)
> 3. Ejecutar `datos.sql`
> 4. A continuación el programa ya debería funcionar 