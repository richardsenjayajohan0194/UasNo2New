package com.example.uasno2new.Model;

public class ItemList {

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
}
