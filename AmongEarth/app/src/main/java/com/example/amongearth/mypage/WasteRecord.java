package com.example.amongearth.mypage;

import java.io.Serializable;

public class WasteRecord implements Serializable {
    public float glass, metal, none, paper, plastic, waste, total;
    public String date;

    public WasteRecord() {
    }
    public WasteRecord(float glass, float metal, float none, float paper, float plastic, float waste, float total, String date) {
        this.glass = glass;
        this.metal = metal;
        this.none = none;
        this.paper = paper;
        this.plastic = plastic;
        this.waste = waste;
        this.total = total;
        this.date = date;
    }
}
