/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author kjbg4
 */
public class MyPageDto {

    private int s_row;
    private int s_col;
    private int t_id;
    private int g_id;
    private int m_id;
    private int cardnum;

    public int getS_row() {
        return s_row;
    }

    public int getS_col() {
        return s_col;
    }

    public int getT_id() {
        return t_id;
    }

    public int getG_id() {
        return g_id;
    }

    public int getM_id() {
        return m_id;
    }

    public int getCardnum() {
        return cardnum;
    }

    public MyPageDto() {
        super();
    }

    public void setcardnum(int cardnum) {
        this.cardnum = cardnum;

    }

    public void setg_id(int g_id) {
        this.g_id = g_id;

    }

    public void setm_id(int m_id) {
        this.m_id = m_id;

    }

    public void sets_col(int s_col) {
        this.s_col = s_col;
    }

    public void sets_row(int s_row) {
        this.s_row = s_row;
    }

    public void sett_id(int t_id) {
        this.t_id = t_id;
    }

    //ºô´õ ÆĞÅÏ
    private MyPageDto(Builder builder) {
        super();
        this.cardnum = builder.cardnum;
        this.g_id = builder.g_id;
        this.m_id = builder.m_id;
        this.s_col = builder.s_col;
        this.s_row = builder.s_row;
        this.t_id = builder.t_id;

    }

    public static class Builder {

        private int s_row;
        private int s_col;
        private int t_id;
        private int g_id;
        private int m_id;
        private int cardnum;

        public Builder() {
        }

        public Builder sets_row(int s_row) {
            this.s_row = s_row;
            return this;
        }

        public Builder sets_col(int s_col) {
            this.s_col = s_col;
            return this;
        }

        public Builder sett_id(int t_id) {
            this.t_id = t_id;
            return this;
        }

        public Builder setg_id(int g_id) {
            this.g_id = g_id;
            return this;
        }

        public Builder setm_id(int m_id) {
            this.m_id = m_id;
            return this;
        }

        public Builder setcardnum(int cardnum) {
            this.cardnum = cardnum;
            return this;
        }

        public MyPageDto build() {
            return new MyPageDto(this);
        }

    }
}
