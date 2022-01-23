import "./styles/quasar.scss";
import lang from "quasar/lang/ja.js";
import "@quasar/extras/material-icons/material-icons.css";

import { Loading } from "quasar";

// To be used on app.use(Quasar, { ... })
export default {
  config: {
    loading: {
      /* look at QuasarConfOptions from the API card */
    },
  },
  plugins: {
    Loading,
  },
  lang: lang,
};
