
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.creditCard.CreditCard;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsor extends UserRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				organisationName;

	@Valid
	@OneToOne(optional = true)
	private CreditCard			creditCard;

	//	@NotBlank
	//	@CreditCardNumber
	//	private String				creditCardNumber;
	//
	//	@NotBlank
	//	@Pattern(regexp = "^[0-9]{3,4}$")
	//	private String				creditCardCvv;
	//
	//	@Range(min = 1, max = 12)
	//	@NotNull
	//	private Integer				creditCardMonth;
	//
	//	@Range(min = 2000, max = 9999)
	//	@NotNull
	//	private Integer				creditCardYear;

}
