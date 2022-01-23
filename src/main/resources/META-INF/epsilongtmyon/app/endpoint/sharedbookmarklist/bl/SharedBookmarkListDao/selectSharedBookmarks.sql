select
   b.bookmark_id
  ,b.title
  ,b.url
  ,b.description
  ,u.user_name
  ,u.user_alias_id
from
  bookmark b
left outer join
  app_user u
on (b.user_id = u.user_id)
where
  b.shared_flag = true
order by
  b.created_date