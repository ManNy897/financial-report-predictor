/**
 * Created by macpro on 11/11/16.
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.util.LinkedList;
import java.util.Map;
import java.io.IOException;
import java.util.Queue;

public class FileExtractorDriver {
    static String dir = "/Users/macpro/Documents/CS585/Capstone/Financial_Report_Predictor/data/data_info";
    static String reportDir = "/Users/macpro/Documents/CS585/Capstone/untagged_data/2005.full";

    public static void main(String[] args) {
        FileExtractor fe = new FileExtractor(dir);
        CSVReader lookup = new CSVReader();
        Classifier classify = new Classifier();
        ArrayList<ReportData> reData = new ArrayList<ReportData>();

        File folder = new File(reportDir);
        File[] files = folder.listFiles((directory, name) -> !name.equals(".DS_Store"));
        int cntFound = 0;
        int cntNotFound = 0;
        for (File file:files){
            String fileName = file.getName();
        //    System.out.println(fileName);
            String companyName = fe.getCompanyName(fileName);
        //    System.out.println(companyName);
            int[] date = fe.getReportDate(fileName);
            String sym = lookup.lookupSymbol(companyName);
            if(sym == null) continue;

//            if(sym == null) {
//                System.out.println("Symbol not found: " + companyName);
//                cntNotFound++;
//            }
//            else{
//                System.out.println("Symbol found: " + sym);
//                cntFound++;
//            }




        }

//        System.out.println("Symbols Found: " + cntFound);
//        System.out.println("Symbols Not Found: " + cntNotFound);







 /*       int noSym = 0;
        int countSym = 0;
        try {
            PrintWriter noSymOut = new PrintWriter(new FileWriter("/Users/macpro/Documents/CS585/Capstone/Financial_Report_Predictor/data/sorted_data/noSymbol/noSym.txt"));
            PrintWriter symOut = new PrintWriter(new FileWriter("/Users/macpro/Documents/CS585/Capstone/Financial_Report_Predictor/data/sorted_data/symbol/symFiles.txt"));

            for (String name : fe.getFileMap().keySet()) {
                ArrayList<String> = fe.get
                String symbol = lookup.get()

                String fName = entry.getKey().toString();
                System.out.print(fe.getCompanyName(fName));
                String cName = fe.getCompanyName(fName);
                int[] date = fe.getReportDate(fName);
                String sym = lookup.lookupSymbol(cName);
                System.out.println("\t" + sym);

                if (sym.equals("SYMBOL NOT FOUND")) {
                    noSymOut.println(fName);
                    noSym++;
                } else {
                    ReportData rd = new ReportData(fName, sym, date, classify.getSnp());
                    reData.add(countSym,rd);
                    symOut.println(fName + "," + cName + "," + date.toString());
                    countSym++;
                }

                //System.out.println(entry.getKey() + "\t" + entry.getValue() );
            }
            noSymOut.close();
            symOut.close();
        }catch(IOException e1) {
            System.out.println("Error during writing");
        }


        try{
            PrintWriter over = new PrintWriter(new FileWriter("/Users/macpro/Documents/CS585/Capstone/Financial_Report_Predictor/data/sorted_data/symbol/outperform/over.txt"));
            PrintWriter under = new PrintWriter(new FileWriter("/Users/macpro/Documents/CS585/Capstone/Financial_Report_Predictor/data/sorted_data/symbol/underperform/under.txt"));

            for(ReportData rd:reData){
                if(classify.isOutproformer(rd)){
                    over.println(rd.getFilename());
                }
                else{
                    under.println(rd.getFilename());
                }
            }
            over.close();
            under.close();
        }catch(IOException e1) {
            System.out.println("Error during writing to over under");
        }



        System.out.println("No SYMBOL: " + noSym);
        System.out.println("SYMBOL FOUND: " + countSym);
        System.out.println("27887E100-10-K-2005-03-31.txt" + "   " + fe.getCompanyName("27887E100-10-K-2005-03-31.txt") + "     " + fe.getReportDate("27887E100-10-K-2005-03-31.txt")[2]);
        System.out.println(lookup.lookupSymbol("NIKE INC"));
        */
    }
}
