package courriers.factory.letters.complexLetter;

import courriers.factory.contents.compositeContent.DefaultFactoryCompositeContent;
import courriers.factory.contents.compositeContent.FactoryCompositeContent;
import courriers.letters.ComplexLetter;

/**
 * Represent a factory to generate a classic {@link ComplexLetter}
 */
public class DefaultFactoryComplexLetter implements FactoryComplexLetter{

protected FactoryCompositeContent factoryCompositeContent;
	
	public DefaultFactoryComplexLetter(){
		this(new DefaultFactoryCompositeContent());
	}
	
	public DefaultFactoryComplexLetter(FactoryCompositeContent factoryCompositeContent){
		this.factoryCompositeContent = factoryCompositeContent;
	}
	
	@Override
	public ComplexLetter createComplexLetter() {
		return new ComplexLetter(factoryCompositeContent.createCompositeContent());
	}

}
