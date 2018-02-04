package io.github.ekardnam.sertraline.builder;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuildPipeline implements Iterable<LayerDescriptor> {
	
	protected List<LayerDescriptor> pipeline = new ArrayList();
	
	public void add(@NotNull LayerDescriptor ld) {
		pipeline.add(ld);
	}
	
	public void add(@NotNull LayerDescriptor ld, int n) {
		for (int i = 0; i < n; i++) {
			add(ld);
		}
	}

	@Override
	public Iterator<LayerDescriptor> iterator() {
		return pipeline.iterator();
	}

	public int size() { return pipeline.size(); }

}
