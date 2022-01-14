package org.springframework.samples.petclinic.feeding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FeedingRepository  extends CrudRepository<Feeding, Integer>{
	
    List<Feeding> findAll();
    
    @Query("SELECT t FROM FeedingType t")
     List<FeedingType> findAllFeedingTypes();
     
    Optional<Feeding> findById(int id);
    
    @Query("SELECT t FROM FeedingType t WHERE t.name = :name")
    FeedingType getFeedingType(@Param("name") String name);
    
    
    Feeding save(Feeding p);
}
