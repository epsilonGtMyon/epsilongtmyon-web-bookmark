<template>
  <router-view />
</template>

<script type="ts">
import { ref, defineComponent } from "vue";

export default defineComponent({
  name: "LayoutDefault",
  setup() {
    return {
      leftDrawerOpen: ref(false),
    };
  },
});
</script>
