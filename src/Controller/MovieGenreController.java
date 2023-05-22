/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MovieGenreDao;
import Model.ProfileManagerModeDao;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kjbg4
 */
public class MovieGenreController {

    DefaultTableModel dft;
    JTable table;
    List<MovieGenreDto> companys;

    public void showMembers() {
        companys = new MovieGenreDao().getList();
        for (MovieGenreDto tmp : companys) {
            Object[] row = {tmp.getGenre(), tmp.getMovie()};
            dft.addRow(row);
        }
    }

    public void addAction(String m_name, String g_name) {
        //1. 입력한 이름과 주소를 읽어온다.

        //2. DB 에 저장한다.
        MovieGenreDto dto = new MovieGenreDto.Builder()
                .setMovie(m_name)
                .setGenre(g_name)
                .build();
                
        //작업의 성공여부를 리턴 받는다. 
        boolean isSuccess = new MovieGenreDao().insert(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "저장 했습니다.");
            //행의 갯수를 강제로 0 로 만들고 

            //다시 출력하기
        } else {
            JOptionPane.showMessageDialog(null, "저장 실패! 중복된 아이디가 있습니다.");
        }

    }
    
 
}
