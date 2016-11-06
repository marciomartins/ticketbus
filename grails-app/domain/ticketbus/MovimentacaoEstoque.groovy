package ticketbus

enum TipoOperacaoEstoque {
    AQUISICAO("Aquisição"),AJUSTE("Ajuste"),CONSUMO("Consumo"),DISTRIBUICAO("Distribuição"),TRANSFERENCIA("Aranferência")
    final String value

    TipoOperacaoEstoque(String value) { this.value = value }

    String toString() { value }
    String getKey() { name() }
}

class MovimentacaoEstoque {

    TipoOperacaoEstoque tipoOpercao
    static belongsTo = [aidf:Aidf, origem:Agencia, destino:Agencia]
    int seqInicial
    int seqFinal
    Date data

    static constraints = {
        tipoOpercao nullable: false
        aidf nullable: false
        /*origem (validator: {val, obj ->
            if (obj.tipoOpercao == tipoOpercao.getKey(TipoOperacaoEstoque.AQUISICAO) && !val)
                return ['tipoOpercao.required']
        }, nullable: true)*/
        origem nullable: true
        destino nullable: true
        data nullable: false;
    }
}
