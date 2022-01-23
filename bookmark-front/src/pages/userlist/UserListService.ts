import { DefaultAjaxClient } from "@/common/ajax/DefaultAjaxClient";
import { AjaxResponse } from "@/common/ajax/response/AjaxResponse";
import { useAjaxClient } from "@/common/ajax/useAjaxClient";
import { UserListSearchRequest } from "./apispec/UserListSearchRequest";
import { UserListSearchResponse } from "./apispec/UserListSearchResponse";

class UserListService {
  constructor(private ajax: DefaultAjaxClient) {}

  /**
   * 検索します。
   * @param request
   * @returns
   */
  search(
    request: UserListSearchRequest
  ): Promise<AjaxResponse<UserListSearchResponse>> {
    return this.ajax.get<UserListSearchResponse>("userlist/search", request);
  }
}

function useUserListService(): UserListService {
  const ajax = useAjaxClient();

  return new UserListService(ajax);
}

export { UserListService, useUserListService };
