package ticketbus

enum TipoVenda{
    NORMAL("Normal"),
    ABERTO("Aberto")

    final String value

    TipoVenda(String value) { this.value = value }

    String toString() { value }
    String getKey() { name() }

}


class Venda {
    TipoVenda tipoVenda
    Agencia agencia
    Date dataVenda
    Date dataViagem
    int porltrona
    Aidf aidf
    int numero
    float tarifa
    Municipio origem
    Municipio destino

    static constraints = {
        tipoVenda nullable: false
        agencia nullable: false
        dataVenda nullable: false
        dataViagem nullable: true
        aidf nullable: false
        origem nullable: false
        destino nullable: false
    }
}
