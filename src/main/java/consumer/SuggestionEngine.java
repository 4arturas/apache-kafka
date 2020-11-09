package consumer;

import lombok.extern.slf4j.Slf4j;
import producer.models.PreferedProduct;
import producer.models.User;
import producer.service.UserDB;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SuggestionEngine
{
    private UserDB userDB = new UserDB();
    public void processSuggestions( String userId, String product )
    {
        String valueSplit[] = product.split(",");
        String productType = valueSplit[0];
        String productColor = valueSplit[1];
        String productDesign = valueSplit[2];

        log.info( "User with ID: " + userId + " show interest over " + productType + " of color " + productColor + " " +
                "and design " + productDesign );

        User user = userDB.findById( userId );
        user.getPreferedProducts().add( new PreferedProduct( productColor, productType, productDesign ) );
        user.setSuggestions( generateSugestions( user.getPreferedProducts() ) );

        userDB.save( user );
    }

    private List<String> generateSugestions( List<PreferedProduct> preferedProductList )
    {
        return Arrays.asList( "TSHIRT,BLUE", "DESIGN,ORANGE,ROCKET", "TSHIRT,PURPLE,CAR" );
    }
}
