<%
if(currentUser == null)
{
%>

    <table border="0" cellpadding="0" cellspacing="0" width="550">
        <tr height="30"><td><img src="images/title-left.png" /></td>
            <td class="title-center" width="100%">Mensaje</td>
            <td><img src="images/title-right.png" /></td></tr>
            
        <tr><td class="cont-outer" colspan="3">
            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                <tr><td class="cont-inner">
                
                    <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                        <tr><td class="login-warning"><strong>Advertencia</strong>
                            <p>Usted ha ingresado a un área restringida. Por favor inicie sesión para ver el contenido de esta página</p></td></tr></table>
                
                </td></tr></table>
        </td></tr>
    </table>

<%
}
%>