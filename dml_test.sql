-- CRUD : �������� ����, �б�, ����, ������ ����Ű�� ����.
-- ���α׷��� �������� ����� �������̽�(�޴�) �⺻ ���.

-- �ܼ� ��ȸ (read)
select * from TBL_STUDENT;

-- insert �׽�Ʈ (create)
insert into TBL_STUDENT values ('2023001', '�趯��', '16', '��⵵');

-- update �׽�Ʈ
update tbl_STUDENT
set age = 17, address ='���α�'
where stuno = '2022012';

-- detete ���� �׽�Ʈ
delete from TBL_STUDENT where stuno = '2023001';

--
select * from TBL_STUDENT where stuno = '2023002';

--
select * from TBL_SCORE;

-- ���⼭���ʹ� �ٸ� ���̺�� ���� �մϴ�.
/*
1. ȸ�� �α��� - ������ ȸ�� ���̵� �Է��ؼ� �����ϸ� �α��� ����
2. ��ǰ ��� ����
3. ��ǰ������ �˻��ϱ�
4. ��ǰ ��ٱ��� ��� - ��ٱ��� ���̺��� �����Ƿ� ���Ÿ� ���ϴ� ��ǰ�� List �� ���
5. ��ǰ ����(����)�ϱ� - ��ٱ����� �����͸� '����' ���̺� �Է��ϱ� (������ insert)
6. ���� ���� ���� ����. �� ���� �ݾ��� ������ֱ�
*/
select * from TBL_CUSTOM;
select * from TBL_PRODUCT;
select * from TBL_PRODUCT where pname like '%' || '����' || '%';

select * from TBL_BUY;				-- ���� ���� ���̺�
select * from TBL_BUY where customid ='mina012';
-- ������ �����ߴ� ���̺��� �������� �ʵ��� ���Ӱ� �����ؼ� �����մϴ�.
create table j_custom
as
select * from tbl_custom;

select * from j_custom;

create table j_product
as
select * from TBL_PRODUCT;

select * from j_product;

create table j_buy
as
select * from tbl_buy;2

select * from j_buy;

-- pk, fk�� �ʿ��ϸ� ���������� �߰� �մϴ�.
-- custom_id, pcode, buy_seq �÷����� pk �����ϱ�
-- tbl_buy ���̺��� �ܷ�Ű�� 2���� �ֽ��ϴ�.(j_buy �ܷ�Ű ���� �����ϰ� �ϰڽ��ϴ�.)

alter table j_custom add constraint custom_pk primary key (custom_id);
alter table j_product add constraint product_pk primary key (pcode);
alter table j_buy add constraint buy_pk primary key (buy_seq);

-- �߰� ������ �Է�
insert into j_product values ('ZZZ01','B1','���ѱ�ٸ��ī��',2400);
insert into j_product values ('APP11','A1','�������� 1�ڽ�',32500);
insert into j_product values ('APP99','A1','������� 10��',25000);

-- j_buy ���̺� ����� ������
drop sequence jbuy_seq;				-- ������ ����
-- ������ ���۰����� �ٽ� �����ϱ�
create sequence jbuy_seq start with 1075;
select jbuy_seq.nextval from dual;

--delete from j_buy where buy_seq = 1029;
-- rollback �׽�Ʈ
select * from j_buy;
alter table j_buy add constraint q_check check (quantity between 1 and 30);
-- check �������� ����
insert into j_buy values (jbuy_seq.nextval, 'twice', 'APP99', 33, sysdate);

-- 6. ���������� ���ų���
-- 2�� ���̺� join �Ͽ� ������� �հ�(���� * ����) ���ı��� ��ȸ�ϱ�
select buy_date, p.pcode, pname, quantity, price, quantity * price total from j_buy b
join j_product p
on p.pcode=b.pcode
and b.customid='twice'
order by buy_date desc
;
-- ���� ���� join ����� view �� �����. view �� create or replace �� ������ �� �������� ����.
-- view �� �������� ���̺��� �ƴմϴ�. ������ ���̺��� �̿��ؼ� ������� ������ ���̺�(���� ���̺�)
create or replace view mypage_buy
as
select buy_date, customid, p.pcode, pname, quantity, price, quantity * price total from j_buy b
join j_product p
on p.pcode=b.pcode
order by buy_date desc
;

select * from mypage_buy where customid='twice';

select sum(total) from mypage_buy where customid='twice';


-- 6�� 19�� �α��� �����ϱ� ���� �н����� �÷� �߰��� �մϴ�.
-- �н����� �÷��� �ؽð� 64���ڸ� �����մϴ�.
alter table j_custom add password char(64);

-- twice �� �н����� �� �����ϱ�
update j_custom set password = '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'
where custom_id = 'twice';

select * from j_custom;

select * from member_tbl_02;