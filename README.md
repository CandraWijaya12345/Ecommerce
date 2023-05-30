# Pembuatan Backend Api untuk Aplikasi E-Commerce Sederhana
Ecommerce merupakan tugas yang bertujuan untuk memenuhi mata kuliah pemrograman berorientasi obyek. Pembuatan tugas ini dilakukan secara individu, atas nama `I Gusti Ngurah Agung Candra Wijaya` dengan NIM `2205551092`. Ecommerce berisikan tentang pembuatan backend API untuk aplikasi e-commerce sederhana. Pembuatan backend ini melibatkan pengembangan server yang akan menangani permintaan dan tanggapan antara aplikasi frontend dan database. Tipe permintaan yang dimaksud yaitu `GET (untuk mendapatkan list atau detail data dari entitas)`, `POST (untuk membuat data entitas baru)`, `PUT (untuk mengubah data dari entitas)`, dan `DELETE (untuk menghapus dari entitas)`. Pembuatan backend API ini memiliki tujuan untuk mengakses dan memanipulasi data setiap entitas dari database. Selain itu, adapun ketentuan-ketentuan umum dan khusus dalam pembuatan tugas tersebut. Berikut ketentuan umum dalam pembuatan tugas tersebut.
1.	Menggunakan struktur tabel database dan request method pada API yang telah ditentukan. 
2.	Body pada request POST dan PUT serta response yang diberikan oleh server API menggunakan format JSON.
3.	Otorisasi akses API menggunakan API key yg di set melalui ENV variable. 
4.	Data disimpan pada database SQLite, 
5.	Pengujian API menggunakan aplikasi Postman.
6.	Alamat Port menggunakan 3 digit terakhir dari NIM yaitu 092.


Sedangkan untuk ketentuan khusus dalam pembuatan tugas meliputi:
1.	Spesifikasi API GET
•	GET /users => daftar semua user
•	GET /users/{id} => informasi user dan alamatnya, jika tidak ada beri HTTP eror response beserta pesannya
•	GET /users/{id}/products => daftar produk milik user
•	GET /users/{id}/orders => daftar order milik user
•	GET /users/{id}/reviews => daftar review yg dibuat user
•	GET /orders/{id} => informasi order, buyer, order detail, review, produk: title, price
•	GET /products => daftar semua produk
•	GET /products/{id} => informasi produk dan seller
•	Filter dengan query params misal: GET/products?field=stock&cond=largerEqual&val=10
2.	Spesifikasi API POST dan PUT
•	Ketika membuat entitas baru, semua data harus lengkap. Jika tidak, maka berikan HTTP error response (misal: 400 untuk BAD REQUEST), beserta pesan errornya. 
•	Ketika melakukan update pada entitas, pastikan entitas memang ada. Jika tidak, maka berikan HTTP error response beserta pesan errornya.

Berikut penjelasan lebih lanjut mengenai pembuatan backend tersebut beserta hasilnya.
## pom.xml
File `pom.xml` berisikan mengenai konfigurasi dan informasi yang diperlukan untuk mengelola proyek menggunakan Maven. Maven akan menggunakan berkas ini untuk mengelola dependensi, mengatur kompilasi, dan menjalankan tugas-tugas lainnya dalam proses pengembangan perangkat lunak.

## SQLiteDatabase
File `SQLiteDatabase` berisikan mengenai data-data yang disimpan pada database SQLitenya.

## src/main/java
File `src/main/java` berisikan mengenai pengimplementasian dari request method pada API.

## GET
- GET /users untuk mendapatkan daftar semua user yang termuat di dalam database. <br/>
`localhost:8092/users` 

```
{
    "User Information": [
        {
            "First_Name": "Rama",
            "Type": "seller",
            "Email": "Ramaputra@gmail.com",
            "Last_Name": "Putra",
            "id_user": 101,
            "Phone Number": "081938475847"
        },
        {
            "First_Name": "Candra",
            "Type": "seller",
            "Email": "candrawijaya@gmail.com",
            "Last_Name": "Wijaya",
            "id_user": 102,
            "Phone Number": "081728374837"
        },
        {
            "First_Name": "Intan",
            "Type": "seller",
            "Email": "Intanapriallana@gmail.com",
            "Last_Name": "Apriliana",
            "id_user": 103,
            "Phone Number": "081627384637"
        },
        {
            "First_Name": "Ari",
            "Type": "seller",
            "Email": "aridiah@gmail.com",
            "Last_Name": "Diah",
            "id_user": 104,
            "Phone Number": "081627382738"
        },
        {
            "First_Name": "Sukma",
            "Type": "seller",
            "Email": "sukmanigraha@gmail.com",
            "Last_Name": "Nigraha",
            "id_user": 105,
            "Phone Number": "081726382732"
        },
        {
            "First_Name": "Roby",
            "Type": "buyer",
            "Email": "robydwimartha@gmail.com",
            "Last_Name": "Dwimartha",
            "id_user": 106,
            "Phone Number": "081129384738"
        },
        {
            "First_Name": "Nanda",
            "Type": "buyer",
            "Email": "nandafebian@gmail.com",
            "Last_Name": "Febian",
            "id_user": 107,
            "Phone Number": "081734726374"
        },
        {
            "First_Name": "Sindhu",
            "Type": "buyer",
            "Email": "sindhuwedana@gmail.com",
            "Last_Name": "Wedana",
            "id_user": 108,
            "Phone Number": "081738473652"
        },
        {
            "First_Name": "Davin",
            "Type": "buyer",
            "Email": "davinditya@gmail.com",
            "Last_Name": "Ditya",
            "id_user": 109,
            "Phone Number": "081837462848"
        },
        {
            "First_Name": "Boy",
            "Type": "buyer",
            "Email": "boybrahmanda@gmail.com",
            "Last_Name": "Brahmanda",
            "id_user": 110,
            "Phone Number": "081736482746"
        }
    ]
}
```
<br/>

