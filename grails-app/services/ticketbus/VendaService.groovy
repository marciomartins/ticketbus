package ticketbus

import grails.transaction.Transactional

@Transactional
class VendaService {

    def estoqueService

    def movimentaEstoque(Venda venda){
        estoqueService.salvarEstoque(
                new MovimentacaoEstoque(tipoOpercao: TipoOperacaoEstoque.CONSUMO,
                        aidf: venda.aidf,
                        origem: venda.agencia,
                        destino: null,
                        seqInicial: venda.numero,
                        seqFinal: venda.numero,
                        data: venda.dataVenda
                )
        )
    }

    def vendaEmAberto(Venda venda){
        Bilhete bil = new Bilhete(numero: venda.numero,
                dataVenda: venda.dataVenda,
                dataViagem: venda.dataViagem,
                origem: venda.origem,
                destino: venda.destino,
                poltrona: venda.porltrona,
                tarifa: venda.tarifa
        )
        bil.save(failOnError: true)
        movimentaEstoque(venda)
    }

    def vendaNormal(Venda venda){
        Bilhete bil = new Bilhete(numero: venda.numero,
                dataVenda: venda.dataVenda,
                dataViagem: venda.dataViagem,
                origem: venda.origem,
                destino: venda.destino,
                poltrona: venda.porltrona,
                tarifa: venda.tarifa
        )
        bil.save(failOnError: true)
        movimentaEstoque(venda)
    }
    def save(Venda venda) {
        venda.save(failOnError: true)
        switch (venda.tipoVenda) {
            case TipoVenda.ABERTO:
                vendaEmAberto(venda)
                break
            case  TipoVenda.NORMAL:
                vendaNormal(venda)
                break
        }

    }
}
