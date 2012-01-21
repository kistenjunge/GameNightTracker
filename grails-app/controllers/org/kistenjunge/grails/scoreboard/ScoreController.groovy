package org.kistenjunge.grails.scoreboard

import org.springframework.dao.DataIntegrityViolationException

class ScoreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [scoreInstanceList: Score.list(params), scoreInstanceTotal: Score.count()]
    }

    def create() {
        [scoreInstance: new Score(params)]
    }

    def save() {
        def scoreInstance = new Score(params)
        if (!scoreInstance.save(flush: true)) {
            render(view: "create", model: [scoreInstance: scoreInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'score.label', default: 'Score'), scoreInstance.id])
        redirect(action: "show", id: scoreInstance.id)
    }

    def show() {
        def scoreInstance = Score.get(params.id)
        if (!scoreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'score.label', default: 'Score'), params.id])
            redirect(action: "list")
            return
        }

        [scoreInstance: scoreInstance]
    }

    def edit() {
        def scoreInstance = Score.get(params.id)
        if (!scoreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'score.label', default: 'Score'), params.id])
            redirect(action: "list")
            return
        }

        [scoreInstance: scoreInstance]
    }

    def update() {
        def scoreInstance = Score.get(params.id)
        if (!scoreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'score.label', default: 'Score'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (scoreInstance.version > version) {
                scoreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'score.label', default: 'Score')] as Object[],
                          "Another user has updated this Score while you were editing")
                render(view: "edit", model: [scoreInstance: scoreInstance])
                return
            }
        }

        scoreInstance.properties = params

        if (!scoreInstance.save(flush: true)) {
            render(view: "edit", model: [scoreInstance: scoreInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'score.label', default: 'Score'), scoreInstance.id])
        redirect(action: "show", id: scoreInstance.id)
    }

    def delete() {
        def scoreInstance = Score.get(params.id)
        if (!scoreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'score.label', default: 'Score'), params.id])
            redirect(action: "list")
            return
        }

        try {
            scoreInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'score.label', default: 'Score'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'score.label', default: 'Score'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
