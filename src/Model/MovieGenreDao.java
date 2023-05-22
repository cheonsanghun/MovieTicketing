/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.LoginDto;
import Controller.MovieGenreDto;
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
public class MovieGenreDao {

    //데이터 삽입 메서드
  public boolean insert(MovieGenreDto dto) {
    Connection conn = null;
    PreparedStatement st1 = null;
    PreparedStatement st2 = null;
    int flag = 0;
    try {
        
        conn = new DbConnect().getConn();
        conn.setAutoCommit(false);
       String sql = "INSERT INTO genre (g_name) VALUES (?);";
st1 = conn.prepareStatement(sql);
st1.setString(1, dto.getGenre());
flag = st1.executeUpdate();

sql = "INSERT INTO movie (m_name) VALUES (?);";
st2 = conn.prepareStatement(sql);
st2.setString(1, dto.getMovie());
flag = st2.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // ... (close resources)
    }
    return flag > 0;
}

    public MovieGenreDto getData(String genre, String movie) {
        //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MovieGenreDto dto = null;

        try {
            //Connection 객체의 참조값 얻어오기 
            conn = new DbConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "SELECT movie.m_name, genre.g_name " + "FROM movie " + "JOIN genre ON movie.t_id = genre.t_id WHERE genre.g_name = ? AND movie.m_name = ?";
            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, movie);
            pstmt.setString(2, genre);
            //? 에 필요한값 바인딩하기 
            //select 문 수행하고 결과를 ResultSet 으로 받아오기 
            rs = pstmt.executeQuery();
            if (rs.next()) {
                //cursor 가 위치한 곳의 칼럼 데이터를 빼오기

                //빌더 패턴 적용
                dto = new MovieGenreDto.Builder()
                        .setGenre(rs.getString("g_name"))
                        .setMovie(rs.getString("m_name"))
                        .build();

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
        return dto;
    }

    public boolean delete(MovieGenreDto dto) {
       //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        int flag = 0;
        try {
            //Connection 객체의 참조값 얻어오기 
            conn = new DbConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "DELETE FROM movie"
                    + " WHERE m_name = ?";
            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기 
            pstmt.setString(1, dto.getMovie());
            //sql 문 실행하기 (INSERT, UPDATE, DELETE)
            flag = pstmt.executeUpdate();
          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        if (flag > 0) {
            //성공
            return true;
        } else {
            //실패
            return false;
        }
    }

    public List<MovieGenreDto> getList() {
        //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MovieGenreDto> list = new ArrayList<>();

        try {
            //Connection 객체의 참조값 얻어오기 
            conn = new DbConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "SELECT movie.m_name, genre.g_name FROM movie JOIN genre ON movie.t_id = genre.t_id";

            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기 

            //select 문 수행하고 결과를 ResultSet 으로 받아오기 
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //cursor 가 위치한 곳의 칼럼 데이터를 빼오기 

                //빌더 패턴 적용
                MovieGenreDto dto = new MovieGenreDto.Builder()
                        .setMovie(rs.getString("m_name"))
                        .setGenre(rs.getString("g_name"))
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
