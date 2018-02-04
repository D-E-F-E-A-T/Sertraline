package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.NeuralNetwork;

public class PerceptronBuilder extends DefaultBuilder {

    public void build(NeuralNetwork network, int inputs, int outputs, LayerBuilder lb) {
        BuildPipeline bp = new BuildPipeline();
        bp.add(new LayerDescriptor(lb, LayerLinker.FEED_FORWARD_LINKER, inputs));
        bp.add(new LayerDescriptor(lb, LayerLinker.OUTPUT_LAYER, outputs));
        build(network, bp);
    }

    public void build(NeuralNetwork network, int inputs, int outputs) {
        build(network, inputs, outputs, LayerBuilder.DEFAULT_BUILDER);
    }

}
