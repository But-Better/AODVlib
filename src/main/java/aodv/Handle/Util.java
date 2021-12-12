package aodv.Handle;

import java.time.LocalTime;

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


}
