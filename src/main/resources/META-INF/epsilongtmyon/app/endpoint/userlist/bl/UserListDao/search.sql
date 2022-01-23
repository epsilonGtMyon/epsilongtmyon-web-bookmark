SELECT
  /*%expand */*
FROM
  app_user  u
WHERE
/*%if @isNotEmpty(param.userAliasId) */
u.user_alias_id LIKE /*@infix(param.userAliasId)*/'' ESCAPE '$'
/*%end */

/*%if @isNotEmpty(param.userName) */
AND u.user_name LIKE /*@infix(param.userName)*/'' ESCAPE '$'
/*%end */

/*%if @isNotEmpty(param.mailAddress) */
AND u.mail_address LIKE /*@infix(param.mailAddress)*/'' ESCAPE '$'
/*%end */

ORDER BY
  u.user_alias_id
