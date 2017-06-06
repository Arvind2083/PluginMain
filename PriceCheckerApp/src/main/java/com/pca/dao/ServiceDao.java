/**
 * 
 */
package com.pca.dao;

import java.util.List;

import org.pca.domain.Plugin;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Arvind
 *
 */
public interface  ServiceDao extends MongoRepository<Plugin,String>{

	
	public List<Plugin> findAll();
	public void add(Plugin plugin) ;

}
