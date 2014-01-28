package larbi.tp.sentiment;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

public class UsingTreeTagger {
	
	private static PrintWriter fluxSortie;
	private static HashMap<String,String> filesNamesPaths ;
	private static File folder;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws TreeTaggerException 
	 */
	public static void main(String[] args) throws IOException, TreeTaggerException {
		// TODO Auto-generated method stub
		
		//récupérer la liste des path des fichiers textes dans une HashMap
        folder = new File("output/CleanedFiles");
        File[] listOfFiles = folder.listFiles();
        filesNamesPaths = new HashMap<String,String>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
            	
            	filesNamesPaths.put(file.getName(),file.getPath());     
            }
        }
        
        
        //Parcourir la HashMap et effectuer les traitements
        for(Entry<String, String> entry : filesNamesPaths.entrySet()) {
        fluxSortie = new PrintWriter(new FileOutputStream("output/outputTagger/tag-"+entry.getKey()));
	    System.setProperty("treetagger.home", "/Users/guemache/Desktop/UtilitaireProgrammation/TreeTagger/");		
        	   File file = new File(entry.getValue());
               Scanner scanner = new Scanner(file);
               String [] tab;
               while (scanner.hasNextLine()) {
               String currentLine = (String) scanner.nextLine();
               if((!currentLine.startsWith("rating"))){
               //current Line est le commentaire en cours dans le fichier de critique en cours
               tab = currentLine.split("");
               //current Line est le commentaire en cours dans le fichier de critique en cours
               tab = currentLine.split(" ");
               //utilisation de TreeTagger
               try {
            	   TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
                   tt.setModel("/Users/guemache/Desktop/UtilitaireProgrammation/TreeTagger/french-par-linux-3.2-utf8.bin");
                   tt.setHandler(new TokenHandler<String>() {
                           public void token(String token, String pos, String lemma) {
                                   fluxSortie.println(token + "\t" + pos + "\t" + lemma);
                                   
                           }
                           
                   });
                   tt.process(asList(tab));
                   
                   
               } 
           finally {
                System.out.println();
           }
               
               }
               }
               fluxSortie.close( );  
               scanner.close();
        }
        
        
        
        

	}

}
