package com.example.phongthuy;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private String name;
    private String description;
    private Uri imageUri;
    private int price;
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    public Product() {
    }

    public Product(String name, String description, Uri imageUrl, int price) {
        this.name = name;
        this.description = description;
        this.imageUri = imageUrl;
        this.price = price;
    }

    public Product(Parcel source) {
        name = source.readString();
        description = source.readString();
        price = source.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUrl) {
        this.imageUri = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeDouble(price);
    }
}
