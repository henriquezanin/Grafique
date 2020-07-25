package model.Api;

public class BacenTimeserie {
    public String data;
    public float valor;

    @Override
    public String toString() {
        return "Timeseries{" +
                "data='" + data + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
