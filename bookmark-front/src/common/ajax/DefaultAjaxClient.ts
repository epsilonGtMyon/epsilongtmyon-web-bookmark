import { AxiosError, AxiosResponse } from "axios";
import { Loading } from "quasar";
import { MessageDialog } from "../components/messagedialog/MessageDialog";

import { AjaxClient } from "./AjaxClient";
import { AjaxClientCore } from "./core/AjaxClientCore";
import { AjaxResponse } from "./response/AjaxResponse";
import { FailedResponse } from "./response/FailedResponse";
import { SuccessResponse } from "./response/SuccessResponse";

async function handleAxiosError(
  e: unknown,
  messageDialog: MessageDialog
): Promise<FailedResponse | null> {
  const ex = e as AxiosError<AxiosResponse>;
  if (!ex.isAxiosError || ex.response == null) {
    return null;
  }
  const response = ex.response;
  if (response.headers["x-application-error"] === "1") {
    return new FailedResponse(ex);
  }

  if (response.status === 401) {
    //UNAUTHORIZED
    //再読み込みしてログイン画面に
    Loading.hide();
    await messageDialog.error("認証が切れました再ログインしてください。");
    Loading.show();
    location.reload();
    return null;
  }

  if (response.status === 403) {
    //FORBIDDEN
    Loading.hide();
    await messageDialog.error("権限がありません。");
    Loading.show();
    return null;
  }

  return null;
}

class DefaultAjaxClient implements AjaxClient {
  constructor(
    private readonly _core: AjaxClientCore,
    private readonly _urlConverter: (url: string) => string,
    private readonly messageDialog: MessageDialog
  ) {}

  async get<R>(url: string, req?: any): Promise<AjaxResponse<R>> {
    const destUrl = this._urlConverter(url);

    return this._wrap(() => {
      return this._core.get<R>(destUrl, req);
    });
  }

  async post<R>(url: string, req?: any): Promise<AjaxResponse<R>> {
    const destUrl = this._urlConverter(url);

    return this._wrap(() => {
      return this._core.post<R>(destUrl, req);
    });
  }

  private async _wrap<R>(
    action: () => Promise<AxiosResponse<R>>
  ): Promise<AjaxResponse<R>> {
    try {
      Loading.show();

      const result = await action();

      return new SuccessResponse(result.data);
    } catch (e) {
      const failedResponse = await handleAxiosError(e, this.messageDialog);
      if (failedResponse != null) {
        return failedResponse;
      }

      console.dir(e);
      //unhandled
      throw e;
    } finally {
      Loading.hide();
    }
  }
}
export { DefaultAjaxClient };
