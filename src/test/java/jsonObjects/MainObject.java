package jsonObjects;

import java.util.List;

public class MainObject {
	public String type;
	public metadata metadata;
	public List<features> features;
	public String getType() {
		return type;
	}
	public metadata getMetadata() {
		return metadata;
	}
	public List<features> getFeatures() {
		return features;
	}
}
