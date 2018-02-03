package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.activation.ActivationFunction;
import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.Neuron;

public class DefaultLayerBuilder implements LayerBuilder {

	protected ActivationFunction af;
	
	protected RandomProvider rp;
	
	public DefaultLayerBuilder() {
		this.af = ActivationFunction.DEFAULT_FUNCTION;
		this.rp = RandomProvider.DEFAULT_PROVIDER;
	}
	
	public DefaultLayerBuilder(ActivationFunction af) {
		this.af = af;
		rp = RandomProvider.DEFAULT_PROVIDER;
	}
	
	public DefaultLayerBuilder(ActivationFunction af, RandomProvider rp) {
		this.af = af;
		this.rp = rp;
	}
	
	
	public Layer build(int neurons) {
		Layer layer = new Layer();
		for (int i = 0; i < neurons; i++) {
			layer.addNeuron(new Neuron(af, rp.random()));
		}
		return layer;
	}

}
