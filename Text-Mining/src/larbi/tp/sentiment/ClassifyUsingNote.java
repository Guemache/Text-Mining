package larbi.tp.sentiment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;
import org.annolab.tt4j.TreeTaggerException;


public class ClassifyUsingNote {
	
	//attributs
	private static PrintWriter fluxSortie;
	private static HashMap<String,String> filesNamesPaths ;
	private static File folder;
	

	/**
	 * @param args
	 * @throws IOException 
	 * @throws TreeTaggerException 
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		//récupérer la liste des path des fichiers textes dans une HashMap
        folder = new File("input/archives");
        File[] listOfFiles = folder.listFiles();
        filesNamesPaths = new HashMap<String,String>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
            	
            	filesNamesPaths.put(file.getName(),file.getPath());     
            }
        }
        
      //Parcourir la HashMap et effectuer les traitements
        for(Entry<String, String> entry : filesNamesPaths.entrySet()) {
      
        	   File file = new File(entry.getValue());
               Scanner scanner = new Scanner(file);
               String [] tab;
               while (scanner.hasNextLine()) {
               String currentLine = (String) scanner.nextLine();
               if((currentLine.startsWith("rating"))){
               //current Line est le commentaire en cours dans le fichier de critique en cours
               tab = currentLine.split(" ");
               int rating = Integer.parseInt(tab[1]);
           
              if(rating>2){
               fluxSortie = new PrintWriter(new FileOutputStream("output/outputUsingNote/positive/"+entry.getKey()));
               while(scanner.hasNextLine()){
            	String currentLine1 = (String) scanner.nextLine();
            	fluxSortie.println(currentLine1);
               }
               fluxSortie.close( );
               }else{
               fluxSortie = new PrintWriter(new FileOutputStream("output/outputUsingNote/negative/"+entry.getKey()));  
               while(scanner.hasNextLine()){
               	String currentLine1 = (String) scanner.nextLine();
               	fluxSortie.println(currentLine1);
                  }
               fluxSortie.close( );
               }
               }
                
               }
               
               scanner.close();
        }
        
        
 	}

}