- GET /users/{id} untuk mendapatkan informasi user dan alamatnya dengan menggunakan id tertentu. <br/>
`localhost:8092/users/109` 

```
{
    "User Information": [
        {
            "First_Name": "Davin",
            "Type": "buyer",
            "Email": "davinditya@gmail.com",
            "Last_Name": "Ditya",
            "Id": 109,
            "City": "Palembang",
            "Phone Number": "081837462848",
            "Postcode": "34567",
            "Province": "Sumatera Selatan"
        }
    ]
}
```

<br/>

- GET /users/{id}/products untuk mendapatkan daftar produk milik user dengan menggunakan id tertentu. <br/>
`localhost:8092/users/101/products` 

```
{
    "Products Information": [
        {
            "User ID": 101,
            "Nama": "Rama",
            "Harga": 150000,
            "Deskripsi": "Sepatu sneakers dengan desain modern dan nyaman",
            "ID Barang": 302,
            "Nama Produk": "Sepatu Sneakers",
            "Stock": 8
        },
        {
            "User ID": 101,
            "Nama": "Rama",
            "Harga": 180000,
            "Deskripsi": "Kemeja polo dengan bahan berkualitas dan gaya yang stylish",
            "ID Barang": 309,
            "Nama Produk": "Kemeja Polo",
            "Stock": 4
        }
    ]
}
```

<br/>

- GET /users/{id}/orders untuk mendapatkan daftar order milik user dengan menggunakan id tertentu. <br/>
`localhost:8092/users/106/orders` 

```

    "Order Information": [
        {
            "Order": 202,
            "Nama": "Roby",
            "Total": 150000,
            "Diskon": 25000,
            "id user": 106
        },
        {
            "Order": 209,
            "Nama": "Roby",
            "Total": 180000,
            "Diskon": 27000,
            "id user": 106
        },
        {
            "Order": 213,
            "Nama": "Roby",
            "Total": 250000,
            "Diskon": 28000,
            "id user": 106
        }
    ]
}
```

<br/>

- GET /users/{id}/reviews untuk mendapatkan daftar review yang dibuat oleh user dengan menggunakan id tertentu. <br/>
`localhost:8092/users/106/reviews` 

```
{
    "Reviews Information": [
        {
            "User": 106,
            "star": 2,
            "First Name": "Roby",
            "Deskripsi": "Kualitas produk kurang memuaskan",
            "Last Name": "Dwimartha",
            "order": 202
        },
        {
            "User": 106,
            "star": 3,
            "First Name": "Roby",
            "Deskripsi": "Pelayanan pelanggan yang perlu ditingkatkan",
            "Last Name": "Dwimartha",
            "order": 209
        },
        {
            "User": 106,
            "star": 3,
            "First Name": "Roby",
            "Deskripsi": "Harga sedikit mahal untuk kualitasnya",
            "Last Name": "Dwimartha",
            "order": 213
        },
        {
            "User": 106,
            "star": 2,
            "First Name": "Roby",
            "Deskripsi": "Kualitas produk kurang memuaskan",
            "Last Name": "Dwimartha",
            "order": 202
        },
        {
            "User": 106,
            "star": 3,
            "First Name": "Roby",
            "Deskripsi": "Pelayanan pelanggan yang perlu ditingkatkan",
            "Last Name": "Dwimartha",
            "order": 209
        },
        {
            "User": 106,
            "star": 3,
            "First Name": "Roby",
            "Deskripsi": "Harga sedikit mahal untuk kualitasnya",
            "Last Name": "Dwimartha",
            "order": 213
        }
    ]
}
```

<br/>

- GET /orders/{id} untuk mendapatkan informasi order, buyer, detail order, review, product title, beserta pricenya secara id tertentu. <br/>
`localhost:8092/orders/201` 

