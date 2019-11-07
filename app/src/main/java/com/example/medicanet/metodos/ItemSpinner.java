package com.example.medicanet.metodos;

public class ItemSpinner {
    private int img;
    private String campo1,campo2,campo3,campo4;

    public ItemSpinner(int img, String campo1, String campo2, String campo3, String campo4){
        this.img=img;
        this.campo1=campo1;
        this.campo2=campo2;
        this.campo3=campo3;
        this.campo4=campo4;
    }

    public int getImg() {
        return img;
    }

    public String getCampo1() {
        return campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public String getCampo3() {
        return campo3;
    }

    public String getCampo4() {
        return campo4;
    }
}
