package io.github.ekardnam.sertraline.objects;


public class IdentifiedObject {

    private static long currentId = 0;

    //TODO("Handle overflow")
    protected long id;

    public IdentifiedObject() {
        id = currentId;
        currentId++;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IdentifiedObject)) return false;
        IdentifiedObject identified = (IdentifiedObject) other;
        return id == identified.id;
    }

    @Override
    public int hashCode() { return Long.hashCode(id); }

}
