package lambdasinaction.chap1;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@Builder
public class Car {
    @NonNull
    private String brand;
    @NonNull
    private String type;
    private String colour;
    private int ccSize;
    private CarSize size;
}
