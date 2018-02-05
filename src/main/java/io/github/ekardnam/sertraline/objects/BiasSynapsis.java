package io.github.ekardnam.sertraline.objects;

/**
 * A class that represent a bias synapsis
 * @author Luca Bertozzi - ekardnam lucabertozzi.pub@gmail.com
 */
public class BiasSynapsis extends Synapsis {

    /**
     * Contructs a bias synapsis
     * @param to the {@link Neuron} the synapsis links to
     * @param theta the threshold value of the bias synapsis
     */
    public BiasSynapsis(Neuron to, double theta) {
        super(null, to, theta);
    }

    /**
     * Overrides {@link Synapsis#getPotential()}
     * @return See {@link Synapsis#getPotential()}
     */
    @Override
    public double getPotential() {
        return -w;
    }

}
