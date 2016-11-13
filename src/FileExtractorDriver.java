/**
 * Created by macpro on 11/11/16.
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.io.IOException;
import java.util.Queue;

public class FileExtractorDriver {
    static String dir = "/Users/patrickdibble/Documents/cs585/nlp_project/financial-report-predictor/data/data_info";


    public static void main(String[] args) {
        FileExtractor fe = new FileExtractor(dir);
        CSVReader lookup = new CSVReader();
        Classifier classify = new Classifier();
        ArrayList<ReportData> reData = new ArrayList<ReportData>();

        int noSym = 0;
        int countSym = 0;
        try {
            PrintWriter noSymOut = new PrintWriter(new FileWriter("/Users/patrickdibble/Documents/cs585/nlp_project" +
                    "/financial-report-predictor/data/sorted_data/noSymbol/noSym.txt"));
            PrintWriter symOut = new PrintWriter(new FileWriter("/Users/patrickdibble/Documents/cs585/nlp_project" +
                    "/financial-report-predictor/data/sorted_data/symbol/symFiles.txt"));

            for (Map.Entry entry : fe.getFileMap().entrySet()) {
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

//
//        try{
//            PrintWriter over = new PrintWriter(new FileWriter("/Users/patrickdibble/Documents/cs585/nlp_project/" +
//                    "financial-report-predictor/data/sorted_data/symbol/outperform/over.txt"));
//            PrintWriter under = new PrintWriter(new FileWriter("/Users/patrickdibble/Documents/cs585/nlp_project/" +
//                    "financial-report-predictor/data/sorted_data/symbol/underperform/under.txt"));
//
//            for(ReportData rd:reData){
//                if(classify.isOutproformer(rd)){
//                    over.println(rd.getFilename());
//                }
//                else{
//                    under.println(rd.getFilename());
//                }
//            }
//            over.close();
//            under.close();
//        }catch(IOException e1) {
//            System.out.println("Error during writing to over under");
//        }



        System.out.println("No SYMBOL: " + noSym);
        System.out.println("SYMBOL FOUND: " + countSym);
        System.out.println("27887E100-10-K-2005-03-31.txt" + "   " + fe.getCompanyName("27887E100-10-K-2005-03-31.txt") + "     " + fe.getReportDate("27887E100-10-K-2005-03-31.txt")[2]);
        System.out.println(lookup.lookupSymbol("NIKE INC"));
    }
}
