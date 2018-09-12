package Windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;

public class PluginFinder implements ActionListener {
	
	
	/**
	 *  finds where are my plugins and notify my ihm 
	 */
	
	// fichier racine pour savoir où sont mes plugins
	private File dir ;
	private PluginFilter filtrer ;
	//instance de notre ihm 
	private IHMPlugins windows ;
		
	
	/**
	 *  PluginFinder constructor initialized the dir  and identify the windows used
	 *  iniates the root folder 
	 * @param instance of the windows 
	 */
	public PluginFinder(IHMPlugins instance) {
		this.dir = new File("bin/Plugins.tools") ;
		this.windows = instance ;
		
		
	}
	
	/**
	 * finds where are my plugins and will notify my ihm 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Refresh");
		boolean temp ;
		this.filtrer = new PluginFilter() ;
		File[] filesList = dir.listFiles();
		ArrayList<File> file_accepted = new ArrayList<File>() ;
		for(File f : filesList){
			if(filtrer.accept(this.dir, f.getName())) {
				file_accepted.add(f) ;
			}	
		}
		windows.notify(file_accepted) ;
		
	}



}
