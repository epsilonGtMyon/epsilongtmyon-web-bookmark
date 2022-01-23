import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";

//TODO できたらstoreの内容は間接的に参照したい
import { useAuthStore } from "@/common/store/authStore";

axios.defaults.timeout = 10 * 60 * 1000;
axios.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";

class AjaxClientCore {
  private readonly _axios: AxiosInstance;

  constructor() {
    this._axios = axios.create({});
  }

  /**
   * Getリクエストを行います。
   * @param url
   * @param data
   * @param config
   */
  public get<R>(
    url: string,
    data: any = {},
    config: AxiosRequestConfig = {}
  ): Promise<AxiosResponse<R>> {
    const param = data || {};
    config.params = {
      ...config.params,
      ...param,
    };

    return this._axios.get<R>(url, config);
  }

  public post<R>(
    url: string,
    data: any = {},
    config: AxiosRequestConfig = {}
  ): Promise<AxiosResponse<R>> {
    const authStore = useAuthStore();
    config.headers = config.headers || {};
    config.headers[
      authStore.csrfHeaderName
    ] = authStore.csrfToken;

    return this._axios.post<R>(url, data, config);
  }

  //TODO 一時的
  public postAsForm<R>(
    url: string,
    data: any = {},
    config: AxiosRequestConfig = {}
  ): Promise<AxiosResponse<R>> {
    const authStore = useAuthStore();
    
    config.headers = config.headers || {};

    config.headers[
      authStore.csrfHeaderName
    ] = authStore.csrfToken;

    const form = new FormData();
    for (const x of Object.keys(data)) {
      form.append(x, data[x]);
    }

    return this._axios.post<R>(url, form, config);
  }
}

const instance = new AjaxClientCore();

export { AjaxClientCore, instance };
