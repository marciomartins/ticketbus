package ticketbus

class Municipio {

    static belongsTo = [pais:Pais,estado:Estado]
    String nome
    String sigla
    String codigo

    String toString(){
        this.nome
    }

    static constraints = {
        pais nullable: false
        estado nullable: false
        nome nullable: false, blank: false, maxSize: 100
        sigla nullable: false, blank: false, maxSize: 3
        codigo nullable: true, blank: false, maxSize: 10
    }
}
