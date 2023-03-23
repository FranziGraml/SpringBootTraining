package trainingSpringBoot.training.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor


public class ToDoCreateDTO {


    @NotBlank(message = "name is mandatory")
    private final String title;

    @NotBlank(message = "name is mandatory")
    private final String description;


   @NotNull
    private final Boolean status = false;

}
