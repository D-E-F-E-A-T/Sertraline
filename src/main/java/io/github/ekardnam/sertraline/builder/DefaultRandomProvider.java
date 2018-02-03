package io.github.ekardnam.sertraline.builder;

import java.util.Random;

public class DefaultRandomProvider implements RandomProvider {

	private Random random = new Random();

	@Override
	public double random() {
		return random.nextDouble();
	}

}
