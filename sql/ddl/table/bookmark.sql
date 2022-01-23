create table BOOKMARK (
   BOOKMARK_ID         bigint         not null
  ,USER_ID             bigint         not null
  ,USER_LIST_ORDER     int            not null
  ,TITLE               varchar(30)   not null
  ,URL                 varchar(200)  not null
  ,DESCRIPTION         varchar(1000)
  ,SHARED_FLAG         boolean       not null
  ,CREATED_DATE        timestamp     not null
  ,constraint PK_BOOKMARK primary key (
    BOOKMARK_ID
  )
  ,constraint UQ_BOOKMARK_1 unique (
     USER_ID
    ,USER_LIST_ORDER
  )
);

comment on table  BOOKMARK                       is 'ブックマーク';
comment on column BOOKMARK.BOOKMARK_ID           is 'ブックマークID';
comment on column BOOKMARK.USER_ID               is 'ユーザーID';
comment on column BOOKMARK.USER_LIST_ORDER       is 'ユーザー表示順';
comment on column BOOKMARK.TITLE                 is 'タイトル';
comment on column BOOKMARK.URL                   is 'URL';
comment on column BOOKMARK.DESCRIPTION           is '説明';
comment on column BOOKMARK.SHARED_FLAG           is '共有フラグ';
comment on column BOOKMARK.CREATED_DATE          is '作成日時';
