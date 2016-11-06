package ticketbus

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VendaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def vendaService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Venda.list(params), model:[vendaCount: Venda.count()]
    }

    def show(Venda venda) {
        respond venda
    }

    def create() {
        respond new Venda(params)
    }

    @Transactional
    def save(Venda venda) {
        if (venda == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (venda.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond venda.errors, view:'create'
            return
        }

        //venda.save flush:true
        vendaService.save(venda)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'venda.label', default: 'Venda'), venda.id])
                redirect venda
            }
            '*' { respond venda, [status: CREATED] }
        }
    }

    def edit(Venda venda) {
        respond venda
    }

    @Transactional
    def update(Venda venda) {
        if (venda == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (venda.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond venda.errors, view:'edit'
            return
        }

        venda.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'venda.label', default: 'Venda'), venda.id])
                redirect venda
            }
            '*'{ respond venda, [status: OK] }
        }
    }

    @Transactional
    def delete(Venda venda) {

        if (venda == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        venda.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'venda.label', default: 'Venda'), venda.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'venda.label', default: 'Venda'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
