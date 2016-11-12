/**
 * Created by macpro on 11/11/16.
 */
import java.util.Map;

public class FileExtractorDriver {
    static String dir = "/Users/macpro/Documents/CS585/Capstone/data_info";

    public static void main(String[] args) {
        FileExtractor fe = new FileExtractor(dir);
        for(Map.Entry entry:fe.getFileMap().entrySet()){
            System.out.println(entry.getKey() + "\t" + entry.getValue() );
        }
        System.out.println("27887E100-10-K-2005-03-31.txt" + "   " + fe.getCompanyName("27887E100-10-K-2005-03-31.txt") + "     " + fe.getReportDate("27887E100-10-K-2005-03-31.txt").toString());

    }
}
