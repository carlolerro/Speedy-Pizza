create schema speedypizza;

use speedypizza;

create table utente(
nome varchar(30) not null,
cognome varchar(30) not null,
email varchar(40) not null,
pass varchar(50) not null,
telefono varchar(15) not null,
data_registrazione timestamp not null,
tipo int not null,
pizzeria_fattorino varchar(25),
primary key(email)

);
create table indirizzo(
id int not null auto_increment,
via varchar(50) not null,
citta varchar(50) not null,
cap varchar(50) not null,
civico varchar(50) not null,
utente varchar(40),
primary key(id)
);
create table pizzeria(
 partita_iva varchar(25) not null,
 nome varchar(25) not null,
 orario_apertura time ,
 orario_chiusura time ,
 giorni_apertura varchar(50),
 indirizzo int ,
 titolare varchar(40),
 iban varchar(30) ,
 descrizione varchar(45),
 telefono varchar(45),
 primary key (partita_iva),
 foreign key(titolare) references utente(email) on update cascade on delete cascade,
 foreign key(indirizzo) references indirizzo(id) on update cascade on delete cascade
 );

create table carta(
numero_carta varchar(20) not null,
scadenza varchar(5) not null,
cvc varchar(3) not null,
intestatario varchar(40) not null,
cliente varchar(40) not null,
foreign key(cliente) references utente(email) on update cascade on delete cascade,
primary key(numero_carta)
);
 
 
 create table recensione(
	id int not null auto_increment,
	starring int not null,
	recensione varchar(600) not null,
    date date not null,
    id_ordine int not null,
    primary key (id)
);
 create table ordine(
 id int not null auto_increment,
 tipo_pagamento int(11) not null,
 stato varchar(30) not null,
 totale float not null,
 id_cliente varchar(40) not null,
 tipo_ordine int not null,
 data_ordine timestamp not null,
 id_pizzeria varchar(25) not null,
 id_indirizzo int,
 id_recensione int,
 id_carta varchar(40),
 id_fattorino varchar(40),
 numero_ordine int(11),
 tracker varchar(45),
 
 primary key(id),
 foreign key(id_pizzeria) references pizzeria(partita_iva) on update cascade on delete no action,
 foreign key(id_cliente) references utente(email) on update cascade on delete no action,
 foreign key(id_indirizzo) references indirizzo(id) on update no action on delete no action,
 foreign key(id_recensione) references recensione(id) on update no action on delete no action,
 foreign key(id_fattorino) references utente(email) on update cascade on delete no action,
 foreign key(id_carta) references carta(numero_carta) on update no action on delete no action
 );

create table categoria(
 nome varchar(20) not null,
 iva int not null,
 primary key(nome)
 );
 
create table prodotto(
 nome varchar(20) not null,
 prezzo float not null,
 disponibilita varchar(5) not null,
 id_categoria varchar(20) not null,
 ingredienti varchar(100) not null,
 id_pizzeria varchar(25) not null,
 primary key(nome,id_pizzeria),
 foreign key(id_categoria) references categoria(nome) on update cascade on delete no action,
foreign key(id_pizzeria) references pizzeria(partita_iva) on update cascade on delete cascade

 );
 create table contenuto_ordine(
 id_ordine int not null,
 id_pizzeria varchar(25) not null,
 id_prodotto varchar(20) not null,
 prezzo_unitario float not null,
 quantita int not null,
 primary key(id_ordine, id_prodotto,id_pizzeria),
 foreign key(id_ordine) references ordine(id) on update no action on delete no action,
 foreign key(id_prodotto) references prodotto(nome) on update no action on delete no action,
 foreign key(id_pizzeria) references prodotto(id_pizzeria) on update cascade on delete no action
 );
 create table richiesta_affiliazione(
 id_richiesta int auto_increment not null,
 nome_pizzeria varchar(30) not null,
 commento varchar(320) not null,
 data date not null,
 stato varchar(30) not null,
 telefono varchar(30) not null,
 partita_iva varchar(30) not null,
 cognome varchar(30) not null,
 nome varchar(30) not null,
 email varchar(30) not null,
 pass varchar(30) not null,
 primary key(id_richiesta)
 );
 
ALTER TABLE utente add foreign key(pizzeria_fattorino) references pizzeria(partita_iva) on update cascade on delete cascade;
ALTER TABLE indirizzo add foreign key (utente) references utente(email) on update cascade on delete cascade;

INSERT INTO `categoria` (`nome`, `iva`) VALUES ('Bibite',10),('Dolci',10),('Secondi',4),('Pizze',10);
INSERT INTO `indirizzo` (`id`, `via`, `citta`, `cap`, `civico`, `utente`) VALUES (1,'DI NAPOLI','Salerno','84572','26',NULL);
INSERT INTO `utente` (`nome`, `cognome`, `email`, `pass`, `telefono`, `data_registrazione`, `tipo`, `pizzeria_fattorino`) VALUES ('pinco','pallino','ristoranteeuropa@gmail.com','prova1','3333333333','2019-12-29 23:00:00',1,NULL),('Alfonso','Fiorentino','gestore@gestore.com','12345a','1234567894','2019-12-30 17:08:00',3,NULL), ('Pippo','Fiorentino','pippo5@gmail.com','prova33','1234567894','2019-12-30 17:08:00',2,NULL), ('Pippo','Pippo2','pippo@gmail.com','prova1','1234567894','2019-12-30 17:08:00',0,NULL);
INSERT INTO `richiesta_affiliazione` (`id_richiesta`, `nome_pizzeria`, `commento`, `data`, `stato`, `telefono`, `partita_iva`, `cognome`, `nome`, `email`, `pass`) VALUES (1,'da pallino','Beh, da pinco pallino','2019-12-30','Accettata','3333333333','12345678910','pallino','pinco','fiorentino.alfo@gmail.com','12345a');
INSERT INTO `pizzeria` (`partita_iva`, `nome`, `orario_apertura`, `orario_chiusura`, `giorni_apertura`, `indirizzo`, `titolare`, `iban`, `descrizione`, `telefono`) VALUES ('12345678910','Regina','01:00:00','23:59:00','lun+mar+mer+gio+ven+sab',1,'ristoranteeuropa@gmail.com','123456789',NULL,'3333333333');
INSERT INTO `prodotto` (`nome`, `prezzo`, `disponibilita`, `id_categoria`, `ingredienti`, `id_pizzeria`) VALUES ('Margherita',6.2,'si','Pizze','oLIO, POMODORO, MOZZARELLA','12345678910'),('Torta cioccolato',5.22,'si','Dolci','Cioccolato, latte','12345678910'); 
INSERT INTO `utente` (`nome`, `cognome`, `email`, `pass`, `telefono`, `data_registrazione`, `tipo`, `pizzeria_fattorino`) VALUES ('Luca','Fattorino','fattorino@fattorino.it','12345a','1234567890','2019-12-30 23:00:00',2,'12345678910');