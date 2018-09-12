package courriers.letters.contents;

import java.util.ArrayList;
import java.util.List;

/**
 * List of content in a letter
 */
public class CompositeContent extends Content{
	protected List<Content> contents = new ArrayList<>();
	
	/**
	 * Create a composite for a letter
	 */
	public CompositeContent() {
		this(new ArrayList<>());
	}
	
	/**
	 * Create a composite with a list of {@link Content} already defined
	 * @param contents The list of {@link Content} to use in the composite
	 */
	public CompositeContent(List<Content> contents){
		this.contents = contents;
	}
	
	@Override
	public String toString() {
		//http://kaioa.com/node/59 for performance of StringBuilder
		StringBuilder allToString = new StringBuilder();
		for(Content c : contents){
			allToString.append(c.toString());
		}
		return allToString.toString();
	}

	public List<Content> getContents() {
		return contents;
	}
}
