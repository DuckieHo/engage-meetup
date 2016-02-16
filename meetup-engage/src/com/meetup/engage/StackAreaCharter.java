package com.meetup.engage;

import java.util.Date;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import extfx.scene.chart.DateAxis;;


public class StackAreaCharter extends Application {
	
    final NumberAxis xAxis = new NumberAxis(1, 31, 1);
    final NumberAxis yAxis = new NumberAxis();
    final StackedAreaChart<Number, Number> sac = new StackedAreaChart<Number, Number>(xAxis, yAxis);
	
    public void start(Stage stage) {
        stage.setTitle("Count of Comments");
        sac.setTitle("Number of Comments over Time");
        XYChart.Series<Number, Number> seriesGroup = new XYChart.Series<Number, Number>();
        seriesGroup.setName("Group Comments");       
        for () {
        	seriesGroup.getData().add(new XYChart.Data(1, 4));
        }
        
        XYChart.Series<Number, Number> seriesEvent = new XYChart.Series<Number, Number>();
        seriesEvent.setName("Event Comments");
        for () {
        	seriesEvent.getData().add(new XYChart.Data(1, 20));
        }
        
        
        Scene scene = new Scene(sac, 1000, 800);
        sac.getData().add(seriesGroup);
        sac.getData().add(seriesEvent);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}