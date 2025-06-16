package org.example.triviadexbackend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_guesses")
public class DailyGuess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;


    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }  // 🔁
    public void setDate(LocalDate date) { this.date = date; }  // 🔁

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Character getCharacter() { return character; }
    public void setCharacter(Character character) { this.character = character; }
}
