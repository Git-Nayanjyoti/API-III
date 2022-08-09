package jsonObjects;

import java.util.List;

public class MainObject {
	public String type;
	public Metadata metadata;
	public List<Features> features;
	public String getType() {
		return type;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public List<Features> getFeatures() {
		return features;
	}
}
