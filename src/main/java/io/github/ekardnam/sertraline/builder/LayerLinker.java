package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.objects.Layer;

public interface LayerLinker {

	LayerLinker FEED_FORWARD_LINKER = new FeedForwardLinker();
	
	public void link(Layer before, Layer after);

}
