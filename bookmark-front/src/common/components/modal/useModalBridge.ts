import { provide } from "vue";
import { ModalBridge } from "./ModalBridge";
import { modalBridgeKey } from "./modalBridgeKey";

function useModalBridge(): ModalBridge {
  const modalBridge = new ModalBridge();
  provide(modalBridgeKey, modalBridge);

  //TODO 本当はclose, cancel だけに絞りたい
  return modalBridge
}
export { useModalBridge };
