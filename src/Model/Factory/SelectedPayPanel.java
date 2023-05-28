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
    private int selectedRow;
    private int selectedCol;
    
    public SelectedPayPanel(String theaterName, String genreName, String movieName, int seatRow , int seatCol) {
    super(theaterName, genreName, movieName, seatRow,  seatCol);
    this.selectedTheater = theaterName;
    this.selectedGenre = genreName;
    this.selectedMovie = movieName;
    this.selectedRow = seatRow;
    this.selectedCol = seatCol;
    }
    
}
