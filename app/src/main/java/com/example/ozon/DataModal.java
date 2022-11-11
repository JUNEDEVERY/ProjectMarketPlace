package com.example.ozon;

public class DataModal {
    private String name;
    private float price;
    private String weight;
    private String nameProiz;
    private String countryProiz;
    private String picture;

    public DataModal(String Name,  float Price,  String Weight, String NameProiz,  String CountryProiz,  String Image) {
        this.name = Name;
        this.weight = Weight;
        this.nameProiz = NameProiz;
        this.countryProiz = CountryProiz;
        this.price = Price;
        this.picture = Image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(String Weight) {
        this.weight = Weight;
    }

    public void setNameProiz(String NameProiz) {
        this.nameProiz = NameProiz;
    }

    public void setCountryProiz(String CountryProiz) {
        this.countryProiz = CountryProiz;
    }

    public void setPrice(float Price) {
        this.price = Price;
    }

    public void setImage(String Image) {
        this.picture = Image;
    }




    public String getName() { return name; }

    public String getWeight() {
        return weight;
    }

    public String getNameProiz() {
        return nameProiz;
    }
    public String getCountryProiz() {
        return countryProiz;
    }

    public Float getPrice() {
        return price;
    }

    public String getImage() {
        return picture;
    }
}
