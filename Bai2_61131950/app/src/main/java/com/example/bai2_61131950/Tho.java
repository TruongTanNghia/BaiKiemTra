package com.example.bai2_61131950;

public class Tho {
    private int id ;
    private String tenBaiTho ;
    private String tenTacGia ;

    public Tho() {
    }

    public Tho(int id, String tenBaiTho, String tenTacGia) {
        this.id = id;
        this.tenBaiTho = tenBaiTho;
        this.tenTacGia = tenTacGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBaiTho() {
        return tenBaiTho;
    }

    public void setTenBaiTho(String tenBaiTho) {
        this.tenBaiTho = tenBaiTho;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }
}
