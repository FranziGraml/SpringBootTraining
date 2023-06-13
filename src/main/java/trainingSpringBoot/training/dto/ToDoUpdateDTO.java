package trainingSpringBoot.training.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@RequiredArgsConstructor

public class ToDoUpdateDTO {

    @NotNull
    @Positive
    private  Long id;

    @NotNull
    private  Boolean status = false;

    @NotBlank
    private String title;
    @NotBlank
    private  String description;


}
