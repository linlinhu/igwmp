package com.emin.igwmp.rstm.dao;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;


public class JSONBPostgreSQLDialect extends PostgreSQL94Dialect{

	public JSONBPostgreSQLDialect() {
		super();
		registerColumnType(Types.JAVA_OBJECT, "jsonb");
		
	}
}
