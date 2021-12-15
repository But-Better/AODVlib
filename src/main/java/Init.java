import aodv.Handle.Util;
import lora.LoraCommando;

import java.util.ArrayList;

public class Init {

    private static int address;
    private static int addressDest;

    public Init(int address, int addressDest) {
        Init.address = address;
        Init.addressDest = addressDest;
        Util.addToRoutingRowAsInit(Init.address);
    }

    public ArrayList<byte[]> getAddressDestAsLoraCommand() {
        String[] result = LoraCommando.changeAddress(String.valueOf(address));
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(result[0].getBytes());
        list.add(result[1].getBytes());
        return list;
    }

    public static int getAddressDest() {
        return addressDest;
    }

    public static int getAddress() {
        return address;
    }
}
