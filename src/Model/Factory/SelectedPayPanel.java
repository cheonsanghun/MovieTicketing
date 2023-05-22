/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

import java.awt.event.ActionListener;

/**
 *
 * @author USER
 */
public class SelectedPayPanel extends PayPanel{
    private String selectedGenre;
    private String selectedTheater;
    private String selectedMovie;
    private String selectedSeat;
    
    public SelectedPayPanel(String theaterName, String genreName, String movieName, String seatMarking) {
    super(theaterName, genreName, movieName, seatMarking);
    this.selectedTheater = theaterName;
    this.selectedGenre = genreName;
    this.selectedMovie = movieName;
    this.selectedSeat = seatMarking;
}
    
}
