package Model;


import Controller.LoginDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DbConnect.DbConnect;
public class ProfileManagerModeDao {

    //데이터 삽입 메서드
    public boolean insert(LoginDto dto) {
        Connection conn = null;
        PreparedStatement st = null;
        int flag = 0;
        try {
            conn = new DbConnect().getConn();
            String sql = "INSERT INTO profile" + "(name,age,id,pw,phone,mail,address)" + " values(?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, dto.getName());       //1~5의 의미는 values의 ? 값
            st.setString(2, dto.getAge());
            st.setString(3, dto.getId());
            st.setString(4, dto.getPw());
            st.setString(5, dto.getPhone());
            st.setString(6, dto.getMail());
            st.setString(7, dto.getAddress());
            flag = st.executeUpdate(); //다음 메서드는 쿼리 실행 하게 해주는 것 // 객체의 값을 반환시키는 것 // 쉽게 말해 SQL문 실행시켜주는 것 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
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

    // 수정 메서드
    
    public boolean update(LoginDto dto) {
        //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        int flag = 0;
        try {
            //Connection 객체의 참조값 얻어오기 
            conn = new DbConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "UPDATE profile"
                    + " SET name=?, age=?, pw=? ,phone=?,mail=?,address=?"
                    + " WHERE id=?";          //만약에 pw, name ,id만 추가할 경우 address 와 mail은 수정이 안됨 갱신 x 따라서 현재 나는 address, mail을 추가해준 상태
            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기 
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getAge());
            pstmt.setString(3, dto.getPw());
            pstmt.setString(4, dto.getPhone());
            pstmt.setString(5, dto.getMail());
            pstmt.setString(6, dto.getAddress());
            pstmt.setString(7, dto.getId());
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

    public LoginDto getData(String id) {
        //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LoginDto dto = null;
        try {
            //Connection 객체의 참조값 얻어오기 
            conn = new DbConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "SELECT pw,name "
                    + " FROM profile"
                    + " WHERE id = ?";
            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기 
            pstmt.setString(1, id);
            //select 문 수행하고 결과를 ResultSet 으로 받아오기 
            rs = pstmt.executeQuery();
            if (rs.next()) {
                //cursor 가 위치한 곳의 칼럼 데이터를 빼오기
                
                                                                                        //빌더 패턴 적용
                dto = new LoginDto.Builder()
                .setPw(rs.getString("pw"))
                .setName(rs.getString("name"))
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
    public boolean delete(LoginDto dto) {
        //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        int flag = 0;
        try {
            //Connection 객체의 참조값 얻어오기 
            conn = new DbConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "DELETE FROM profile"
                    + " WHERE pw=?";
            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기 
            pstmt.setString(1, dto.getPw());
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

    public List<LoginDto> getList() {
        //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LoginDto> list = new ArrayList<>();

        try {
            //Connection 객체의 참조값 얻어오기 
            conn = new DbConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "SELECT name,age,id, pw,phone,mail,address"
                    + " FROM profile";
            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기 

            //select 문 수행하고 결과를 ResultSet 으로 받아오기 
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //cursor 가 위치한 곳의 칼럼 데이터를 빼오기 
                
                
                                                                                            //빌더 패턴 적용
                LoginDto dto = new LoginDto.Builder()
                .setName(rs.getString("name"))
                .setAge(rs.getString("age"))
                .setId(rs.getString("id"))
                .setPw(rs.getString("pw"))
                .setPhone(rs.getString("phone"))
                .setMail(rs.getString("mail"))
                .setAddress(rs.getString("address"))
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
