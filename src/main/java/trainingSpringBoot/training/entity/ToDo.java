package trainingSpringBoot.training.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor    //leerer Konstruktor (deault Konstruktor)
@AllArgsConstructor   // alle Argumente (Attribute) die im Konstruktor wären
@Getter
@Setter
@Entity              //Zeichnet die Klasse als Entität aus
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //legt die ID als Primärschlüssel fest

    private Long id;


    private String title;
    private String description;
    private Boolean status;



}
