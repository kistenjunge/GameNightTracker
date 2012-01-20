package org.kistenjunge.grails.scoreboard

class Session {
	Date date
	String name
	static hasMany = [results:Result]

    static constraints = {
	name (blank:false, nullable:false)
    }

    String toString()
    {
	return name;
    }
}
