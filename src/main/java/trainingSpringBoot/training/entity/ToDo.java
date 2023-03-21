package trainingSpringBoot.training.entity;


import jakarta.persistence.*;

@Entity
public class ToDo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String description;
    private String date;

    public ToDo() {
    }
}
