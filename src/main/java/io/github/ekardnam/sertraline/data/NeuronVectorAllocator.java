package io.github.ekardnam.sertraline.data;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.Neuron;

import java.util.concurrent.atomic.AtomicReference;

public class NeuronVectorAllocator extends NeuronAllocator<MutableVector> {

    public NeuronVectorAllocator(@NotNull NeuralNetwork network) {
        super();
        for (Layer l : network.getLayers()) for (Neuron n : l) {
            allocated.put(n, new AtomicReference<>(new MutableVector(n.getInLinks().size())));
        }
    }

    public MutableVector getVector(Neuron n) {
        if (!allocated.containsKey(n)) throw new IllegalArgumentException("Illegal");
        return allocated.get(n).get();
    }

    public void zeroMemory() {
        for (AtomicReference<MutableVector> vecref : allocated.values()) vecref.get().zeroIt();
    }

}
