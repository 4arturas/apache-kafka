package models;

import enums.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    public User( UserId userId )
    {
        this.userId = userId;
        this.preferedProducts = new ArrayList<>();
        this.suggestions = new ArrayList<>();
    }
    private UserId userId;
    private String userName;
    private Date dateOfBirth;
    private List<PreferedProduct> preferedProducts;
    private List<String> suggestions;
}
