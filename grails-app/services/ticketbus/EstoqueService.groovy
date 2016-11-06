package ticketbus

import grails.transaction.Transactional
import grails.validation.ValidationException

@Transactional
class EstoqueService {

    def incrementaEstoque(MovimentacaoEstoque mvestoque){
        List<Estoque> estoques = Estoque.findAllByAgenciaAndAidf(mvestoque.destino, mvestoque.aidf)
        Estoque est
        Boolean criarEstoque = true
        for(Estoque estoque: estoques){
            if (estoque.sequenciaFinal.equals(mvestoque.seqInicial - 1)) {
                estoque.sequenciaFinal = mvestoque.seqFinal
                est = estoque
                criarEstoque = false
                break
            }
            else if (estoque.sequenciaInicial.equals(mvestoque.seqFinal + 1)) {
                estoque.sequenciaInicial = mvestoque.seqInicial
                criarEstoque = false
                est = estoque
                break
            }
        }
        if (criarEstoque || estoques.size().equals(0))
            est = new Estoque(aidf: mvestoque.aidf, agencia: mvestoque.destino, sequenciaInicial: mvestoque.seqInicial, sequenciaFinal: mvestoque.seqFinal)
        //est.save(failOnError:true)

        if (!criarEstoque) {
            Estoque estDel
            for (Estoque estoque: estoques) {
                if (estoque.sequenciaInicial.equals(mvestoque.seqFinal + 1)) {
                    est.sequenciaFinal = estoque.sequenciaFinal
                    estDel = estoque
                    break
                }
            }
            if (estDel)
              estDel.delete(failOnError: true)
        }
        est.save(failOnError:true)
    }

    def decrementaEstoque(MovimentacaoEstoque mvestoque) {
        List<Estoque> estoquesOrigem = Estoque.findAllByAgenciaAndAidf(mvestoque.origem, mvestoque.aidf)
        Estoque est
        Estoque estDel
        Estoque estNovo
        Boolean houveDecremento = false
        for(Estoque estoque: estoquesOrigem){
            if ((mvestoque.seqInicial >= estoque.sequenciaInicial) && (mvestoque.seqFinal <= estoque.sequenciaFinal)) {
                houveDecremento = true
                if (estoque.sequenciaFinal.equals(mvestoque.seqFinal) && estoque.sequenciaInicial.equals(mvestoque.seqInicial)) {
                    estDel = estoque
                    break
                }else if (estoque.sequenciaInicial.equals(mvestoque.seqInicial)) {
                    estoque.sequenciaInicial = mvestoque.seqFinal + 1
                    est = estoque
                    break
                }else if (estoque.sequenciaFinal.equals(mvestoque.seqFinal)) {
                    estoque.sequenciaFinal = mvestoque.seqInicial - 1
                    est = estoque
                    break
                } else if (mvestoque.seqInicial > estoque.sequenciaInicial && mvestoque.seqFinal < estoque.sequenciaFinal) {
                    estNovo = new Estoque(aidf: mvestoque.aidf, agencia: mvestoque.origem, sequenciaInicial:  mvestoque.seqFinal+1, sequenciaFinal: estoque.sequenciaFinal)
                    estoque.sequenciaFinal = mvestoque.seqFinal - 1
                    est = estoque
                    break
                }
            }
        }

        if (!houveDecremento || estoquesOrigem.size().equals(0))
            throw new ValidationException("Estoque não econtrado na agência de origem", mvestoque.origem.errors)
        if (estDel)
            estDel.delete(failOnError: true)
        if (estNovo)
            estNovo.save(failOnError: true)
        if (est)
            est.save(failOnError: true)
    }

    def aquisicaoEstoque(MovimentacaoEstoque mvestoque){
        if (mvestoque.origem)
            throw new ValidationException("Operação não permitida!", mvestoque.origem.errors)
        mvestoque.save(failOnError:true);
        incrementaEstoque(mvestoque)
    }

    def ajusteEstoque(MovimentacaoEstoque mvestoque){
        if ((!mvestoque.origem) && (!mvestoque.destino))
            throw new ValidationException("Operação não permitida! Selecione uma agência de Origem e/ou Destino", mvestoque.origem.errors)
        mvestoque.save(failOnError:true);
        incrementaEstoque(mvestoque)
        decrementaEstoque (mvestoque)
    }

    def consumoEstoque(MovimentacaoEstoque mvestoque){
        decrementaEstoque(mvestoque)
    }

    def distribuicaoEstoque(MovimentacaoEstoque mvestoque){
        mvestoque.save(failOnError:true);
        incrementaEstoque(mvestoque)
        decrementaEstoque(mvestoque)
    }


    def salvarEstoque(MovimentacaoEstoque mvestoque) {
        switch (mvestoque.tipoOpercao) {
            case TipoOperacaoEstoque.AQUISICAO:
                if (!mvestoque.destino)
                    throw new ValidationException("Agência de destino não informada", mvestoque.destino.errors)
                aquisicaoEstoque(mvestoque)
                break
            case TipoOperacaoEstoque.AJUSTE:
                ajusteEstoque(mvestoque)
                break
            case TipoOperacaoEstoque.CONSUMO:
                consumoEstoque(mvestoque)
                break
            case TipoOperacaoEstoque.DISTRIBUICAO:
                distribuicaoEstoque(mvestoque)
                break
            case TipoOperacaoEstoque.TRANSFERENCIA:
                distribuicaoEstoque(mvestoque)
                break
            default:
                break
        }
        //if (mvestoque.tipoOpercao.equals(TipoOperacaoEstoque.AQUISICAO))
        //def estoque = new Estoque(aidf: mvestoque.aidf, agencia: mvestoque.destino, sequenciaInicial: mvestoque.seqInicial, sequenciaFinal: mvestoque.seqFinal)
        //estoque.save(flush:true)
    }
}

/*
Cenários
Incluir
11-20 -> 01-10 = 01-20
11-20 -> 01-09 = 01-09 e 11-20
01-10 -> 11-20 = 01-20
11-11 -> 01-10 = 01-11
01-01 -> 02-10 = 01-10
1-10
11-20

Excluir
11-20 -> 01-20 =


 */