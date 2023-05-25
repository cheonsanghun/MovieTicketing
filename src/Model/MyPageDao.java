/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.LoginDto;
import Controller.MyPageDto;
import DbConnect.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kjbg4
 */
public class MyPageDao {
     public List<MyPageDto> getList() {
        //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MyPageDto> list = new ArrayList<>();

        try {
            //Connection 객체의 참조값 얻어오기 
            conn = new DbConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "SELECT s_row,s_col,t_id,g_id,m_id,cardnum"
                    + " FROM seat";
            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기 

            //select 문 수행하고 결과를 ResultSet 으로 받아오기 
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //cursor 가 위치한 곳의 칼럼 데이터를 빼오기 
                
                
                                                                                            //빌더 패턴 적용
                MyPageDto dto = new MyPageDto.Builder()
                .setcardnum(rs.getInt("cardnum"))
                .setg_id(rs.getInt("g_id"))
                .setm_id(rs.getInt("m_id"))
                .sets_col(rs.getInt("s_col"))
                .sets_row(rs.getInt("s_row"))
                .sett_id(rs.getInt("t_id"))
                .build();

                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return list;
    }
}
