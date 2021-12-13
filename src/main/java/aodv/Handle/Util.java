package aodv.Handle;

import aodv.packet.Packet;
import aodv.packet.RreqPacket;
import aodv.row.ReversRoutingRow;
import aodv.row.RoutingTabelle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.List;

public class Util {

    /**
     * How long must a message wait before can send it
     *
     * @param timer = e.g 60 = 1minute
     * @return datetime.now()+random*timer
     */
    public static LocalTime waitingPeriod(int timer) {
        LocalTime time = LocalTime.now();
        return time.plusSeconds((int) (Math.random() * timer));
    }

    /**
     * @return <a href="https://datatracker.ietf.org/doc/html/rfc3561">AODV RFC look at page 11</a>
     */
    public static int twosComplementSubtract(int a, int b) {
        return a + (~b + 1);
    }


    /**
     * returns the type of the message as an integer
     *
     * @param msg that is recieved
     * @return 0 for RREQ, 1 for RREP, 2 for RERR, 3 for MSG, 4 for ACK
     */
    public static int getPacketType(byte[] msg) {
        if (msg[0] % 2 == 1) {
            return ((msg[0] - 1) / (int) Math.pow(2, 4));
        }
        return ((msg[0]) / (int) Math.pow(2, 4));
    }

    /**
     * Add Routing information from RREQ to {@link RoutingTabelle}
     *
     * @param rreqPacket = {@link RreqPacket}
     */
    public static void callbackOfRREQ(RreqPacket rreqPacket) {
        List<ReversRoutingRow> row = RoutingTabelle.getInstance().getReversRoutingRows();

        int index = 0;
        boolean nodeNotFound = true;
        for (ReversRoutingRow routingRow : row) {
            if (rreqPacket.getOriginAddress() == routingRow.getSource()
                    && rreqPacket.getRequestId() == routingRow.getRreqId()
            ) {
                if (rreqPacket.getHopCount() < routingRow.getMetrix()) {
                    RoutingTabelle.getInstance().add(
                            new ReversRoutingRow(
                                    rreqPacket.getDestAddress(),
                                    rreqPacket.getHopCount(),
                                    rreqPacket.getOriginAddress(),
                                    rreqPacket.getRequestId(),
                                    rreqPacket.getPrevHopAddress()),
                            index);
                }
            } else {
                nodeNotFound = false;
            }
            index++;
        }

        if (nodeNotFound) {
            RoutingTabelle.getInstance().add(
                    new ReversRoutingRow(
                            rreqPacket.getDestAddress(),
                            rreqPacket.getHopCount(),
                            rreqPacket.getOriginAddress(),
                            rreqPacket.getRequestId(),
                            rreqPacket.getPrevHopAddress())
            );
        }
    }

    private <T extends Packet> byte[] objectToByteArray(T packet) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        byte[] arr = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(packet);
            out.flush();
            arr = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return arr;
    }
}
