<xsl:stylesheet version="2.0"
	xmlns:bcx="http://de.bundesbank.de/cocas/export/calibrationdata"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema">

<xsl:output encoding="Windows-1252" method="text"/>

<xsl:param name="lf" as="xs:string" select="'&#10;'"/>
<xsl:param name="cs" as="xs:string" select="';'"/>
<xsl:param name="rs" as="xs:string" select="'_'"/>

<xsl:output method="text"/>

<xsl:key name="k1" match="bcx:column" use="if (not(@scenario)) then @name else concat(@scenario, $rs, @name)"/>

<xsl:variable name="main-input" select="/"/>

<xsl:variable name="cols" as="element(column)*">
	<xsl:for-each-group select="/calibrationDataFile/bcx:account/bcx:column" group-by="if (not(@scenario)) then @name else concat(@scenario, $rs, @name)">
		<!--
		<xsl:sort select="@name"/>
		<xsl:sort select="@scenario"/>
		-->
		<column key="{current-grouping-key()}"/>
	</xsl:for-each-group>
</xsl:variable>

<xsl:template match="calibrationDataFile">
	<xsl:value-of select="concat('companyID',$cs,'schemaCountry',$cs,'schemaAccountingType',$cs,'schemaVersion',$cs,'accountID',$cs,'identNumber',$cs,'status',$cs,'certificationDate',$cs,'referenceDate',$cs)"/>
	<xsl:value-of select="$cols/@key" separator="{$cs}"/>
	<xsl:value-of select="$lf"/>
	<xsl:apply-templates select="bcx:account"/>
</xsl:template>

<xsl:template match="bcx:account">
	<xsl:value-of select="concat(@companyID,$cs,@schemaCountry,$cs,@schemaAccountingType,$cs,@schemaVersion,$cs,@accountID,$cs,@identNumber,$cs,@status,$cs,@certificationDate,$cs,@referenceDate,$cs)"/>
	<xsl:value-of select="for $col in $cols
												return
													(if (key('k1', $col/@key, current())) then replace(replace(key('k1', $col/@key, current()), '\n', ' '), ';', ',') else '')" separator="{$cs}"/>
	<xsl:value-of select="$lf"/>
</xsl:template>

</xsl:stylesheet>