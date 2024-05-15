package com.konyvesbolt.konyvek_beadando.model;

import jakarta.persistence.*;

@Entity
@Table(name ="books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "books_id")
    private Long id;

    @Column(name = "books_title")
    private String titles;

    @Column(name = "books_author")
    private String author;

    @Column(name = "books_released")
    private int released;

    @Column(name = "books_genre")
    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BookEntity() {}

    public BookEntity(String titles, String author, int released, String genre) {
        this.titles = titles;
        this.author = author;
        this.released = released;
        this.genre = genre;
    }
}