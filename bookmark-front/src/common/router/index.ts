import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

//layout
import NormalLayout from "../layout/normal/NormalLayout.vue";
import PlainLayout from "../layout/plain/PlainLayout.vue";
import { envParam } from "../envParam";

//state
import { useAuthStore } from "../store/authStore";

// import Home from '../../pages/Home.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/login",
  },

  {
    path: "/login",
    component: PlainLayout,
    children: [
      {
        path: "",
        name: "Login",
        component: () =>
          import(/* webpackChunkName: "login" */ "@/pages/login/Login.vue"),
        meta: {
          secure: false,
        },
      },
    ],
  },
  {
    path: "/n",
    name: "NormalLayout",
    component: NormalLayout,
    children: [
      {
        path: "questionnairelist",
        name: "QuestionnaireList",
        component: () =>
          import(
            /* webpackChunkName: "questionnairelist" */ "@/pages/questionnairelist/QuestionnaireList.vue"
          ),
      },
      {
        path: "userlist",
        name: "UserList",
        component: () =>
          import(
            /* webpackChunkName: "userlist" */ "@/pages/userlist/UserList.vue"
          ),
        meta: {
          authorities: ["ROLE_ADMIN"],
        },
      },
      {
        path: "bookmarklist",
        name: "bookmarklist",
        component: () =>
          import(
            /* webpackChunkName: "bookmarklist" */ "@/pages/bookmarklist/BookmarkList.vue"
          ),
      },
      {
        path: "sharedbookmarklist",
        name: "sharedbookmarklist",
        component: () =>
          import(
            /* webpackChunkName: "sharedbookmarklist" */ "@/pages/sharedbookmarklist/SharedBookmarkList.vue"
          ),
      },

      //----------------------------------
      //TODO sandbox
      {
        path: "validationsandbox",
        name: "ValidationSandbox",
        component: () =>
          import(
            /* webpackChunkName: "validationsandbox" */ "@/pages/validationsandbox/ValidationSandbox.vue"
          ),
      },
      {
        path: "dialogsandbox",
        name: "DialogSandbox",
        component: () =>
          import(
            /* webpackChunkName: "dialogsandbox" */ "@/pages/dialogsandbox/DialogSandbox.vue"
          ),
      },
      {
        path: "modalsandbox",
        name: "ModalSandbox",
        component: () =>
          import(
            /* webpackChunkName: "modalsandbox" */ "@/pages/modalsandbox/ModalSandbox.vue"
          ),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(envParam.baseUrl),
  routes,
});

router.beforeEach((to) => {
  const authStore = useAuthStore()

  const authenticated = authStore.authenticated;
  const authorities = authStore.authorities || [];
  const securePage = to.meta?.secure !== false;
  const requireAuthorities = (to.meta?.authorities as string[]) || [];

  if (to.path === "/login" && authenticated) {
    // ログイン済みなのにログインページに来た場合は、共通のトップページ
    // スラッシュを指定された時も/loginにリダイレクトしてるのでここで対応できる。
    return "/n/sharedbookmarklist";
  }

  if (securePage) {
    // 認証が必要なページである。
    if (authStore.authenticated) {
      // 認証済みなのでok
    } else {
      return "/login";
    }

    if (requireAuthorities.length > 0) {
      // 権限が必要な場合

      const hasAuthority = requireAuthorities.some((rqAu) =>
        authorities.includes(rqAu)
      );
      if (hasAuthority) {
        // 権限があるのでOK
      } else {
        // 権限ないので遷移させない、本当は権限なしページを用意するべきだが
        return false;
      }
    }
  }
});

export default router;
