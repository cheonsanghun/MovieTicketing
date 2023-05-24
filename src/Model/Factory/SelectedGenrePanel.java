/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

/**
 *
 * @author USER
 */
import java.lang.String;

public class SelectedGenrePanel extends GenrePanel {
    private String selectedTheater;
    
    public SelectedGenrePanel(String theaterName) {
        super(theaterName);
        this.selectedTheater = theaterName;
    }


    @Override
    public TheaterPanel createTheaterPanel() {
        return null;
    }

    @Override
    public GenrePanel createGenrePanel() { 
        return null; 
    }

    @Override
    public MoviePanel createMoviePanel() {
        return null;
    }

    @Override
    public SeatPanel creatSeatPanel() {
        return null;
    }

    @Override
    public PayPanel creatPayPanel() {
        return null;
    }


}
