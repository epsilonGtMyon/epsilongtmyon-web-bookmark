type BookmarkEditorRegisterResult = {
  type: "add" | "modify";
  bookmarkId: number;
  title: string;
  url: string;
  description: string;
  sharedFlag: boolean;
};

type BookmarkEditorRemoveResult = {
  type: "remove";
};

type BookmarkEditorResult =
  | BookmarkEditorRegisterResult
  | BookmarkEditorRemoveResult;

export {
  BookmarkEditorRegisterResult,
  BookmarkEditorRemoveResult,
  BookmarkEditorResult,
};
