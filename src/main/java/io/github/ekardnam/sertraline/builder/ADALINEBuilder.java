package io.github.ekardnam.sertraline.builder;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;

public class ADALINEBuilder extends DefaultBuilder {

    public void build(@NotNull NeuralNetwork network, int inputs, int outputs) {
        BuildPipeline bp = new BuildPipeline();
        bp.add(new LayerDescriptor(LayerBuilder.LINEAR_BUILDER, LayerLinker.FEED_FORWARD_LINKER, inputs));
        bp.add(new LayerDescriptor(LayerBuilder.LINEAR_BUILDER, LayerLinker.OUTPUT_LAYER, outputs));
        build(network, bp);
    }

    @Override
    public void build(@NotNull NeuralNetwork network, @NotNull BuildPipeline pipeline) {
        if (pipeline.size() != 2) throw new IllegalArgumentException("Pipeline must have two descriptors for perceptron");
        super.build(network, pipeline);
    }
}
