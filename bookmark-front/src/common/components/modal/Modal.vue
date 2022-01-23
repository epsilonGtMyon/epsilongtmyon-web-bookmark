<template>
  <q-dialog v-model="visible" persistent>
    <q-layout view="hHh Lpr lFf" class="bg-white" container>
      <q-header elevated class="glossy">
        <q-toolbar>
          <q-toolbar-title> {{ title }} </q-toolbar-title>
        </q-toolbar>
      </q-header>

      <q-page-container>
        <q-page padding>
          <slot></slot>
        </q-page>
      </q-page-container>

      <q-footer class="bg-white">
        <slot name="footer"></slot>
      </q-footer>
    </q-layout>
  </q-dialog>
</template>

<script lang="ts">
import { defineComponent, ref, onBeforeUnmount, inject } from "vue";
import { modalBridgeKey } from "./modalBridgeKey";
import { ModalResult } from "./ModalResult";

export default defineComponent({
  name: "Modal",
  props: {
    title: {
      type: String,
    },
  },
  setup(props, context) {
    // ダイアログ表示
    const visible = ref(false);

    let resolve: (result: unknown) => void = () => {
      //
    };

    onBeforeUnmount(() => {
      resolve(ModalResult.destroyedResult());
      visible.value = false;
      resolve = () => {
        //
      };
      context.emit("closed");
    });

    const show = (pResolve: (result: unknown) => void, param: unknown) => {
      visible.value = true;
      resolve = pResolve;
      context.emit("shown", param);
    };
    const close = (result: unknown) => {
      resolve(ModalResult.completeResult(result));
      visible.value = false;
      resolve = () => {
        //
      };
      context.emit("closed");
    };
    const cancel = () => {
      resolve(ModalResult.canceledResult());
      visible.value = false;
      resolve = () => {
        //
      };

      context.emit("closed");
    };

    const modalBridge = inject(modalBridgeKey);
    modalBridge?.install(show, close, cancel);

    return {
      visible,
    };
  },
});
</script>
