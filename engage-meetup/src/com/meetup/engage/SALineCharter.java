package com.meetup.engage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class SALineCharter extends Application {

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis(0,20,1);
    final StackedAreaChart<String, Number> sac = new StackedAreaChart<String, Number>(xAxis, yAxis);

	// JavaFX does not have a DateAxis... Quick workaround to get X-axis aligned.
    final static int YEAR_BEGIN = 2010;
    final static int YEAR_END = 2016;
    
	@Override
    public void start(Stage stage) {
        final Parameters params = getParameters();
        final List<String> parameters = params.getRaw();
 
        String[] evntArr = parameters.get(0).split("\\|");
        Arrays.sort(evntArr);

        String[] grpArr = parameters.get(1).split("\\|");
        Arrays.sort(grpArr);

        stage.setTitle("Count of Comments");
        sac.setTitle("Number of Comments over Time");
        
    	XYChart.Series<String, Number> seriesEvent = new XYChart.Series<String, Number>();
		seriesEvent.setName("Event Comments");
		Map<String, Number> evntMap = new HashMap<String, Number>();
    	for (int i = 1;i<evntArr.length;i++) {
			String[] keyValue = evntArr[i].split(",");
			evntMap.put(keyValue[0],Float.parseFloat(keyValue[1]));
    	}

		XYChart.Series<String, Number> seriesGroup = new XYChart.Series<String, Number>();
        seriesGroup.setName("Group Comments");
        Map<String, Number> grpMap = new HashMap<String, Number>();;
    	for (int i = 1;i<grpArr.length;i++) {
			String[] keyValue = grpArr[i].split(",");
			grpMap.put(keyValue[0],Float.parseFloat(keyValue[1]));			
    	}
    	
    	for (int i = YEAR_BEGIN; i<YEAR_END; i++) {
        	for (int j = 1;j<=12;j++) {
        		String dateYYYY = new Integer(i).toString();
        		String dateMM = new Integer(j).toString();
        		if (dateMM.length() == 1) {
        			dateMM = "0" + dateMM;
        		}
        		String category = dateYYYY + "-" + dateMM;
        		
        		Number evntCnt = evntMap.get(category);
        		if(evntCnt == null) {
        			seriesEvent.getData().add(new XYChart.Data<String, Number>(category,0));
        		} else {
        			seriesEvent.getData().add(new XYChart.Data<String, Number>(category,evntCnt));
        		}
        		
        		Number grpCnt = grpMap.get(category);
        		if(grpCnt == null) {
        			seriesGroup.getData().add(new XYChart.Data<String, Number>(category,0));
        		} else {
        			seriesGroup.getData().add(new XYChart.Data<String, Number>(category,grpCnt));
        		}        		
        	}
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