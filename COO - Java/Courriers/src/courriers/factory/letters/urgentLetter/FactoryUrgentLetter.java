package courriers.factory.letters.urgentLetter;

import courriers.letters.decorators.UrgentLetter;

public interface FactoryUrgentLetter {
	
	UrgentLetter<?> createUrgentLetter();
	
}
