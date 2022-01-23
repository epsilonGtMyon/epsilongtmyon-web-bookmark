
/**
 * エラーメッセージに許容する型
 * 文字列、もしくは message:stringを含むもの
 */
 export type ErrorMessageContent = string | { message: string; [K: string]: unknown };