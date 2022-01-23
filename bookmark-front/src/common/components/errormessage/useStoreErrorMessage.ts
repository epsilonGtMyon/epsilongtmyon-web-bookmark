import { computed } from "vue";
import { useBasicStore } from "@/common/store/basicStore";
import { ErrorMessageContent } from "./ErrorMessageContent";

function useStoreErrorMessage() {
  const basicStore = useBasicStore();

  const errorMessages = computed(() => basicStore.errorMessages);

  const setMessages = (messages: ErrorMessageContent[]) => {
    basicStore.setErrorMessages(messages);
  };
  const clearMessage = () => {
    basicStore.clearErrorMessages();
  };

  return {
    errorMessages,
    setMessages,
    clearMessage,
  };
}

export { useStoreErrorMessage };
