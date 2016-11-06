package ticketbus

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AidfController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Aidf.list(params), model:[aidfCount: Aidf.count()]
    }

    def show(Aidf aidf) {
        respond aidf
    }

    def create() {
        respond new Aidf(params)
    }

    @Transactional
    def save(Aidf aidf) {
        if (aidf == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (aidf.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond aidf.errors, view:'create'
            return
        }

        aidf.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'aidf.label', default: 'Aidf'), aidf.id])
                redirect aidf
            }
            '*' { respond aidf, [status: CREATED] }
        }
    }

    def edit(Aidf aidf) {
        respond aidf
    }

    @Transactional
    def update(Aidf aidf) {
        if (aidf == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (aidf.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond aidf.errors, view:'edit'
            return
        }

        aidf.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'aidf.label', default: 'Aidf'), aidf.id])
                redirect aidf
            }
            '*'{ respond aidf, [status: OK] }
        }
    }

    @Transactional
    def delete(Aidf aidf) {

        if (aidf == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        aidf.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'aidf.label', default: 'Aidf'), aidf.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'aidf.label', default: 'Aidf'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
