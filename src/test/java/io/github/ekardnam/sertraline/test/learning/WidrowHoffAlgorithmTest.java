package io.github.ekardnam.sertraline.test.learning;


import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.builder.LayerBuilder;
import io.github.ekardnam.sertraline.builder.NetworkBuilder;
import io.github.ekardnam.sertraline.data.AbstractVector;
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
        NetworkBuilder.PERCEPTRON_BUILDER.build(network, inputs, outputs, LayerBuilder.STEP_BUILDER);
        WidrowHoffAlgorithm algo = new WidrowHoffAlgorithm();
        //AND data, first output is i1 AND i2 and second is i1 NAND i2
        boolean trained = algo.train(network, new ArrayProvider(new double[] {1, 1, 1, 0, 0, 1, 0, 0}, new double[] {1, 0, 0, 1, 0, 1, 0, 1}, inputs, outputs));
        System.out.println("Trained: " + trained);
        AbstractVector oneone = network.output(new Vector(2, new double[] {1, 1}));
        AbstractVector onezero = network.output(new Vector(2, new double[] {1, 0}));
        AbstractVector zeroone = network.output(new Vector(2, new double[] {0, 1}));
        AbstractVector zerozero = network.output(new Vector(2, new double[] {0, 0}));
        System.out.println("1 AND 1 = " + oneone.get(0) + " --- 1 NAND 1 = " + oneone.get(1));
        System.out.println("1 AND 0 = " + onezero.get(0) + " --- 1 NAND 0 = " + onezero.get(1));
        System.out.println("0 AND 1 = " + zeroone.get(0) + " --- 0 NAND 1 = " + zeroone.get(1));
        System.out.println("0 AND 0 = " + zerozero.get(0) + " --- 0 NAND 0 = " + zerozero.get(1));
    }

}