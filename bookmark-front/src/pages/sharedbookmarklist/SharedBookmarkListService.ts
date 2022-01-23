import { DefaultAjaxClient } from "@/common/ajax/DefaultAjaxClient";
import { AjaxResponse } from "@/common/ajax/response/AjaxResponse";
import { useAjaxClient } from "@/common/ajax/useAjaxClient";
import { SharedBookmarkListSearchResponse } from "./apispec/SharedBookmarkListSearchResponse";

class SharedBookmarkListService {
  constructor(private ajax: DefaultAjaxClient) {}

  public search(): Promise<AjaxResponse<SharedBookmarkListSearchResponse>> {
    return this.ajax.get<SharedBookmarkListSearchResponse>(
      "shared-bookmark-list/search"
    );
  }
}

function useSharedBookmarkListService(): SharedBookmarkListService {
  const ajax = useAjaxClient();

  return new SharedBookmarkListService(ajax);
}

export { useSharedBookmarkListService, SharedBookmarkListService };
