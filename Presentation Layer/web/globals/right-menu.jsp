            <!-- Tabla del menu derecho-->
            <table width="200" cellpadding="0" cellspacing="0">
            	<tr height="30"><td><img src="images/title-left.png" /></td>
                	<td width="180" class="title-center">Ayuda</td>
                    <td><img src="images/title-right.png" /></td></tr>
                    
				<tr><td class="cont-outer" colspan="3" width="200">
                	<table border="0" cellspacing="0" cellpadding="0">
                    	<tr><td class="cont-inner"><%if(parrafoDeAyuda != null){
                                 %><p><%=parrafoDeAyuda%></p><%
                        }else{%><p><%="Aca deber�amos escribir algo como para ayudar al usuario en cada secci�n, como lo" +
                        "que deber�a hacer o no s� depronto m�s opciones para alg�n caso de uso. En fin, el espacio queda abierto para"+
                        "que ustedes decidan."%></p><%}%></td></tr></table>
				</td></tr>
			</table>
                         