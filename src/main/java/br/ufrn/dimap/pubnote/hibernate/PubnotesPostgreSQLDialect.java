package br.ufrn.dimap.pubnote.hibernate;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL81Dialect;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.PostgresPlusDialect;

public class PubnotesPostgreSQLDialect extends PostgreSQL82Dialect 
{
	public PubnotesPostgreSQLDialect()
	{
		registerColumnType(Types.VARCHAR, Long.MAX_VALUE, "varchar");
	}
}
