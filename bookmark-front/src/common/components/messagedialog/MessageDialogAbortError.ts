class MessageDialogAbortError implements Error {
  name: string;
  message: string;
  stack?: string | undefined;

  constructor(message?: string) {
    this.name = "MessageDialogAbortError";
    this.message = message || "";
  }
}

export { MessageDialogAbortError };
