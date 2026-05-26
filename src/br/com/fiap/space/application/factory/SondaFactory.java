package br.com.fiap.space.application.factory;

import br.com.fiap.space.domain.entity.Sonda;
import br.com.fiap.space.domain.entity.SondaExploradora;
import br.com.fiap.space.domain.entity.SondaMineradora;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.TipoSondaInvalidoException;
import br.com.fiap.space.domain.valueobject.CompartimentoCarga;
import br.com.fiap.space.domain.valueobject.Coordernada;
import br.com.fiap.space.domain.valueobject.NivelEnergia;

public class SondaFactory {

    public static SondaExploradora criarSondaExploradora(Coordernada coordernada, NivelEnergia bateria, Double alcanceSensor) {
        return new SondaExploradora(bateria, coordernada, alcanceSensor);
    }

    public static SondaMineradora criarSondaMineradora(Coordernada coordernada, NivelEnergia bateria, Double volumeMaximo) {
        CompartimentoCarga carga = new CompartimentoCarga(volumeMaximo);
        return new SondaMineradora(bateria, coordernada, carga);
    }

    public static Sonda criarSonda(TipoSonda tipoSonda, Integer eixoX, Integer eixoY, Double nivelBateria, Double capacidadeMaxima, Double alcanceSensor, Double volumeMaximo) {
        
        Coordernada coordernada = new Coordernada(eixoX, eixoY);
        NivelEnergia bateria = new NivelEnergia(nivelBateria, capacidadeMaxima);
        
        switch (tipoSonda) {
            case EXPLORADORA:
                return SondaFactory.criarSondaExploradora(coordernada, bateria, alcanceSensor);
            case MINERADORA:
                return SondaFactory.criarSondaMineradora(coordernada, bateria, volumeMaximo);
            default:
                throw new TipoSondaInvalidoException();
        }
    }
}
