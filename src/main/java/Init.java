import aodv.Handle.Util;

public class Init {

    public Init(int address) {
        Util.addToRoutingRowAsInit(address);
    }
}
