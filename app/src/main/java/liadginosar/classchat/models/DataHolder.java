package liadginosar.classchat.models;

/**
 * Created by liadginosar on 03/04/2018.
 */

public class DataHolder {
    private String data;
    public String getData() {return data;}
    public void setData(String data) {this.data = data;}

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}
}