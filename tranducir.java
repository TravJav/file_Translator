package translate;

import com.memetix.mst.MicrosoftTranslatorAPI;
import com.memetix.mst.translate.Translate;
import com.memetix.mst.language.Language;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JFileChooser;

import javax.swing.JFrame;

public class tranducir extends JFrame {

    /*
    Key required by microsoft translator for ID.
     */
 /*
    Vars for JFileChooser found in choose_file method
     */
    JFileChooser fc;
    File inputFile;
    File original_file;

    public tranducir() {
        Translate.setClientId("xxxxxxxxxxxxxx");
        Translate.setClientSecret("xxxxxxxxx");
    }

    /*
    First call Function to load file into
     */
    public void choose_file() throws Exception {

        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int dialog = fc.showOpenDialog(null);
        if (dialog == JFileChooser.APPROVE_OPTION) {
            File original_file = fc.getSelectedFile();


            extract_data(original_file);

        }
    }

    /*
    Extract data from the file
    and ask user what language is this being translated to
     */
    public void extract_data(File original_file) throws Exception {

        System.out.println("this is your files name" + original_file);

        System.out.println("Please Enter The Language You Wish To Translate To");

        String target;

        Scanner scan = new Scanner(System.in);
        target = scan.nextLine();
        System.out.println("You want this language" + target);

        /*
        Switch target language based on users input
         */
        try {
            switch (target) {

                case "spanish":
                    System.out.println("  /n Translating To Spanish");

                    spanish(original_file);

                    break;

                case "englsih":
                    System.out.println(" /n Translating To English");
                    english(original_file);
                    break;

                case "german":
                    System.out.println("  /n Translating To German");
                    german(original_file);
                    break;

                case "french":
                    System.out.println("  /n Translating To French");
                    french(original_file);

                    break;
            }

        } catch (NullPointerException e) {
            System.out.println("Language requested:  " + target + "is not available");
        }

    }

    public void spanish(File original_file) throws Exception {

        // puts the file into a string
        String translated_file = new String(Files.readAllBytes(Paths.get(original_file.getAbsolutePath())));
        System.out.println("File Being Translated: " + translated_file);

        String spanishTranslation = com.memetix.mst.translate.Translate.execute(translated_file, Language.SPANISH);

        System.out.println(spanishTranslation);
        create_file_translated(spanishTranslation);

    }

    public void english(File original_file) throws IOException, Exception {
        // puts the file into a string
        String translated_file = new String(Files.readAllBytes(Paths.get(original_file.getAbsolutePath())));
        System.out.println("File That Is Being Translated: " + translated_file);

        String spanishTranslation = com.memetix.mst.translate.Translate.execute(translated_file, Language.ENGLISH);

        System.out.println(spanishTranslation);
        create_file_translated(translated_file);
    }

    public void french(File original_file) throws IOException, Exception {
        // puts the file into a string
        String translated_file = new String(Files.readAllBytes(Paths.get(original_file.getAbsolutePath())));
        System.out.println("File Being Translated : " + translated_file);

        String spanishTranslation = com.memetix.mst.translate.Translate.execute(translated_file, Language.FRENCH);

        System.out.println(spanishTranslation);
        create_file_translated(translated_file);
    }

    public void german(File original_file) throws IOException, Exception {
        // puts the file into a string
        String translated_file = new String(Files.readAllBytes(Paths.get(original_file.getAbsolutePath())));
        System.out.println("File Being Translated: " + translated_file);

        String spanishTranslation = com.memetix.mst.translate.Translate.execute(translated_file, Language.GERMAN);

        System.out.println(spanishTranslation);
        create_file_translated(translated_file);
    }

    /*
    
    Create the actual file thats been translated
     */
    public void create_file_translated(String translated_file) throws FileNotFoundException {

        try {

            JFileChooser js2 = new JFileChooser();
            js2.setDialogTitle("Save As");
            js2.setCurrentDirectory(new File("./"));
            int result = js2.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {

                File nef = js2.getSelectedFile();
                PrintWriter fw = new PrintWriter(nef.getPath());
                fw.write(translated_file);
                fw.flush();
            }
            }catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
        
    }

    

    public static void main(String[] args) throws Exception {

        tranducir trans = new tranducir();
        trans.choose_file();

    }

}
