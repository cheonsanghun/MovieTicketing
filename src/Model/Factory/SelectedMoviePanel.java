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


public class SelectedMoviePanel extends MoviePanel {
    private String selectedGenre;
    private String selectedTheater;
    
    public SelectedMoviePanel( String theaterName ,String genreName) {
        
        super(theaterName,genreName);
        this.selectedTheater = theaterName;
        this.selectedGenre = genreName;
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
