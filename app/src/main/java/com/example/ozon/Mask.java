package com.example.ozon;

import android.os.Parcel;
import android.os.Parcelable;

public class Mask implements Parcelable {
    private int id;
    private String name;
    private float price;
    private String weight;
    private String nameProiz;
    private String countryProiz;
    private String image;


    
    public Mask(int ID, String Name, float Price, String Weight, String NameProiz,
                String CountryProiz, String Image) {
        this.id = ID;
        this.name = Name;
        this.price = Price;
        this.weight = Weight;
        this. nameProiz = NameProiz;
        this.countryProiz = CountryProiz;
        this.image = Image;


    }

    protected Mask(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readFloat();
        weight = in.readString();
        nameProiz = in.readString();
        countryProiz = in.readString();
        image = in.readString();

    }

    public static final Creator<Mask> CREATOR = new Creator<Mask>() {
        @Override
        public Mask createFromParcel(Parcel in) {
            return new Mask(in);
        }

        @Override
        public Mask[] newArray(int size) {
            return new Mask[size];
        }
    };


    public void setID(int ID) {
        this.id = ID;
    }

    public void setName(String Name) {
        name = Name;
    }

    public void setPrice(float Price) {
        this.price = Price;
    }

    public void setWeight(String Weight) {
        weight = Weight;
    }

    public void setNameProiz(String NameProiz) { nameProiz = NameProiz;}

    public void setCountryProiz(String CountryProiz) { countryProiz = CountryProiz;}



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeString(weight);
        parcel.writeString(nameProiz);
        parcel.writeString(countryProiz);

    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getWeight() {
        return weight;
    }

    public String getNameProiz() {
        return nameProiz;
    }

    public String getCountryProiz() { return countryProiz; }
    public String getImage() {
        return image;
    }


    public int getID() {
        return id;
    }

}
