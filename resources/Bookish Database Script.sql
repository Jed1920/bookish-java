
create database test;

use test; 

create table csv_list
(
id					int not null,
title 				varchar(150) not null,
author				varchar(250) not null,
book_category_id	varchar(150),
created_at			varchar(150),
updated_at			varchar(150),
slug				varchar(150),
isbn				varchar(150),
subtitle			varchar(150),
subjects			varchar(512),
cover_photo_url		varchar(512),
primary key 	(id)
);

create table book
(
id 				INT unsigned not null auto_increment, 
title 			varchar(150) not null,
author 			varchar(250) not null,
primary key 	(id)
);
insert into book (title,author)
select distinct title,author
from csv_list;


create table book_copy
(
id 				INT unsigned not null, 
book_id			INT unsigned not null,
primary key 	(id),
FOREIGN KEY (book_id) REFERENCES book(id)
);
insert into book_copy (id, book_id)
select csv_list.id, book.id 
from csv_list join book on csv_list.title = book.title and csv_list.author = book.author;


select id from book_copy where book_id = 441;

select * from book;
select * from csv_list;
select * from book_copy;


