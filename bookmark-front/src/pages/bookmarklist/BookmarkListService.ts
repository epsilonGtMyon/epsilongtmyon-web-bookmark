import { DefaultAjaxClient } from "@/common/ajax/DefaultAjaxClient";
import { AjaxResponse } from "@/common/ajax/response/AjaxResponse";
import { useAjaxClient } from "@/common/ajax/useAjaxClient";
import { BookmarkListSearchResponse } from "./apispec/BookmarkListSearchResponse";

class BookmarkListService {
  constructor(private ajax: DefaultAjaxClient) {}

  public search(): Promise<AjaxResponse<BookmarkListSearchResponse>> {
    return this.ajax.get<BookmarkListSearchResponse>("bookmark-list/search");
  }
}

function useBookmarkListService(): BookmarkListService {
  const ajax = useAjaxClient();

  return new BookmarkListService(ajax);
}

export { BookmarkListService, useBookmarkListService };
