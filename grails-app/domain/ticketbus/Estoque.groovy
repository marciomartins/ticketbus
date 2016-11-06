package ticketbus

class Estoque {

    static belongsTo = [agencia:Agencia, aidf: Aidf]
    int sequenciaInicial
    int sequenciaFinal

    static constraints = {
        agencia nullable: false
        aidf nullable: false;
    }
}
