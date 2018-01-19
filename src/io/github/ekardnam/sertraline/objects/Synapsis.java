package io.github.ekardnam.sertraline.objects;

public class Synapsis extends NetworkObject {
	
		//input neuron
		private Neuron in;
		
		//output neuron
		private Neuron out;
		
		//synapsis weight
		public double w;
		
		public Synapsis(Neuron in, Neuron out, double w) {
			this.in = in;
			this.out = out;
			this.w = w;
		}
		
		public Synapsis(Neuron in, Neuron out) {
			this.in = in;
			this.out = out;
		}
		
		public Neuron getIn() {
			return in;
		}
		
		public Neuron getOut() {
			return out;
		}
		
		public void setIn(Neuron in) {
			this.in = in;
		}
		
		public void setOut(Neuron out) {
			this.out = out;
		}

}
