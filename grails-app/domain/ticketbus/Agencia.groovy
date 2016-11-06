package ticketbus

class Agencia {
    static belongsTo = [estado:Estado, municipio:Municipio]
    String sigla
    String nome

    String toString(){
        this.nome
    }

    static constraints = {
        estado nullable: false
        municipio nullable: false
        sigla nullable: false, maxSize: 4
        nome nullable: false, blank: false, maxSize: 50
    }
}
