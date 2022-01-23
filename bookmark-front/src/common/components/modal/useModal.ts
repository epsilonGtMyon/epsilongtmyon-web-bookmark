import { Ref } from "vue";
import { ModalComponentType } from "./ModalComponentType";
import { ModalResult } from "./ModalResult";

type UseModalResult<P, R> = {
  show: (param?: P) => Promise<ModalResult<R>>;
};

function useModal<R = never, P = never>(
  modalComponent: Ref<ModalComponentType | null>
): UseModalResult<P, R> {

  const show = (param?: P) => {
    if (modalComponent == undefined || modalComponent.value == null) {
      throw new Error();
    }
    return modalComponent.value.modalBridge.show<P, R>(param);
  };
  return {
    show,
  };
}

export { useModal };
