package org.kistenjunge.grails.scoreboard

import org.springframework.dao.DataIntegrityViolationException

class GamerController {
	def scaffold = true

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gamerInstanceList: Gamer.list(params), gamerInstanceTotal: Gamer.count()]
    }

    def create() {
        [gamerInstance: new Gamer(params)]
    }

    def save() {
        def gamerInstance = new Gamer(params)
        if (!gamerInstance.save(flush: true)) {
            render(view: "create", model: [gamerInstance: gamerInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'gamer.label', default: 'Gamer'), gamerInstance.id])
        redirect(action: "show", id: gamerInstance.id)
    }

    def show() {
        def gamerInstance = Gamer.get(params.id)
        if (!gamerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gamer.label', default: 'Gamer'), params.id])
            redirect(action: "list")
            return
        }

        [gamerInstance: gamerInstance]
    }

    def edit() {
        def gamerInstance = Gamer.get(params.id)
        if (!gamerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gamer.label', default: 'Gamer'), params.id])
            redirect(action: "list")
            return
        }

        [gamerInstance: gamerInstance]
    }

    def update() {
        def gamerInstance = Gamer.get(params.id)
        if (!gamerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gamer.label', default: 'Gamer'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (gamerInstance.version > version) {
                gamerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'gamer.label', default: 'Gamer')] as Object[],
                          "Another user has updated this Gamer while you were editing")
                render(view: "edit", model: [gamerInstance: gamerInstance])
                return
            }
        }

        gamerInstance.properties = params

        if (!gamerInstance.save(flush: true)) {
            render(view: "edit", model: [gamerInstance: gamerInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'gamer.label', default: 'Gamer'), gamerInstance.id])
        redirect(action: "show", id: gamerInstance.id)
    }

    def delete() {
        def gamerInstance = Gamer.get(params.id)
        if (!gamerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gamer.label', default: 'Gamer'), params.id])
            redirect(action: "list")
            return
        }

        try {
            gamerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'gamer.label', default: 'Gamer'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'gamer.label', default: 'Gamer'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
