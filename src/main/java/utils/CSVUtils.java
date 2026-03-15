package utils;

import java.io.*;
import java.util.*;

public class CSVUtils {
    public static Object[][] getCSVData() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testingdata.csv";
        List<Object[]> data = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
            		if (line.trim().isBlank()) {
                    continue;
                }
                String[] values = line.split(",");
                data.add(values);
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        Object[][] result = new Object[data.size()][];
        for(int i=0;i<data.size();i++){
            result[i] = data.get(i);
        }
        return result;
    }
}