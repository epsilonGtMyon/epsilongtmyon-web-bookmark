<template>
  <q-page class="login q-px-md row justify-center items-center" padding>
    <div class="login-body col-7 column shadow-3 q-pa-md">
      <store-aware-error-message />
      <div class="login-title col-3 self-center">😀😀😀😀</div>
      <div class="col-3">
        <q-input v-model="userId" label="ユーザーID" />
      </div>

      <div class="col-3">
        <q-input
          v-model="password"
          :type="passwordInputType"
          label="パスワード"
        >
          <template v-slot:append>
            <q-icon
              :name="passwordVisible ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="passwordVisible = !passwordVisible"
            />
          </template>
        </q-input>
      </div>

      <div class="col">
        <q-btn color="primary" label="login" @click="login" />
      </div>
    </div>
  </q-page>
</template>

<script lang="ts">
import { computed, ref, defineComponent } from "vue";
import { useQuasar } from "quasar";

import { useStoreErrorMessage } from "@/common/components/errormessage/useStoreErrorMessage";
import { useAjaxClient } from "@/common/ajax/useAjaxClient";

export default defineComponent({
  name: "Login",
  setup() {
    const $q = useQuasar();
    const { setMessages, clearMessage } = useStoreErrorMessage();
    const ajax = useAjaxClient();

    const userId = ref("");
    const password = ref("");
    const passwordVisible = ref(false);
    const passwordInputType = computed(() =>
      passwordVisible.value ? "input" : "password"
    );

    const login = async () => {
      clearMessage();
      const resp = await ajax.post<any>("/api/login", {
        username: userId.value,
        password: password.value,
      });

      if (resp.isFailed()) {
        return;
      }

      if (resp.data.authenticationResult) {
        $q.loading.show();
        location.reload();
      } else {
        setMessages(["ログインに失敗しました。"]);
      }
    };
    return { userId, password, passwordVisible, passwordInputType, login };
  },
});
</script>

<style lang="scss" scoped>
.login-body {
  height: 400px;
}
.login-title {
  font-size: 3rem;
}
</style>
