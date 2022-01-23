
type UserEditorRegisterResult = {
  type: "register",
  userAliasId: string,
  userName: string,
  mailAddress: string,
  adminFlag: boolean;
}
type UserEditorRemoveResult = {
  type: "remove",
}

type UserEditorResult = UserEditorRegisterResult | UserEditorRemoveResult

export {
  UserEditorRegisterResult,
  UserEditorRemoveResult,
  UserEditorResult
}