type SharedBookmarkListDtoResponse = {
  bookmarkId: number;
  title: string;
  url: string;
  description: string | null;
  userName: string | null;
  userAliasId: string | null;
};

export { SharedBookmarkListDtoResponse };
