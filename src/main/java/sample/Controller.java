package sample;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final Duration translate_Dur=Duration.seconds(0.25);

    public Circle circle;
    public Circle circle1;
    public Circle circle2;
    public Circle circle3;
    public Circle circle4;
    public Circle circle5;
    public Circle circle6;
    public Circle circles;
    public Circle circleb;
    public ProgressBar pr2;
    public ProgressBar pr3;
    public ProgressBar pr4;
    public ProgressBar pr8;
    public ProgressBar pr7;
    public ProgressBar pr6;
    public ProgressBar pr5;
    public ProgressBar pr1;


    private Double phaseMultiplier=360.0;
    private Double magnitudeOffset=65.0;
    private Media media;
    private MediaPlayer mediaPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUI();
    }

    public void setUI(){
        media=new Media(new File("D:\\WIN11\\Video\\Ikson - Lights (Official).mp4").toURI().toString());
        mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setAudioSpectrumNumBands(8);
        mediaPlayer.setAudioSpectrumInterval(0.01);
        mediaPlayer.setAudioSpectrumListener((double timestamp,double duration,float[] magnitudes,float[] phase)->{

            if (magnitudes.length>=8 && phase.length>=8){
                System.out.println("Timestamp: "+timestamp);
                System.out.println("Duration: "+duration);
                System.out.println("Magnitudes: "+magnitudes[0]+", "+magnitudes[1]
                +", "+magnitudes[2]+", "+magnitudes[3]);
                System.out.println("Phases: "+phase[0]+", "+phase[1]
                        +", "+phase[2]+", "+phase[3]);

                pr5.setPrefWidth(magnitudes[0]+magnitudeOffset);
                pr2.setPrefWidth(magnitudes[1]+magnitudeOffset);
                pr3.setPrefWidth(magnitudes[2]+magnitudeOffset);
                pr4.setPrefWidth(magnitudes[3]+magnitudeOffset);
                pr1.setPrefWidth(magnitudes[4]+magnitudeOffset);
                pr6.setPrefWidth(magnitudes[5]+magnitudeOffset);
                pr7.setPrefWidth(magnitudes[6]+magnitudeOffset);
                pr8.setPrefWidth(magnitudes[7]+magnitudeOffset);

                NumberAxis xaxis = new NumberAxis(35,105,5);
                NumberAxis yaxis = new NumberAxis(1,2.2,0.1);
                xaxis.setLabel("Weight");
                yaxis.setLabel("Height");

                //Configuring ScatterChart
                ScatterChart s = new ScatterChart(xaxis,yaxis);
                s.setTitle("Perfect height according to your weight");

                //Configuring Series and adding data to the series
                XYChart.Series series = new XYChart.Series();
                series.setName("Height value");
                series.getData().add(new XYChart.Data(40,1.27));
                series.getData().add(new XYChart.Data(45,1.35));
                series.getData().add(new XYChart.Data(50,1.42));
                series.getData().add(new XYChart.Data(55,1.49));

            }
        });
    }

    public void play(){
        mediaPlayer.play();
    }

}
