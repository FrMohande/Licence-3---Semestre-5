package Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class IHMPlugins extends JFrame implements Listener {

	
	/**
	 * this is a the Human–computer interaction to our application
	 */
	
	//Instanciation d'un TextArea 
	JTextArea textArea = new JTextArea(500,500) ;

	//Instanciation d'un scrollPanel 
	JScrollPane scrollPane = new JScrollPane(textArea);

	//Instanciation d'une barre de menu
	private JMenuBar menuBar = new JMenuBar() ;
	private JMenu menu_file = new JMenu("File") ;
	private JMenu menu_tools = new JMenu("Tools") ;
	private JMenu menu_help = new JMenu("Help") ;

	//Timer 
	private Timer timer ;

	//instance de notre ihm 
	private final static IHMPlugins instance = new IHMPlugins();

	
	/** creates the Human–computer interaction ( We use the swing application framework ) 
	 * with the differents elements like the menus, pannel and the text area
	 */
	public IHMPlugins()  {
		//Modifier le titre de la fenêtre
		setTitle("Extendable Editor") ;
		//Modifier la taille de la fenêtre
		setSize(800,400) ;

		//Termine le processus lorsqu'on clique sur la croix rouge
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		//Permet de mettre le TextArea et le scrollbar
		this.add(scrollPane, BorderLayout.CENTER);

		//Menu 
		menuBar.add(menu_file) ;
		menuBar.add(menu_tools) ;
		menuBar.add(menu_help) ;	    
		setJMenuBar(menuBar) ;

		// Cr�ation et lancement du timer
		timer = createTimer();
		timer.start() ;

		//Afficher la JFrame
		setVisible(true) ;
	}
	/** 
	 * creates the object Timer 
	 * @return a Timer with 5 secondes's tic and with PluginFinder as ActionListener 
	 */
	private Timer createTimer() {
		// Cr�ation d'un timer qui génère un tic toutes les 5 secondes
		return new Timer(5000,new PluginFinder(this)) ;

	}

	/**
	 * Main of the application
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * see Listener's interface 
	 */
	@Override
	public void notify(ArrayList<File> file_accept)  {
		//Mettre à jour le menu 
		menu_tools.removeAll() ;
		for(File f : file_accept){
			JMenuItem menuItem = new JMenuItem(f.getName().replace(".class", "")) ;
			menuItem.addActionListener(menuItemsOnClick) ;
			menu_tools.add(menuItem);
		}

	}
	

	ActionListener menuItemsOnClick = new ActionListener() {
		/**
		 * Events for the menu items when the users clicked on one on them 
		 * @param <code>e<code>  indicates a component-defined action. 
		 */
		public void actionPerformed(ActionEvent e) {
			/* DOC pour utilisé la  réflexivité
			 * http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
			 */

			//permet de savoir quel MenuItem a apeller l'�v�nement ;
			JMenuItem menuItemSelected = (JMenuItem) e.getSource() ;

			try {

				String nomClasse = "Plugins."+ menuItemSelected.getText() ;
				//récupère la classe du plugins utilisé
				Class classe  = Class.forName(nomClasse) ;
				//crée un object du plugin 
				Object Objectclasse =  classe.newInstance() ;
				//recupère la méthode changeText
				Method laMethode = Objectclasse.getClass().getMethod("changeText",String.class) ;
				
				//Utilise la méthode changeText et mets à jour le texte Area
				textArea.setText((String) laMethode.invoke(Objectclasse,textArea.getText())) ;
			} catch (ClassNotFoundException | InvocationTargetException | 
					IllegalArgumentException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException   v) {
				v.printStackTrace();
			} 		
		}
	};


}


