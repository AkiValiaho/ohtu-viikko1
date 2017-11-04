package ohtu.ohtuvarasto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }


    @Test
    public void toString_palauttaaOikein() {
        varasto = new Varasto(1,1);
        String s = varasto.toString();
        assertEquals(s, "saldo = 1.0, vielä tilaa 0.0");
    }

    @Test
    public void otaKaikkiMahdollinenVarastosta() {
        varasto = new Varasto(1, 1);
        double v = varasto.otaVarastosta(1.5);
        assertEquals(1, v, 0);
    }

    @Test
    public void otaVarastostaNegatiivinen() {
        varasto = new Varasto(1, 1);
        varasto.otaVarastosta(-1);
        assertEquals(varasto.getSaldo(), 1, 0);
    }

    @Test
    public void lisaaVarastoTayteen() {
        varasto = new Varasto(1, 0);
        varasto.lisaaVarastoon(1.5);
        assertEquals(varasto.paljonkoMahtuu(), 0, 0);
    }


    @Test
    public void lisaaVarastoon_maaraAlleNollan_return() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), 0);
    }

    @Test
    public void alkusaldoYliTilavuuden() {
        varasto =new Varasto(1, 1.5);
        assertEquals(1, varasto.getSaldo(), 0);
    }

    @Test
    public void alkusaldoSamaKuinTilavuus() {
        varasto = new Varasto(1, 1);
        assertEquals(1, varasto.getSaldo(), 0);
    }

    @Test
    public void alkusaldoPienempiKuinNolla() {
        varasto = new Varasto(0, -1);
        assertEquals(0, varasto.getSaldo(), 0);
    }

    @Test
    public void volumeLessThanOneOtherConstructor() {
        varasto = new Varasto(0, 0);
        //Tilavuuden pitäisi olla 0
        assertEquals(0, varasto.getTilavuus(), 0);
    }

    @Test
    public void volumeMoreThanOneOtherConstructor() {
        varasto = new Varasto(1.1, 0);
        //Tilavuuden pitäisi olla 1.1
        assertEquals(1.1, varasto.getTilavuus(), 0);
    }

    @Test
    public void volumeLessThanOne() {
        varasto = new Varasto(0);
        //Tilavuuden pitäisi olla 0
        assertEquals(0, varasto.getTilavuus(), 0);
    }

    @Test
    public void volumeMoreThanOne() {
        varasto = new Varasto(1.1);
        //Tilavuuden pitäisi olla 1.1
        assertEquals(1.1, varasto.getTilavuus(), 0);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}