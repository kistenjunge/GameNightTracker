package org.kistenjunge.grails.scoreboard

class Result {
	static belongsTo = [session:Session]
	Gamer gamer
	Game game
	int score

    static constraints = {
		gamer(nullable:false)
		game(nullable:false)
		session(nullable:false)
    }
}
