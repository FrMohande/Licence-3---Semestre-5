package courriers.factory.letters.urgentLetter;

import courriers.factory.letters.promissoryNote.DefaultFactoryPrommisoryNote;
import courriers.factory.letters.promissoryNote.FactoryPromissoryNote;
import courriers.factory.letters.simpleLetter.DefaultFactorySimpleLetter;
import courriers.factory.letters.simpleLetter.FactorySimpleLetter;
import courriers.letters.decorators.UrgentLetter;
import courriers.utils.UtilsCourriers;

public class RandomDecorateFactoryUrgentLetter implements FactoryUrgentLetter {

	FactorySimpleLetter factorySimpleLetter;
	FactoryPromissoryNote factoryPromissoryNote;
	
	public RandomDecorateFactoryUrgentLetter() {
		this(new DefaultFactorySimpleLetter(), new DefaultFactoryPrommisoryNote());
	}
	

	public RandomDecorateFactoryUrgentLetter(FactorySimpleLetter factorySimpleLetter, FactoryPromissoryNote factoryPromissoryNote) {
		this.factorySimpleLetter = factorySimpleLetter;
		this.factoryPromissoryNote = factoryPromissoryNote;
	}


	@Override
	public UrgentLetter<?> createUrgentLetter() {
		return UtilsCourriers.randInt(0, 2) == 1 ? new UrgentLetter<>(factorySimpleLetter.createSimpleLetter())
				 : new UrgentLetter<>(factoryPromissoryNote.createPromissoryNote());
	}

}
