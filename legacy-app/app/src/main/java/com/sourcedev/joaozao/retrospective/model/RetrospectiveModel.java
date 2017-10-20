package com.sourcedev.joaozao.retrospective.model;

import android.os.Parcel;

/**
 * Created by joaozao on 27/01/17.
 */

public class RetrospectiveModel extends ShamrockersModel{

  private String mMinusPlusPhrase;
  private String mID;
  private boolean mPlus;

  public RetrospectiveModel() {

  }

  public RetrospectiveModel(String minusPlusPhrase, boolean plus) {
    mMinusPlusPhrase = minusPlusPhrase;
    mPlus = plus;
  }


  public String getMinusPlusPhrase() {
    return mMinusPlusPhrase;
  }

  public void setMinusPlusPhrase(String minusPlusPhrase) {
    mMinusPlusPhrase = minusPlusPhrase;
  }

  public boolean isPlus() {
    return mPlus;
  }

  public void setPlus(boolean plus) {
    mPlus = plus;
  }

  public String getID() {
    return mID;
  }

  public void setID(String ID) {
    mID = ID;
  }


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
    dest.writeString(this.mMinusPlusPhrase);
    dest.writeString(this.mID);
    dest.writeByte(this.mPlus ? (byte) 1 : (byte) 0);
  }

  protected RetrospectiveModel(Parcel in) {
    super(in);
    this.mMinusPlusPhrase = in.readString();
    this.mID = in.readString();
    this.mPlus = in.readByte() != 0;
  }

  public static final Creator<RetrospectiveModel> CREATOR = new Creator<RetrospectiveModel>() {
    @Override
    public RetrospectiveModel createFromParcel(Parcel source) {
      return new RetrospectiveModel(source);
    }

    @Override
    public RetrospectiveModel[] newArray(int size) {
      return new RetrospectiveModel[size];
    }
  };
}
