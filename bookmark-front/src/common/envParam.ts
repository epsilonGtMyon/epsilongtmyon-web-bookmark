const baseUrl = (process.env.BASE_URL as string) || "";
const ajaxBaseUrl = `${baseUrl}api`;
const nodeEnv = process.env.NODE_ENV as string;
const isProd = nodeEnv === "production";
const isDev = !isProd;

const envParam = {
  baseUrl,
  ajaxBaseUrl,
  isProd,
  isDev,
};

if (isDev) {
  console.log({ envParam });
}

export { envParam };
