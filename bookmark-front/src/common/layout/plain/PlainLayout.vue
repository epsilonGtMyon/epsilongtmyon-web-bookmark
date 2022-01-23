<template>
  <q-layout view="hHh Lpr lFf">
    <q-page-container>
      <router-view></router-view>
    </q-page-container>
    <message-dialog-view />
  </q-layout>
</template>

<script >
import { defineComponent } from "vue";

export default defineComponent({
  name: "PlainLayout",
  setup() {
    return {};
  },
});
</script>