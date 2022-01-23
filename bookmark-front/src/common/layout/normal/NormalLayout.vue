<template>
  <q-layout view="hHh Lpr lFf">
    <q-header elevated class="glossy">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          aria-label="Menu"
          icon="menu"
        />

        <q-toolbar-title> ブックマーク管理 </q-toolbar-title>

        <div>Quasar v{{ $q.version }}</div>

        <q-btn flat label="ログアウト" @click="confirmLogout" />
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered class="bg-grey-2">
      <q-list>
        <!--
        <q-item-label header>Essential Links</q-item-label>
        -->
        <q-item to="sharedbookmarklist" clickable v-ripple>
          <q-item-section>
            <q-item-label>共有ブックマーク</q-item-label>
            <q-item-label caption>共有ブックマークの一覧です。</q-item-label>
          </q-item-section>
        </q-item>

        <q-item to="bookmarklist" clickable v-ripple>
          <q-item-section>
            <q-item-label>ブックマーク</q-item-label>
            <q-item-label caption>ブックマークです。</q-item-label>
          </q-item-section>
        </q-item>

        <q-item to="userlist" clickable v-ripple>
          <q-item-section>
            <q-item-label>ユーザー一覧</q-item-label>
            <q-item-label caption>ユーザーの一覧です。</q-item-label>
          </q-item-section>
        </q-item>

        <template v-if="false">
          <q-item-label header>Sandbox</q-item-label>

          <q-item to="validationsandbox" clickable v-ripple>
            <q-item-section>
              <q-item-label>validationsandbox</q-item-label>
              <q-item-label caption>validationsandbox</q-item-label>
            </q-item-section>
          </q-item>
          <q-item to="dialogsandbox" clickable v-ripple>
            <q-item-section>
              <q-item-label>dialogsandbox</q-item-label>
              <q-item-label caption>dialogsandbox</q-item-label>
            </q-item-section>
          </q-item>
          <q-item to="modalsandbox" clickable v-ripple>
            <q-item-section>
              <q-item-label>modalsandbox</q-item-label>
              <q-item-label caption>modalsandbox</q-item-label>
            </q-item-section>
          </q-item>
        </template>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view></router-view>
    </q-page-container>
    <message-dialog-view />
  </q-layout>
</template>

<script lang="ts">
import { ref, defineComponent } from "vue";
import { useMessageDialog } from "@/common/components/messagedialog/useMessageDialog";
import { useAjaxClient } from "@/common/ajax/useAjaxClient";
import { envParam } from "@/common/envParam";

export default defineComponent({
  name: "NormalLayout",
  setup() {
    const md = useMessageDialog();
    const ajax = useAjaxClient();
    const leftDrawerOpen = ref(false);

    const confirmLogout = async () => {
      const r = await md.confirm("ログアウトしますか?");
      if (!r) {
        return;
      }
      await ajax.post("logout")
      location.href = envParam.baseUrl
    };
    return {
      leftDrawerOpen,
      confirmLogout,
    };
  },
});
</script>
