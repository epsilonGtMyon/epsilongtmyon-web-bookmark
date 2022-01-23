import { inject, onBeforeUnmount } from "vue";
import { MessageDialog } from "./MessageDialog";
import { messageDialogKey } from "./messageDialogKey";

/**
 * メッセージダイアログを使用します。
 * @param abortWhenUnmount 使用するコンポーネントが廃棄されるときにアボートを実行するか
 * @returns メッセージダイアログ
 */
function useMessageDialog(abortWhenUnmount = true): MessageDialog {
  const messageDialog = inject(messageDialogKey);
  if (messageDialog == undefined) {
    throw new Error(`messageDialog is not found`);
  }

  if (abortWhenUnmount) {
    //ダイアログが表示されているコンポーネントが廃棄されるときにダイアログが表示されていれば、ダイアログも閉じる
    onBeforeUnmount(() => {
      messageDialog.abortIfVisible();
    });
  }

  return messageDialog;
}

export { useMessageDialog };
