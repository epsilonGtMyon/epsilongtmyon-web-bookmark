create table QUESTIONNAIRE_ANSWER (
   QUESTIONNAIRE_ID    bigint not null
  ,USER_ID             bigint not null
  ,ANSWER01_SELECTED   varchar(1) not null
  ,ANSWER02_SELECTED   varchar(1) not null
  ,ANSWER03_SELECTED   varchar(1) not null
  ,ANSWER04_SELECTED   varchar(1) not null
  ,ANSWER05_SELECTED   varchar(1) not null
  ,ANSWER06_SELECTED   varchar(1) not null
  ,ANSWER07_SELECTED   varchar(1) not null
  ,ANSWER08_SELECTED   varchar(1) not null
  ,ANSWER09_SELECTED   varchar(1) not null
  ,ANSWER10_SELECTED   varchar(1) not null
  ,ANSWER11_SELECTED   varchar(1) not null
  ,ANSWER12_SELECTED   varchar(1) not null
  ,ANSWER13_SELECTED   varchar(1) not null
  ,ANSWER14_SELECTED   varchar(1) not null
  ,ANSWER15_SELECTED   varchar(1) not null
  ,OTHER_INPUT         varchar(1000)
  ,constraint PK_QUESTIONNAIRE_ANSWER primary key (
     QUESTIONNAIRE_ID
    ,USER_ID
  )
);

comment on table QUESTIONNAIRE_ANSWER   is 'アンケート回答';
comment on column QUESTIONNAIRE_ANSWER.QUESTIONNAIRE_ID is 'アンケートID';
comment on column QUESTIONNAIRE_ANSWER.USER_ID is 'ユーザーID';
comment on column QUESTIONNAIRE_ANSWER.ANSWER01_SELECTED   is '回答０１選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER02_SELECTED   is '回答０２選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER03_SELECTED   is '回答０３選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER04_SELECTED   is '回答０４選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER05_SELECTED   is '回答０５選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER06_SELECTED   is '回答０６選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER07_SELECTED   is '回答０７選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER08_SELECTED   is '回答０８選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER09_SELECTED   is '回答０９選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER10_SELECTED   is '回答１０選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER11_SELECTED   is '回答１１選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER12_SELECTED   is '回答１２選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER13_SELECTED   is '回答１３選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER14_SELECTED   is '回答１４選択';
comment on column QUESTIONNAIRE_ANSWER.ANSWER15_SELECTED   is '回答１５選択';
comment on column QUESTIONNAIRE_ANSWER.OTHER_INPUT         is 'その他入力';
