package Windows;

import java.io.File;
import java.util.ArrayList;

public interface Listener {
	
	
	/** 
	 * Updates the menu tools with the accepted plugin
	 * @param <code>file_accept</code> is the files with the plugin or plugins for the menu tools  
	 */
	public void notify(ArrayList<File> f) ;

}
