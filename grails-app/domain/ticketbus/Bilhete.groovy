package ticketbus

class Bilhete {

    int numero
    Date dataVenda
    Date dataViagem
    Municipio origem
    Municipio destino
    int poltrona
    float tarifa


    static constraints = {
        dataVenda nullable: false
        dataViagem nullable: true
        origem nullable: false
        destino nullable: false
    }
}
