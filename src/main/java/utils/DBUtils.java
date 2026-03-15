package utils;

import java.sql.*;
import java.util.*;

public class DBUtils {
    public static Object[][] getDBData(){
        List<Object[]> data = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/simple_travel_agency",
                    "root",
                    "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bookingdata");
            while(rs.next()){
                Object[] row = new Object[7];
                row[0] = rs.getString("name");
                row[1] = rs.getString("address");
                row[2] = rs.getString("city");
                row[3] = rs.getString("state");
                row[4] = rs.getString("zip");
                row[5] = rs.getString("card");
                row[6] = rs.getString("name_on_card");
                data.add(row);
            }
            rs.close();
            st.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        Object[][] result = new Object[data.size()][];
        for(int i=0;i<data.size();i++){
            result[i] = data.get(i);
        }
        return result;
    }
}