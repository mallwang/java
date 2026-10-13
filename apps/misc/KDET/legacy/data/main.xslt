<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:bcx="http://de.bundesbank.de/cocas/export/calibrationdata"
	version="2.0">

<xsl:output encoding="UTF-8" method="xml" omit-xml-declaration="yes"/>

<!-- Section legt fest welcher Teil der Kalibrierungsdaten transformiert werden soll. [accounts | companies | ratings | additionals | defaults | externalRatings] -->
<xsl:param name="Section">ratings</xsl:param>

<!-- Leerzeichen und Umbrüche entfernen -->
<xsl:strip-space elements="*"/>

<!-- Auswahl der zu exportierenden Daten je nach Eingabe des Parameters "Section" -->
<xsl:template match="/bcx:calibrationDataFile">
	<xsl:choose>
		<xsl:when test="$Section='accounts'">
			<xsl:copy-of select="//bcx:account"/>
		</xsl:when>
		<xsl:when test="$Section='companies'">
			<xsl:copy-of select="//bcx:company"/>
		</xsl:when>
		<xsl:when test="$Section='ratings'">
			<xsl:copy-of select="//bcx:rating"/>
		</xsl:when>
		<xsl:when test="$Section='externalRatings'">
			<xsl:copy-of select="//bcx:externalRating"/>
		</xsl:when>
		<xsl:when test="$Section='additionals'">
			<xsl:copy-of select="//bcx:additionalData"/>
		</xsl:when>
<!--		<xsl:when test="$Section='externalRatings'">
			<xsl:copy-of select="//bcx:externalRating"/>
		</xsl:when>-->
		<xsl:when test="$Section='defaults'">
			<xsl:copy-of select="//bcx:default"/>
		</xsl:when>
		<xsl:otherwise>
			<xsl:element name="Error">
				Der Übergebene Parameter ist falsch.
				<xsl:element name="parameter">
					<xsl:element name="Section"><xsl:value-of select="$Section"/></xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<!-- Exportierung der Daten -->
<xsl:template match="bcx:company | bcx:additionalData | bcx:externalRating | bcx:default | bcx:account | bcx:rating">
	<xsl:copy-of select="."/>
</xsl:template>

</xsl:stylesheet>