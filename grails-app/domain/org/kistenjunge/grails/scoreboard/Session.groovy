package org.kistenjunge.grails.scoreboard

class Session {
	Date date
	String name
	static hasMany = [results:Result]

    static constraints = {
	date(format:"dd-MM-yyyy")
	name(blank:false)
    }

    String toString()
    {
	"$name"
    }
}
