# Zurich Insurance Api

Proyecto diseñado para la visualización y administración de **Clientes** y **Pólizas**.  
Este servicio provee endpoints REST para gestionar la información, así como autenticación de usuarios.

---

## Tabla de Contenidos
1. [Descripción General](#descripción-general)
2. [Objetivos y Funcionalidades](#objetivos-y-funcionalidades)
3. [Requisitos Previos](#requisitos-previos)
4. [Instalación](#instalación)
5. [Ejecución](#ejecución)
6. [Endpoints Principales](#endpoints-principales)
    - [Client](#client)
    - [Insurance](#insurance)
    - [User](#user)
7. [Pruebas](#pruebas)
8. [Despliegue](#despliegue)
9. [Contacto](#contacto)

---

## Descripción General
El proyecto **Zurich Insurance Api** permite la administración de **Clientes** y **Pólizas** de manera centralizada, ofreciendo un punto de acceso (API) para crear, consultar, actualizar y eliminar dicha información. También provee una ruta para la autenticación de usuarios.

---

## Objetivos y Funcionalidades
- **Administración de Clientes**: Crear, consultar, actualizar y eliminar clientes.
- **Administración de Pólizas**: Crear, consultar, actualizar y eliminar pólizas.
- **Manejo de Usuarios**: Permite autenticación básica para realizar operaciones.
- **Documentación de la API**: Vía Swagger, se cuenta con una interfaz detallada de endpoints.

---

## Requisitos Previos
- **Java 17.x**
- **Maven 3.9.1**
- **PostgreSQL 13.x** (para la ejecución de pruebas)
- (Opcional) **Docker**, contemplado para futuros despliegues

---

## Instalación
1. **Clonar** el repositorio:
   ```bash
   git clone https://github.com/RoquePE921204/zurich-back.git
   cd zurich-back
   ```
2. **Construir el proyecto** (sin ejecutar pruebas, si la BD no está configurada):
   ```bash
   mvn clean install -Dmaven.test.skip=true
   ```

---

## Ejecución
1. **Levantar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```
   o bien:
   ```bash
   mvn clean package
   java -jar target/insurance-management-0.0.1-SNAPSHOT.jar
   ```
2. **Puerto por defecto**: `8080`
3. **Swagger**: Disponible en [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
4. **IDE**: Puedes usar **Spring Tools 4** o **IntelliJ** para correr y depurar el proyecto.

---

## Endpoints Principales

### Client
Operaciones relacionadas con los clientes.

- **GET /client/{id}**  
  Obtiene un cliente específico por su ID.
- **PUT /client**  
  Actualiza un cliente existente recibiendo un `ClientRequest` con los datos.
- **POST /client**  
  Crea un nuevo cliente con los datos de un `ClientRequest`.
- **DELETE /client/{id}**  
  Elimina un cliente por su ID.
- **GET /client/list**  
  Lista todos los clientes registrados.

### Insurance
Operaciones relacionadas con las pólizas.

- **GET /insurance/{id}**  
  Obtiene una póliza específica por su ID.
- **PUT /insurance**  
  Actualiza una póliza existente con un `InsuranceRequest`.
- **POST /insurance**  
  Crea una nueva póliza con un `InsuranceRequest`.
- **DELETE /insurance/{id}**  
  Elimina una póliza por su ID.
- **GET /insurance/list/{id}**  
  Lista pólizas asignadas a un cliente con un ID.

### User
Operaciones relacionadas con el login de usuarios.

- **POST /user/login**  
  Autentica un usuario recibiendo un `LoginRequest` con `user` y `password`.

---

## Pruebas
Para ejecutar las pruebas unitarias e integrales, se requiere **PostgreSQL 13.x** configurado. Luego:

```bash
mvn clean install
```
Esto ejecutará todas las pruebas (unitarias e integrales).  
Si necesitas omitir las pruebas, puedes usar:

```bash
mvn clean install -Dmaven.test.skip=true
```

---

## Despliegue
Actualmente, no se ha implementado un despliegue formal en **Docker** u otro ambiente. Está contemplado para versiones futuras de este proyecto.

---

## Contacto
**Autor**: Emanuel Roque Pimentel  
**Email**: roque.p.e.921204@gmail.com

