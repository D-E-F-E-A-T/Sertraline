package io.github.ekardnam.sertraline.data;

import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.Neuron;
import io.github.ekardnam.sertraline.objects.Synapsis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class SynapsisAllocator<T> {

    protected Map<Synapsis, AtomicReference<T>> allocated;

    public SynapsisAllocator(NeuralNetwork network) {
        allocated = new HashMap<>();
        for (Layer l : network.getLayers()) for (Neuron n : l) for (Synapsis s : n.getOutLinks()) {
            allocated.put(s, new AtomicReference<>(null));
        }
    }

    public AtomicReference<T> get(Synapsis s) {
        if (!allocated.containsKey(s)) throw new IllegalArgumentException("Illegal");
        return allocated.get(s);
    }

}
