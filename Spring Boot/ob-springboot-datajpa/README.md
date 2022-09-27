## Spring Boot

Proyecto Spring Boot con las dependencias / starters:

* H2
* Spring Data JPA
* Spring Web
* Spring Boot Dev tools

Aplicacion API REST con acceso a base de datos H2 para persistir la informacion

El acceso se puede realizar desde Postman o Navegador.


# Entidad Book

1. Book (entidad)
2. BookRepository (para almacenar en db)
3. BookController (para poder acceder desde una url)
   1. Buscar todos los libros
   2. Buscar un solo libro
   3. Crear un nuevo libro
   4. Actualizar un libro existente
   5. Borrar un libro
   6. Borrar todos los libros