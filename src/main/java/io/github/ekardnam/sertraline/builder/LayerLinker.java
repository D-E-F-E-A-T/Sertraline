package io.github.ekardnam.sertraline.builder;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.objects.Layer;

public interface LayerLinker {

	LayerLinker FEED_FORWARD_LINKER = new FeedForwardLinker();
	LayerLinker OUTPUT_LAYER = null;
	
	public void link(@NotNull Layer before, @NotNull Layer after);

}
