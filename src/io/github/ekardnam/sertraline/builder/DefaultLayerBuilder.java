package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.Neuron;
import io.github.ekardnam.sertraline.transfer.TransferFunction;

public class DefaultLayerBuilder implements LayerBuilder {

	protected TransferFunction tf;
	
	protected RandomProvider rp;
	
	public DefaultLayerBuilder() {
		this.tf = TransferFunction.DEFAULT_FUNCTION;
		this.rp = RandomProvider.DEFAULT_PROVIDER;
	}
	
	public DefaultLayerBuilder(TransferFunction tf) {
		this.tf = tf;
		rp = RandomProvider.DEFAULT_PROVIDER;
	}
	
	public DefaultLayerBuilder(TransferFunction tf, RandomProvider rp) {
		this.tf = tf;
		this.rp = rp;
	}
	
	
	public Layer build(int neurons) {
		Layer layer = new Layer();
		for (int i = 0; i < neurons; i++) {
			layer.addNeuron(new Neuron(tf, rp.random()));
		}
		return layer;
	}

}
