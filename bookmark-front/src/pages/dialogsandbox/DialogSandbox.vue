<template>
  <q-page class="q-px-md" padding>
    <div class="row shadow-3 q-pa-md">
      <div class="col">
        <q-btn color="primary" label="alert" @click="showAlert" />
      </div>
    </div>
    <div class="row shadow-3 q-pa-md">
      <div class="col">
        <q-btn color="primary" label="alert" @click="showAlert1" />
      </div>
      <div class="col">
        <q-btn color="primary" label="alert" @click="showAlert2" />
      </div>
      <div class="col">
        <q-btn color="primary" label="alert" @click="showAlert3" />
      </div>
    </div>

    <q-dialog v-model="visibleAlert" persistent>
      <q-card style="min-width: 350px">
        <q-card-section class="text-primary q-pa-sm">
          <div class="text-h6">Alert</div>
        </q-card-section>
        <q-card-section> あれこれ </q-card-section>

        <q-card-actions align="right">
          <q-btn class="text-primary" label="Cancel" v-close-popup />
          <q-btn color="primary" label="Add address" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useMessageDialog } from "@/common/components/messagedialog/useMessageDialog";

export default defineComponent({
  name: "DialogSandbox",
  setup() {
    const md = useMessageDialog();

    const visibleAlert = ref(false);

    const showAlert = () => {
      visibleAlert.value = true;
    };
    const showAlert1 = async () => {
      await md.info(`aiueo
kakikukeko`);
    };
    const showAlert2 = async () => {
      await md.confirm(`aiueo
kakikukeko`);
    };
    const showAlert3 = async () => {
      await md.error(`aiueo
kakikukeko`);
    };
    return {
      visibleAlert,
      showAlert,
      showAlert1,
      showAlert2,
      showAlert3,
    };
  },
});
</script>
