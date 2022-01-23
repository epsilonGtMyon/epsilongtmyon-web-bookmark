create table APP_USER (
   USER_ID       bigint        not null
  ,USER_ALIAS_ID varchar(10)  not null
  ,USER_NAME     varchar(50)   not null
  ,MAIL_ADDRESS  varchar(255)  not null
  ,PASSWORD      varchar(255)  not null
  ,ADMIN_FLAG     boolean       not null default false
  ,constraint PK_APP_USER primary key (
    USER_ID
  )
  ,constraint UQ_APP_USER_USER_ALIAS_ID unique (
    USER_ALIAS_ID
  )

);

comment on table  APP_USER               is 'アプリユーザー';
comment on column APP_USER.USER_ID       is 'ユーザーID';
comment on column APP_USER.USER_ALIAS_ID is 'ユーザー別ID';
comment on column APP_USER.USER_NAME     is 'ユーザー名';
comment on column APP_USER.MAIL_ADDRESS  is 'メールアドレス';
comment on column APP_USER.PASSWORD      is 'パスワード';
comment on column APP_USER.ADMIN_FLAG     is '管理者フラグ';
