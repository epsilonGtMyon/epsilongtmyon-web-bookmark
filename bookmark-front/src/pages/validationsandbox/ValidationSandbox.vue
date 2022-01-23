<template>
  <q-page class="q-px-md" padding>
    <store-aware-error-message />
    <div class="row shadow-3 q-pa-md">
      <div class="col">
        <q-btn color="primary" label="get" @click="get" />
      </div>
      <div class="col">
        <q-btn color="primary" label="post" @click="post" />
      </div>
      <div class="col">
        <q-btn color="primary" label="appmessage" @click="appmessage" />
      </div>
    </div>
  </q-page>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import { useAjaxClient } from "@/common/ajax/useAjaxClient";
import { useStoreErrorMessage } from "@/common/components/errormessage/useStoreErrorMessage";
import { ErrorMessageContent } from "@/common/components/errormessage/ErrorMessageContent";

export default defineComponent({
  name: "ValidationSandbox",
  setup() {
    const ajax = useAjaxClient();
    const { setMessages, clearMessage } = useStoreErrorMessage();

    const get = async () => {
      clearMessage();
      const resp = await ajax.get("/api/validationsandbox/get");
      if (resp.isFailed()) {
        const errors: ErrorMessageContent[] = resp.getAppMessages();

        for (let i = 0; i < 100; i++) {
          errors.push(`${i}`);
        }
        setMessages(errors);
        return;
      }
    };
    const post = async () => {
      clearMessage();
      const resp = await ajax.post("/api/validationsandbox/post");
      if (resp.isFailed()) {
        setMessages(resp.getAppMessages());
        return;
      }
    };
    const appmessage = async () => {
      clearMessage();
      const resp = await ajax.get("/api/validationsandbox/appmessage");
      if (resp.isFailed()) {
        setMessages(resp.getAppMessages());
        return;
      }
    };
    return {
      get,
      post,
      appmessage,
    };
  },
});
</script>
