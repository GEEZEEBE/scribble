DROP TABLE scomment;
DROP TABLE sboard;
DROP TABLE susers;

DROP SEQUENCE seq_scomment_no;
DROP SEQUENCE seq_sboard_no;
DROP SEQUENCE seq_susers_no;

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
  reg_date   DATE	        NOT NULL,
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
  CONSTRAINT PK_susers PRIMARY KEY (user_id, email)
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


INSERT INTO susers VALUES (seq_susers_no.nextval, 'test@test.test', '홍길동', '1234', null);
SELECT * FROM susers;

INSERT INTO sboard VALUES (seq_sboard_no.nextval, '제목 테스트', '내용 테스트', 5, SYSDATE, 'pic01.jpg', NULL, 1);
SELECT * FROM sboard ORDER BY reg_date DESC;

INSERT INTO scomment VALUES (seq_scomment_no.nextval, '코멘트 내용 테스트', 1, 1, SYSDATE, NULL);
SELECT * FROM scomment;

SELECT ROWNUM, A.*								
FROM ( SELECT *			
	   FROM sboard b					
	   JOIN susers u					
	   ON b.user_id = u.user_id 		
	   WHERE b.isdeleted is NULL		
	   ORDER BY b.hit DESC			
) A									
WHERE ROWNUM < 5;	


SELECT *					
FROM scomment c				
JOIN susers u				
ON c.user_id = u.user_id	
WHERE c.comment_id = 1;		


SELECT *															
FROM ( SELECT ROWNUM AS RNUM, A.*									
		FROM ( SELECT *												
			   FROM sboard b										
			   JOIN susers u										
			   ON b.user_id = u.user_id								
			   WHERE (b.title || b.content || b.reg_date || u.name)	
			   LIKE '&test&'												
			   AND b.isdeleted is NULL								
			   ORDER BY b.reg_date DESC								
		) A															
		WHERE ROWNUM <= 10 										
) 																	
WHERE RNUM > 5;														