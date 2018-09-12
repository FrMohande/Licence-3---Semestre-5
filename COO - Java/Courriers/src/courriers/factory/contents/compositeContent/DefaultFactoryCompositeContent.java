package courriers.factory.contents.compositeContent;

import java.util.ArrayList;
import java.util.List;

import courriers.factory.contents.moneyContent.DefaultFactoryMoneyContent;
import courriers.factory.contents.textContent.DefaultFactoryTextContent;
import courriers.letters.contents.CompositeContent;
import courriers.letters.contents.Content;

/**
 * This factory generate a list of {@link Content}
 */
public class DefaultFactoryCompositeContent implements FactoryCompositeContent{

	@Override
	public CompositeContent createCompositeContent() {
		List<Content> contents = new ArrayList<>();
		contents.add(new DefaultFactoryTextContent().createTextContent());
		contents.add(new DefaultFactoryMoneyContent().createMoneyContent());
		return new CompositeContent(contents);
	}

}
