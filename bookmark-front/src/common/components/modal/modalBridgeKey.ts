import { InjectionKey } from "vue";
import { ModalBridge } from "./ModalBridge";

const modalBridgeKey: InjectionKey<ModalBridge> = Symbol();
export { modalBridgeKey };
