import { createApp } from "vue";
import { createPinia } from "pinia";
import { Quasar } from "quasar";
import router from "./common/router";

import { instance } from "./common/ajax/core/AjaxClientCore";
import App from "./App.vue";
import quasarUserOptions from "./quasar-user-options";

//Components
import ErrorMessage from "./common/components/errormessage/ErrorMessage.vue";
import StoreAwareErrorMessage from "./common/components/errormessage/StoreAwareErrorMessage.vue";
import MessageDialogView from "./common/components/messagedialog/MessageDialogView.vue";
import Modal from "./common/components/modal/Modal.vue";

import { MessageDialog } from "./common/components/messagedialog/MessageDialog";
import { messageDialogKey } from "./common/components/messagedialog/messageDialogKey";
import { useAuthStore } from "./common/store/authStore";

(async () => {
  const resp = await instance.get<any>("/api/init/init");

  const app = createApp(App);
  app.use(Quasar, quasarUserOptions);
  app.use(createPinia());
  app.use(router);

  const authStore = useAuthStore();
  authStore.setAuthState({
    authenticated: resp.data.authenticated,
    csrfToken: resp.data.xToken,
    csrfHeaderName: resp.data.xHeaderName,
    authorities: resp.data.authorities,
  });

  app.component(ErrorMessage.name, ErrorMessage);
  app.component(StoreAwareErrorMessage.name, StoreAwareErrorMessage);
  app.component(MessageDialogView.name, MessageDialogView);
  app.component(Modal.name, Modal);

  // message dialog
  const messageDialog = new MessageDialog();
  app.provide(messageDialogKey, messageDialog);

  app.mount("#app");
})();
