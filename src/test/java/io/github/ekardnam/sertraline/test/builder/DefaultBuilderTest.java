package io.github.ekardnam.sertraline.test.builder;

import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.builder.*;
import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.Neuron;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DefaultBuilderTest {

    @Test
    public void testDefaultBuilder() {
        int neurons = 4;
        NeuralNetwork nn = new NeuralNetwork();
        BuildPipeline bp = new BuildPipeline();
        bp.add(new LayerDescriptor(LayerBuilder.DEFAULT_BUILDER, LayerLinker.FEED_FORWARD_LINKER, neurons), 3);
        NetworkBuilder.DEFAULT_BUILDER.build(nn, bp);
        List<Layer> layers = nn.getLayers();
        for (int i = 0; i < layers.size() - 1; i++) {
            Assert.assertTrue("Layers aren't linked correctly", FeedForwardLinker.areLinkedFeedForwardly(layers.get(i), layers.get(i + 1)));
            Assert.assertTrue("There aren't how many neurons as expected", layers.get(i).getHowManyNeurons() == neurons);
        }
        for (Layer l : layers) {
            for (Neuron n : l) {
                System.out.println(n.weights());
            }
        }
    }

}