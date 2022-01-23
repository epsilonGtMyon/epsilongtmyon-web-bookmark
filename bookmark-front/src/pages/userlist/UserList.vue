<template>
  <q-page class="q-px-md" padding>
    <store-aware-error-message />
    <div class="shadow-3 q-pa-md">
      <div class="row q-mb-md">
        <div class="col-4">
          <q-input v-model="userAliasId" type="text" label="ID"> </q-input>
        </div>
        <div class="col-4">
          <q-input v-model="userName" type="text" label="名前"> </q-input>
        </div>

        <div class="col-4">
          <q-input v-model="mailAddress" type="text" label="メールアドレス">
          </q-input>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <q-btn color="primary" label="検索" @click="search" />
        </div>
        <div class="col">
          <q-btn
            class="text-primary"
            outline
            label="新規登録"
            @click="addUser"
          />
        </div>
      </div>
    </div>

    <div class="row shadow-3 q-pa-md">
      <div class="col">
        <q-list>
          <template v-for="(u, index) of users" :key="u.userAliasId">
            <q-item clickable v-ripple @click="selectedUser(u, index)">
              <q-item-section>
                <q-item-label             >
                  {{ u.userAliasId }} ({{ u.mailAddress }})
                  <template v-if="u.adminFlag">★</template>
                </q-item-label>
                <q-item-label caption>{{ u.userName }}</q-item-label>
              </q-item-section>
            </q-item>
          </template>
        </q-list>
      </div>
    </div>

    <user-editor ref="userEditorModalRef" />
  </q-page>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useStoreErrorMessage } from "@/common/components/errormessage/useStoreErrorMessage";
import { useModal } from "@/common/components/modal/useModal";
import { ModalComponentType } from "@/common/components/modal/ModalComponentType";

import { useUserListService } from "./UserListService";
import UserEditor from "@/dialogs/usereditor/UserEditor.vue";
import { UserEditorParam } from "@/dialogs/usereditor/spec/UserEditorParam";
import { UserEditorResult } from "@/dialogs/usereditor/spec/UserEditorResult";

type UserRecord = {
  userAliasId: string;
  userName: string;
  mailAddress: string;
  adminFlag: boolean;
};

export default defineComponent({
  name: "UserList",
  components: {
    UserEditor,
  },
  setup() {
    const { setMessages, clearMessage } = useStoreErrorMessage();

    const userAliasId = ref("");
    const userName = ref("");
    const mailAddress = ref("");

    //--------------------------------
    // 検索系

    const service = useUserListService();
    const users = ref<UserRecord[]>([]);

    const search = async () => {
      const request = {
        userAliasId: userAliasId.value,
        userName: userName.value,
        mailAddress: mailAddress.value,
      };

      users.value = [];
      clearMessage();
      const resp = await service.search(request);
      if (resp.isFailed()) {
        setMessages(resp.getAppMessages());
        return;
      }
      users.value = resp.data.userList;
    };

    //--------------------------------
    // モーダル系

    const userEditorModalRef = ref<ModalComponentType | null>(null);
    const userEditorModal = useModal<UserEditorResult, UserEditorParam>(
      userEditorModalRef
    );

    const selectedUser = async (selectedUser: UserRecord, index: number) => {
      const param: UserEditorParam = {
        userAliasId: selectedUser.userAliasId,
      };
      const r = await userEditorModal.show(param);
      if (r.isNotCompleted()) {
        return;
      }
      const result = r.completeResult();
      if (result.type === "register") {
        users.value[index] = {
          userAliasId: result.userAliasId,
          userName: result.userName,
          mailAddress: result.mailAddress,
          adminFlag: result.adminFlag,
        };
      } else {
        users.value.splice(index, 1);
      }
    };

    const addUser = async () => {
      const param: UserEditorParam = {};
      const r = await userEditorModal.show(param);
      if (r.isNotCompleted()) {
        return;
      }
      //新規追加時は検索しなおす
      search();
    };
    return {
      userAliasId,
      userName,
      mailAddress,

      users,
      search,
      selectedUser,
      addUser,

      //-------------------
      userEditorModalRef,
    };
  },
});
</script>
