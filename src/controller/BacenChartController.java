package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import model.Bacen;
import model.GraphData;
import java.time.LocalDate;

public class BacenChartController {
    private LocalDate initialDate;
    private LocalDate endDate;
    @FXML
    private TextField textFieldDatasetCode;
    @FXML
    private Label labelDatasetCodeError;
    @FXML
    private TextField textFieldDatasetName;
    @FXML
    private CheckBox checkBoxEnableDateFilter;
    @FXML
    private DatePicker datePickerInitial;
    @FXML
    private DatePicker datePickerFinal;
    @FXML
    private Label labelInitialDatePickerError;
    @FXML
    private Label labelFinalDatePickerError;
    @FXML
    private TextField labelChart;
    @FXML
    private TextField labelX;
    @FXML
    private TextField labelY;
    @FXML
    private Button buttonAddChart;
    @FXML
    private Button buttonClean;
    @FXML
    private Button buttonDefineChartData;
    @FXML
    private Button buttonExport;
    @FXML
    private Button buttonBackToMain;
    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    protected void initialize(){
        checkBoxEnableDateFilter.setSelected(false);
        datePickerInitial.setDisable(true);
        datePickerFinal.setDisable(true);
    }

    @FXML
    protected void checkBoxEnableDateFilterAction(ActionEvent e){
        if(checkBoxEnableDateFilter.isSelected()){
            datePickerInitial.setDisable(false);
            datePickerFinal.setDisable(false);
        }
        else{
            datePickerInitial.setDisable(true);
            datePickerFinal.setDisable(true);
        }
    }

    private void setInitialDate(LocalDate date){
        initialDate = date;
    }

    private void setEndDate(LocalDate date){
        endDate = date;
    }

    private boolean validateDatePicker(){
        if(datePickerInitial.getValue() == null){
            labelInitialDatePickerError.setText("Insira a data inicial");
            return false;
        }
        else if(datePickerFinal.getValue() == null){
            labelInitialDatePickerError.setVisible(false);
            labelFinalDatePickerError.setText("Insira a data final");
            return false;
        }
        else{
            labelInitialDatePickerError.setVisible(false);
            labelFinalDatePickerError.setVisible(false);
            return true;
        }
    }

    @FXML
    protected void buttonAddChartAction(ActionEvent e){
        if(!validateDatasetCodeTextField(textFieldDatasetCode))
            return;
        if(checkBoxEnableDateFilter.isSelected()){
            if(!validateDatePicker())
                return;
            else{
                setInitialDate(datePickerInitial.getValue());
                setEndDate(datePickerFinal.getValue());
            }
        }
        else{
            setInitialDate(null);
            setEndDate(null);
        }
        addSerieOnChart();
    }

    private void addSerieOnChart(){
        Integer code = Integer.parseInt(textFieldDatasetCode.getText());
        GraphData bacen = new Bacen(code);
        bacen.setStartDate(initialDate);
        bacen.setEndDate(endDate);
        String[] xAxis = bacen.getXAxis();
        float[] yAxis = bacen.getYAxis();
        XYChart.Series series = new XYChart.Series();
        series.setName(textFieldDatasetName.getText());
        for (int i = 0; i < xAxis.length; i++) {
            series.getData().add(new XYChart.Data(xAxis[i], yAxis[i]));
        }
        lineChart.getXAxis().setAnimated(false);
        lineChart.getData().add(series);
    }

    @FXML
    private void setChartLabels(){
        lineChart.setTitle(labelChart.getText());
        lineChart.getXAxis().setLabel(labelX.getText());
        lineChart.getYAxis().setLabel(labelY.getText());
    }

    private boolean validateDatasetCodeTextField(TextField datasetCode){
        if(datasetCode.getText() != null) {
            if (datasetCode.getText().isEmpty()) {
                labelDatasetCodeError.setText("Insira o código do dataset");
                return false;
            }
            if(!datasetCode.getText().matches("[0-9]+")){
                labelDatasetCodeError.setText("Insira apenas números");
                return false;
            }
        }
        labelDatasetCodeError.setVisible(false);
        return true;
    }

    @FXML
    protected void buttonBackToMainAction(ActionEvent e){
        Main.changeScreen(Utils.Screens.MAIN);
    }

    @FXML
    protected void buttonCleanAction(ActionEvent e){
        lineChart.getData().clear();
    }

}
