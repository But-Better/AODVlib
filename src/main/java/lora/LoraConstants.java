package lora;

public class LoraConstants {

    public final static CharSequence[] items = new CharSequence[]{
            LoraConstants.AT,
            LoraConstants.AT_ADDR_DEFAULT,
            LoraConstants.AT_CFG_DEFAULT,
            LoraConstants.AT_DEST_DEFAULT,
            LoraConstants.AT_SAVE,
            LoraConstants.AT_SEND
    };

    public final static String AT = "AT\r\n";
    public final static String AT_RX = "AT+RX\r\n";

    /**
     * Need a addr like 0001
     */
    public final static String AT_ADDR = "AT+ADDR=";

    /**
     * Need a dest addr like FFFF as broadcast
     */
    public final static String AT_DEST = "AT+DEST=";

    /**
     * Need CFG
     */
    public final static String AT_CFG = "AT+CFG=";

    /**
     * Save the current cfg
     */
    public final static String AT_SAVE = "AT+SAVE\r\n";

    /**
     * Need length of byte in int
     */
    public final static String AT_SEND = "AT+SEND=";

    /**
     * Ending of all executable line
     */
    final static String END_OF_LINE = "\r\n";

    //DEFAULT AT COMMANDS
    public static String defaultCfg = "433000000,5,6,12,4,1,0,0,0,0,3000,8,8\r\n";
    public static String defaultAddr = "0001\r\n";
    public static String defaultDest = "FFFF\r\n";

    public final static String AT_CFG_DEFAULT = AT_CFG + defaultCfg;
    public final static String AT_ADDR_DEFAULT = AT_ADDR + defaultAddr;
    public final static String AT_DEST_DEFAULT = AT_DEST + defaultDest;

}
