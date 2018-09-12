package courriers.factory.contents.abs;

import courriers.factory.contents.compositeContent.DefaultFactoryCompositeContent;
import courriers.factory.contents.compositeContent.FactoryCompositeContent;
import courriers.factory.contents.moneyContent.DefaultFactoryMoneyContent;
import courriers.factory.contents.moneyContent.FactoryMoneyContent;
import courriers.factory.contents.textContent.DefaultFactoryTextContent;
import courriers.factory.contents.textContent.FactoryTextContent;
import courriers.letters.contents.CompositeContent;
import courriers.letters.contents.MoneyContent;
import courriers.letters.contents.TextContent;

/**
 * This factory generate contents for letters
 */
public class DefaultAbstractFactoryContent implements AbstractFactoryContent{
	
	protected FactoryCompositeContent factoryCompositeContent;
	protected FactoryMoneyContent factoryMoneyContent;
	protected FactoryTextContent factoryTextContent;
	
	public DefaultAbstractFactoryContent(){
		this(new DefaultFactoryCompositeContent(), new DefaultFactoryMoneyContent(), new DefaultFactoryTextContent());
	}
	
	public DefaultAbstractFactoryContent(FactoryCompositeContent factoryCompositeContent, FactoryMoneyContent factoryMoneyContent, FactoryTextContent factoryTextContent) {
		this.factoryCompositeContent = factoryCompositeContent;
		this.factoryMoneyContent = factoryMoneyContent;
		this.factoryTextContent = factoryTextContent;
	}

	@Override
	public TextContent createTextContent() {
		return factoryTextContent.createTextContent();
	}

	@Override
	public MoneyContent createMoneyContent() {
		return factoryMoneyContent.createMoneyContent();
	}

	@Override
	public CompositeContent createCompositeContent() {
		return factoryCompositeContent.createCompositeContent();
	}

}