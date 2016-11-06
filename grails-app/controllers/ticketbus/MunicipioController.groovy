package ticketbus

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MunicipioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Municipio.list(params), model:[municipioCount: Municipio.count()]
    }

    def show(Municipio municipio) {
        respond municipio
    }

    def create() {
        respond new Municipio(params)
    }

    @Transactional
    def save(Municipio municipio) {
        if (municipio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (municipio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond municipio.errors, view:'create'
            return
        }

        municipio.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'municipio.label', default: 'Municipio'), municipio.id])
                redirect municipio
            }
            '*' { respond municipio, [status: CREATED] }
        }
    }

    def edit(Municipio municipio) {
        respond municipio
    }

    @Transactional
    def update(Municipio municipio) {
        if (municipio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (municipio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond municipio.errors, view:'edit'
            return
        }

        municipio.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'municipio.label', default: 'Municipio'), municipio.id])
                redirect municipio
            }
            '*'{ respond municipio, [status: OK] }
        }
    }

    @Transactional
    def delete(Municipio municipio) {

        if (municipio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        municipio.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'municipio.label', default: 'Municipio'), municipio.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'municipio.label', default: 'Municipio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
