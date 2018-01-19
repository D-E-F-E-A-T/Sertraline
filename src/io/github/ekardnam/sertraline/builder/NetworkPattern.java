package io.github.ekardnam.sertraline.builder;

public class NetworkPattern {
	
	protected BuildPipeline pipeline;
	
	public NetworkPattern(BuildPipeline pipeline) {
		this.pipeline = pipeline;
	}
	
	public BuildPipeline getPipeline() {
		return pipeline;
	}

}
