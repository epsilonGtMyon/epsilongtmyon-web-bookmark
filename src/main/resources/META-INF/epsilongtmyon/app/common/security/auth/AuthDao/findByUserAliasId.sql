SELECT
  user_id
 ,user_alias_id
 ,user_name
 ,password
 ,admin_flag
FROM
  app_user
WHERE
  user_alias_id = /* userAliasId */'test'
