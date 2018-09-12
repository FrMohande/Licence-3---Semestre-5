package courriers.factory.letters.promissoryNote;

import courriers.factory.contents.moneyContent.DefaultFactoryMoneyContent;
import courriers.factory.contents.moneyContent.FactoryMoneyContent;
import courriers.letters.PromissoryNote;

/**
 * Represent a factory to generate a classic {@link PromissoryNote}
 */
public class DefaultFactoryPrommisoryNote implements FactoryPromissoryNote{
	protected FactoryMoneyContent factoryMoneyContent;
	
	public DefaultFactoryPrommisoryNote() {
		this(new DefaultFactoryMoneyContent());
	}
	
	public DefaultFactoryPrommisoryNote(FactoryMoneyContent factoryMoneyContent) {
		this.factoryMoneyContent = factoryMoneyContent;
	}
	
	@Override
	public PromissoryNote createPromissoryNote() {
		return new PromissoryNote(factoryMoneyContent.createMoneyContent());
	}

}
