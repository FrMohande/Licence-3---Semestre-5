package courriers.factory.city;

import courriers.city.City;

/**
 * Represent a factory to generate a specific case of a {@link City}
 */
public interface FactoryCity {
	City createCity();
}
