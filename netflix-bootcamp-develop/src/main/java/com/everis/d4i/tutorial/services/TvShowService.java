
package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import org.apache.logging.log4j.message.Message;


/*TV-SHOW SERVICE INTERFACE  */

public interface TvShowService {


	List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException;

	TvShowRest getTvShowById(Long id) throws NetflixException;

	void renameTvShow(Long tvShowId, String tvShowName) throws NetflixException;

	void deleteTvShow(Long tvShowId) throws NetflixException;

	List<AwardRest>  listAllAwards(Long tvShowId)throws NetflixException;

}
