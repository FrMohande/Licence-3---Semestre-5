package courriers.factory.inhabitant;

import courriers.city.City;
import courriers.city.Inhabitant;

/**
 * Represent a factory to generate a specific case of an {@link Inhabitant}
 */
public interface FactoryInhabitant {

	Inhabitant createInhabitant();
	void setCity(City city);
	City getCity();
}
