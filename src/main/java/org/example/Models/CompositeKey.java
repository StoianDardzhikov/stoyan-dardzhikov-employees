package org.example.Models;

public class CompositeKey {
    int id1;
    int id2;

    public CompositeKey(int id1, int id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CompositeKey other))
            return super.equals(obj);

        return (id1 == other.id1 && id2 == other.id2)
                || (id2 == other.id1 && id1 == other.id2);
    }

    @Override
    public int hashCode() {
        return id1 * id2 + id1 + id2;
    }

    @Override
    public String toString() {
        return "CompositeKey{" +
                "id1=" + id1 +
                ", id2=" + id2 +
                '}';
    }
}
