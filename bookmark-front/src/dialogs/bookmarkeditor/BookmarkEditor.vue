<template>
  <modal title="ブックマーク登録" @shown="shown" @closed="cleanup">
    <template #default>
      <error-message :messages="errorMessages" />

      <div class="row">
        <div class="col-6">
          <q-input v-model="title" type="text" label="タイトル"> </q-input>
        </div>
        <div class="col-6">
          <q-toggle
            v-model="sharedFlag"
            label="共有"
          />
        </div>

        
      </div>
      <div class="row">
        <div class="col">
          <q-input v-model="url" type="text" label="URL"> </q-input>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <q-input v-model="description" type="textarea" label="説明">
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
import { ErrorMessageContent } from "@/common/components/errormessage/ErrorMessageContent";
import { NonNull } from "@/common/util/NonNull";

import { useBookmarkEditorService } from "./BookmarkEditorService";
import { BookmarkEditorParam } from "./spec/BookmarkEditorParam";
import {
  BookmarkEditorRegisterResult,
  BookmarkEditorRemoveResult,
} from "./spec/BookmarkEditorResult";
import { BooleanUtil } from "@/common/util/BooleanUtil";

export default defineComponent({
  name: "BookmarkEditor",
  setup() {
    const md = useMessageDialog();
    const modalBridge = useModalBridge();
    const errorMessages = ref<ErrorMessageContent[]>([]);

    const service = useBookmarkEditorService();
    //-------------------------------
    let bookmarkId: number | null = null;
    const isNew = ref(false);
    const title = ref("");
    const url = ref("");
    const description = ref("");
    const sharedFlag = ref(false);

    const shown = async (param: BookmarkEditorParam) => {
      if (param.bookmarkId == null) {
        // 新規
        isNew.value = true;
        return;
      }
      const response = await service.init(param.bookmarkId);
      if (response.isFailed()) {
        errorMessages.value = response.getAppMessages();
        return;
      }
      const d = response.data;
      bookmarkId = d.bookmarkId;
      isNew.value = false;
      title.value = d.title;
      url.value = d.url;
      description.value = d.description;
      sharedFlag.value = d.sharedFlag;
    };

    const cleanup = () => {
      errorMessages.value = [];
      bookmarkId = null;
      isNew.value = false;
      title.value = "";
      url.value = "";
      description.value = "";
      sharedFlag.value = false;
    };

    const register = async () => {
      errorMessages.value = [];
      const response = await service.register({
        bookmarkId,
        title: title.value,
        url: url.value,
        description: description.value,
        sharedFlag: BooleanUtil.toFlagText(sharedFlag.value),
      });
      if (response.isFailed()) {
        errorMessages.value = response.getAppMessages();
        return;
      }
      await md.info("登録しました。");

      const d = response.data;
      const r: BookmarkEditorRegisterResult = {
        type: isNew.value ? "add" : "modify",
        bookmarkId: d.bookmarkId,
        title: title.value,
        url: url.value,
        description: description.value,
        sharedFlag: sharedFlag.value
      };
      modalBridge.close(r);
    };
    const cancel = () => {
      modalBridge.cancel();
    };
    const remove = async () => {
      const c = await md.confirm("削除します。よろしいですか？");
      if (!c) {
        return;
      }

      errorMessages.value = [];
      const response = await service.remove(NonNull.assert(bookmarkId));
      if (response.isFailed()) {
        errorMessages.value = response.getAppMessages();
        return;
      }
      await md.info("削除しました。");

      const r: BookmarkEditorRemoveResult = {
        type: "remove",
      };
      modalBridge.close(r);
    };
    return {
      modalBridge,
      errorMessages,
      isNew,
      title,
      url,
      description,
      sharedFlag,

      shown,
      cleanup,

      register,
      cancel,
      remove,
    };
  },
});
</script>
