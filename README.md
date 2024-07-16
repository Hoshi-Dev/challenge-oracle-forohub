![forohub_readme copia](https://github.com/user-attachments/assets/855898f5-c30a-433c-891e-0bd928d30087)

<a href="https://www.aluracursos.com/" target="_blank"><img src="https://custom-icon-badges.demolab.com/badge/Latam-text?style=for-the-badge&logo=book&logoColor=blue&label=Alura&color=blue" alt="Alura Latam"/></a>
<a href="https://www.oracle.com/ar/education/oracle-next-education/" target="_blank"><img src="https://img.shields.io/badge/Oracle_Next_Education-text?style=for-the-badge&logo=oracle&logoColor=orange&label=ONE&color=orange" alt="Oracle Next Education" /></a>
<a href="https://www.jetbrains.com/es-es/idea/" target="_blank"><img src="https://img.shields.io/badge/Intellij_IDEA-text?style=for-the-badge&logo=intellijidea&logoColor=red&label=IDE&color=red " alt="Intellij IDEA"> </a>
<a href="https://www.oracle.com/ar/java/" target="_blank"><img src="https://custom-icon-badges.demolab.com/badge/Java_17-text?style=for-the-badge&logo=java&logoColor=red&label=Lenguage&color=red" alt="Java 17"></a>
<a href="https://spring.io/projects/spring-boot" target="_blank"><img alt="Static Badge" src="https://img.shields.io/badge/Spring_Boot-text?style=for-the-badge&logo=spring&labelColor=grey&color=green"></a>
<a href="https://springdoc.org/" target="_blank"><img src="https://custom-icon-badges.demolab.com/badge/swagger_ui-text?style=for-the-badge&logoSource=feather&logo=swagger&logoColor=red&label=API&color=red" alt="Exchange Rate API"></a>
<a href="https://trello.com/b/9DeAlIsq/foro-hub-challenge-back-end" target="_blank"><img src="https://img.shields.io/badge/Trello-text?style=for-the-badge&logo=trello&logoColor=blue&label=app&color=blue&link=logoSource%3Dfeather" alt="Trello"></a>

## ONE | Fase - Especialización Back-End G6 Alura - Oracle Next Education
Esta aplicación se realizó como parte de la formación en Oracle Next Eucation (ONE) y Alura Latam, usando Spring Boot, MySQL y Security de Spring.

Ofrece las opciones de CRUD para las entidades Topic, Comment y User.

CRUD básicos para todos los objetos:
+ Registro.
+ Actualización por ID.
+ Lista completa.
+ Eliminación por ID.

En Topic se agrega:
+ Listado por User ID.
+ Muestra por ID.
+ Listado por categoría.

En Comment se agrega:
+ Listado por User ID.
+ Listado por Topic ID.

En User se agrega:
+ Login por nombre de usuario y contraseña.

Todo se realiza únicamente por acceso autorizado, usando la autenticación STATELESS con JWT(JSON WEB TOKEN).
Se manejan los datos a través de DTOs (DATA TRANSFER OBJECT), tanto para el ingreso y las respuestas. Así evitamos enviar las entidades directamente a los endpoint, y ocultar detalles que no se deberían ver.

> [!NOTE]
> Para el manejo del proyecto se nos brindó un Trello con los paso a seguir.
>
> <a href="https://trello.com/b/9DeAlIsq/foro-hub-challenge-back-end" target="_blank"><img src="https://img.shields.io/badge/Trello-text?style=for-the-badge&logo=trello&logoColor=blue&label=app&color=blue&link=logoSource%3Dfeather" alt="Trello"></a>

### :wrench: Pre-requisitos
+  [IntelliJ IDEA Community Edition](https://www.jetbrains.com/es-es/idea/)
+ [JDK](https://www.oracle.com/ar/java/)
+ [Spring Boot](https://spring.io/projects/spring-boot)
+ [Spring Initializr](https://start.spring.io/)
+ Spring Web
+ Lombook
+ Spring Data
+ Flyway
+ MySQL
+ Spring Validation
+ Spring Security
+ JWT auth0
+ Inmsomnia 

### :minidisc: MySQL Database
Se trabaja con 3 tablas que se crean actomaticamente gracias a Flyway:
+ users
+ comments
+ topics

### :wrench: En acción
Una vez iniciada la aplicación, configurado las dependecias necesarias y con la base de datos creada.
Puedes ingresar a [Swagger UI](http://localhost:8080/swagger-ui/index.html#/) para probar la API por tu cuenta.
No olvides generar el token de autenticación, primero registrando un usuario y luego haciendo login. Este token se ingresa en la pestaña Autorize de Swagger UI.

### Estado del proyecto
Cumplidos los requisitos mínimos para la entrega, pero en desarrollo para mejor implementación y futura finalización.
