package courriers.factory.letters.registeredLetter;

import courriers.factory.letters.promissoryNote.DefaultFactoryPrommisoryNote;
import courriers.factory.letters.promissoryNote.FactoryPromissoryNote;
import courriers.factory.letters.simpleLetter.DefaultFactorySimpleLetter;
import courriers.factory.letters.simpleLetter.FactorySimpleLetter;
import courriers.letters.decorators.RegisteredLetter;
import courriers.utils.UtilsCourriers;

public class RandomDecorateFactoryRegisteredLetter implements FactoryRegisterLetter {

	protected FactorySimpleLetter factorySimpleLetter;
	protected FactoryPromissoryNote factoryPromissoryNote;
	
	public RandomDecorateFactoryRegisteredLetter() {
		this(new DefaultFactorySimpleLetter(), new DefaultFactoryPrommisoryNote());
	}

	public RandomDecorateFactoryRegisteredLetter(FactorySimpleLetter factorySimpleLetter, FactoryPromissoryNote factoryPromissoryNote) {
		this.factorySimpleLetter = factorySimpleLetter;
		this.factoryPromissoryNote = factoryPromissoryNote;
	}

	@Override
	public RegisteredLetter<?> createRegisteredLetter() {
		return UtilsCourriers.randInt(0, 2) == 1 ? new RegisteredLetter<>(factorySimpleLetter.createSimpleLetter())
												 : new RegisteredLetter<>(factoryPromissoryNote.createPromissoryNote());
	}
	
	

}
