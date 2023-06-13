package trainingSpringBoot.training.dto;



import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor


public class ToDoCreateDTO {


    @NotBlank
    private final String title;

    @NotBlank
    private final String description;


   @NotNull
    private final Boolean status = false;

}
