package courriers.factory.letters.abs;

import courriers.factory.letters.complexLetter.DefaultFactoryComplexLetter;
import courriers.factory.letters.complexLetter.FactoryComplexLetter;
import courriers.factory.letters.promissoryNote.DefaultFactoryPrommisoryNote;
import courriers.factory.letters.promissoryNote.FactoryPromissoryNote;
import courriers.factory.letters.registeredLetter.FactoryRegisterLetter;
import courriers.factory.letters.registeredLetter.RandomDecorateFactoryRegisteredLetter;
import courriers.factory.letters.simpleLetter.DefaultFactorySimpleLetter;
import courriers.factory.letters.simpleLetter.FactorySimpleLetter;
import courriers.factory.letters.urgentLetter.FactoryUrgentLetter;
import courriers.factory.letters.urgentLetter.RandomDecorateFactoryUrgentLetter;
import courriers.letters.ComplexLetter;
import courriers.letters.Letter;
import courriers.letters.PromissoryNote;
import courriers.letters.SimpleLetter;
import courriers.letters.decorators.RegisteredLetter;
import courriers.letters.decorators.UrgentLetter;

/**
 * Represent a factory to generate basic {@link Letter}
 */
public class DefaultAbstractFactoryLetter implements AbstractFactoryLetter{

	protected FactorySimpleLetter factorySimpleLetter;
	protected FactoryPromissoryNote factoryPromissoryNote;
	protected FactoryComplexLetter factoryComplexLetter;
	protected FactoryRegisterLetter factoryRegisterLetter;
	protected FactoryUrgentLetter factoryUrgentLetter;
	
	public DefaultAbstractFactoryLetter() {
		this(new DefaultFactorySimpleLetter(), new DefaultFactoryPrommisoryNote(), new DefaultFactoryComplexLetter(), 
			 new RandomDecorateFactoryRegisteredLetter(), new RandomDecorateFactoryUrgentLetter());
	}
	
	public DefaultAbstractFactoryLetter(FactorySimpleLetter factorySimpleLetter, FactoryPromissoryNote factoryPromissoryNote, FactoryComplexLetter factoryComplexLetter,
										FactoryRegisterLetter factoryRegisterLetter, FactoryUrgentLetter factoryUrgentLetter) {
		this.factorySimpleLetter = factorySimpleLetter;
		this.factoryPromissoryNote = factoryPromissoryNote;
		this.factoryComplexLetter = factoryComplexLetter;
		this.factoryRegisterLetter = factoryRegisterLetter;
		this.factoryUrgentLetter = factoryUrgentLetter;
	}
	
	@Override
	public PromissoryNote createPromissoryNote() {
		return factoryPromissoryNote.createPromissoryNote();
	}

	@Override
	public SimpleLetter createSimpleLetter() {
		return factorySimpleLetter.createSimpleLetter();
	}

	@Override
	public ComplexLetter createComplexLetter() {
		return factoryComplexLetter.createComplexLetter();
	}

	@Override
	public RegisteredLetter<?> createRegisteredLetter() {
		return factoryRegisterLetter.createRegisteredLetter();
	}

	@Override
	public UrgentLetter<?> createUrgentLetter() {
		return factoryUrgentLetter.createUrgentLetter();
	}
}