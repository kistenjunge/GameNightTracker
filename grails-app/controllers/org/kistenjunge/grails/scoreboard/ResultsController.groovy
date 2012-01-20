package org.kistenjunge.grails.scoreboard

import org.springframework.dao.DataIntegrityViolationException

class ResultsController {
	def scaffold = true
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [resultsInstanceList: Results.list(params), resultsInstanceTotal: Results.count()]
    }

    def create() {
        [resultsInstance: new Results(params)]
    }

    def save() {
        def resultsInstance = new Results(params)
        if (!resultsInstance.save(flush: true)) {
            render(view: "create", model: [resultsInstance: resultsInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'results.label', default: 'Results'), resultsInstance.id])
        redirect(action: "show", id: resultsInstance.id)
    }

    def show() {
        def resultsInstance = Results.get(params.id)
        if (!resultsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'results.label', default: 'Results'), params.id])
            redirect(action: "list")
            return
        }

        [resultsInstance: resultsInstance]
    }

    def edit() {
        def resultsInstance = Results.get(params.id)
        if (!resultsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'results.label', default: 'Results'), params.id])
            redirect(action: "list")
            return
        }

        [resultsInstance: resultsInstance]
    }

    def update() {
        def resultsInstance = Results.get(params.id)
        if (!resultsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'results.label', default: 'Results'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (resultsInstance.version > version) {
                resultsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'results.label', default: 'Results')] as Object[],
                          "Another user has updated this Results while you were editing")
                render(view: "edit", model: [resultsInstance: resultsInstance])
                return
            }
        }

        resultsInstance.properties = params

        if (!resultsInstance.save(flush: true)) {
            render(view: "edit", model: [resultsInstance: resultsInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'results.label', default: 'Results'), resultsInstance.id])
        redirect(action: "show", id: resultsInstance.id)
    }

    def delete() {
        def resultsInstance = Results.get(params.id)
        if (!resultsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'results.label', default: 'Results'), params.id])
            redirect(action: "list")
            return
        }

        try {
            resultsInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'results.label', default: 'Results'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'results.label', default: 'Results'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
