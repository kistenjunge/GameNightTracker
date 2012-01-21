package org.kistenjunge.grails.scoreboard

import org.springframework.dao.DataIntegrityViolationException

class ResultController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [resultInstanceList: Result.list(params), resultInstanceTotal: Result.count()]
    }

    def create() {
        [resultInstance: new Result(params)]
    }

    def save() {
        def resultInstance = new Result(params)
        if (!resultInstance.save(flush: true)) {
            render(view: "create", model: [resultInstance: resultInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'result.label', default: 'Result'), resultInstance.id])
        redirect(action: "show", id: resultInstance.id)
    }

    def show() {
        def resultInstance = Result.get(params.id)
        if (!resultInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), params.id])
            redirect(action: "list")
            return
        }

        [resultInstance: resultInstance]
    }

    def edit() {
        def resultInstance = Result.get(params.id)
        if (!resultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), params.id])
            redirect(action: "list")
            return
        }

        [resultInstance: resultInstance]
    }

    def update() {
        def resultInstance = Result.get(params.id)
        if (!resultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (resultInstance.version > version) {
                resultInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'result.label', default: 'Result')] as Object[],
                          "Another user has updated this Result while you were editing")
                render(view: "edit", model: [resultInstance: resultInstance])
                return
            }
        }

        resultInstance.properties = params

        if (!resultInstance.save(flush: true)) {
            render(view: "edit", model: [resultInstance: resultInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'result.label', default: 'Result'), resultInstance.id])
        redirect(action: "show", id: resultInstance.id)
    }

    def delete() {
        def resultInstance = Result.get(params.id)
        if (!resultInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), params.id])
            redirect(action: "list")
            return
        }

        try {
            resultInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'result.label', default: 'Result'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'result.label', default: 'Result'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
