import { defineStore } from "pinia";
import { ErrorMessageContent } from "../components/errormessage/ErrorMessageContent";

type BasicState = {
  errorMessages: ErrorMessageContent[];
};

const useBasicStore = defineStore("basicStore", {
  state: () =>
    ({
      errorMessages: [],
    } as BasicState),
  actions: {
    setErrorMessages(payload: ErrorMessageContent[]) {
      this.$patch((state) => {
        state.errorMessages = payload;
      });
    },
    clearErrorMessages() {
      this.$patch((state) => {
        state.errorMessages = [];
      });
    },
  },
});

export { useBasicStore };
