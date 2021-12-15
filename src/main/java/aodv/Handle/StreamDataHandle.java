package aodv.Handle;

import aodv.packet.*;

import java.util.Arrays;

public class StreamDataHandle {

    /**
     * takes the byte input array, that is recieved and returns an Packet Objekt of the given package type
     *
     * @param msg as a byte array
     * @return msg as a packet object when the packet could be identified, null when the type could not be identified
     */
    public static Packet readDataTraffic(byte[] msg) {
        switch (Util.getPacketType(msg)) {
            case 0:
                RreqPacket rreqPacket = returnRREQ(msg);
                Util.callbackOfRREQ(rreqPacket);
                return rreqPacket;
            case 1:
                return returnRREP(msg);
            case 2:
                return returnRERR(msg);
            case 3:
                return returnMSG(msg);
            case 4:
                return returnAck(msg);
            default:
                return null;
        }
    }

    /**
     * takes a byte array that is a rreq message and turns it into an rreq object
     *
     * @param rreq message as a byte array
     * @return rreq object
     */
    public static RreqPacket returnRREQ(byte[] rreq) {
        RreqPacket returnRreq = new RreqPacket();

        int type = 0;
        int flags = returnFlags(rreq[0]);

        returnRreq.setMessageType(type);
        returnRreq.setFlags(flags);
        returnRreq.setHopAddress(rreq[1]);
        returnRreq.setPrevHopAddress(rreq[2]);
        returnRreq.setRequestId(rreq[3]);
        returnRreq.setDestAddress(rreq[4]);
        returnRreq.setDestSequence(rreq[5]);
        returnRreq.setHopCount(rreq[6]);
        returnRreq.setOriginAddress(rreq[7]);
        returnRreq.setOriginSequence(rreq[8]);

        return returnRreq;
    }

    /**
     * takes a byte array that is a rrep message and turns it into an rrep object
     *
     * @param rrep message as a byte array
     * @return rrep object
     */
    public static RrepPacket returnRREP(byte[] rrep) {
        RrepPacket rrepPacket = new RrepPacket();

        int type = 1;
        int flags = returnFlags(rrep[0]);

        rrepPacket.setMessageType(type);
        rrepPacket.setFlags(flags);
        rrepPacket.setHopAddress(rrep[1]);
        rrepPacket.setPrevHopAddress(rrep[2]);
        rrepPacket.setRequestId(rrep[3]);
        rrepPacket.setDestAddress(rrep[4]);
        rrepPacket.setDestSequence(rrep[5]);
        rrepPacket.setHopCount(rrep[6]);
        rrepPacket.setOriginAddress(rrep[7]);
        rrepPacket.setTtl(rrep[8]);

        return rrepPacket;
    }

    /**
     * takes a byte array that is a rerr message and turns it into an rerr object
     *
     * @param rerr message as a byte array
     * @return rerr object
     */
    public static RerrPacket returnRERR(byte[] rerr) {
        RerrPacket rerrPacket = new RerrPacket();

        int type = 2;
        int flags = returnFlags(rerr[0]);

        rerrPacket.setMessageType(type);
        rerrPacket.setFlags(flags);
        rerrPacket.setHopAddress(rerr[1]);
        rerrPacket.setPrevHopAddress(rerr[2]);
        rerrPacket.setPathCount(rerr[3]);

        int rerrHight = rerr.length / 3;
        int amountOfAdressesAndDestSequences = rerrHight - 1;
        int[] destAdress = new int[amountOfAdressesAndDestSequences];
        int[] destSequence = new int[amountOfAdressesAndDestSequences];

        destAdress[0] = rerr[4];
        destSequence[0] = rerr[5];

        for (int i = 1; i < amountOfAdressesAndDestSequences; ++i) {
            destAdress[i] = rerr[3 + 3 * i];
            destSequence[i] = rerr[4 + 3 * i];
        }

        rerrPacket.setDestAdresses(destAdress);
        rerrPacket.setDestSequences(destSequence);

        return rerrPacket;
    }

    /**
     * takes a byte array that is a msg message and turns it into an msg object
     *
     * @param msg message as a byte array
     * @return msg object
     */
    public static MsgPacket returnMSG(byte[] msg) {
        MsgPacket msgPacket = new MsgPacket();

        int type = 3;
        int flags = returnFlags(msg[0]);

        msgPacket.setMessageType(type);
        msgPacket.setFlags(flags);
        msgPacket.setHopAddress(msg[1]);
        msgPacket.setPrevHopAddress(msg[2]);
        msgPacket.setDestAddress(msg[3]);
        msgPacket.setOriginSequence(msg[4]);
        msgPacket.setHopCount(msg[5]);

        byte[] msgMessagePart = Arrays.copyOfRange(msg, 6, msg.length);
        String decodedMSG = new String(msgMessagePart);
        msgPacket.setText(decodedMSG);

        return msgPacket;
    }

    /**
     * takes a byte array that is a ack message and turns it into an ack object
     *
     * @param ack message as a byte array
     * @return ack object
     */
    public static AckPacket returnAck(byte[] ack) {
        AckPacket ackPacket = new AckPacket();

        int type = 4;
        int flags = returnFlags(ack[0]);

        ackPacket.setMessageType(type);
        ackPacket.setFlags(flags);
        ackPacket.setHopAddress(ack[1]);
        ackPacket.setPrevHopAddress(ack[2]);

        return ackPacket;
    }

    /**
     * takes the first byte and the value of the last 4 bit
     *
     * @param typeAndFlags the first byte of the byte array input
     * @return last 4 bit value as byte
     */
    private static int returnFlags(byte typeAndFlags) {
        byte frontOfByte = (byte) ((byte) (typeAndFlags / Math.pow(2,4))*Math.pow(2,4));
        byte backOfByte = (byte) (typeAndFlags - frontOfByte);

        return backOfByte;
    }

}