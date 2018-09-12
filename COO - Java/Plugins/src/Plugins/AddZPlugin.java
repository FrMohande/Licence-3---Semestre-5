package Plugins;

import Windows.Plugin;

public class AddZPlugin implements Plugin {

	
	/**
	 *	returns the text with z added 
	 *	@return the text with z added
	 */
	@Override
	public String changeText(String text) {
		return text + "z" ;
	}

}
