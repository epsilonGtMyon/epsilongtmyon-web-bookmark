/**
 * ダイアログのタイトルやボタンの種類
 */
type MessageDialogType = "primary" | "danger";

/**
 * ダイアログのボタンのパターン
 */
type MessageDialogActionButton = {
  /** ボタンのテキスト */
  text: string;
  /** ボタンの種類 */
  buttonType: MessageDialogType;
  /** アウトライン形式か？ */
  outline?: boolean;
};

/** ダイアログのボタンをクリックしたときのハンドラ */
type MessageDialogActionButtonHandler = (index: number) => void;
/** 中止のハンドラ(rejectする) */
type MessageDialogAbortHandler = <T extends Error>(error: T) => void;

/** ダイアログを表示するためのパラメータ */
type MessageDialogShowParam = {
  title: string;
  titleType: MessageDialogType;
  message: string;
  actionButtons: MessageDialogActionButton[];
  actionButtonHandler: MessageDialogActionButtonHandler;
  abortHandler: MessageDialogAbortHandler;
};

/** ダイアログの実体を操作するためのハンドル */
type MessageDialogHandle = {
  show(param: MessageDialogShowParam): void;
  abort<T extends Error>(error: T): void;
};

export {
  MessageDialogType,
  MessageDialogActionButton,
  MessageDialogActionButtonHandler,
  MessageDialogAbortHandler,
  MessageDialogShowParam,
  MessageDialogHandle,
};
