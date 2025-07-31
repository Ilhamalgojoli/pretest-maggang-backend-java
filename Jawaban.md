# Soal test maggang Backend engineer dengan Springboot

Berikut adalah soal/pertanyaan yang perlu dijawab oleh peserta maggang

## knowledge base

1. Apa yang anda ketahui tentang Rest API?
2. Apa yang anda ketahui tentang Server side and Client side processing?
3. Apa yang anda ketahui tentang Monolith dan Microservices, berikan contohnya?
4. Apa yang anda ketahui tentang Design pattern inversion of Control serta Dependency Injection?
5. Apa yang anda ketahui tentang Java programming dan Spring framework khususnya spring-boot?

## Jawaban

1. Rest api atau Representational State Transfer Aplication Programming Interface yaitu sebuah gaya 
arsitektur dari sebuah aplikasi yang digunakan untuk saling berkomunikasi antar aplikasi entah dalam
dalam aplikasi web atau mobile yang berbasis HTTP request.

2. Server side merupakan tempat terjadi sebuah proses pengolahan data request yang dilakukan dari
client side.Sedangkan client side ialah tempat user agar bisa melakukan request yang diinginkan
ke server yang dimiliki sebuah aplikasi,contoh list product yang ingin dilihat oleh client.

3. Monolith -> merupakan sebuah metode untuk merancang sebuah layanan aplikasi dalam 1 aplikasi saja,
contoh: misal seperti project aplikasi ecommerse ber skala kecil.
    
    Microservice -> merupakan sebuah metode untuk merancang aplikasi dengan cara memisahkan layanan
layanan yang dimiliki menjadi beberapa bagian,jadi dari sebuah bentuk aplikasi besar,layanan yang ada 
didalam nya dipecah menjadi bagian-bagian yang terpisah dan independen,dan berkomunikasi via REST API
contoh: seperti aplikasi eccommerse yang sudah berskala besar,dan punya banyak layanan didalam nya,oleh
karena itu lebih baik dipecah layanan nya agar lebih mudah untuk memaintance nya.

4. Inversion of Control -> prinsip desain dalam pengembangan perangkat lunak yang membalikkan alur kontrol dalam aplikasi.

   Dependency Injection -> Sebuah teknik dimana object-object yang dibutuhkan oleh sebuah class atau dependency
yang disediakan oleh pihak external, bukan diciptakan sendiri oleh class tersebut.
5. Java Programming Language -> merupakan sebuah bahasa pemograman berorientasi object atau OOP
dari perusahaan oracle.Program java dikompilasi menggunakan sebuah mesin virtual yang bernama JVM.

    Springboot -> Framework java yang membantu developer untuk mengembangkan suatu aplikasi berbasis java,terutama
aplikasi web dan enterprise.

## Design modules

Dalam suatu schenario ada requirement membuat aplikasi e-commerse seperti Tokopedia seperti berikut:

1. Catalog, pelanggan mencari product di toko
    ![catalog](imgs/catalog.png)
2. Item, bisa melihat detail informasi produk
    ![items](imgs/item.png)
3. Cart, pelanggan bisa menambahkan produk yang ingin di beli ke keranjang
    ![cart](imgs/cart.png)
4. Setelah di checkout, masuk ke list transaction
    ![list-transaction](imgs/list-transaction.png)
5. Kita juga bisa liat detail transactionya
    ![detail-transaction](imgs/detail-transaction.png)

Kemudian temen-temen buat design database, module (monolith/microservices) berdasarkan gambar atau schenario tersebut. Serta jelakan mengapa menggunakan design tersebut.

## Praktek

Berdasarkan analisa tersebut, buat project monorepo (pada repository ini) dengan menggunakan framework springboot seperti berikut specifikasinya:

- Database: `PostgreSQL 15`
- JDK version: `Oracle JDK 17 or later`
- Springboot version: `3.0.x`

terkait design system Toko, Barang, Pembelian pada ecommerse tersebut.