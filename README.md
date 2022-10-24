## Spring Boot
Proyecto Spring Boot con las dependencias / starters:
* H2
* Spring Data JPA
* Spring Boot DevTools
* Spring web
* Springfox
* SpringSecurity

Aplicacion API REST con acceso a base de datos H2 para
peristir la informacion.

Acceso desde Postman o navegador.

Documentacion Swagger.

Workspace público Postman para interactuar con los controller
https://www.postman.com/flight-candidate-76684667/workspace/public/collection/22995934-43f7d409-7fab-47fa-8893-098a7361c83d?ctx=documentation

## Entidad laptop

1.Laptop

2.LaptopRepository

3.LaptopController
 * Listar todo los laptops.
 * buscar registro por ID.
 * añadir registros (laptop).
 * actualizar resgistro.
 * borrar todos los laptops.
 * borrar registro por id.

4.HelloControler
* Da forma mediante un documento html a la pagina inicial de la 
    api desde el navegador.

## SpringSecurity
1.WebSecurityConfig
* Cifrado de contraseñas de usuarios usando Pbkdf2PasswordEncoder().
* creación de dos usuarios 
    * admin
    * user