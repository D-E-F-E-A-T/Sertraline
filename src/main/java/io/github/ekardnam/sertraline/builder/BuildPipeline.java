package io.github.ekardnam.sertraline.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuildPipeline implements Iterable<LayerDescriptor> {
	
	protected List<LayerDescriptor> pipeline = new ArrayList<LayerDescriptor>();
	
	public void add(LayerDescriptor ld) {
		pipeline.add(ld);
	}
	
	public void add(LayerDescriptor ld, int n) {
		for (int i = 0; i < n; i++) {
			add(ld);
		}
	}

	public Iterator<LayerDescriptor> iterator() {
		return new Iterator<LayerDescriptor>() {
			
			private int index = 0;
			
			public boolean hasNext() {
				return index < pipeline.size();
			}
			
			public LayerDescriptor next() {
				return pipeline.get(index++);
			}
			
		};
	}

}
