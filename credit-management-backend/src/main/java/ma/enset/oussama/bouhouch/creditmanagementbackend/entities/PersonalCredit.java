package ma.enset.oussama.bouhouch.creditmanagementbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PERSONAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalCredit extends Credit {
    private String motif;
}