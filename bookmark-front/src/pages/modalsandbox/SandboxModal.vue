<template>
  <modal title="Sandbox01" @shown="shown">
    モーダルです。

    <input type="text" v-model="value01" />
    <q-btn color="primary" label="Ok" @click="ok" />
    <q-btn color="primary" label="Cancel" @click="cancel" />

    <q-btn color="primary" label="nest" @click="openNest" />
    <SandboxModal ref="sandboxModal" />
  </modal>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { ModalComponentType } from "@/common/components/modal/ModalComponentType";
import { useModal } from "@/common/components/modal/useModal";
import { useModalBridge } from "@/common/components/modal/useModalBridge";

export default defineComponent({
  name: "SandboxModal",
  setup() {
    const modalBridge = useModalBridge();
    const value01 = ref("");

    const shown = (param: unknown) => {
      console.log("shown", param);
    };
    const ok = () => {
      modalBridge.close({ value01: value01.value });
    };
    const cancel = () => {
      modalBridge.cancel();
    };

    //-------------------------
    const sandboxModal = ref<ModalComponentType | null>(null);
    const modal = useModal<unknown, { aa: number }>(sandboxModal);
    const openNest = async () => {
      const r = await modal.show({ aa: 2 });
      if (r.completeResult) {
        console.log(r.completeResult());
      } else if (r.canceled) {
        console.log("canceled");
      }
    };

    return {
      modalBridge,
      //--------
      value01,
      shown,
      ok,
      cancel,

      sandboxModal,
      openNest,
    };
  },
});
</script>
