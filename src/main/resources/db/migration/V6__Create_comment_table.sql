create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	type int not null,
	commentor int not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	like_count bigint default 0,
		primary key (id)
);