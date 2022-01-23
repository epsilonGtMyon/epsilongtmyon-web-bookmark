class NonNull {

  /**
   * null, undefinedではないことを表明
   * @param value 
   * @returns 
   */
  public static assert<T>(value: T | null | undefined): T {
    if (value === null || value === undefined) {
      throw new Error(`value is null or undefined`);
    }
    return value;
  }
}

export { NonNull };
