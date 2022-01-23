import { FailedResponse } from "./FailedResponse";
import { SuccessResponse } from "./SuccessResponse";
type AjaxResponse<T> = SuccessResponse<T> | FailedResponse;

export { AjaxResponse };
