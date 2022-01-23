/**
 * モーダルの結果
 */
class ModalResult<T> {
  constructor(
    public readonly destroyed: boolean,
    public readonly canceled: boolean,
    public readonly result: T | undefined
  ) {}

  public isCompleted(): boolean {
    if (this.destroyed || this.canceled) {
      return false;
    }
    return true;
  }

  public isNotCompleted(): boolean {
    return !this.isCompleted();
  }

  public completeResult(): T {
    if (!this.isCompleted()) {
      throw new Error("not complete");
    }
    if (this.result === undefined) {
      throw new Error("result is undefined");
    }
    return this.result;
  }

  //------------------
  //factory methods

  /**
   * 画面遷移などによりモーダルが廃棄されたことを表す結果を返す
   * @returns
   */
  public static destroyedResult(): ModalResult<null> {
    return new ModalResult<null>(true, false, null);
  }

  /**
   * キャンセルされた結果を返す
   * @returns
   */
  public static canceledResult(): ModalResult<null> {
    return new ModalResult<null>(false, true, null);
  }

  /**
   * 正常終了の結果を返す
   * @param result 結果
   * @returns
   */
  public static completeResult<T>(result: T): ModalResult<T> {
    return new ModalResult<T>(false, false, result);
  }
}

export { ModalResult };
