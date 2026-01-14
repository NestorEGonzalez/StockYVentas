![Build Status](https://github.com/NestorEGonzalez/stockyventas/actions/workflows/maven.yml/badge.svg?branch=master)

# 📦 Stock y Ventas

**En desarrollo**

Sistema de gestión de **stock** y **registro de ventas de productos**, desarrollado con **Spring Boot** y **PostgreSQL** mediante **TDD**.

## 🚀 Características actuales
- Alta, baja, modificación y consulta de productos en la base de datos.
- Persistencia con **Spring Data JPA**.
- Pruebas de repositorios con **JUnit 5** y **Testcontainers** (PostgreSQL real en contenedor Docker).

## 🛠️ Tecnologías utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Testcontainers + JUnit 5
- Maven

## ⚙️ Requisitos
- **Docker** instalado y corriendo (necesario para ejecutar los tests con Testcontainers).
- **Java 17** o superior.
- **Maven** para la construcción del proyecto.

## ▶️ Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/NestorEGonzalez/stockyventas.git

2. Compilar y ejecutar:
    ```bash
   mvn spring-boot:run
3. Ejeutar pruebas (se levantará un contenedor PostgreSQL automáticamente):
    ```bash
    mvn test

## 📌 Estado del proyecto
Actualmente el sistema gestiona productos y pruebas de repositorios con PostgreSQL.  
Todavía falta implementar los **endpoints REST** para poder interactuar con el sistema vía web (por ejemplo, crear, listar, modificar y eliminar productos desde un cliente HTTP).

## 🚀 Próximos pasos
- Implementación de la lógica de negocio mediante el Service.
- Implementar endpoints REST con Spring MVC.
- Exponer API para gestionar stock y ventas desde el navegador o aplicaciones externas.
- Agregar seguridad: login con usuario y contraseña.
- Extender funcionalidades para pedidos recibidos.
