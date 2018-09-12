																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																			package Windows;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;

public class PluginFilter implements FilenameFilter {
	
	
	/** 
	 * allows to knows if my files are a plugin or not 
	 */
	
	
	
	
	
	/**
	 * @param dir root folder where are my package Plugins
	 * @param name's file in my package Plugin 
	 * @return <code>True</code> if the file belongs to the package Plugins else <code>False</code>
	 */
	@Override
	public boolean accept(File dir, String name) {
		if(endsWithClass(name)) {
			try {
				//Pour windows ::
				/*-int index = dir.toString().lastIndexOf("\\") ;
				String file = dir.toString().substring(index+1) ;
				String nomClasse = file + name.replace(".class", "") ; */
				//Pour linux 
				String nomClasse = dir.getName() + "." + name.replace(".class", "") ;
				Class classe = Class.forName(nomClasse) ;
				return Plugin.class.isAssignableFrom(classe) ;
			} catch (ClassNotFoundException e) {
				return false ;
			}
		}
		return false ;
	}
	/**
	 * @param name 
	 * @return <code>True</code> if the name finish with ".class" else  <code>false</code> 
	 * 
	 */
	public boolean endsWithClass(String name) {
		return name.endsWith(".class") ;
	}
	

}
