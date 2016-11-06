package ticketbus

class Aidf {
    static belongsTo = [estado:Estado]
    String codigo
    int seqInicial
    int seqFinal
    Date aquisicao
    Date vencimento

    String toString(){
        this.codigo
    }

    static constraints = {
        estado nullable: false
        codigo nullable: false, blank: false, maxSize: 50;
        aquisicao nullable: false
        vencimento nullable: false
    }
}
