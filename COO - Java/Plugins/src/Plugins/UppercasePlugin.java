package Plugins;

import Windows.Plugin;

public class UppercasePlugin implements Plugin {


	/**
	 *	returns the text to small caps
	 *	@return the text to small caps
	 */
	
	@Override
	public String changeText(String text) {
		return text.toUpperCase() ;
	}



}
