package org.springframework.samples.petclinic.feeding;

import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class FeedingService {
	
	private FeedingRepository feedingRepository;
	
	public FeedingService(FeedingRepository feedingRepository) {
		this.feedingRepository = feedingRepository;
	}
	
	@Transactional
    public List<Feeding> getAll() throws DataAccessException{
        return feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return null;
    }

    public FeedingType getFeedingType(String typeName) {
        return feedingRepository.getFeedingType(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
    	if(p.getPet().getType() != p.getFeedingType().getPetType()) {
    		throw new UnfeasibleFeedingException();
    	}
    	else {
    		feedingRepository.save(p);
    		return p;
    	}
    }    
    
}
