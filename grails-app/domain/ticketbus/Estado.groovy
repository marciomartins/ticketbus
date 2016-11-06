package ticketbus

class Estado {

    static belongsTo = [pais: Pais]
    String nome
    String sigla

    //static hasMany = [municipio: Municipio]

    String toString(){
        this.nome
    }

    static constraints = {
        pais nullable: false
        nome nullable: false, blank: false, maxSize: 50
        sigla nullable: false, blank: false, maxSize: 2
    }
}
