type BookmarkListDtoResponse = {
  bookmarkId: number;
  listOrder: number;
  title: string;
  url: string;
  description: string | null;
  sharedFlag: boolean;
};

export { BookmarkListDtoResponse };
