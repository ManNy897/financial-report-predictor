import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by macpro on 11/10/16.
 */
public class FileExtractor {
    HashMap<String, List<String>> fileInfo = new HashMap<String, List<String>>();

    public FileExtractor(String dir){
        buildFilesMap(dir);
    }

    public void buildFilesMap(String dir){
        File folder = new File(dir);
        File[] files = folder.listFiles((directory, name) -> !name.equals(".DS_Store"));
        for (File file:files){
            try {
                addElementsFromFile(new BufferedReader(new FileReader(file)));
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    public void addElementsFromFile(BufferedReader br) throws IOException {
        for(String line; (line = br.readLine()) != null;){
            String[] parts = line.split("\t");
            ArrayList<String> elements = new ArrayList<String>();
            String fileName = parts[0] + ".txt";
            String date = parts[1];
            String companyName = parts[3].split(" /")[0];
            elements.add(companyName);
            elements.add(date);
            fileInfo.put(fileName, elements);
        }
        br.close();
    }

    public HashMap<String, List<String>> getFileMap(){
        return fileInfo;
    }

    public LocalDate getReportDate(String fileName){
        String dateString = fileInfo.get(fileName).get(1);
        LocalDate  date = LocalDate.parse(dateString, DateTimeFormatter.BASIC_ISO_DATE);
        return date;
    }

    public String getCompanyName(String fileName){
        String companyName = fileInfo.get(fileName).get(0);
        return companyName;
    }

 /*   public String getReportSymbol(String fileName){

    }
*/
}


