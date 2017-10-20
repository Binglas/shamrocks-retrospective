package com.sourcedev.joaozao.retrospective.model;

import android.os.Parcel;

/**
 * Created by joaozao on 27/01/17.
 */

public class ShamrockersModel implements android.os.Parcelable {

    private String name;
    private String description;
    private int thumbnail;
    private boolean mIsReady;

    public ShamrockersModel() {
    }


    public ShamrockersModel(String name, String description, int thumbnail) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        mIsReady = false;
    }


    public static final Creator<ShamrockersModel> CREATOR = new Creator<ShamrockersModel>() {
        @Override
        public ShamrockersModel createFromParcel(Parcel in) {
            return new ShamrockersModel(in);
        }

        @Override
        public ShamrockersModel[] newArray(int size) {
            return new ShamrockersModel[size];
        }
    };

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

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }


    public boolean isReady() {
        return mIsReady;
    }

    public void setReady(boolean ready) {
        mIsReady = ready;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.thumbnail);
        dest.writeByte(this.mIsReady ? (byte) 1 : (byte) 0);
    }

    protected ShamrockersModel(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.thumbnail = in.readInt();
        this.mIsReady = in.readByte() != 0;
    }

}
