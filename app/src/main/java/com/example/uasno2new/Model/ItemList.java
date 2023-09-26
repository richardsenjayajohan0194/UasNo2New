package com.example.uasno2new.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemList implements Parcelable{

        private Integer _idItem;

        private int _imageSource;

        private String _nameItemAndSize;

        private Integer _price;

        public ItemList(Integer _idItem, int _imageSource, String _nameItemAndSize, Integer _price){
            this._idItem = _idItem;
            this._imageSource = _imageSource;
            this._nameItemAndSize = _nameItemAndSize;
            this._price = _price;
        }

        public int get_idItem(){
            return  _idItem;
        }

        public void set_idItem(int _idItem){
            this._idItem = _idItem;
        }

        public int get_imageSource(){
            return _imageSource;
        }

        public void set_imageSource(int _imageSource){
            this._imageSource = _imageSource;
        }

        public String get_nameItemAndSize(){
            return _nameItemAndSize;
        }

        public void set_nameItemAndSize(String _nameItemAndSize){ this._nameItemAndSize = _nameItemAndSize; }

        public Integer get_price(){
            return _price;
        }

        public void set_price(Integer _price){
            this._price = _price;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._idItem);
        dest.writeValue(this._imageSource);
        dest.writeString(this._nameItemAndSize);
        dest.writeValue(this._price);
    }

    public void readFromParcel(Parcel source) {
        this._idItem= (Integer) source.readValue(Integer.class.getClassLoader());
        this._imageSource = (Integer) source.readValue(Integer.class.getClassLoader());
        this._nameItemAndSize= source.readString();
        this._price = (Integer) source.readValue(Integer.class.getClassLoader());
    }

    protected ItemList(Parcel in) {
        this._idItem = (Integer) in.readValue(Integer.class.getClassLoader());
        this._imageSource = (Integer) in.readValue(Integer.class.getClassLoader());
        this._nameItemAndSize = in.readString();
        this._price = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ItemList> CREATOR = new Parcelable.Creator<ItemList>() {
        @Override
        public ItemList createFromParcel(Parcel source) {
            return new ItemList(source);
        }

        @Override
        public ItemList[] newArray(int size) {
            return new ItemList[size];
        }
    };
}
