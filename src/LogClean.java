import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
public class LogClean {
    public static void main(String[] args) throws Exception{
        String tempIP;
        List<String> ls = new ArrayList<String>();
        String[] filePathOlds = new String[] {"logs/out-log-0912-19.csv"};
        CsvReader csvreader_old;
        for(String filePathOld : filePathOlds) {
            csvreader_old = new CsvReader(filePathOld);
            while(csvreader_old.readRecord()) {
                ls.add(csvreader_old.get(1));
            }
        }
//        String filePathOld = "out-test1.csv";


        String filePathIn = "logs/log-0920-26.csv";
        String filePathOut = "logs/out-log-0920-26.csv";
        CsvReader csvreader = new CsvReader(filePathIn);
        if(csvreader.readHeaders()) {
            int count = 0;
            CsvWriter csvwriter = new CsvWriter(filePathOut);
            csvwriter.writeRecord(csvreader.getHeaders());
            while(csvreader.readRecord()) {
                if(!ls.contains(tempIP = csvreader.get(1))) {
                    ls.add(tempIP);
                    csvwriter.writeRecord(csvreader.getValues());
                    ++count;
                }

        }
            csvwriter.flush();
            csvwriter.close();
            csvreader.close();
            System.out.println(count);

        }
    }
}
