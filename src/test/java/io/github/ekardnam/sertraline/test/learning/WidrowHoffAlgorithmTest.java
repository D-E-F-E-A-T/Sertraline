package io.github.ekardnam.sertraline.test.learning;


import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.builder.LayerBuilder;
import io.github.ekardnam.sertraline.builder.NetworkBuilder;
import io.github.ekardnam.sertraline.data.ArrayProvider;
import io.github.ekardnam.sertraline.data.Vector;
import io.github.ekardnam.sertraline.learning.WidrowHoffAlgorithm;
import org.junit.Assert;
import org.junit.Test;

public class WidrowHoffAlgorithmTest {

    @Test
    public void testWidrowHoff() {
        int inputs = 2, outputs = 2;
        NeuralNetwork network = new NeuralNetwork();
        NetworkBuilder.PERCEPTRON_BUILDER.build(network, 2, 2, LayerBuilder.STEP_BUILDER);
        WidrowHoffAlgorithm algo = new WidrowHoffAlgorithm();
        //AND data, first output is i1 AND i2 and second is i1 NAND i1
        algo.train(network, new ArrayProvider(new double[] {1, 1, 1, 0, 0, 1, 0, 0}, new double[] {1, 0, 0, 1, 0, 1, 0, 1}, inputs, outputs));
        Assert.assertTrue(network.output(new Vector(2, new double[] {1, 1})).equals(new Vector(2, new double[] {1, 0})));
        Assert.assertTrue(network.output(new Vector(2, new double[] {1, 0})).equals(new Vector(2, new double[] {0, 1})));
        Assert.assertTrue(network.output(new Vector(2, new double[] {0, 1})).equals(new Vector(2, new double[] {0, 1})));
        Assert.assertTrue(network.output(new Vector(2, new double[] {0, 1})).equals(new Vector(2, new double[] {0, 1})));
    }

}