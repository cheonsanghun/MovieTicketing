/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

/**
 *
 * @author USER
 */
public class SelectedSeatPanel extends SeatPanel{
    private String selectedGenre;
    private String selectedTheater;
    private String selectedMovie;
    
    
    public SelectedSeatPanel(String theaterName, String genreName , String movieName) {
        super(theaterName,genreName,movieName);
        this.selectedTheater = theaterName;
        this.selectedGenre = genreName;
        this.selectedMovie = movieName;
    }



    public TheaterPanel createTheaterPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public GenrePanel createGenrePanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public MoviePanel createMoviePanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public SeatPanel creatSeatPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
