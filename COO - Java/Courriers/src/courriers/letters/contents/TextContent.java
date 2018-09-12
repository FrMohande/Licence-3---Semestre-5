package courriers.letters.contents;

/**
 * Text content for a letter
 */
public class TextContent extends Content {
	protected String contentLetter;
	
	/**
	 * Create a text content
	 * @param contentLetter the content of the letter
	 */
	public TextContent(String contentLetter){
		this.contentLetter = contentLetter;
	}
	
	@Override
	public String toString(){
		return "a text content ("+ contentLetter +")";
	}

	/**
	 * Get the text of the {@link TextContent}
	 * @return The text of the content
	 */
	public String getContentLetter() {
		return contentLetter;
	}
}
