create table PRIVATE_BOOKMARK (
   PRIVATE_BOOKMARK_ID bigint         not null
  ,USER_ID             bigint         not null
  ,LIST_ORDER          int            not null
  ,TITLE               varchar(30)   not null
  ,URL                 varchar(200)  not null
  ,DESCRIPTION         varchar(1000)
  ,constraint PK_PRIVATE_BOOKMARK primary key (
    PRIVATE_BOOKMARK_ID
  )
  ,constraint UQ_PRIVATE_BOOKMARK_1 unique (
    USER_ID
    ,LIST_ORDER
  )
);

comment on table  PRIVATE_BOOKMARK                       is '個人ブックマーク';
comment on column PRIVATE_BOOKMARK.PRIVATE_BOOKMARK_ID   is '個人ブックマークID';
comment on column PRIVATE_BOOKMARK.USER_ID               is 'ユーザーID';
comment on column PRIVATE_BOOKMARK.LIST_ORDER            is '表示順';
comment on column PRIVATE_BOOKMARK.TITLE                 is 'タイトル';
comment on column PRIVATE_BOOKMARK.URL                   is 'URL';
comment on column PRIVATE_BOOKMARK.DESCRIPTION           is '説明';