import { DefaultAjaxClient } from "@/common/ajax/DefaultAjaxClient";
import { useAjaxClient } from "@/common/ajax/useAjaxClient";
import { AjaxResponse } from "@/common/ajax/response/AjaxResponse";
import { BookmarkEditorInitRequest } from "./apispec/BookmarkEditorInitRequest";
import { BookmarkEditorInitResponse } from "./apispec/BookmarkEditorInitResponse";
import { BookmarkEditorRemoveRequest } from "./apispec/BookmarkEditorRemoveRequest";
import { BookmarkEditorRegisterRequest } from "./apispec/BookmarkEditorRegisterRequest";
import { BookmarkEditorRegisterResponse } from "./apispec/BookmarkEditorRegisterResponse";

class BookmarkEditorService {
  constructor(private ajax: DefaultAjaxClient) {}

  init(bookmarkId: number): Promise<AjaxResponse<BookmarkEditorInitResponse>> {
    const request: BookmarkEditorInitRequest = {
      bookmarkId,
    };
    return this.ajax.get<BookmarkEditorInitResponse>(
      "bookmark-editor/init",
      request
    );
  }

  register(
    request: BookmarkEditorRegisterRequest
  ): Promise<AjaxResponse<BookmarkEditorRegisterResponse>> {
    return this.ajax.post<BookmarkEditorRegisterResponse>(
      "bookmark-editor/register",
      request
    );
  }

  remove(bookmarkId: number): Promise<AjaxResponse<void>> {
    const request: BookmarkEditorRemoveRequest = {
      bookmarkId,
    };

    return this.ajax.post<void>("bookmark-editor/remove", request);
  }
}

function useBookmarkEditorService(): BookmarkEditorService {
  const ajax = useAjaxClient();

  return new BookmarkEditorService(ajax);
}

export { BookmarkEditorService, useBookmarkEditorService };
