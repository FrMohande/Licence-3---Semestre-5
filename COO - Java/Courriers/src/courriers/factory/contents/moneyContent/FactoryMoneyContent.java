package courriers.factory.contents.moneyContent;

import courriers.letters.contents.MoneyContent;

/**
 * Represent a factory to generate a specific {@link MoneyContent}
 * @author maxime
 *
 */
public interface FactoryMoneyContent {
	MoneyContent createMoneyContent();
}
