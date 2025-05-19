package elmoutaouaki.abderrahim.backend.dtos;

import elmoutaouaki.abderrahim.backend.entities.CreditImmobilier.TypeBien;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBien typeBien;
}