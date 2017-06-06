/**
 * 
 */
package org.pca.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Arvind
 *
 */
@Document(collection = "Plugin")
public class Plugin {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
