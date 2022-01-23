type UserDtoResponse = {
  userAliasId: string;
  userName: string;
  mailAddress: string;
  adminFlag: boolean;
};

type UserListSearchResponse = {
  userList: UserDtoResponse[];
};

export { UserListSearchResponse, UserDtoResponse };
