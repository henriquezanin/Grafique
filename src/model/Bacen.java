package model;
import model.Api.Api;
import model.Api.BacenTimeserie;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Bacen extends GraphData {

    private String datasetCode;
    private List<BacenTimeserie> data = null;

    public Bacen(int datasetCode){
        this.datasetCode = Integer.toString(datasetCode);
        this.url = "https://api.bcb.gov.br/dados/serie/bcdata.sgs."+datasetCode+"/dados?formato=json";
    }

    public void setStartDate(LocalDate startDate){
        if(startDate == null)
            return;
        String formattedDate = startDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        this.url += "&dataInicial="+formattedDate;
    }

    public void setEndDate(LocalDate endDate){
        if(endDate == null)
            return;
        String formattedDate = endDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        this.url += "&dataFinal="+formattedDate;
    }

    public void setName(String serieName) {
        this.name = serieName;
    }

    @Override
    public ArrayList<?> getData() throws IOException, InterruptedException {
        initializeApi();
        ArrayList<BacenTimeserie> newData = new ArrayList<>();
        for(BacenTimeserie e : data){
            newData.add(e);
        }
        return newData;
    }

    private Api initializeApi() throws IOException, InterruptedException {
        Api api = new Api(url);
        api.initialize();
        this.data = api.convertResponse(BacenTimeserie.class);
        System.out.println(url);
        return api;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String[] getXAxis(){
        if(data ==  null){
            try{
                initializeApi();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] xAxis = new String[data.size()];
        for(int i = 0; i < data.size(); i++){
            xAxis[i] = data.get(i).data;
        }
        return xAxis;
    }

    @Override
    public float[] getYAxis() {
        if (data == null) {
            try{
                initializeApi();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        float[] yAxis = new float[data.size()];
        for (int i = 0; i < data.size(); i++) {
            yAxis[i] = data.get(i).valor;
        }
        return yAxis;
    }
}

