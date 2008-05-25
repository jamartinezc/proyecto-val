            <!-- Tabla del menu derecho-->
            <table width="200" cellpadding="0" cellspacing="0">
            	<tr height="30"><td><img src="images/title-left.png" /></td>
                    <td width="180" class="title-center"><%=rightTitle%></td>
                    <td><img src="images/title-right.png" /></td></tr>
                    
                <tr><td class="cont-outer" colspan="3" width="200">
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr><td class="cont-inner">
                        
                        <%
                        if(rightContent != null)
                        {
                            out.println(rightContent);
                        }
                        else
                        {
                        %>
                        
                            <p style="color:#ff0000"><strong>¡Ups!</strong><br />No se ha escrito texto de ayuda para está sección</p>
                        
                        <%
                        }
                        %>
                        
                        </td></tr></table>
                </td></tr>
            </table>
                         