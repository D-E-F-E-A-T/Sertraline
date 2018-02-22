package io.github.ekardnam.sertraline.data;

import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.Neuron;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class NeuronAllocator<T> {

    protected Map<Neuron, AtomicReference<T>> allocated;

    public NeuronAllocator() {
        allocated = new HashMap<>();
    }

    public NeuronAllocator(NeuralNetwork network) {
        this();
        for (Layer l : network.getLayers()) for (Neuron n : l) {
            allocated.put(n, new AtomicReference<>(null));
        }
    }

    public AtomicReference<T> get(Neuron n) {
        return allocated.get(n);
    }

}
