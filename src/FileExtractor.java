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
    //mapping file names to list containing date and company name of report
    HashMap<String, List<String>> fileInfo = new HashMap<String, List<String>>();

    public FileExtractor(String dir) {
        buildFilesMap(dir);
    }


    /**
     * Builds a map of the file names of the reports and the information we want (company name and date)
     * @param dir Directory of the folder containing the metadata files
     */
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

    /**
     * Reads in each metadata file and adds the values to the map
     * @param br
     * @throws IOException
     */
    public void addElementsFromFile(BufferedReader br) throws IOException {
        for(String line; (line = br.readLine()) != null;){
            String[] parts = line.split("\t");
            ArrayList<String> elements = new ArrayList<String>();
            String fileName = parts[0] + ".txt";
            String date = parts[1];
            String companyName = parts[3].split("/")[0].trim().toLowerCase().replaceAll("[^a-zA-Z0-9\\s&]", "").replace("corporation", "corp");
            elements.add(companyName);
            elements.add(date);
            fileInfo.put(fileName, elements);
        }
        br.close();
    }

    /**
     *
     * @return the map containing file names company name and report date
     */
    //used just for testing
    public HashMap<String, List<String>> getFileMap(){
        return fileInfo;
    }

    /**
     * Returns the date of the report
     * @param fileName
     * @return localDate object representing the date of the report
     */
    public LocalDate getReportDate(String fileName){
        String dateString = fileInfo.get(fileName).get(1);
        LocalDate  date = LocalDate.parse(dateString, DateTimeFormatter.BASIC_ISO_DATE);
        int[] dateArray = new int[3];
        dateArray[0] = date.getDayOfMonth();
        dateArray[1] = date.getMonthValue();
        dateArray[2] = date.getYear();
        return date;
    }

    /**
     * Returns the company name of the specified report
     * @param fileName
     * @return
     */
    public String getCompanyName(String fileName){
        String companyName = fileInfo.get(fileName).get(0);
        return companyName;
    }

    //Not developed
 /*   public String getReportSymbol(String fileName){

    }
*/
}


