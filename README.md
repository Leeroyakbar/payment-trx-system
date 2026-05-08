# Microservices System - Product & Transaction

Sistem microservices sederhana yang dibangun menggunakan **Spring Boot 4 (Java 17)** untuk transaksi sederhana. Sistem ini dirancang untuk berjalan secara Containerization menggunakan **Docker** dan **PostgreSQL**.

## Fitur Utama
- **Product Service**: Handle Product (Get Product, Check Stock, Reduce Stock).
- **Transaction Service**: Proses transaksi yang terintegrasi dengan ketersediaan stok di Product Service.
- **Database Per Service**: Setiap service memiliki database PostgreSQL tersendiri (`product_db` dan `trx_db`) untuk menjaga independensi data.
- **Dockerized**: Seluruh ekosistem dapat dijalankan dengan satu perintah.

## Tech Stack
- **Backend**: Java 17, Spring Boot 4
- **ORM**: Spring Data JPA (Hibernate)
- **Database**: PostgreSQL 15
- **Inter-service Communication**: RestTemplate / WebClient
- **Containerization**: Docker & Docker Compose

## Prasyarat
Pastikan perangkat Anda sudah terinstal:
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Git](https://git-scm.com/)

## Cara Menjalankan Aplikasi

1. **Clone Repository**
   ```bash
   git clone https://github.com/Leeroyakbar/payment-trx-system.git
   cd payment-trx-system

2. Persiapan Environment
   Buat file bernama `.env` di direktori utama proyek (sejajar dengan `docker-compose.yaml`). Gunakan template berikut:

   ```env
      DB_USER=postgres
      DB_PASSWORD=ex

      PRODUCT_DB_NAME=product_db
      TRX_DB_NAME=trx_db

### Hostname menggunakan nama service di Docker Compose
PRODUCT_SERVICE_URL=http://product-service:8081/api/v1/products

3. Menjalankan Sistem
   ```bash
      docker-compose up --build
