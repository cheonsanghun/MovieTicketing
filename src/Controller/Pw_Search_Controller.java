/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.LoginDto;
import Model.PwSearchDao;
import View.Pw_Search;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author kjbg4
 */
public class Pw_Search_Controller {
    
       public void addAction(String uid, String uname) {
           Pw_Search ps = new Pw_Search();
     
        LoginDto dto = new LoginDto.Builder()
                .setName(uname)
                .setId(uid)
                .build();
        PwSearchDao psd = new PwSearchDao();
        String value = psd.findpw(uid, uname);

        if (value.equals("errorinformation")) {
            JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다. 다시 시도해주세요");
        } else {
            JOptionPane.showMessageDialog(null, "회원님의 비밀번호는" + value + "입니다.");
        }
    }
}
