package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

	Varasto varasto;
	double vertailuTarkkuus = 0.0001;

	@Before
	public void setUp() {
		varasto = new Varasto(10);
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

		assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
	}

	@Test
	public void lisaysEiLisaaYliMaksimiTilan() {
		varasto.lisaaVarastoon(11);

		assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
	}

	@Test
	public void saldoEiMeneNegatiiviseksiJaOttaminenEiPalautaYliSaldon() {
		varasto.lisaaVarastoon(5);
		double saatuMaara = varasto.otaVarastosta(11);
		assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
		assertEquals(5, saatuMaara, vertailuTarkkuus);
	}

	@Test
	public void tilavuusOnVahintaan0() {
		varasto = new Varasto(-1);
		assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
		varasto = new Varasto(-1, 5);
		assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);

	}

	@Test
	public void alkuSaldoOnMin0JaMaxTilavuus() {
		varasto = new Varasto(5, -1);
		assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
		varasto = new Varasto(5, 10);
		assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
		varasto = new Varasto(5, 3);
		assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);

	}

	@Test
	public void negatiivisenMaaranLisaaminenEiOnnistu() {
		varasto.lisaaVarastoon(5);
		varasto.lisaaVarastoon(-1);
		assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);

	}

	@Test
	public void negatiivisenMaaranOttaminenEiOnnistu() {
		varasto.lisaaVarastoon(5);
		varasto.otaVarastosta(-1);
		assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);

	}

	@Test
	public void toStringToimii() {
		varasto.toString();
	}

}