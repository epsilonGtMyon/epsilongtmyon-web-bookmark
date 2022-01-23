import { InjectionKey } from "vue";
import { MessageDialog } from "./MessageDialog";

/**
 * メッセージダイアログをinjectするためのキー
 */
const messageDialogKey: InjectionKey<MessageDialog> = Symbol();
export { messageDialogKey };
