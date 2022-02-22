ALTER SEQUENCE seq_board INCREMENT BY 1;

select seq_baord.NEXTVAL from dual;

drop SEQUENCE seq_board;

create sequence seq_board start with 1 INCREMENT BY 1;

truncate table tbl_board;

select count(bno) from tbl_board;

select * from tbl_board order by bno;

insert into tbl_board (bno, title, content, writer)
values(SEQ_BOARD.nextval, '새 글', '내용', 'parkh');

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

insert into tbl_board (bno, title, content, writer)
(select SEQ_BOARD.nextval, title, content, writer from tbl_board);

commit;

select * from tbl_board where bno=10;

select * from tbl_board where bno like(10);

SELECT * FROM TABLE (DBMS_XPLAN.DISPLAY_CURSOR(null, null, 'ALLSTATS LAST'));


/* 최신 순으로 정렬하기 위해 */
select * from tbl_board order by bno+1 DESC;

select /*+FULL(tbl_board) */ * from tbl_board order by bno desc;
select /*+INDEX_DESC(tbl_board pk_board) */ * from tbl_board where bno > 0;
select /*+INDEX_ASC(tbl_board pk_board) */ * from tbl_board where bno > 0;

select /*+FULL(tbl_board) */ rownum rn, bno, title from tbl_board;

select /*+INDEX_DESC(tbl_board pk_board) */ rownum rn, bno, title, content from tbl_board where rownum <= 10;

select * 
from (select /*+INDEX_DESC(tbl_board pk_board) */ rownum rn, bno, title, content from tbl_board where rownum >=20 )
where rn > 10;

select bno, title, content, writer, regDate, updateDate
from (select /*+INDEX_DESC(tbl_board pk_board) */ rownum rn, bno, title, content, writer, regDate, updateDate from tbl_board where rownum <=20 )
where rn > 10;

select /*+INDEX_DESC(tbl_board pk_board) */ rownum rn, bno, title, content, writer, regDate, updateDate from tbl_board where rownum <=20;