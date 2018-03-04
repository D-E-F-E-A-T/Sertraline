package io.github.ekardnam.sertraline.objects;

import java.util.Objects;

public class IdentifiedObject {

    @Override
    public boolean equals(Object other) { return this == other; }

    @Override
    public int hashCode() { return Objects.hashCode(this); }

}
