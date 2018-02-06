package io.github.ekardnam.sertraline.test.data;

import io.github.ekardnam.sertraline.data.Vector;
import org.junit.Assert;
import org.junit.Test;

public class VectorTest {

    @Test
    public void squareTest() {
        System.out.println(new Vector(2, new double[] {1, 0}).square());
    }

}