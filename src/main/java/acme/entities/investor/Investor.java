
package acme.entities.investor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "sector")
})
public class Investor extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				investorStatement;

	@Range(min = 0, max = 5)
	private Integer				starNumber;
}
