package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PreferedProduct
{
    private String color;
    private String type;
    private String designType;
}