```
{
    "Orders Information": [
        {
            "idUser": 107,
            "idOrder": 201,
            "Description": "Pelayanan yang baik",
            "Price": "400000",
            "Star": 4,
            "Note": 100001,
            "idProduct": 301,
            "Quantity": 2,
            "Title": "Tas Buaya",
            "Name": "Nanda"
        }
    ]
}
```

<br/>

- GET /products untuk mendapatkan daftar semua produk. <br/>
`localhost:8092/products` 

```
{
    "Product Information": [
        {
            "Description": "Tas dari kulit buaya untuk menciptakan kenyamanan masyarakat terbaik",
            "User": 102,
            "Price": "200000",
            "Title": "Tas Buaya",
            "Id": 301,
            "Stock": 5
        },
        {
            "Description": "Sepatu sneakers dengan desain modern dan nyaman",
            "User": 101,
            "Price": "150000",
            "Title": "Sepatu Sneakers",
            "Id": 302,
            "Stock": 8
        },
        {
            "Description": "Baju batik dengan motif tradisional yang elegan",
            "User": 104,
            "Price": "250000",
            "Title": "Baju Batik",
            "Id": 303,
            "Stock": 6
        },
        {
            "Description": "Jam tangan pria dengan tampilan mewah dan presisi",
            "User": 104,
            "Price": "180000",
            "Title": "Jam Tangan Pria",
            "Id": 304,
            "Stock": 10
        },
        {
            "Description": "Celana jeans dengan gaya kasual yang trendi",
            "User": 105,
            "Price": "120000",
            "Title": "Celana Jeans",
            "Id": 305,
            "Stock": 12
        },
        {
            "Description": "Tas ransel dengan banyak kompartemen untuk keperluan sehari-hari",
            "User": 103,
            "Price": "220000",
            "Title": "Tas Ransel",
            "Id": 306,
            "Stock": 7
        },
        {
            "Description": "Kacamata sunglasses dengan perlindungan UV yang tinggi",
            "User": 105,
            "Price": "90000",
            "Title": "Kacamata Sunglasses",
            "Id": 307,
            "Stock": 9
        },
        {
            "Description": "Topi trucker dengan desain sporty dan nyaman dipakai",
            "User": 103,
            "Price": "50000",
            "Title": "Topi Trucker",
            "Id": 308,
            "Stock": 15
        },
        {
            "Description": "Kemeja polo dengan bahan berkualitas dan gaya yang stylish",
            "User": 101,
            "Price": "180000",
            "Title": "Kemeja Polo",
            "Id": 309,
            "Stock": 4
        },
        {
            "Description": "Dompet kulit asli dengan desain yang elegan dan fungsional",
            "User": 102,
            "Price": "300000",
            "Title": "Dompet Kulit",
            "Id": 310,
            "Stock": 11
        }
    ]
}
```

<br/>

- GET /products/{id} untuk mendapatkan informasi produk dan seller dengan menggunakan id tertentu. <br/>
`localhost:8092/products/101` 

```
{
    "Product Information": [
        {
            "First_Name": "Rama",
            "Type": "seller",
            "Description": "Sepatu sneakers dengan desain modern dan nyaman",
            "Email": "Ramaputra@gmail.com",
            "Price": "150000",
            "Last_Name": "Putra",
            "Title": "Sepatu Sneakers",
            "Seller": 101,
            "Id": 302,
            "id_user": 101,
            "Phone Number": "081938475847",
            "Stock": 8
        },
        {
            "First_Name": "Rama",
            "Type": "seller",
            "Description": "Kemeja polo dengan bahan berkualitas dan gaya yang stylish",
            "Email": "Ramaputra@gmail.com",
            "Price": "180000",
            "Last_Name": "Putra",
            "Title": "Kemeja Polo",
            "Seller": 101,
            "Id": 309,
            "id_user": 101,
            "Phone Number": "081938475847",
            "Stock": 4
        }
    ]
}
```

<br/>

## POST
- Membuat data baru ke dalam aplikasi e-commerce dengan id baru <br/>
```
{
            "First_Name": "Tude",
            "Type": "seller",
            "Email": "tudeprayatna@gmail.com",
            "Last_Name": "Prayatna",
            "id_user": 111,
            "Phone Number": "081456873526"
}
```
`1 rows inserted!`


<br/>

## PUT
- Mengubah data yang ada pada id tertentu  <br/>
```
 {
            "First_Name": "Tude",
            "Type": "seller",
            "Email": "tudeprayatna123@gmail.com",
            "Last_Name": "Prayatna",
            "id_user": 111,
            "Phone Number": "081456873526"
        }
```
`1 rows updated!`

<br/>

## DELETE
- Menghapus data dengan id tertentu <br/>
```
localhost:8092/users/108
```
`1 rows deleted!`
