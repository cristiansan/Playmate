package app.playmate;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Competitor implements Serializable, Parcelable {


	private int id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
    private String pais;
    private String biografia;
    private String mail;
    private String user2;

    private CompetitorImage[] imagenes;

    //hola

	public Competitor(){
		
	}

    public Competitor(JSONObject jsonObject){

            try{

                setId(jsonObject.getInt("id"));
                setNombre(jsonObject.getString("nombre"));
                setApellido(jsonObject.getString("apellido"));
                setFechaNacimiento(new Date(jsonObject.getLong("fechaNacimiento")));
                setPais(jsonObject.getString("pais"));
                setBiografia(jsonObject.getString("biografia"));

                JSONArray jsonArray=jsonObject.getJSONArray("imagenes");
                imagenes= new CompetitorImage[jsonArray.length()];

                for (int i =0;i<jsonArray.length();i++){
                    JSONObject imagen= jsonArray.getJSONObject(i);
                    CompetitorImage competitorImage= new CompetitorImage(imagen);
                    imagenes[i]= competitorImage;
                }

            }catch (Exception e){
                Log.d("Competitor",e.getLocalizedMessage());

            }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public CompetitorImage[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(CompetitorImage[] imagenes) {
        this.imagenes = imagenes;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Override
	public String toString() {
		return "id: "+ id +" nombre: "+ nombre + " apellido: "+ apellido+ " fechaNacimiento: "+ fechaNacimiento+ " pais: "+ pais+ " biografia: "+ biografia+ " imagenes: "+ imagenes;
	}

    public JSONObject toJSon() throws JSONException{
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("nombre", nombre);
        jsonObject.put("apellido",apellido );
        jsonObject.put("fechaNacimiento",fechaNacimiento.getTime() );
        jsonObject.put("paisId", pais );
        jsonObject.put("biografia", biografia );
        jsonObject.put("aplicacionName","playmate");
        jsonObject.put("mail",mail);
        jsonObject.put("user2",user2);

        return jsonObject;


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeLong(fechaNacimiento != null ? fechaNacimiento.getTime() : -1);
        dest.writeString(this.pais);
        dest.writeString(this.biografia);
        //dest.writeParcelableArray(this.imagenes, 0);
        dest.writeTypedArray(this.imagenes, 0);
    }

    protected Competitor(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.apellido = in.readString();
        long tmpFechaNacimiento = in.readLong();
        this.fechaNacimiento = tmpFechaNacimiento == -1 ? null : new Date(tmpFechaNacimiento);
        this.pais = in.readString();
        this.biografia = in.readString();

        imagenes = in.createTypedArray(CompetitorImage.CREATOR);
        //this.imagenes = (CompetitorImage[]) in.readParcelableArray(CompetitorImage.class.getClassLoader());
    }

    public static final Parcelable.Creator<Competitor> CREATOR = new Parcelable.Creator<Competitor>() {
        public Competitor createFromParcel(Parcel source) {
            return new Competitor(source);
        }

        public Competitor[] newArray(int size) {
            return new Competitor[size];
        }
    };
}
