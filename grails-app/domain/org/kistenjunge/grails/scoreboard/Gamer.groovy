package org.kistenjunge.grails.scoreboard

class Gamer {
	String name
	String surname
	String gamertag
	String password
	byte[] avatar
	String avatarType
	static hasMany = [scores:Score]

    static constraints = {
	name (blank:false, nullable:false)
	surname (blank:false, nullable:false)
	gamertag (blank:false, nullable:false, unique:true)
	password (blank:false, nullable:false, password:true)
	avatar(nullable:true, maxSize: 16384)
	avatarType(nullable:true)
    }

    String toString()
    {
	"$gamertag"
    }
}
