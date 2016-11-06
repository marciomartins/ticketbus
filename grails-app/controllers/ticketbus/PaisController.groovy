package ticketbus

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PaisController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Pais.list(params), model:[paisCount: Pais.count()]
    }

    def show(Pais pais) {
        respond pais
    }

    def create() {
        respond new Pais(params)
    }

    @Transactional
    def save(Pais pais) {
        if (pais == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pais.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pais.errors, view:'create'
            return
        }

        pais.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pais.label', default: 'Pais'), pais.id])
                redirect pais
            }
            '*' { respond pais, [status: CREATED] }
        }
    }

    def edit(Pais pais) {
        respond pais
    }

    @Transactional
    def update(Pais pais) {
        if (pais == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pais.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pais.errors, view:'edit'
            return
        }

        pais.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pais.label', default: 'Pais'), pais.id])
                redirect pais
            }
            '*'{ respond pais, [status: OK] }
        }
    }

    @Transactional
    def delete(Pais pais) {

        if (pais == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pais.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pais.label', default: 'Pais'), pais.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pais.label', default: 'Pais'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
