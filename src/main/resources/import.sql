insert into BIBLIOTHEQUE(ID,VILLE,NUMERO,RUE,ZIP,PRENOM,NOM,TYPE) values(101,'Paris',11,'rue de la bbtq 1',75000,'PrenomDir1','NomDir1','ASSOCIATIVE');
insert into BIBLIOTHEQUE(ID,VILLE,NUMERO,RUE,ZIP,PRENOM,NOM,TYPE) values(102,'Toulouse',12,'rue de la bbtq 2',31000,'PrenomDir2','NomDir2','NATIONALE');
insert into BIBLIOTHEQUE(ID,VILLE,NUMERO,RUE,ZIP,PRENOM,NOM,TYPE) values(103,'Marseille',13,'rue de la bbtq 3',13000,'PrenomDir3','NomDir3','PUBLIQUE');
insert into BIBLIOTHEQUE(ID,VILLE,NUMERO,RUE,ZIP,PRENOM,NOM,TYPE) values(104,'Strasbourg',14,'rue de la bbtq 4',67000,'PrenomDir4','NomDir4','SCOLAIRE');
insert into BIBLIOTHEQUE(ID,VILLE,NUMERO,RUE,ZIP,PRENOM,NOM,TYPE) values(105,'Brest',15,'rue de la bbtq 5',29000,'PrenomDir5','NomDir5','UNIVERSITAIRE');
insert into BIBLIOTHEQUE(ID,VILLE,NUMERO,RUE,ZIP,PRENOM,NOM,TYPE) values(106,'Caen',16,'rue de la bbtq 6',59000,'PrenomDir6','NomDir6','PUBLIQUE');

insert into LIVRE(ID,AUTEUR,GENRE,ISBN,NB_PAGE,TITRE) values(101,'Ellroy','POLAR','12345ISBN','345','American Tabloid');
insert into LIVRE(ID,AUTEUR,GENRE,ISBN,NB_PAGE,TITRE) values(102,'Ellroy','POLAR','12346ISBN','355','L.A. Confidential');
insert into LIVRE(ID,AUTEUR,GENRE,ISBN,NB_PAGE,TITRE) values(103,'Ellroy','POLAR','12347ISBN','323','Le Dhalia Noir');

insert into LIVRE(ID,AUTEUR,GENRE,ISBN,NB_PAGE,TITRE) values(104,'Demasio','SF','12310ISBN','345','La horde du contrevent');
insert into LIVRE(ID,AUTEUR,GENRE,ISBN,NB_PAGE,TITRE) values(105,'Demasio','SF','12349ISBN','355','La zone du dehors');
insert into LIVRE(ID,AUTEUR,GENRE,ISBN,NB_PAGE,TITRE) values(106,'Demasio','SF','12348ISBN','323','Les furtifs');

insert into BIBLIOTHEQUE_CATALOGUE(BIBLIOTHEQUE_ID,CATALOGUE_ID) values (101,101);
insert into BIBLIOTHEQUE_CATALOGUE(BIBLIOTHEQUE_ID,CATALOGUE_ID) values (101,102);
insert into BIBLIOTHEQUE_CATALOGUE(BIBLIOTHEQUE_ID,CATALOGUE_ID) values (101,103);
insert into BIBLIOTHEQUE_CATALOGUE(BIBLIOTHEQUE_ID,CATALOGUE_ID) values (106,104);
insert into BIBLIOTHEQUE_CATALOGUE(BIBLIOTHEQUE_ID,CATALOGUE_ID) values (106,105);
insert into BIBLIOTHEQUE_CATALOGUE(BIBLIOTHEQUE_ID,CATALOGUE_ID) values (106,106);