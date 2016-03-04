package app.playmate;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SFRIAS on 03/03/2016.
 */
public class CompetitorImage implements Serializable, Parcelable {


    private int id;
    private String imageLink;
    private Date lastUpdate;
    private Boolean deleted;


    public CompetitorImage(){

    }


    public CompetitorImage(JSONObject imagen){

        try{

            setId(imagen.getInt("id"));
            setImageLink(imagen.getString("imageLink"));
            setLastUpdate(new Date(imagen.getLong("lastUpdate")));
            setDeleted(imagen.getBoolean("deleted"));

        }catch (Exception e){


        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imageLink);
        dest.writeLong(lastUpdate != null ? lastUpdate.getTime() : -1);
        dest.writeValue(this.deleted);
    }

    protected CompetitorImage(Parcel in) {
        this.id = in.readInt();
        this.imageLink = in.readString();
        long tmpLastUpdate = in.readLong();
        this.lastUpdate = tmpLastUpdate == -1 ? null : new Date(tmpLastUpdate);
        this.deleted = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CompetitorImage> CREATOR = new Parcelable.Creator<CompetitorImage>() {
        public CompetitorImage createFromParcel(Parcel source) {
            return new CompetitorImage(source);
        }

        public CompetitorImage[] newArray(int size) {
            return new CompetitorImage[size];
        }
    };
}
