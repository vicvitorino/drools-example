package br.droolsexample.service;

import br.droolsexample.model.CorridaTaxi;
import br.droolsexample.model.Tarifa;
import lombok.extern.log4j.Log4j2;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TarifaTaxiService {

    @Autowired
    private KieContainer kContainer;

    public Long calcularTarifa(final CorridaTaxi corridaTaxi, final Tarifa tarifaPasseio) {
        KieSession kieSession = kContainer.newKieSession();
        kieSession.setGlobal("tarifaPasseio", tarifaPasseio);
        kieSession.insert(corridaTaxi);
        kieSession.fireAllRules();
        kieSession.dispose();
        log.info("Tarifa Total: " + tarifaPasseio.getTarifaTotal());
        return tarifaPasseio.getTarifaTotal();
    }

}
