package courriers.tests.letters.contents;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import courriers.letters.contents.TextContent;

public class TextContentTest {
	public final static String CONTENT_LETTER = "bla bla";
	
	@Test
	public void testTextContent() {
		TextContent content = new TextContent(CONTENT_LETTER);
		assertTrue(content.getContentLetter().equals(CONTENT_LETTER));
	}

}
