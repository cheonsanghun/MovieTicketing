/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

/**
 *
 * @author USER
 */
public interface MovieFactory {
    TheaterPanel createTheaterPanel();
    GenrePanel createGenrePanel();
    MoviePanel createMoviePanel();
    SeatPanel creatSeatPanel();
    PayPanel creatPayPanel();
}
