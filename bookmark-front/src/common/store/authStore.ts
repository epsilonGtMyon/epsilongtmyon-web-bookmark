import { defineStore } from "pinia";

type AuthState = {
  authenticated: boolean;
  csrfToken: string;
  csrfHeaderName: string;
  authorities: string[];
};

const useAuthStore = defineStore("auth", {
  state: () =>
    ({
      authenticated: false,
      csrfToken: "",
      csrfHeaderName: "",
      authorities: [],
    } as AuthState),
  getters: {
    isAdmin(state) {
      return state.authorities.includes("ROLE_ADMIN");
    },
  },
  actions: {
    setAuthState(payload: AuthState) {
      this.$patch(payload);
    },
  },
});

export { useAuthStore };
