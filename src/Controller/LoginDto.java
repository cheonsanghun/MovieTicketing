/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * 빌더 패턴 적용
 */
public class LoginDto {
    //회원가입 및 로그인에 대한 필드
    private String name; // 이름
    private String age; //나이 
    private String id; // 아이디
    private String pw; //비밀번호
    private String phone; // 휴대폰
    private String mail; // 메일
    private String address; // 주소
    private int cardnum; // 카드정보
    
    //getter
    public String getName() {
        return name;
    }
    public int getcardnum() {
        return cardnum;
    }
    public String getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }
   public void setPw(String pw){
        this.pw=pw;
    }
   
    public LoginDto() {
        super();
    }
    //빌더 패턴
    private LoginDto(Builder builder) {
        super();
        this.name = builder.name;
        this.age =  builder.age;
        this.id =  builder.id;
        this.pw =  builder.pw;
        this.phone =  builder.phone;
        this.mail =  builder.mail;
        this.address =  builder.address;
        this.cardnum=builder.cardnum;
    }

    public static class Builder {

        private String name;
        private String id;
        private String pw;
         private String age;
         private String phone;
         private String mail;
         private String address;
         private int cardnum;
        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
         public Builder setcardnum(int cardnum) {
            this.cardnum = cardnum;
            return this;
        }
        public Builder setAge(String age) {
            this.age = age;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setPw(String pw) {
            this.pw = pw;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

       public LoginDto build() {
          return new LoginDto(this);
        }

    }
}
