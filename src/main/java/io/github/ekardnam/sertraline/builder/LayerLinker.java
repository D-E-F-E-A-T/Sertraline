package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.objects.Layer;

public interface LayerLinker {
	
	public void link(Layer before, Layer after);

}
