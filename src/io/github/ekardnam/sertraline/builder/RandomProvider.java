package io.github.ekardnam.sertraline.builder;

public interface RandomProvider {
	
	public static final RandomProvider DEFAULT_PROVIDER = new DefaultRandomProvider();
	
	public double random();

}
