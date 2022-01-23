<template>
  <q-dialog class="messageDialog" v-model="visible" persistent>
    <q-card style="min-width: 350px">
      <q-card-section class="q-pa-sm" :class="titleClass">
        <div class="text-h6">
          {{ title }}
        </div>
      </q-card-section>
      <q-card-section style="white-space: pre-wrap">
        {{ message }}
      </q-card-section>

      <q-card-actions align="right">
        <template v-for="(ab, index) of actionButtons" :key="index">
          <MessageDialogActionButtonComponent
            :buttonType="ab.buttonType"
            :buttonOutline="ab.outline"
            :label="ab.text"
            @click="clicked(index)"
          />
        </template>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script lang="ts">
import {
  computed,
  defineComponent,
  onBeforeUnmount,
  onMounted,
  ref,
} from "vue";
import {
  MessageDialogType,
  MessageDialogActionButton,
  MessageDialogActionButtonHandler,
  MessageDialogAbortHandler,
  MessageDialogShowParam,
} from "./MessageDialogTypes";
import MessageDialogActionButtonComponent from "./MessageDialogActionButtonComponent.vue";
import { MessageDialogAbortError } from "./MessageDialogAbortError";
import { useMessageDialog } from "./useMessageDialog";

export default defineComponent({
  name: "MessageDialogView",
  components: {
    MessageDialogActionButtonComponent,
  },
  setup() {
    const visible = ref(false);

    const title = ref("");
    const titleType = ref<MessageDialogType>("primary");
    const message = ref("");
    const titleClass = computed(() => {
      const classes: string[] = [];
      switch (titleType.value) {
        case "primary":
          classes.push("text-primary");
          break;
        case "danger":
          classes.push("text-negative");
          break;
      }

      return classes;
    });
    let actionButtonHandler: MessageDialogActionButtonHandler = () => {
      //noop
    };
    let abortHandler: MessageDialogAbortHandler = () => {
      //noop
    };

    const actionButtons = ref<MessageDialogActionButton[]>([]);

    /** 表示 */
    const show = (param: MessageDialogShowParam) => {
      visible.value = true;

      title.value = param.title;
      titleType.value = param.titleType;
      message.value = param.message;
      actionButtons.value = param.actionButtons;
      actionButtonHandler = param.actionButtonHandler;
      abortHandler = param.abortHandler;
    };

    /** ボタンクリック時 */
    const clicked = (buttonOrder: number) => {
      actionButtonHandler(buttonOrder);
      cleanup();
    };

    const cleanup = () => {
      visible.value = false;
      actionButtonHandler = () => {
        //noop;
      };
      abortHandler = () => {
        //noop;
      };
    };

    const messageDialog = useMessageDialog();

    onMounted(() => {
      //マウント時に このコンポーネントのshowとかを handleとして公開する。
      messageDialog.replaceHandle({
        show,
        abort(error) {
          abortHandler(error);
          cleanup();
        },
      });
    });

    onBeforeUnmount(() => {
      abortHandler(new MessageDialogAbortError());
    });

    return {
      visible,
      title,
      titleClass,
      message,
      actionButtons,

      show,
      clicked,
    };
  },
});
</script>
