
package acme.entities.company;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "sector")
})
public class Company extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceo;

	@NotBlank
	private String				activities;

	@NotBlank
	@URL
	private String				website;

	@NotBlank
	@Pattern(regexp = "^([+][1-9][0-9]{0,2}[ ])?([(][0-9]{1,4}[)][ ])?[0-9]{6,10}$")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	private boolean				incorporated;

	@Range(min = 0, max = 5)
	private Integer				stars;


	@Transient
	public String getIncorporatedName() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.name);
		if (this.incorporated) {
			sb.append(", Inc.");
		} else {
			sb.append(", LLC");
		}

		return sb.toString();
	}
}
