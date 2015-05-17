package com.agrimerkezi.mayitedavisi.util;

public class SaatlikTakip {
    public int no;
    public String saatAraligi;
    public int kristalloid;
    public int kolloid;

    //constructor
    public SaatlikTakip(int no, String saatAraligi, int kristalloid, int kolloid) {
        this.no = no;
        this.saatAraligi = saatAraligi;
        this.kristalloid = kristalloid;
        this.kolloid = kolloid;
    }
}