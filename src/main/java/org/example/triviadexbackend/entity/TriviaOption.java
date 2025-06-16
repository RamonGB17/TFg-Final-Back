package org.example.triviadexbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trivia_options")
public class TriviaOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trivia_id", nullable = false)
    private Trivia trivia;

    @Column(name = "option_text", nullable = false)
    private String optionText;

    // Constructor vacío
    public TriviaOption() {
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trivia getTrivia() {
        return trivia;
    }

    public void setTrivia(Trivia trivia) {
        this.trivia = trivia;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
