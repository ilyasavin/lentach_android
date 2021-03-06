
package com.lentach.api.models.wallpost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Reposts implements Parcelable {

    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     *
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }


    protected Reposts(Parcel in) {
        count = in.readByte() == 0x00 ? null : in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (count == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(count);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<Reposts> CREATOR = new Creator<Reposts>() {
        @Override
        public Reposts createFromParcel(Parcel in) {
            return new Reposts(in);
        }

        @Override
        public Reposts[] newArray(int size) {
            return new Reposts[size];
        }
    };
}
