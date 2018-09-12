package courriers.factory.city;

import courriers.city.City;
import courriers.factory.inhabitant.DefaultFactoryInhabitant;
import courriers.factory.inhabitant.FactoryInhabitant;

/**
 * A basis creation of a city
 *
 */
public class DefaultFactoryCity implements FactoryCity {
	
	protected String nameCity;
	protected FactoryInhabitant factoryInhabitant;
	protected int nbHabitant;
	protected City city;
	
	public final static int DEFAULT_NB_INHABITANT = 100;

	
	public DefaultFactoryCity(String nameCity) {
		this(nameCity, DEFAULT_NB_INHABITANT);
	}
	
	public DefaultFactoryCity(String nameCity, int nbHabitant) {
		this(nameCity, new DefaultFactoryInhabitant(), nbHabitant);
	}
	
	public DefaultFactoryCity(String nameCity, FactoryInhabitant factoryInhabitant, int nbHabitant) {
		this.nameCity = nameCity;
		this.nbHabitant = nbHabitant;
		setFactoryInhabitant(factoryInhabitant);
	}
	
	public void setFactoryInhabitant(FactoryInhabitant factoryInhabitant) {
		this.factoryInhabitant = factoryInhabitant;
	}
	
	protected void linkedCity() {
		factoryInhabitant.setCity(city);
	}
	
	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}
	
	protected String messageCreateCity() {
		return "Creating "+ nameCity + " city.";
	}
	
	protected String messageCreateNInhabitant() {
		return "Creating "+ nbHabitant + " inhabitant.";
	}

	@Override
	public City createCity() {
		city = new City(nameCity);
		linkedCity();
		System.out.println(messageCreateCity());
		for(int i=0; i < nbHabitant; ++i) {
			city.addInhabitants(factoryInhabitant.createInhabitant());
		}
		System.out.println(messageCreateNInhabitant());
		return city;
	}
}
