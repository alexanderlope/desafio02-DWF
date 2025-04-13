# desafio02-DWF
Desarrollo aplicación con servicio REST

Enrique Alexander Solano Lopez SL223188 
Gladis del Carmen Rivas Miranda RM191684

Este proyecto es una API RESTful construida con Java, Spring Boot, JPA, y H2, que permite gestionar clientes, productos y pedidos de una tienda. La API permite registrar, listar y consultar pedidos respetando reglas de negocio como stock, validaciones de datos y pagos.

Tecnologias Utilizadas:
Java 17
Spring Boot 3.x
Spring Data JPA
Base de datos: H2 (por defecto) o MySQL
Maven
Lombok (opcional)
Postman para pruebas

Configuracion del Entorno
Requisitos:
Java JDK 17+
Maven 3+
IDE recomendado: IntelliJ IDEA

configuracion de la base de datos:
la base datos H2 no requiere instalacion. ya que spring boot la crea automaticamente. se accede a la consola H2 desde http://localhost:8080/h2-console con la url de la base jdbc:h2:mem:tienda y el usuario y contraseña. y se ejecuta la clase principal desde la IDE

Reglas de Validación

Productos deben tener:
nombre no vacío
precio positivo
stock ≥ 0

Clientes:
Email único y válido
Nombre obligatorio

Pedidos:
Solo productos con stock
Total y cantidades positivas
Estado permitido: PENDIENTE, EN PREPARACIÓN, ENTREGADO, CANCELADO
Método de pago: EFECTIVO, TARJETA, TRANSFERENCIA

para las pruebas se usa Postman para probar todos los endpoints. También usar Swagger si se agrega la dependencia para springdoc-openapi
