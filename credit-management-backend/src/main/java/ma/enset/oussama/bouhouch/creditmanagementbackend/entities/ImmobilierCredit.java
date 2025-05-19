package ma.enset.oussama.bouhouch.creditmanagementbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.PropertyType;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImmobilierCredit extends Credit {
    private PropertyType type;
}