package ticketbus

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MovimentacaoEstoqueController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static allowedMethods = [save: "POST", delete: "DELETE"]

    def estoqueService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MovimentacaoEstoque.list(params), model:[movimentacaoEstoqueCount: MovimentacaoEstoque.count()]
    }

    def show(MovimentacaoEstoque movimentacaoEstoque) {
        respond movimentacaoEstoque
    }

    def create() {
        respond new MovimentacaoEstoque(params)
    }

    @Transactional
    def save(MovimentacaoEstoque movimentacaoEstoque) {
        if (movimentacaoEstoque == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (movimentacaoEstoque.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond movimentacaoEstoque.errors, view:'create'
            return
        }

        //movimentacaoEstoque.save flush:true
        estoqueService.salvarEstoque(movimentacaoEstoque)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'movimentacaoEstoque.label', default: 'MovimentacaoEstoque'), movimentacaoEstoque.id])
                //redirect movimentacaoEstoque
                redirect action: "index", method: "GET"
            }
            '*' { respond movimentacaoEstoque, [status: CREATED] }
        }
    }

    def edit(MovimentacaoEstoque movimentacaoEstoque) {
        respond movimentacaoEstoque
    }

    @Transactional
    def update(MovimentacaoEstoque movimentacaoEstoque) {
        if (movimentacaoEstoque == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (movimentacaoEstoque.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond movimentacaoEstoque.errors, view:'edit'
            return
        }

        movimentacaoEstoque.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'movimentacaoEstoque.label', default: 'MovimentacaoEstoque'), movimentacaoEstoque.id])
                redirect movimentacaoEstoque
            }
            '*'{ respond movimentacaoEstoque, [status: OK] }
        }
    }

    @Transactional
    def delete(MovimentacaoEstoque movimentacaoEstoque) {

        if (movimentacaoEstoque == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        movimentacaoEstoque.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'movimentacaoEstoque.label', default: 'MovimentacaoEstoque'), movimentacaoEstoque.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'movimentacaoEstoque.label', default: 'MovimentacaoEstoque'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
