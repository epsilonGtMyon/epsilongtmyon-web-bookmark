<template>
  <q-page class="q-px-md" padding>
    <store-aware-error-message />
    <div class="q-py-md">
      <div class="row">
        <div class="col">
          <q-btn color="primary" label="新規登録" @click="addBookmark" />
        </div>
      </div>
    </div>

    <div class="shadow-3 q-pa-md">
      <q-list>
        <template
          v-for="(bookmark, index) of bookmarks"
          :key="bookmark.bookmarkId"
        >
          <q-expansion-item expand-separator expand-icon-toggle>
            <template #header>
              <q-item-section>
                <a
                  :href="bookmark.url"
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <template v-if="bookmark.sharedFlag">
                    【共有】
                  </template>
                  {{ bookmark.title }}</a
                >
              </q-item-section>

              <q-item-section side>
                <q-btn
                  color="primary"
                  label="編集"
                  @click="editBookmark(bookmark.bookmarkId, index)"
                />
              </q-item-section>
            </template>
            <template #default>
              <q-card>
                <q-card-section>
                  {{ bookmark.description }}
                </q-card-section>
              </q-card>
            </template>
          </q-expansion-item>
        </template>
      </q-list>
    </div>

    <bookmark-editor ref="bookmarkEditorModalRef" />
  </q-page>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from "vue";
import { useModal } from "@/common/components/modal/useModal";
import { useStoreErrorMessage } from "@/common/components/errormessage/useStoreErrorMessage";

import { ModalComponentType } from "@/common/components/modal/ModalComponentType";
import BookmarkEditor from "@/dialogs/bookmarkeditor/BookmarkEditor.vue";
import { BookmarkEditorParam } from "@/dialogs/bookmarkeditor/spec/BookmarkEditorParam";
import { BookmarkEditorResult } from "@/dialogs/bookmarkeditor/spec/BookmarkEditorResult";
import { useBookmarkListService } from "./BookmarkListService";

type BookmarkListRecord = {
  bookmarkId: number;
  //listOrder: number;
  title: string;
  url: string;
  description: string | null;
  sharedFlag: boolean;
};

export default defineComponent({
  name: "BookmarkList",
  components: {
    BookmarkEditor,
  },
  setup() {
    const { setMessages, clearMessage } = useStoreErrorMessage();

    const service = useBookmarkListService();
    const bookmarks = ref<BookmarkListRecord[]>([]);

    //--------------------------------
    // モーダル系

    const bookmarkEditorModalRef = ref<ModalComponentType | null>(null);
    const bookmarkEditorModal = useModal<
      BookmarkEditorResult,
      BookmarkEditorParam
    >(bookmarkEditorModalRef);

    const addBookmark = async () => {
      const param: BookmarkEditorParam = {};
      const r = await bookmarkEditorModal.show(param);
      if (r.isNotCompleted()) {
        return;
      }

      const result = r.completeResult();
      if (result.type === "add") {
        bookmarks.value.push({
          bookmarkId: result.bookmarkId,
          title: result.title,
          url: result.url,
          description: result.description,
          sharedFlag: result.sharedFlag,
        });
      }
    };

    const editBookmark = async (bookmarkId: number, index: number) => {
      const param: BookmarkEditorParam = { bookmarkId };
      const r = await bookmarkEditorModal.show(param);
      if (r.isNotCompleted()) {
        return;
      }

      const result = r.completeResult();
      if (result.type === "modify") {
        bookmarks.value[index] = {
          ...bookmarks.value[index],

          title: result.title,
          url: result.url,
          description: result.description,
          sharedFlag: result.sharedFlag,
        };
      } else if (result.type === "remove") {
        bookmarks.value.splice(index, 1);
      }
    };

    onMounted(async () => {
      await search();
    });

    const search = async () => {
      bookmarks.value = [];
      clearMessage();
      const resp = await service.search();
      if (resp.isFailed()) {
        setMessages(resp.getAppMessages());
        return;
      }
      bookmarks.value = resp.data.bookmarks;
    };

    return {
      bookmarks,

      addBookmark,
      editBookmark,
      bookmarkEditorModalRef,
    };
  },
});
</script>
