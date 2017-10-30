import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * 格式说明：
 *     log-0912-19.csv为原始文件
 * out-log-0912-19.csv为对应的LogClean的输出文件（没有删除重复ip（全局不重复））
 */
public class LogClean {
    public static void main(String[] args) throws Exception{
        String tempIP;
        List<String> ls = new ArrayList<String>();
        //filePathOlds为往期的输出文件数组，举例：
        //当清洗第三期的原始文件（log-0927-1003.csv）时，该数组中为第一第二期的输出文件（out-log-0912-19.csv, out-log-0920-26.csv）
        String[] filePathOlds = new String[] {"logs/out-log-0912-19.csv", "logs/out-log-0920-26.csv", "logs/out-log-0927-1003.csv"
        , "logs/out-log-1004-1008.csv", "logs/out-log-1009-1013.csv"};
        CsvReader csvreader_old;
        for(String filePathOld : filePathOlds) {
            csvreader_old = new CsvReader(filePathOld);
            while(csvreader_old.readRecord()) {
                ls.add(csvreader_old.get(1));
            }
        }
//        String filePathOld = "out-test1.csv";

        //当前要清洗的原始文件
        String filePathIn = "logs/log-1020-1026.csv";
        //当前文件清洗后的输出文件
        String filePathOut = "logs/out-log-1020-1026.csv";
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
