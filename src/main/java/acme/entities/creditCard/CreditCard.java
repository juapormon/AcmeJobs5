
package acme.entities.creditCard;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	@Pattern(regexp = "^[0-9]{3,4}$")
	private String				creditCardCvv;

	@Range(min = 1, max = 12)
	@NotNull
	private Integer				creditCardMonth;

	@Range(min = 2000, max = 9999)
	@NotNull
	private Integer				creditCardYear;
}
