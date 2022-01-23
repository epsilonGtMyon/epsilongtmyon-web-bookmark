--herokuの行数制限のため横で 回答選択肢をもつ
create table QUESTIONNAIRE (
   QUESTIONNAIRE_ID         bigint        not null
  ,TITLE                    varchar(100) not null
  ,DESCRIPTION              varchar(1000)
  ,QUESTION                 varchar(1000) not null
  ,MIN_ANSWER               smallint
  ,MAX_ANSWER               smallint
  ,ENABLE_OTHER             varchar(1)    not null
  ,ANSWER01                 varchar(100)
  ,ANSWER02                 varchar(100)
  ,ANSWER03                 varchar(100)
  ,ANSWER04                 varchar(100)
  ,ANSWER05                 varchar(100)
  ,ANSWER06                 varchar(100)
  ,ANSWER07                 varchar(100)
  ,ANSWER08                 varchar(100)
  ,ANSWER09                 varchar(100)
  ,ANSWER10                 varchar(100)
  ,ANSWER11                 varchar(100)
  ,ANSWER12                 varchar(100)
  ,ANSWER13                 varchar(100)
  ,ANSWER14                 varchar(100)
  ,ANSWER15                 varchar(100)
  ,constraint PK_QUESTIONNAIRE primary key (
     QUESTIONNAIRE_ID
  )
);

comment on table QUESTIONNAIRE   is 'アンケート';
comment on column QUESTIONNAIRE.QUESTIONNAIRE_ID is 'アンケートID';
comment on column QUESTIONNAIRE.TITLE            is 'タイトル';
comment on column QUESTIONNAIRE.DESCRIPTION      is '説明';
comment on column QUESTIONNAIRE.QUESTION         is '質問';
comment on column QUESTIONNAIRE.MIN_ANSWER       is '最低回答数';
comment on column QUESTIONNAIRE.MAX_ANSWER       is '最大回答数';
comment on column QUESTIONNAIRE.ENABLE_OTHER     is 'その他を許可';
comment on column QUESTIONNAIRE.ANSWER01         is '回答０１';
comment on column QUESTIONNAIRE.ANSWER02         is '回答０２';
comment on column QUESTIONNAIRE.ANSWER03         is '回答０３';
comment on column QUESTIONNAIRE.ANSWER04         is '回答０４';
comment on column QUESTIONNAIRE.ANSWER05         is '回答０５';
comment on column QUESTIONNAIRE.ANSWER06         is '回答０６';
comment on column QUESTIONNAIRE.ANSWER07         is '回答０７';
comment on column QUESTIONNAIRE.ANSWER08         is '回答０８';
comment on column QUESTIONNAIRE.ANSWER09         is '回答０９';
comment on column QUESTIONNAIRE.ANSWER10         is '回答１０';
comment on column QUESTIONNAIRE.ANSWER11         is '回答１１';
comment on column QUESTIONNAIRE.ANSWER12         is '回答１２';
comment on column QUESTIONNAIRE.ANSWER13         is '回答１３';
comment on column QUESTIONNAIRE.ANSWER14         is '回答１４';
comment on column QUESTIONNAIRE.ANSWER15         is '回答１５';
