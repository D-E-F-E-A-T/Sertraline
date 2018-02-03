package io.github.ekardnam.sertraline.objects;

public class Synapsis {
	
		protected Neuron from;
		
		protected Neuron to;
		
		public double w;
		
		public Synapsis(Neuron from, Neuron to, double w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		public Synapsis(Neuron from, Neuron to) {
			this.from = from;
			this.to = to;
		}
		
		public Neuron getFrom() {
			return from;
		}
		
		public Neuron getTo() {
			return to;
		}
		
		public void setFrom(Neuron from) {
			this.from = from;
		}
		
		public void setTo(Neuron to) {
			this.to = to;
		}

}
