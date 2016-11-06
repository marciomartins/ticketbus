package ticketbus

class Pais {

    String nome

    //static hasMany = [estado: Estado]

    String toString(){
        this.nome
    }

    static constraints = {
        nome nullable: false, blank: false, maxSize: 50, unique: true
    }
}
