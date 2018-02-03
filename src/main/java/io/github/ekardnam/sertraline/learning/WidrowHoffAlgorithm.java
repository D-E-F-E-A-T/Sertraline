package io.github.ekardnam.sertraline.learning;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.activation.ActivationFunction;
import io.github.ekardnam.sertraline.data.DataProvider;

import java.util.concurrent.atomic.AtomicBoolean;

class WidrowHoffAlgorithm extends LearningAlgorithm {

	private boolean isADALIN(@NotNull NeuralNetwork network) {
		if (network.getLayers().size() != 2) return false;
		AtomicBoolean valid = new AtomicBoolean(true);
		for (int i = 0; i < 2; i++) {
			network.getLayers().get(i).forEach(neuron -> {
				if (neuron.getActivationFunction() != ActivationFunction.LINEAR_FUNCTION) {
					valid.set(false);
				}
			});
		}
		return valid.get();
	}

	@Override
	public boolean init(@NotNull NeuralNetwork network) {
		return isADALIN(network); //works on ADALIN
	}

	@Override
	public void algorithm(@NotNull NeuralNetwork network, DataProvider provider) {

	}
}
