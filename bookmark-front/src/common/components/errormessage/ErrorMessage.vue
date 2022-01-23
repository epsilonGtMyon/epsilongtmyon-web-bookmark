<template>
  <q-banner class="text-white bg-red" v-if="visibleMessages">
    <template v-slot:avatar>
      <q-icon name="warning" color="white" />
    </template>

    <div class="q-my-none">
      <slot :errorMessages="errorMessages">
        <template v-for="mes in errorMessages" :key="mes.key">
          <div>{{ mes.message }}</div>
        </template>
      </slot>
    </div>
  </q-banner>
</template>

<script lang="ts">
import { computed, defineComponent, PropType } from "vue";
import { ErrorMessageContent } from "./ErrorMessageContent";

//TODO メッセージエリアの高さ制限方法
export default defineComponent({
  name: "ErrorMessage",

  props: {
    messages: {
      type: Array as PropType<ErrorMessageContent[]>,
      required: true,
    },
  },
  setup(props) {
    const errorMessages = computed(() => {
      const keyPrefix = `${Date.now()}`;

      return props.messages.map((mes, index) => {
        let m = "";
        if (typeof mes === "string") {
          m = mes;
        } else {
          m = mes.message;
        }
        return {
          key: `${keyPrefix}_${index}`,
          message: m,
        };
      });
    });

    const visibleMessages = computed(() => {
      return errorMessages.value.length > 0;
    });
    return {
      errorMessages,
      visibleMessages,
    };
  },
});
</script>
