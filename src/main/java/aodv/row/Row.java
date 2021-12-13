package aodv.row;

import java.util.Objects;

public abstract class Row {

    private int destination;
    private int metrix;

    Row(int destination, int metrix) {
        this.destination = destination;
        this.metrix = metrix;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getMetrix() {
        return metrix;
    }

    public void setMetrix(int metrix) {
        this.metrix = metrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return destination == row.destination && metrix == row.metrix;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, metrix);
    }

    @Override
    public String toString() {
        return "Tabelle{" +
                "destination=" + destination +
                ", metrix=" + metrix +
                '}';
    }
}
