<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/umowa">

		<fo:root font-family="ttfarial">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait"
					page-height="29.7cm" page-width="21.0cm" margin="2cm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
					<fo:block text-align-last="center">
						<xsl:value-of select="tytul"/> nr <xsl:value-of select="nrUmowy" />
					</fo:block>
					<fo:block>&#160;</fo:block>
					<fo:block>
						Zawarta w dniu <xsl:value-of select="dataZawarcia"/> w Warszawie
					</fo:block>
					<fo:block>
						pomiędzy Politechniką Warszawską - <xsl:value-of select="jednostka"/> 
					</fo:block>
					<fo:block>
						 <xsl:value-of select="adresJednostki"/> 
					</fo:block>
					<fo:block>
						 zwaną dalej „Zleceniodawcą” reprezentowanym przez
					</fo:block>
					<fo:block>
						 <xsl:value-of select="reprezentant"/> 
					</fo:block>
					<fo:block>
						 a
					</fo:block>
					<fo:block>
						<xsl:value-of select="pracownik"/> 
					</fo:block>
					<fo:block>
						zamieszkała /y 
					</fo:block>
					<fo:block>
						<xsl:value-of select="adresPracownika"/> 
					</fo:block>
					<fo:block>
						 zwanym dalej „Zleceniobiorcą”.
					</fo:block>
					<fo:block text-align-last="center">
						 § 1
					</fo:block>
					<fo:block>
						Zleceniodawca zleca, a Zleceniobiorca zobowiązuje się do wykonania  z dołożeniem należytej staranności:
					</fo:block>
					<fo:block>
						<xsl:value-of select="przedmiotUmowy"/> 
					</fo:block>
					<fo:block>
						Zleceniobiorca z tytułu realizacji umowy otrzyma wynagrodzenie brutto w wysokości
					</fo:block>
					<fo:block>
						<xsl:value-of select="wynagrodzenie"/> zł, płatne
						<xsl:value-of select="platnosc"/>.
					</fo:block>
					<fo:block text-align-last="center">
						 § 2
					</fo:block>
					<fo:block>
						Zleceniobiorca wystawi Zleceniodawcy rachunek/rachunki, które będą stanowić podstawę do wypłaty wynagrodzenia.
					</fo:block>
					<fo:block>
						Wypłata wynagrodzenia nastąpi nie później niż 18 dnia miesiąca następującego po miesiącu, w którym dostarczono rachunek do Zleceniodawcy.
					</fo:block>
					<fo:block text-align-last="center">
						 § 3
					</fo:block>
					<fo:block>
						W razie zwłoki Zleceniobiorcy w wykonaniu zlecenia Zleceniodawca może umowę rozwiązać w trybie natychmiastowym, bez prawa Zleceniobiorcy do wynagrodzenia. 
					</fo:block>
					<fo:block>
						Na podstawie art. 24 ust. 1 z dnia 29 sierpnia 1997 roku o ochronie danych osobowych (Dz. U. z 2002 r. Nr 101 poz. 926 z późn. zm.) Zleceniodawca informuje Zleceniobiorcę, że administratorem danych, przetwarzającym dane osobowe jest Politechnika Warszawska z siedzibą w Warszawie, Plac Politechniki 1. Dane osobowe są przetwarzane wyłącznie w celu wykonania zadań administratora danych wynikających z tej umowy. Zleceniobiorca ma prawo dostępu do treści swoich danych osobowych przetwarzanych przez Politechnikę Warszawską, a także prawo do ich poprawiania.
					</fo:block>
					<fo:block text-align-last="center">
						 § 4
					</fo:block>
					<fo:block>
						Wszelkie zmiany umowy wymagają formy pisemnej pod rygorem nieważności.
					</fo:block>
					<fo:block>
						Do spraw nieuregulowanych niniejszą umową mają zastosowanie przepisy prawa w szczególności Kodeksu cywilnego.
					</fo:block>
					<fo:block>
						Wszelkie spory dotyczące realizacji umowy będą rozstrzygane w pierwszej kolejności na drodze polubownej.
					</fo:block>
					<fo:block>
					Do rozstrzygania sporów nierozstrzygniętych na drodze polubownej właściwym sądem jest sąd powszechny właściwy dla siedziby Zleceniodawcy.
					</fo:block>
					<fo:block>
						Umowę sporządzono w dwóch jednobrzmiących egzemplarzach, po jednym dla każdej ze stron.
					</fo:block>
					<fo:block>&#160;</fo:block><fo:block>&#160;</fo:block><fo:block>&#160;</fo:block><fo:block>&#160;</fo:block>
					<fo:block>
					
					<fo:table table-layout="fixed" border-style="none">
						<fo:table-body>
					
							<fo:table-row>
								<fo:table-cell border-style="none">
									<fo:block>
										................................................
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="none">
									<fo:block>
										................................................
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border-style="none">
									<fo:block>
										Zleceniodawca
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="none">
									<fo:block>
										Zleceniobiorca
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>