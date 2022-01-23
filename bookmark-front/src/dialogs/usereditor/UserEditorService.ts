import { DefaultAjaxClient } from "@/common/ajax/DefaultAjaxClient";
import { AjaxResponse } from "@/common/ajax/response/AjaxResponse";
import { useAjaxClient } from "@/common/ajax/useAjaxClient";
import { UserEditorInitRequest } from "./apispec/UserEditorInitRequest";
import { UserEditorInitResponse } from "./apispec/UserEditorInitResponse";
import { UserEditorRegisterRequest } from "./apispec/UserEditorRegisterRequest";
import { UserEditorRemoveRequest } from "./apispec/UserEditorRemoveRequest";

class UserEditorService {
  constructor(private ajax: DefaultAjaxClient) {}

  init(userAliasId?: string): Promise<AjaxResponse<UserEditorInitResponse>> {
    const request: UserEditorInitRequest = {
      userAliasId,
    };
    return this.ajax.get<UserEditorInitResponse>("usereditor/init", request);
  }

  register(request: UserEditorRegisterRequest): Promise<AjaxResponse<number>> {
    return this.ajax.post<number>("usereditor/register", request);
  }

  remove(request: UserEditorRemoveRequest): Promise<AjaxResponse<never>> {
    return this.ajax.post<never>("usereditor/remove", request);
  }
}

function useUserEditorService(): UserEditorService {
  const ajax = useAjaxClient();

  return new UserEditorService(ajax);
}

export { UserEditorService, useUserEditorService };
