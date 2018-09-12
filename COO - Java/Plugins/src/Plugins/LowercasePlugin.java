package Plugins;

import Windows.Plugin;

public class LowercasePlugin implements Plugin {
	

	/**
	 *	returns the text to capital letters
	 *	@return the text to capital letters 
	 */
	@Override
	public String changeText(String text) {
		return text.toLowerCase() ;
	}

	

}
