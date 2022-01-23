<template>
  <q-page class="q-px-md" padding>
    <store-aware-error-message />

    <div class="shadow-3 q-pa-md">
      <q-list>
        <template
          v-for="bookmark of sharedBookmarks"
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
                  {{ bookmark.title }}</a
                >
              </q-item-section>
            </template>
            <template #default>
              <q-card>
                <q-card-section>
                  <template v-if="bookmark.userAliasId != null">
                    <div>
                      {{ bookmark.userName }}:{{ bookmark.userAliasId }}
                    </div>
                  </template>
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
import { useStoreErrorMessage } from "@/common/components/errormessage/useStoreErrorMessage";

import BookmarkEditor from "@/dialogs/bookmarkeditor/BookmarkEditor.vue";
import { useSharedBookmarkListService } from "./SharedBookmarkListService";

type SharedBookmarkListRecord = {
  bookmarkId: number;
  title: string;
  url: string;
  description: string | null;
  userName: string | null;
  userAliasId: string | null;
};

export default defineComponent({
  name: "SharedBookmarkList",
  components: {
    BookmarkEditor,
  },
  setup() {
    const { setMessages, clearMessage } = useStoreErrorMessage();

    const service = useSharedBookmarkListService();
    const sharedBookmarks = ref<SharedBookmarkListRecord[]>([]);

    onMounted(async () => {
      await search();
    });

    const search = async () => {
      sharedBookmarks.value = [];
      clearMessage();
      const resp = await service.search();
      if (resp.isFailed()) {
        setMessages(resp.getAppMessages());
        return;
      }
      sharedBookmarks.value = resp.data.sharedBookmarks;
    };

    return {
      sharedBookmarks,
    };
  },
});
</script>
