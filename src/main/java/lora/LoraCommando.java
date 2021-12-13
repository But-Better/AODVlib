package lora;

import java.util.Base64;

public class LoraCommando {

    private final static String END_OF_LINE = "\r\n";

    public static String[] sendMsg(Base64 msg) {
        return new String[]{LoraConstants.AT_SEND + msg.toString().length() + END_OF_LINE, msg + END_OF_LINE};
    }

    public static String[] sendMsg(String msg) {
        return new String[]{LoraConstants.AT_SEND + msg.length() + END_OF_LINE, msg + END_OF_LINE};
    }

    public static String[] changeAddress(String address) throws IllegalArgumentException {
        if (address.length() != 4) {
            throw new IllegalArgumentException("Address size isn't 4");
        }

        return new String[]{LoraConstants.AT_DEST + address + END_OF_LINE, LoraConstants.AT_SAVE};
    }

    public static String[] changeAddressDest(String destAddress) throws IllegalArgumentException {
        if (destAddress.length() != 4) {
            throw new IllegalArgumentException("Address size isn't 4");
        }

        return new String[]{LoraConstants.AT_DEST + destAddress + END_OF_LINE, LoraConstants.AT_SAVE};
    }

    public static String[] changeCFG(String cfg) {
        return new String[]{LoraConstants.AT_CFG + cfg + END_OF_LINE, LoraConstants.AT_SAVE};
    }

    public static String[] changeToDefault() {
        return new String[]{LoraConstants.AT_ADDR_DEFAULT, LoraConstants.AT_DEST_DEFAULT, LoraConstants.AT_CFG_DEFAULT, LoraConstants.AT_SAVE};
    }
}
