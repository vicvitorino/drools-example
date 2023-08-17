package br.droolsexample.service;

import br.droolsexample.model.CorridaTaxi;
import br.droolsexample.model.Tarifa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TarifaTaxiServiceIntegrationTest {

    @Autowired
    private TarifaTaxiService tarifaTaxiService;

    @Test
    public void whenNightSurchargeFalseAndDistanceLessThan10_thenFixFareWithoutNightSurcharge() {
        final CorridaTaxi corridaTaxi = new CorridaTaxi();
        corridaTaxi.setEhSobretaxaNoturna(false);
        corridaTaxi.setDistanciaKm(9L);
        final Tarifa tarifaPasseio = new Tarifa();
        final Long tarifaTotal = tarifaTaxiService.calcularTarifa(corridaTaxi, tarifaPasseio);

        assertNotNull(tarifaTotal);
        assertEquals(Long.valueOf(70), tarifaTotal);
    }

    @Test
    public void whenNightSurchargeTrueAndDistanceLessThan10_thenFixFareWithNightSurcharge() {
        final CorridaTaxi corridaTaxi = new CorridaTaxi();
        corridaTaxi.setEhSobretaxaNoturna(true);
        corridaTaxi.setDistanciaKm(5L);
        final Tarifa tarifaPasseio = new Tarifa();
        final Long tarifaTotal = tarifaTaxiService.calcularTarifa(corridaTaxi, tarifaPasseio);

        assertNotNull(tarifaTotal);
        assertEquals(Long.valueOf(100), tarifaTotal);
    }

    @Test
    public void whenNightSurchargeFalseAndDistanceLessThan100_thenDoubleFareWithoutNightSurcharge() {
        final CorridaTaxi corridaTaxi = new CorridaTaxi();
        corridaTaxi.setEhSobretaxaNoturna(false);
        corridaTaxi.setDistanciaKm(50L);
        final Tarifa tarifaPasseio = new Tarifa();
        final Long tarifaTotal = tarifaTaxiService.calcularTarifa(corridaTaxi, tarifaPasseio);

        assertNotNull(tarifaTotal);
        assertEquals(Long.valueOf(170), tarifaTotal);
    }

    @Test
    public void whenNightSurchargeTrueAndDistanceLessThan100_thenDoubleFareWithNightSurcharge() {
        final CorridaTaxi corridaTaxi = new CorridaTaxi();
        corridaTaxi.setEhSobretaxaNoturna(true);
        corridaTaxi.setDistanciaKm(50L);
        final Tarifa tarifaPasseio = new Tarifa();
        final Long tarifaTotal = tarifaTaxiService.calcularTarifa(corridaTaxi, tarifaPasseio);

        assertNotNull(tarifaTotal);
        assertEquals(Long.valueOf(250), tarifaTotal);
    }

    @Test
    public void whenNightSurchargeFalseAndDistanceGreaterThan100_thenExtraPercentFareWithoutNightSurcharge() {
        final CorridaTaxi corridaTaxi = new CorridaTaxi();
        corridaTaxi.setEhSobretaxaNoturna(false);
        corridaTaxi.setDistanciaKm(100L);
        final Tarifa tarifaPasseio = new Tarifa();
        final Long tarifaTotal = tarifaTaxiService.calcularTarifa(corridaTaxi, tarifaPasseio);

        assertNotNull(tarifaTotal);
        assertEquals(Long.valueOf(220), tarifaTotal);
    }

    @Test
    public void whenNightSurchargeTrueAndDistanceGreaterThan100_thenExtraPercentFareWithNightSurcharge() {
        final CorridaTaxi corridaTaxi = new CorridaTaxi();
        corridaTaxi.setEhSobretaxaNoturna(true);
        corridaTaxi.setDistanciaKm(100L);
        final Tarifa tarifaPasseio = new Tarifa();
        final Long tarifaTotal = tarifaTaxiService.calcularTarifa(corridaTaxi, tarifaPasseio);

        assertNotNull(tarifaTotal);
        assertEquals(Long.valueOf(350), tarifaTotal);
    }

}
