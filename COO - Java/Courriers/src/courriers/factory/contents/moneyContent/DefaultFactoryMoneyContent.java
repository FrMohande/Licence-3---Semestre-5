package courriers.factory.contents.moneyContent;

import courriers.letters.contents.MoneyContent;
import courriers.utils.UtilsCourriers;

/**
 * Represent a factory to generate a random {@link MoneyContent}
 */
public class DefaultFactoryMoneyContent implements FactoryMoneyContent{
	
	public static final int BOUND_MAX = 1000;
	public static final int BOUND_MIN = 1;
	
	@Override
	public MoneyContent createMoneyContent() {	
		return new MoneyContent(UtilsCourriers.randDouble(BOUND_MIN, BOUND_MAX));
	}

}
