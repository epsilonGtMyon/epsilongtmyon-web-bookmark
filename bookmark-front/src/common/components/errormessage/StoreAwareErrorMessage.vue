<template>
  <error-message :messages="errorMessages">
    <slot></slot>
  </error-message>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStoreErrorMessage } from "./useStoreErrorMessage";

export default defineComponent({
  name: "StoreAwareErrorMessage",
  setup() {
    const { errorMessages } = useStoreErrorMessage();

    return {
      errorMessages
    };
  },
});
</script>
