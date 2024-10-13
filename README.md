Paso 1:
	Se debe realizar el registro del usuario con el Endpoitn /api/v1/library/auth/registrar que se encuentra en carpeta
	Auth dentro de la coleccion de Postman
	
En la solucion del examen se agregan 3 features adicionales
- Autenticacion: Implementacion de Spring Security para validacion de JWT
- Aspecto: Se crea un aspecto para medir tiempo de ejecucion de metodos
- Validacion de campos de entrada (Request)

Exercise 1: Respuestas en archivo Word

Exercise 2:
/api/v1/library/employee/all
/api/v1/library/employee/1
/api/v1/library/employee/registrar

Exercise 3:
/api/v1/library/book/all
/api/v1/library/book/isbn/A2
/api/v1/library/book/registrar

Exercise 4:
/api/v1/library/book/author?author=Mariano Sigman
/api/v1/library/book/fecha?fecha=2024-10-08

Exercise 5:
Metodo createBook es arrobada con anotacion @Transactional y se valida campos obligatorios donde se valida que fecha de publicacion 
no sea mayor a la fecha actual