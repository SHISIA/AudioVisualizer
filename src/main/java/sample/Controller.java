package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ProgressBar pr2;
    public ProgressBar pr3;
    public ProgressBar pr4;
    public ProgressBar pr8;
    public ProgressBar pr7;
    public ProgressBar pr6;
    public ProgressBar pr5;
    public ProgressBar pr1;
    public Label song;
    public TextField songLink;
    public Button load;


    private Double magnitudeOffset=65.0;
    private Media media;
    private MediaPlayer mediaPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpFileChoosing();
    }

    public void setUI(){
        if (!songLink.getText().isEmpty()){
            media=new Media(new File(songLink.getText()).toURI().toString());
            mediaPlayer=new MediaPlayer(media);
            mediaPlayer.setVolume(10);
            mediaPlayer.setAudioSpectrumNumBands(8);
            mediaPlayer.setAudioSpectrumInterval(0.01);
            mediaPlayer.setAudioSpectrumListener((double timestamp,double duration,float[] magnitudes,float[] phase)->{

                if (magnitudes.length>=8 && phase.length>=8){
                    pr5.setPrefWidth(magnitudes[0]+magnitudeOffset);
                    pr2.setPrefWidth(magnitudes[1]+magnitudeOffset);
                    pr3.setPrefWidth(magnitudes[2]+magnitudeOffset);
                    pr4.setPrefWidth(magnitudes[3]+magnitudeOffset);
                    pr1.setPrefWidth(magnitudes[4]+magnitudeOffset);
                    pr6.setPrefWidth(magnitudes[5]+magnitudeOffset);
                    pr7.setPrefWidth(magnitudes[6]+magnitudeOffset);
                    pr8.setPrefWidth(magnitudes[7]+magnitudeOffset);
                }
            });
        }
    }

    public void setUpFileChoosing(){
       load.setOnAction(e->{
           FileChooser fileChooser=new FileChooser();
           File file = fileChooser.showOpenDialog(load.getScene().getWindow());
           fileChooser.setTitle("View Media");
           fileChooser.setInitialDirectory(
                   new File(System.getProperty("user.home"))
           );
           fileChooser.getExtensionFilters().addAll(
                   new FileChooser.ExtensionFilter("All Files", "*.*"),
                   new FileChooser.ExtensionFilter("HQ Music/Videos", "*.mp4"),
                   new FileChooser.ExtensionFilter("Music", "*.mp3")
           );
           if (file!=null){
               String fName=file.toString();
               songLink.setText(fName);
               song.setText(file.getName());
           }
       });
    }

    public void play(){
        setUI();
        if (!(mediaPlayer==null&& songLink.getText().isEmpty())){
            mediaPlayer.play();
        }
    }
}
