DROP TABLE scomment;
DROP TABLE sboard;
DROP TABLE susers;

DROP SEQUENCE seq_scomment_no;
DROP SEQUENCE seq_sboard_no;
DROP SEQUENCE seq_suers_no;

CREATE TABLE sboard
(
  board_id  NUMBER         NOT NULL,
  title     VARCHAR2(500)  NOT NULL,
  content   VARCHAR2(4000) NOT NULL,
  hit       NUMBER         NOT NULL,
  reg_date  DATE	       NOT NULL,
  img_name  VARCHAR2(200) ,
  isdeleted VARCHAR2(10)  ,
  user_id   NUMBER         NOT NULL,
  CONSTRAINT PK_sboard PRIMARY KEY (board_id)
);

CREATE TABLE scomment
(
  comment_id NUMBER         NOT NULL,
  content    VARCHAR2(2500) NOT NULL,
  board_id   NUMBER         NOT NULL,
  user_id    NUMBER         NOT NULL,
  isdeleted  VARCHAR2(10)  ,
  CONSTRAINT PK_scomment PRIMARY KEY (comment_id)
);

CREATE TABLE susers
(
  user_id   NUMBER        NOT NULL,
  email     VARCHAR2(100) NOT NULL,
  name      VARCHAR2(100) NOT NULL,
  password  VARCHAR2(100) NOT NULL,
  isdeleted VARCHAR2(10) ,
  CONSTRAINT PK_susers PRIMARY KEY (user_id)
);

ALTER TABLE sboard
  ADD CONSTRAINT FK_susers_TO_sboard
    FOREIGN KEY (user_id)
    REFERENCES susers (user_id);

ALTER TABLE scomment
  ADD CONSTRAINT FK_sboard_TO_scomment
    FOREIGN KEY (board_id)
    REFERENCES sboard (board_id);

ALTER TABLE scomment
  ADD CONSTRAINT FK_susers_TO_scomment
    FOREIGN KEY (user_id)
    REFERENCES susers (user_id);
   
CREATE SEQUENCE seq_sboard_no
INCREMENT BY 1 
START WITH 1 ;

CREATE SEQUENCE seq_susers_no
INCREMENT BY 1 
START WITH 1 ;

CREATE SEQUENCE seq_scomment_no
INCREMENT BY 1 
START WITH 1 ;
