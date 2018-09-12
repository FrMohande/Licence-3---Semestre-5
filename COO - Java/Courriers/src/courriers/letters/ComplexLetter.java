package courriers.letters;


import courriers.letters.contents.CompositeContent;

/**
 * Generate letter using {@link CompositeContent}
 */
public class ComplexLetter extends Letter<CompositeContent>{
	private static final int  COST_COMPLEX_LETTER = 10;
	
	/**
	 * Create letter using {@link CompositeContent}
	 * @param compositeContent the composite to use in the letter
	 */
	public ComplexLetter(CompositeContent compositeContent) {
		this(COST_COMPLEX_LETTER, compositeContent);
	}
	
	/**
	 * Create a letter using a {@link CompositeContent} and a specific cost
	 * @param cost the cost of the letter
	 * @param compositeContent the composite to use in the letter
	 */
	public ComplexLetter(double cost, CompositeContent compositeContent){
		super(cost, compositeContent);
	}

	@Override
	protected void action() {  }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
