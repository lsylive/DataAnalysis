<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:variable name="icons_src_dir">.</xsl:variable>
	
	<xsl:template match="item">
		<div align="center" style="width:100px!important;height:90px!important;">
			<img >
					<xsl:attribute name="src"><xsl:value-of select="$icons_src_dir"/>/<xsl:value-of select="./@image"/></xsl:attribute>
			</img>
			<div class="dhx_folders_FTILES_item_text" style="font-size:12px;width:90px!important;height:auto!important;text-align:center!important;">
				  <span><xsl:value-of select="./@name"/></span>
			</div>
		</div>
	</xsl:template>
	
</xsl:stylesheet>