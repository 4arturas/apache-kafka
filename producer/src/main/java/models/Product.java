package models;

import lombok.Builder;
import lombok.Data;
import enums.Color;
import enums.DesignType;
import enums.ProductType;

@Data
@Builder
public class Product
{
    private Color color;
    private ProductType type;
    private DesignType designType;
}
