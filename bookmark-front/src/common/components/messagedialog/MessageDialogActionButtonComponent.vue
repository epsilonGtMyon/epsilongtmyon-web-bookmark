<template>
  <q-btn v-bind="$attrs" :color="color" :class="classes" />
</template>

<script lang="ts">
import { computed, defineComponent, PropType } from "vue";
import { MessageDialogType } from "./MessageDialogTypes";

export default defineComponent({
  name: "MessageDialogActionButtonComponent",
  inheritAttrs: false,
  props: {
    buttonType: {
      type: String as PropType<MessageDialogType>,
      required: true,
    },
    buttonOutline: {
      type: Boolean,
    },
  },
  setup(props) {
    const color = computed<string | undefined>(() => {
      if (props.buttonOutline) {
        return undefined;
      }
      switch (props.buttonType) {
        case "primary":
          return "primary";
        case "danger":
          return "negative";
        default:
          return undefined;
      }
    });

    const classes = computed<string[] | undefined>(() => {
      if (!props.buttonOutline) {
        return undefined;
      }
      switch (props.buttonType) {
        case "primary":
          return ["text-primary"];
        case "danger":
          return ["text-negative"];
        default:
          return undefined;
      }
    });

    return { color, classes };
  },
});
</script>
