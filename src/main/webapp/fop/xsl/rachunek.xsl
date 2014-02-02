<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/rachunek">
		<fo:root font-family="ttfarial">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait"
					page-height="29.7cm" page-width="21.0cm" margin="2cm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
					<fo:block text-align-last="right">
						<xsl:value-of select="miejscowosc" />, <xsl:value-of select="dataWystawienia" />
					</fo:block>
					<fo:block>
						<xsl:value-of select="pracownik" />
					</fo:block>
					<fo:block>
						<xsl:value-of select="typNumeru" />:<xsl:value-of select="numer" />
					</fo:block>
					<fo:block>&#160;</fo:block>
					<fo:block text-align-last="center">RACHUNEK nr <xsl:value-of select="nrRachunku"/></fo:block>
					<fo:block>&#160;</fo:block>
					<fo:block>
						Dla Politechniki Warszawskiej - <xsl:value-of select="jednostka"/>
					</fo:block>
					<fo:block>
						 <xsl:value-of select="adresJednostki"/> 
					</fo:block>
					<fo:block>
						za <xsl:value-of select="przedmiotUmowy"/>
					</fo:block>
					<fo:block>
						wykonane w ramach umowy nr <xsl:value-of select="nrUmowy"/>
					</fo:block>
					<fo:block>
						na kwotę <xsl:value-of select="kwota"/> zł brutto
					</fo:block>
					<fo:block>&#160;</fo:block>
					<fo:block>&#160;</fo:block>
					<fo:block>&#160;</fo:block>
					<fo:block>................................................</fo:block>
					<fo:block>podpis wystawcy rachunku</fo:block>
					
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>