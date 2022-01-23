type UserEditorRegisterRequest = {
  userId: number | null;
  userAliasId: string;
  userName: string;
  mailAddress: string;
  adminFlag: boolean;
  password: string;
}


export {
  UserEditorRegisterRequest
}