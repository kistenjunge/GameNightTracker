package org.kistenjunge.grails.scoreboard

class Results {
	Gamer gamer
	Game game
	Session session
	int score

    static constraints = {
		gamer(nullable:false)
		game(nullable:false)
		session(nullable:false)
		score(nullable:false)
    }
}
