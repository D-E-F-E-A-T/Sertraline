package io.github.ekardnam.sertraline.objects;

public class BiasSynapsis extends Synapsis {

    public BiasSynapsis(Neuron to, double theta) {
        super(null, to, theta);
    }

    @Override
    public double getPotential() {
        return -w;
    }

}
