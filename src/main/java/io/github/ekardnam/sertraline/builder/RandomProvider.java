package io.github.ekardnam.sertraline.builder;

public interface RandomProvider {
	
	RandomProvider DEFAULT_PROVIDER = new DefaultRandomProvider();
	
	public double random();

}
