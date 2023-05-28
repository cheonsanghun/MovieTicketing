/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DbConnect.Singleton.DbConnect;
import java.sql.Statement;
        /*
        비밀번호 찾기에 대한 Dao 클래스
 */
public class PwSearchDao {
      static private Connection conn = null;
    static private Statement st = null;
    private ResultSet rs = null;
    String pass = null; 
    
  public String findpw(String id, String name) {
     
        int flag = 0;
        try {
            conn = new DbConnect().getConn();
            String sql ="select pw FROM profile WHERE id = '" + id + "' and name = '" + name + "'";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
          
               if (rs.next()) {
                pass = rs.getString("pw");
                return pass;
            } else {
                return "errorinformation";
            }
        } catch (Exception e) {
           return "errorinformation";
        }
  }
}
    


    

