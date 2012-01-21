package org.kistenjunge.grails.scoreboard

class Result {
	static belongsTo = [session:Session]
	static hasMany = [scores:Score]
	Game game

    static constraints = {
		game(nullable:false)
		session(nullable:false)
    }

    String toString() {
	"Results for game $game"
    }

}
