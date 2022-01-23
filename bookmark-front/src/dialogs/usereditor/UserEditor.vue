<template>
  <modal title="ユーザー登録" @shown="shown" @closed="cleanup">
    <template #default>
      <errorMessage :messages="errorMessages"></errorMessage>
      <div class="row items-center">
        <div class="col-8">
          <q-input v-model="userAliasId" type="text" label="ID"> </q-input>
        </div>
        <div class="col-4">
          <q-toggle v-model="adminFlag" label="管理者" />
        </div>
      </div>
      <div class="row">
        <div class="col">
          <q-input v-model="userName" type="text" label="名前"> </q-input>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <q-input v-model="mailAddress" type="text" label="メールアドレス">
          </q-input>
        </div>
      </div>
      <div class="row items-center">
        <div class="col-4">
          <q-checkbox v-model="editablePassword" label="パスワード変更" />
        </div>
        <div class="col-8">
          <q-input
            v-model="password"
            :type="passwordVisible ? 'input' : 'password'"
            :disable="!editablePassword"
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
      </div>
    </template>

    <template #footer>
      <div class="q-pa-md row justify-end q-gutter-sm">
        <q-btn label="登録" color="primary" @click="register"></q-btn>
        <q-btn
          label="キャンセル"
          class="text-primary"
          outline
          @click="cancel"
        ></q-btn>
        <template v-if="!isNew">
          <q-btn
            label="削除"
            class="text-negative"
            @click="remove"
            outline
          ></q-btn>
        </template>
      </div>
    </template>
  </modal>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useMessageDialog } from "@/common/components/messagedialog/useMessageDialog";
import { useModalBridge } from "@/common/components/modal/useModalBridge";
import { UserEditorParam } from "./spec/UserEditorParam";
import { useUserEditorService } from "./UserEditorService";
import {
  UserEditorRegisterResult,
  UserEditorRemoveResult,
} from "./spec/UserEditorResult";
import { ErrorMessageContent } from "@/common/components/errormessage/ErrorMessageContent";
import { NonNull } from "@/common/util/NonNull";

export default defineComponent({
  name: "UserEditor",
  setup() {
    const md = useMessageDialog();
    const modalBridge = useModalBridge();
    const errorMessages = ref<ErrorMessageContent[]>([]);
    //-------------------------------

    let userId: number | null = null;
    const isNew = ref(false);
    const userAliasId = ref("");
    const userName = ref("");
    const mailAddress = ref("");
    const adminFlag = ref(false);
    const editablePassword = ref(false);
    const password = ref("");
    const passwordVisible = ref(false);

    const service = useUserEditorService();
    const shown = async (param: UserEditorParam) => {
      if (param.userAliasId == null) {
        //新規
        isNew.value = true;
        editablePassword.value = true;
        return;
      }
      const response = await service.init(param.userAliasId);
      if (response.isFailed()) {
        errorMessages.value = response.getAppMessages();
        return;
      }
      const u = response.data;
      userId = u.userId;
      isNew.value = false;
      userAliasId.value = u.userAliasId;
      userName.value = u.userName;
      mailAddress.value = u.mailAddress;
      adminFlag.value = u.adminFlag;
      password.value = "";
    };

    const cleanup = () => {
      // 値のクリアなど
      errorMessages.value = [];
      userId = null;
      isNew.value = false;
      userAliasId.value = "";
      userName.value = "";
      mailAddress.value = "";
      adminFlag.value = false;
      editablePassword.value = false;
      password.value = "";
      passwordVisible.value = false;
    };

    /**
     * 登録
     */
    const register = async () => {
      errorMessages.value = [];
      const response = await service.register({
        userId,
        userAliasId: userAliasId.value,
        userName: userName.value,
        mailAddress: mailAddress.value,
        adminFlag: adminFlag.value,
        password: editablePassword.value ? password.value : "",
      });
      if (response.isFailed()) {
        errorMessages.value = response.getAppMessages();
        return;
      }
      await md.info("登録しました。");

      const r: UserEditorRegisterResult = {
        type: "register",
        userAliasId: userAliasId.value,
        userName: userName.value,
        mailAddress: mailAddress.value,
        adminFlag: adminFlag.value,
      };

      modalBridge.close(r);
    };

    const cancel = () => {
      modalBridge.cancel();
    };

    /**
     * 削除
     */
    const remove = async () => {
      const c = await md.confirm("削除します。よろしいですか？");
      if (!c) {
        return;
      }

      errorMessages.value = [];
      const response = await service.remove({
        userId: String(NonNull.assert(userId)),
      });
      if (response.isFailed()) {
        errorMessages.value = response.getAppMessages();
        return;
      }
      await md.info("削除しました。");

      const r: UserEditorRemoveResult = {
        type: "remove",
      };
      modalBridge.close(r);
    };

    return {
      modalBridge,
      errorMessages,
      isNew,

      userAliasId,
      userName,
      mailAddress,
      adminFlag,
      editablePassword,
      password,
      passwordVisible,

      shown,
      cleanup,

      register,
      cancel,
      remove,
    };
  },
});
</script>
