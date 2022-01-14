package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feeding extends BaseEntity {
	
	@NotNull
	@Column(name = "start_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    LocalDate startDate;
    
	@Column(name = "weeks_duration")        
    @NotNull
    @Min(1)
    double weeksDuration;
	
	@NotNull
	@ManyToOne(optional = false)
    Pet pet;   
	
	@ManyToOne
	@JoinColumn(name = "feeding_type")
    FeedingType feedingType;
}
