package org.kistenjunge.grails.scoreboard

class Score {
	static belongsTo = [result:Result]
	Gamer gamer
	int points
    static constraints = {
	gamer(nullable:false)
	points(nullable:false)
    }

    String toString(){
	"$gamer : $points"
    }
}
