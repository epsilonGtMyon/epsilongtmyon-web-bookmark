<template>
  <q-page class="q-px-md" padding>
    <q-btn color="primary" label="open" @click="open" />

    <SandboxModal ref="sandboxModal" />
  </q-page>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { ModalComponentType } from "@/common/components/modal/ModalComponentType";
import { useModal } from "@/common/components/modal/useModal";
import SandboxModal from "./SandboxModal.vue";

export default defineComponent({
  name: "ModalSandbox",
  components: {
    SandboxModal,
  },
  setup() {
    const sandboxModal = ref<ModalComponentType | null>(null);
    const modal = useModal<unknown, { aa: number }>(sandboxModal);

    const open = async () => {
      const r = await modal.show({ aa: 2 });
      if (r.completeResult) {
        console.log(r.completeResult());
      } else if (r.canceled) {
        console.log("canceled");
      }
    };
    return {
      sandboxModal,
      open,
    };
  },
});
</script>
