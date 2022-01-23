import { envParam } from "../envParam";
import { instance as _core } from "./core/AjaxClientCore";
import { DefaultAjaxClient } from "./DefaultAjaxClient";
import { useMessageDialog } from "../components/messagedialog/useMessageDialog";

function useAjaxClient(): DefaultAjaxClient {
  const messageDialog = useMessageDialog();

  return new DefaultAjaxClient(_core, urlConverter, messageDialog);
}

const urlConverter = (url: string) => {
  if (url.startsWith("/")) {
    return url;
  }
  return `${envParam.ajaxBaseUrl}/${url}`;
};

export { useAjaxClient };
