package larbi.tp.sentiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleaningFiles {
	
	//attributs
	private static PrintWriter fluxSortie;
	private static HashMap<String,String> filesNamesPaths ;
	private static File folder;
	private static int rating;

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
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
        fluxSortie = new PrintWriter(new FileOutputStream("output/CleanedFiles/Cleaned"+entry.getKey()));
        	   File file = new File(entry.getValue());
               Scanner scanner = new Scanner(file);
               String [] tab;
               while (scanner.hasNextLine()) {
               String currentLine = (String) scanner.nextLine();
               if(currentLine.startsWith("rating")){
               tab = currentLine.split(" ");
               rating = Integer.parseInt(tab[1]);
               }
               if((!currentLine.startsWith("rating"))){
               //current Line est le commentaire en cours dans le fichier de critique en cours
                Pattern p = Pattern.compile("[a-zA-Z_0-9_é_è_à_’_'_ï_ô_ê]*");
           		Matcher m = p.matcher(currentLine);

                   while (m.find()) {
                       fluxSortie.print(m.group()+" ");
                   }
               
               fluxSortie.println();
              
               }
               
               }
               if(rating>2){
               fluxSortie.print(", P");
               fluxSortie.close( ); 
               }else{
               fluxSortie.print(", N"); 
               fluxSortie.close( ); 
               }
                
               scanner.close();
        }
        
        
        
        
        
        System.out.println("Fin d'éxecution");
        
        
        

	}

}
