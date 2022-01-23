import { AxiosError } from "axios";
import { AppMessage } from "../AppMessage";
import { AjaxResponseType } from "./AjaxResponseType";
import { SuccessResponse } from "./SuccessResponse";

class FailedResponse {
  readonly type: AjaxResponseType = "failed";

  constructor(private readonly axiosError: AxiosError) {}

  public isSuccess(): this is SuccessResponse<unknown> {
    return false;
  }

  public isFailed(): this is FailedResponse {
    return true;
  }

  public getAppMessages(): AppMessage[] {
    if (!this.axiosError.isAxiosError || this.axiosError.response == null) {
      return [];
    }
    return this.axiosError.response.data.messages;
  }
}

export { FailedResponse };
