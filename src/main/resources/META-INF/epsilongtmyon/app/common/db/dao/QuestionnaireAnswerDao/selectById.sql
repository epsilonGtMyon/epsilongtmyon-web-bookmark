select
  /*%expand*/*
from
  questionnaire_answer
where
  questionnaire_id = /* questionnaireId */1
  and
  user_id = /* userId */1
