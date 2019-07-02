---------------------------
-- Insérer dans les tables
---------------------------

-- MEMBERS
INSERT INTO MEMBERS VALUES (1,'Levy','Dylan','1996-10-10','MEN','41867@etu.he2b.be','0211223344',null, null, null,null);
INSERT INTO MEMBERS VALUES (2,'Levy','Sarah','1999-03-26','WOMAN',null,null,null, null, null,null);
INSERT INTO MEMBERS VALUES (3,'Mendez','Micaela','1997-04-17','WOMAN',null, null,null, null, null,null);

--HOBBIES
INSERT INTO HOBBIES VALUES (1,2,'Equitation');
INSERT INTO HOBBIES VALUES (2,2,'Lecture');

-- LINKS
INSERT INTO LINKS VALUES(1, 'FRIENDS', 0, 1, 2, TRUE);
INSERT INTO LINKS VALUES(2, 'FRIENDS', 0, 2, 3, TRUE);

--SEQUENCES
INSERT INTO SEQUENCES VALUES('MEMBERS', 3);
INSERT INTO SEQUENCES VALUES('HOBBIES', 2);
INSERT INTO SEQUENCES VALUES ('LINKS',2);