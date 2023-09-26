package com.example.uasno2new.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ItemHistory {

    private Integer _IdHistory;

    private Integer _IdUser;

    private ItemList _Item;

    private LocalDate Date;

    private LocalTime Time;

    public ItemHistory(Integer _IdHistory, Integer _IdUser, ItemList _Item, LocalDate Date, LocalTime Time){
        this._IdHistory = _IdHistory;
        this._IdUser = _IdUser;
        this._Item = _Item;
        this.Date = Date;
        this.Time = Time;
    }


    public Integer get_IdHistory(){
        return  _IdHistory;
    }

    public void set_IdHistory(int _IdHistory){
        this._IdHistory = _IdHistory;
    }

    public int get_IdUser(){
        return  _IdUser;
    }

    public void set_IdUser(int _IdUser){
        this._IdUser = _IdUser;
    }

    public ItemList get_Item(){
        return _Item;
    }

    public void set_Item(ItemList _Item){
        this._Item = _Item;
    }

    public LocalDate get_Date() { return Date; }

    public void set_Date(LocalDate localDate) {
        this.Date = Date;
    }

    public LocalTime get_Time() { return Time; }

    public void set_Time(LocalDate localDate) {
        this.Time = Time;
    }
}
