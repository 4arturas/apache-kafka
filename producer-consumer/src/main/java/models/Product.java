package models;

import enums.Color;
import enums.DesignType;
import enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product
{
    private Color color;
    private ProductType type;
    private DesignType designType;
}
