import { AjaxResponseType } from "./AjaxResponseType";
import { FailedResponse } from "./FailedResponse";

class SuccessResponse<T> {
  readonly type: AjaxResponseType = "success";

  constructor(public readonly data: T) {}

  public isSuccess(): this is SuccessResponse<T> {
    return true;
  }
  public isFailed(): this is FailedResponse {
    return false;
  }
}

export { SuccessResponse };
