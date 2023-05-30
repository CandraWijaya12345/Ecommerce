PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE addresses(
	id_user INTEGER,
	type TEXT CHECK(type IN('WNI','WNA')),
	line1 VARCHAR(55),
	line2 VARCHAR(55),
	city VARCHAR(55),
	province VARCHAR(55),
	postcode VARCHAR(55),
	FOREIGN KEY (id_user) REFERENCES users(id_user)
);
INSERT INTO addresses VALUES(101,'WNI','Jalan Kamboja','Gang Semanggi','Denpasar','Bali','80581');
INSERT INTO addresses VALUES(102,'WNA','Jalan Anggrek','Gang Mawar','Jakarta','DKI Jakarta','12345');
INSERT INTO addresses VALUES(103,'WNI','Jalan Dahlia','Gang Melati','Bandung','Jawa Barat','67890');
INSERT INTO addresses VALUES(104,'WNA','Jalan Mawar','Gang Anggrek','Surabaya','Jawa Timur','54321');
INSERT INTO addresses VALUES(105,'WNI','Jalan Melati','Gang Dahlia','Medan','Sumatera Utara','98765');
INSERT INTO addresses VALUES(106,'WNA','Jalan Tulip','Gang Lily','Semarang','Jawa Tengah','45678');
INSERT INTO addresses VALUES(107,'WNI','Jalan Lily','Gang Tulip','Yogyakarta','DI Yogyakarta','23456');
INSERT INTO addresses VALUES(108,'WNA','Jalan Mawar','Gang Anggrek','Malang','Jawa Timur','87654');
INSERT INTO addresses VALUES(109,'WNI','Jalan Dahlia','Gang Melati','Palembang','Sumatera Selatan','34567');
INSERT INTO addresses VALUES(110,'WNA','Jalan Anggrek','Gang Mawar','Pontianak','Kalimantan Barat','76543');
CREATE TABLE products(
	id_product INTEGER PRIMARY KEY AUTOINCREMENT,
	id_user INTEGER,
	title VARCHAR(55),
	description TEXT,
	price VARCHAR(100),
	stock INTEGER,
	FOREIGN KEY (id_user) REFERENCES users(id_user)
);
INSERT INTO products VALUES(301,102,'Tas Buaya','Tas dari kulit buaya untuk menciptakan kenyamanan masyarakat terbaik','200000',5);
INSERT INTO products VALUES(302,101,'Sepatu Sneakers','Sepatu sneakers dengan desain modern dan nyaman','150000',8);
INSERT INTO products VALUES(303,104,'Baju Batik','Baju batik dengan motif tradisional yang elegan','250000',6);
INSERT INTO products VALUES(304,104,'Jam Tangan Pria','Jam tangan pria dengan tampilan mewah dan presisi','180000',10);
INSERT INTO products VALUES(305,105,'Celana Jeans','Celana jeans dengan gaya kasual yang trendi','120000',12);
INSERT INTO products VALUES(306,103,'Tas Ransel','Tas ransel dengan banyak kompartemen untuk keperluan sehari-hari','220000',7);
INSERT INTO products VALUES(307,105,'Kacamata Sunglasses','Kacamata sunglasses dengan perlindungan UV yang tinggi','90000',9);
INSERT INTO products VALUES(308,103,'Topi Trucker','Topi trucker dengan desain sporty dan nyaman dipakai','50000',15);
INSERT INTO products VALUES(309,101,'Kemeja Polo','Kemeja polo dengan bahan berkualitas dan gaya yang stylish','180000',4);
INSERT INTO products VALUES(310,102,'Dompet Kulit','Dompet kulit asli dengan desain yang elegan dan fungsional','300000',11);
CREATE TABLE IF NOT EXISTS "users" (
	"id_user"	INTEGER,
	"first_name"	VARCHAR(55),
	"last_name"	VARCHAR(55),
	"email"	VARCHAR(55),
	"phone_number"	VARCHAR(13),
	"type"	TEXT CHECK("type" IN ('seller', 'buyer')),
	PRIMARY KEY("id_user" AUTOINCREMENT)
);
INSERT INTO users VALUES(101,'Rama','Putra','Ramaputra@gmail.com','081938475847','seller');
INSERT INTO users VALUES(102,'Candra','Wijaya','candrawijaya@gmail.com','081728374837','seller');
INSERT INTO users VALUES(103,'Intan','Apriliana','Intanapriallana@gmail.com','081627384637','seller');
INSERT INTO users VALUES(104,'Ari','Diah','aridiah@gmail.com','081627382738','seller');
INSERT INTO users VALUES(105,'Sukma','Nigraha','sukmanigraha@gmail.com','081726382732','seller');
INSERT INTO users VALUES(106,'Roby','Dwimartha','robydwimartha@gmail.com','081129384738','buyer');
INSERT INTO users VALUES(107,'Nanda','Febian','nandafebian@gmail.com','081734726374','buyer');
INSERT INTO users VALUES(108,'Sindhu','Wedana','sindhuwedana@gmail.com','081738473652','buyer');
INSERT INTO users VALUES(109,'Davin','Ditya','davinditya@gmail.com','081837462848','buyer');
INSERT INTO users VALUES(110,'Boy','Brahmanda','boybrahmanda@gmail.com','081736482746','buyer');
CREATE TABLE orders(
	id_order INTEGER PRIMARY KEY AUTOINCREMENT,
	id_user INTEGER,
	note INTEGER,
	total INTEGER,
	discount INTEGER,
	is_paid BOOLEAN,
	FOREIGN KEY (id_user) REFERENCES users(id_user)
);
INSERT INTO orders VALUES(201,107,100001,720000,20000,1);
INSERT INTO orders VALUES(202,106,100002,630000,25000,1);
INSERT INTO orders VALUES(203,108,100003,890000,12000,1);
INSERT INTO orders VALUES(204,109,100004,410000,45000,1);
INSERT INTO orders VALUES(205,110,100005,580000,28000,1);
INSERT INTO orders VALUES(206,107,100006,540000,15000,1);
INSERT INTO orders VALUES(207,107,100007,780000,42000,1);
INSERT INTO orders VALUES(208,108,100008,940000,50000,1);
INSERT INTO orders VALUES(209,106,100009,610000,27000,1);
INSERT INTO orders VALUES(210,109,100010,880000,38000,1);
INSERT INTO orders VALUES(211,110,100011,720000,33000,1);
INSERT INTO orders VALUES(212,108,100012,630000,42000,1);
INSERT INTO orders VALUES(213,106,100013,890000,28000,1);
INSERT INTO orders VALUES(214,108,100014,410000,40000,1);
INSERT INTO orders VALUES(215,109,100015,580000,35000,1);
INSERT INTO orders VALUES(216,109,100016,540000,23000,1);
INSERT INTO orders VALUES(217,110,100017,780000,31000,1);
INSERT INTO orders VALUES(218,107,100018,940000,44000,1);
INSERT INTO orders VALUES(219,108,100019,610000,26000,1);
INSERT INTO orders VALUES(220,109,100020,880000,50000,1);
CREATE TABLE reviews(
	id_order INTEGER,
	star INTEGER,
	description TEXT,
	FOREIGN KEY (id_order) REFERENCES orders(id_order)
);
CREATE TABLE order_details(
	id_order INTEGER,
	id_product INTEGER,
	quantity INTEGER,
	price INTEGER,
	FOREIGN KEY (id_product) REFERENCES products(id_product), 
	FOREIGN KEY (id_order) REFERENCES orders(id_order)
);
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('users',111);
INSERT INTO sqlite_sequence VALUES('products',310);
INSERT INTO sqlite_sequence VALUES('orders',220);
COMMIT;
