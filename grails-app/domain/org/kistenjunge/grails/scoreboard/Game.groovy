package org.kistenjunge.grails.scoreboard

class Game {
	String title
	String url
    static constraints = {
		title (blank:false, nullable:false)
		url (url:true, nullable:true)
    }
    String toString()
    {
	return title;
    }
}
