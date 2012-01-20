package org.kistenjunge.grails.scoreboard

class Gamer {
	String name
	String surname
	String gamertag
	String image
	String password
    static constraints = {
		name (blank:false, nullable:false)
		surname (blank:false, nullable:false)
		gamertag (blank:false, nullable:false)
		password (blank:false, nullable:false)
		image (blank:true, nullable:true)
    }
    String toString()
    {
	return gamertag;
    }
}