package com.mongodb.hadoop.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class MongoRequest {

    private static final Log log = LogFactory.getLog( MongoRequest.class );

	private String uri;
	private String query;
	
	public MongoRequest(){
		
	}
	
	public MongoRequest(String uri, BasicDBObject query){
		this.setUri(uri);
		this.setQuery(query);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public BasicDBObject getQuery() {
        
		BasicDBObject query = null;
        
		try {
            final Object dbObj = JSON.parse(this.query);
            query = (BasicDBObject) dbObj;
        }
        catch ( final Exception e ) {
            log.info( "Cannot parse JSON...", e );
            throw new IllegalArgumentException( "Provided JSON String is not representable/parseable as a DBObject.",
                                                e );
        }
		
		return query;
	}

	public void setQuery(BasicDBObject query) {
		this.query = JSON.serialize(query);
	}
	
	
	
	
}
