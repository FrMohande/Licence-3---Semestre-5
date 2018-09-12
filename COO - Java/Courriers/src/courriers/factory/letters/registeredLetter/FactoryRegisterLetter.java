package courriers.factory.letters.registeredLetter;

import courriers.letters.decorators.RegisteredLetter;

public interface FactoryRegisterLetter {
	
	RegisteredLetter<?> createRegisteredLetter();
}
