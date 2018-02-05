package io.github.ekardnam.sertraline.objects;

/**
 * A class that represent a {@link Synapsis}
 */
public class Synapsis {

	/**
	 * The {@link Neuron} the synapsis starts from
	 */
	protected Neuron from;

	/**
	 * The {@link Neuron} the synapsis links to
	 */
	protected Neuron to;

	/**
	 * Synapsis's weight
	 */
	public double w;

	/**
	 * Constructs a synapsis
	 * @param from {@link #from}
	 * @param to {@link #to}
	 * @param w {@link #w}
	 */
	public Synapsis(Neuron from, Neuron to, double w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}

	/**
	 * Constructs a synapsis
	 * @param from {@link #from}
	 * @param to {@link #to}
	 */
	public Synapsis(Neuron from, Neuron to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * Get's the potential at this synapsis
	 * @return the potential
	 */
	public double getPotential() {
		return from.out * w;
	}

}
