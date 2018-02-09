package io.github.ekardnam.sertraline.test.builder;

import io.github.ekardnam.sertraline.builder.DefaultLayerBuilder;
import io.github.ekardnam.sertraline.objects.Layer;
import org.junit.Assert;
import org.junit.Test;

public class DefaultLayerBuilderTest {

    @Test
    public void testBuiltLayer() {
        int neurons = 5;
        DefaultLayerBuilder dlb = new DefaultLayerBuilder();
        Layer l = dlb.build(neurons);
        Assert.assertTrue("Not how many neurons as expected", l.howManyNeurons() == neurons);
    }

}