import { ModalResult } from "./ModalResult";

class ModalBridge {
  //Modalコンポーネントを操作するための関数
  private showFunc?: (
    pResolve: (param: unknown) => void,
    param: unknown
  ) => void;
  private closeFunc?: (param: unknown) => void;
  private cancelFunc?: () => void;

  constructor() {
    //noop
  }

  public install(
    showFunc: (pResolve: (param: unknown) => void, param: unknown) => void,
    closeFunc: (param: unknown) => void,
    cancelFunc: () => void
  ): void {
    this.showFunc = showFunc;
    this.closeFunc = closeFunc;
    this.cancelFunc = cancelFunc;
  }

  /**
   * 表示
   * @param param パラメータ
   * @returns 戻り値
   */
  public show<P, R>(param?: P): Promise<ModalResult<R>> {
    //参照の退避
    const showFunc = this.showFunc;

    if (showFunc == undefined) {
      throw new Error("showFunc is null or undefined");
    }
    return new Promise<ModalResult<R>>((resolve) => {
      //ちょっと強引に回避
      const x = resolve as (value: unknown) => void;
      showFunc(x, param);
    });
  }

  /**
   * クローズ
   * @param param 戻り値
   */
  public close(param?: unknown): void {
    if (this.closeFunc == null) {
      throw new Error("closeFunc is null or undefined");
    }
    this.closeFunc(param);
  }

  /**
   * キャンセル
   */
  public cancel(): void {
    if (this.cancelFunc == null) {
      throw new Error("cancelFunc is null or undefined");
    }
    this.cancelFunc();
  }
}
export { ModalBridge };
