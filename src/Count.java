import java.io.File;
import java.io.FileReader;
import java.util.*;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
public class Count {
    public static void main(String[] args) throws Exception{
        String tempIP;
        String[] filePathOlds = new String[] {"logs/log-0912-19.csv", "logs/log-0920-26.csv"};
        Map<String, String> records = new HashMap<String, String>();
        CsvReader csvreader_old;
        for(String filePathOld : filePathOlds) {
            csvreader_old = new CsvReader(filePathOld);
            csvreader_old.readHeaders();
            while(csvreader_old.readRecord()) {
                if(records.containsKey(tempIP = csvreader_old.get(1))) {
                    records.put(tempIP, Integer.parseInt(records.get(tempIP))+1+"");
                } else {
                    records.put(tempIP, "1");
                }
            }
        }

        String filePathOut = "count/count-till0926.csv";
            int count = 0;
            CsvWriter csvwriter = new CsvWriter(filePathOut);
            csvwriter.writeRecord(new String[]{"IP","count"});
            Iterator it = records.entrySet().iterator();
            while(it.hasNext()) {
               Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
               String value = entry.getValue();
               if(!value.equals("1")) {
                   csvwriter.writeRecord(new String[]{entry.getKey(), entry.getValue()});
                   ++count;
               }
            }
            csvwriter.flush();
            csvwriter.close();
            System.out.println(count);

    }
}