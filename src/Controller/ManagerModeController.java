/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

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
public class ManagerModeController {

    DefaultTableModel dft;
    JTable table;
    // 추가하기 메서드
    List<LoginDto> companys;

    public void showMembers() {
        companys = new ProfileManagerModeDao().getList();
        for (LoginDto tmp : companys) {
            Object[] row = {tmp.getName(), tmp.getAge(), tmp.getId(), tmp.getPw(), tmp.getPhone(), tmp.getMail(), tmp.getAddress()};
            dft.addRow(row);
        }
    }

    public void addAction(String name, String age, String id, String pw, String phone, String mail, String address) {
        //1. 입력한 이름과 주소를 읽어온다.

        //2. DB 에 저장한다.
        LoginDto dto = new LoginDto.Builder()
                .setName(name)
                .setAge(age)
                .setId(id)
                .setPw(pw)
                .setPhone(phone)
                .setMail(mail)
                .setAddress(address)
                .build();
        //작업의 성공여부를 리턴 받는다. 
        boolean isSuccess = new ProfileManagerModeDao().insert(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "저장 했습니다.");
            //행의 갯수를 강제로 0 로 만들고 

            //다시 출력하기
        } else {
            JOptionPane.showMessageDialog(null, "저장 실패! 중복된 아이디가 있습니다.");
        }

    }

    //삭제 메서드
    public void deleteAction() {
        //선택된 row 의 인덱스를 얻어와서

        int index = table.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "삭제할 행을 선택해 주세요.");
        }
        if (index == -1) {
            return;
        }

        //DB 에서 삭제하고
        LoginDto dto = new LoginDto();
        dto.setPw(companys.get(index).getPw());
        new ProfileManagerModeDao().delete(dto);
        //다시 출력
       

        //작업의 성공여부를 리턴 받는다. 
        boolean isSuccess = new ProfileManagerModeDao().delete(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "삭제 했습니다.");
        } else {
            JOptionPane.showMessageDialog(null, "저장 했습니다.");
        }

    }

    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange()");
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("tableCellEditor")) {
            //선택된 row 의 index 를 얻어와서 
            int index = table.getSelectedRow();
            //인덱스에 해당하는 model 에서 입력된 이름과 주소를 읽어온다. 
            String name = (String) dft.getValueAt(index, 0); //2번째 인덱스의 주소를 읽어옴
            String age = (String) dft.getValueAt(index, 1); //1번째 인덱스의 이름을 읽어옴
            String id = (String) dft.getValueAt(index, 2); //0번째 인덱스의 번호를 읽어옴
            String pw = (String) dft.getValueAt(index, 3); //1번째 인덱스의 이름을 읽어옴
            String phone = (String) dft.getValueAt(index, 4); //1번째 인덱스의 이름을 읽어옴
            String mail = (String) dft.getValueAt(index, 5); //3번째 인덱스의 입사일을 읽어옴
            String address = (String) dft.getValueAt(index, 6); //3번째 인덱스의 입사일을 읽어옴
            //DB 에 수정 반영

            //빌더 패턴 
            LoginDto dto = new LoginDto.Builder()
                    .setName(name)
                    .setAge(age)
                    .setId(id)
                    .setPw(pw)
                    .setPhone(phone)
                    .setMail(mail)
                    .setAddress(address)
                    .build();
            new ProfileManagerModeDao().update(dto);
        }

    }
    //회원목록 전체 출력

}
