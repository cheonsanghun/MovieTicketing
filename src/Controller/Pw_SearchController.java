/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.PwSearchDao;
import View.Pw_SearchView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author kjbg4
 */
public class Pw_SearchController {
    
       public void addAction(String uid, String uname) {
           Pw_SearchView ps = new Pw_SearchView();
     
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
