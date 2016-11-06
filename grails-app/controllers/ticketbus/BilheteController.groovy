package ticketbus

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BilheteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Bilhete.list(params), model:[bilheteCount: Bilhete.count()]
    }

    def show(Bilhete bilhete) {
        respond bilhete
    }

    def create() {
        respond new Bilhete(params)
    }

    @Transactional
    def save(Bilhete bilhete) {
        if (bilhete == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bilhete.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bilhete.errors, view:'create'
            return
        }

        bilhete.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bilhete.label', default: 'Bilhete'), bilhete.id])
                redirect bilhete
            }
            '*' { respond bilhete, [status: CREATED] }
        }
    }

    def edit(Bilhete bilhete) {
        respond bilhete
    }

    @Transactional
    def update(Bilhete bilhete) {
        if (bilhete == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bilhete.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bilhete.errors, view:'edit'
            return
        }

        bilhete.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bilhete.label', default: 'Bilhete'), bilhete.id])
                redirect bilhete
            }
            '*'{ respond bilhete, [status: OK] }
        }
    }

    @Transactional
    def delete(Bilhete bilhete) {

        if (bilhete == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        bilhete.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bilhete.label', default: 'Bilhete'), bilhete.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bilhete.label', default: 'Bilhete'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
