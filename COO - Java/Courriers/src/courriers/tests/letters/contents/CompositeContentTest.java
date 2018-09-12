package courriers.tests.letters.contents;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import courriers.letters.contents.CompositeContent;
import courriers.letters.contents.Content;
import courriers.letters.contents.MoneyContent;
import courriers.letters.contents.TextContent;

public class CompositeContentTest {

	@Test
	public void testCompositeContent() {
		ArrayList<Content> contents = new ArrayList<>();
		contents.add(new TextContent("bla bla"));
		contents.add(new MoneyContent(10));
		
		CompositeContent complexContent = new CompositeContent(contents);
		assertTrue(complexContent != null);
		assertEquals(contents.size(), complexContent.getContents().size());
	}

}
