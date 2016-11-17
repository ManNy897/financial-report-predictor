import java.io.File;
import java.time.LocalDate;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class FileExtractorDriver {
    //directories of all the files to be used... simply change the years that you are running on
    //you also have to separately make the year directories in the underdata and overdata
    static String ROOT = System.getProperty("user.dir");
    static String METADATADIR = ROOT + "/data/data_info";
    static String REPORTDIR = ROOT + "/data/unsorted_data/2006.mda/";
    static String SNPDIR = ROOT + "/data/snp_data/SnP_closing_prices.csv";
    static String SYMBOLDIR = ROOT + "/data/snp_data/symbols.csv";
    static String UNDERDATA = ROOT + "/data/sorted_data/under/2006/";
    static String OVERDATA = ROOT + "/data/sorted_data/over/2006/";

    public static void main(String[] args) {
        FileExtractor fe = new FileExtractor(METADATADIR);
        SymbolTable symbolTable = new SymbolTable(SYMBOLDIR);
        Classifier classify = new Classifier(SNPDIR);

        File folder = new File(REPORTDIR);
        File[] files = folder.listFiles((directory, name) -> !name.equals(".DS_Store"));

        int cntOver = 0;
        int cntUnder = 0;
        int cntTotal = 0;
        int cntNotFoundSym = 0;

        for (File file:files){
            cntTotal += 1;
            String fileName = file.getName();
            String companyName = fe.getCompanyName(fileName);
            LocalDate date = fe.getReportDate(fileName);
            String sym = symbolTable.lookupSymbol(companyName);

            if(sym == null){
                cntNotFoundSym ++;
                continue;
            }

            ReportData rd = new ReportData(sym, date);

            try {
                Path source = Paths.get(REPORTDIR + fileName);
                boolean outperformed = classify.isOutperformer(rd);

                if (outperformed){
                    System.out.println(fileName + ": overperformed");
                    cntOver += 1;

                    Path overDir = Paths.get(OVERDATA);
                    Files.copy(source, overDir.resolve(source.getFileName()));
                }
                else{
                    System.out.println(fileName + ": underperformed");
                    cntUnder += 1;
                    Path underDir = Paths.get(UNDERDATA);
                    Files.copy(source, underDir.resolve(source.getFileName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Total Number of Symbols Found: " + (cntTotal - cntNotFoundSym));
        System.out.println("Overperformers: " + cntOver);
        System.out.println("Underperformers: " + cntUnder);
        System.out.println("Total Number of Documents: " + cntTotal);

    }
}
