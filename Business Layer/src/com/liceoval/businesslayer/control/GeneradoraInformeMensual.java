/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import Errores.NoItemFoundException;
import com.liceoval.businesslayer.control.registro.ControladoraDeRegistro;
import com.liceoval.businesslayer.control.registro.exceptions.NotaNoCalculableException;
import com.liceoval.businesslayer.control.registro.exceptions.RegistroNoEncontradoException;
import com.liceoval.businesslayer.entities.Estado;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneradoraInformeMensual {
    
    public static void generarInformesMensuales() throws NoItemFoundException{
        
        Calendar fecha = Calendar.getInstance();//fecha de hoy
        //obtener el mes anterior, el que se acaba de completar, el del informe.
        fecha.add(Calendar.MONTH, -1);
        
        String mesInforme = fecha.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es"));//cambiar por fecha factory
        int mes = fecha.get(Calendar.MONTH);
        String añoInforme = String.valueOf(fecha.get(Calendar.YEAR));//getDisplayName(Calendar.YEAR, Calendar.LONG);
        List<VO.Estudiante> estudiantes = DAO.DaoEstudiante.consultarTodos();
        //consultar estudiantes por curso y contar los examenes del curso antes de empezar
        Iterator<VO.Estudiante> itEstudiantes = estudiantes.iterator();
        
        while(itEstudiantes.hasNext()){
            
            Collection<VO.ExamenMes> ExamenesPorMes;
            int ganadosTotal=0;
            int noPresentados=0;
            VO.Estudiante estudiante = itEstudiantes.next();
            //VO.Estudiante estudiante = DAO.DaoEstudiante.consultarUno(290);//borrar 
            int idEstudiante = estudiante.getIdEstudiante();
            
            //nombre para insertar en el informe
            String nombreEstudiante = estudiante.getIdUsuario().getNombres()+" "+estudiante.getIdUsuario().getApellidos();
            String codigoEstudiante = estudiante.getIdEstudiante().toString();
            
            //consultar cuantos examenes planeó
            String planeadasEstudiante;
            int cantidadPlaneada=0;
            //ExamenesPorMes = estudiante.getPlaneacionAnual().getMateriaPlaneadaCollection();
            
            //ExamenesPorMes = DUMMY.DAO.DaoMateriaPlaneada.getExamenesMes(idEstudiante, mes);//DUMMY.DAO.DaoMateriaPlaneada.getMateriasPlaneadasMes(idEstudiante, mes);
            try {
                ExamenesPorMes = DAO.DaoExamenMes.examenesMesDeEstudianteEnMes(idEstudiante, mes);
            } catch (NoItemFoundException ex) {
                ExamenesPorMes = new LinkedList<VO.ExamenMes>();
            }
            
            Iterator<VO.ExamenMes> itExamenesPorMes = ExamenesPorMes.iterator();
            VO.ExamenMes ExamenesEsteMes;
            while(itExamenesPorMes.hasNext()){//un estudiante ve pocas materias, este ciclo itera unas 3 veces
                ExamenesEsteMes = itExamenesPorMes.next();
                cantidadPlaneada += ExamenesEsteMes.getplaneados();//materiaPlaneada.getIdExamenMes().getPresentados();
                
            }
            planeadasEstudiante = String.valueOf(cantidadPlaneada);
            
            //consultar examenes ganados antes y ganados en este mes, por materia del estudiante
            //preparar la lista de materias en un Properties
            Properties presentadosMateria = new Properties();
            Properties ganadosAntesMateria = new Properties();
            Properties ganadosMesMateria = new Properties();
            Properties totalExamenesDeMateria = new Properties();
            Properties notasDefinitivasMateria = new Properties();
            int grado = estudiante.getIdGrado().getIdGrado();
            Collection<VO.Registro> registros = estudiante.getRegistroCollection();//DAO.DaoRegistro.consultarRegistrosActivosInactivos(idEstudiante, true);
            Iterator<VO.Registro> itRegistros = registros.iterator();
            int presentados=0;
            while(itRegistros.hasNext()){
                VO.Registro registro = itRegistros.next();
                //int idMateria = registro.getIdMateria().getIdMateria();
                registro.getExamenSolicitadoCollection();
                Collection <VO.ExamenSolicitado> examenesPresentados = registro.getExamenSolicitadoCollection();//DUMMY.DAO.DaoExamenSolicitado.consultarExamenesSolicitadosPorRegistro(registro);
                //recorrer examenes presentados y guardar los ganados y presentados en los Properties
                Iterator<VO.ExamenSolicitado> itExamenesPresentados=examenesPresentados.iterator();
                int ganadosAntes=0,ganadosMes=0;
                while(itExamenesPresentados.hasNext()){
                    VO.ExamenSolicitado examenPresentado = itExamenesPresentados.next();
                    int resultado = examenPresentado.getIdEstado().getIdEstado();
                        
                        if( resultado==Estado.GANADO.getIdEstado() ) {
                            Calendar fechaPresentado = Calendar.getInstance();
                            fechaPresentado.setTime(examenPresentado.getFecha());
                            if(fechaPresentado.get(Calendar.MONTH) != mes){
                                ganadosAntes++;
                            }else{
                                ganadosMes++;
                                ganadosTotal++;
                            }
                            presentados++;
                        }else if( resultado==Estado.NO_PRESENTADO.getIdEstado() ){
                            noPresentados++;
                        }else{
                            presentados++;
                        }
                    
                }
                presentadosMateria.setProperty(registro.getIdMateria().getNombre(),String.valueOf(presentados) );
                ganadosAntesMateria.setProperty(registro.getIdMateria().getNombre(),String.valueOf(ganadosAntes) );
                ganadosMesMateria.setProperty(registro.getIdMateria().getNombre(),String.valueOf(ganadosMes) );
                
                float notaRegistro;
                if( !registro.getActivo() ){
                    try {
                        notaRegistro = ControladoraDeRegistro.calcularNotaDefinitiva(registro.getIdRegistro());
                        notasDefinitivasMateria.setProperty(registro.getIdMateria().getNombre(),String.valueOf(notaRegistro) );
                    } catch (NotaNoCalculableException ex) {
                        ex.printStackTrace();
                        // como se verifica que el registro sea inactivo, esta excepcion no deveria lanzarce
                    } catch (RegistroNoEncontradoException ex) {
                        ex.printStackTrace();
                        // como se obtienen todos los registros del estudiante, esta excepcion no deveria lanzarce
                    }
                }
                
                
                
            }
            //contar los examenes del curso por materia
            Collection<VO.Materia> materiasDelGrado = null;
            try {
                materiasDelGrado = DAO.DaoMateria.materiasDeGrado(grado);
            } catch (NoItemFoundException ex) {
                materiasDelGrado = new LinkedList();
            }
            Iterator<VO.Materia> itMateriasDelGrado = materiasDelGrado.iterator();
            while(itMateriasDelGrado.hasNext()){
                VO.Materia materiaDelGrado = itMateriasDelGrado.next();
                String nombreMateria = materiaDelGrado.getNombre();
                totalExamenesDeMateria.setProperty( nombreMateria, String.valueOf(materiaDelGrado.getExamenCollection().size()) );
            }
            System.out.println("parametros: "+codigoEstudiante+" "+ nombreEstudiante+" "+ grado+" "+ noPresentados+" "+ ganadosTotal+" "+mesInforme+" "+añoInforme+" "+ planeadasEstudiante+" "+ presentados+" "+ ganadosAntesMateria+" "+ ganadosMesMateria+" "+totalExamenesDeMateria+" "+notasDefinitivasMateria);
            String informeGenerado = generarInforme(codigoEstudiante, nombreEstudiante, grado, noPresentados, ganadosTotal,mesInforme, añoInforme, planeadasEstudiante, presentados, ganadosAntesMateria, ganadosMesMateria, totalExamenesDeMateria,notasDefinitivasMateria);
            System.out.println(informeGenerado);
        }
    }
    
    private static String generarInforme(String codigoEstudiante, String nombreEstudiante,int grado, int noPresentados, int ganadosTotal, String mesInforme, String añoInforme, String planeadasEstudiante, int presentados, Properties ganadosAntesMateria, Properties ganadosMesMateria, Properties totalExamenesDeMateria, Properties notasDefinitivasMateria){
        
        String informe="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"+
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"+
                "<head>\n"+
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"+
                "<style type=\"text/css\">\n"+
                "body{\n"+
                "	color:#ffffff;\n"+
                "	font-size:9pt;\n"+
                "	margin-top:0pc;\n"+
                "	margin-left:0px;\n"+
                "	margin-right:0px;\n"+
                "	margin-bottom:0px;\n"+
                "	background-color:#003399;\n"+
                "	font-family:'Trebuchet MS', Tahoma, Verdana, Arial, Helvetica, Sans}\n"+
                "\n"+
                ".system-logo{\n"+
                "	padding-top:0px;\n"+
                "	padding-left:0px;\n"+
                "	padding-right:0px;\n"+
                "	padding-bottom:0px;\n"+
                "	background-repeat:repeat-x;\n"+
                "	background-image:url(./logo-back.jpg)}\n"+
                "	\n"+
                ".system-info{\"\n"+
                "	color:#000000;\n"+
                "	padding-top:4px;\n"+
                "	padding-left:4px;\n"+
                "	padding-right:4px;\n"+
                "	padding-bottom:4px;\n"+
                "	background-repeat:repeat-x;\n"+
                "	background-image:url(./logo-back.jpg)}\n"+
                "	\n"+
                ".nav-bar{\n"+
                "	color:#ffffff;\n"+
                "	font-weight:bold;\n"+
                "	padding-left:5px;\n"+
                "	background-repeat:repeat-x;\n"+
                "	background-image:url(./back-bar.jpg)}\n"+
                "	\n"+
                ".nav-bar-link:link{\n"+
                "	color:#ffffff;\n"+
                "	font-weight:bold;\n"+
                "	text-decoration:none}\n"+
                "	\n"+
                ".nav-bar-link:active{\n"+
                "	color:#ffffff;\n"+
                "	font-weight:bold;\n"+
                "	text-decoration:none}\n"+
                "	\n"+
                ".nav-bar-link:visited{\n"+
                "	color:#ffffff;\n"+
                "	font-weight:bold;\n"+
                "	text-decoration:none}\n"+
                "	\n"+
                ".nav-bar-link:hover{\n"+
                "	color:#ffffff;\n"+
                "	font-weight:bold;\n"+
                "	text-decoration:underline}\n"+
                "	\n"+
                ".title-center{\n"+
                "	color:#ffffff;\n"+
                "	font-size:10pt;\n"+
                "	font-weight:bold;\n"+
                "       text-align:center;\n"+
                "	background-repeat:repeat-x;\n"+
                "	background-image:url(./title-center.png)}\n"+
                "	\n"+
                ".cont-outer{\n"+
                "	padding-top:2px;\n"+
                "	padding-left:3px;\n"+
                "	padding-right:3px;\n"+
                "	padding-bottom:2px;\n"+
                "	background-color:#7BB1D9;\n"+
                "	border-top:1px solid #000000;\n"+
                "	border-left:1px solid #000000;\n"+
                "	border-right:1px solid #000000;\n"+
                "	border-bottom:1px solid #000000}\n"+
                "	\n"+
                ".cont-inner{\n"+
                "	color:#000000;\n"+
                "	padding-top:3px;\n"+
                "	padding-left:4px;\n"+
                "	padding-right:4px;\n"+
                "	padding-bottom:3px;\n"+
                "	background-color:#ffffff;\n"+
                "	border-top:1px solid #000000;\n"+
                "	border-left:1px solid #000000;\n"+
                "	border-right:1px solid #000000;\n"+
                "	border-bottom:1px solid #000000}\n"+
                "	\n"+
                "a:link{\n"+
                "	color:#003399;\n"+
                "	text-decoration:none}\n"+
                "	\n"+
                "a:active{\n"+
                "	color:#0066CC;\n"+
                "	text-decoration:unerline}\n"+
                "	\n"+
                "a:visited{\n"+
                "	color:#0033CC;\n"+
                "	text-decoration:none}\n"+
                "	\n"+
                "a:hover{\n"+
                "	color:#0066CC;\n"+
                "	text-decoration:underline}\n"+
                "	\n"+
                "	\n"+
                ".center-padding{\n"+
                "	padding-left:20px;\n"+
                "	padding-right:20px}\n"+
                "</style>\n"+
                "<title>Informe Mensual</title>\n"+
                "</head>\n"+
                "<body>\n"+
                "<p align=\"center\"> <b><br><br><br><br>LICEO VAL (Vida, Amor, Luz)<br /></b></p>\n"+
                "<!-- , ,, , , , , , , ganadosAntesMateria, ganadosMesMateria, totalExamenesDeMateria -->\n"+
                "<table border=\"0\" style=\"margin-left:auto; margin-right:auto\" cellpadding=\"0\" cellspacing=\"0\" width=\"550\">\n"+
                "<tr height=\"30\"><td><img src=\"./title-left.png\" /></td>\n"+
                "<td class=\"title-center\" width=\"100%\">INFORME   MENSUAL  DE  DESEMPEÑO</td>\n"+
                "<td><img src=\"./title-right.png\" /></td></tr>\n"+
                "<tr><td class=\"cont-outer\" colspan=\"3\">\n"+
                "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n"+
                "        <tr><td class=\"cont-inner\">\n"+
                "            <table cellspacing=\"0\" cellpadding=\"0\">\n"+
                "              <col width=\"244\" />\n"+
                "              <col width=\"110\" />\n"+
                "              <col width=\"112\" />\n"+
                "              <col width=\"104\" />\n"+
                "              <col width=\"88\" />\n"+
                "              <col width=\"82\" />\n"+
                "              <col width=\"88\" />\n"+
                "              <tr height=\"22\">\n"+
                "                <td height=\"22\" width=\"244\">Nombre:</td>\n"+
                "                <td width=\"110\">#nombreEstudiante#</td>\n"+
                "                <td width=\"112\"></td>\n"+
                "                <td width=\"104\"></td>\n"+
                "                <td width=\"88\"></td>\n"+
                "                <td width=\"82\">Código:</td>\n"+
                "                <td align=\"right\" width=\"88\">#codigoEstudiante#</td>\n"+
                "              </tr>\n"+
                "              <tr height=\"22\">\n"+
                "                <td height=\"22\">Mes:</td>\n"+
                "                <td>#mesInforme#</td>\n"+
                "                <td></td>\n"+
                "                <td>Año:</td>\n"+
                "                <td>#añoInforme#</td>\n"+
                "                <td>Grado:</td>\n"+
                "                <td align=\"right\">#grado#</td>\n"+
                "              </tr>\n"+
                "              <tr height=\"10\">\n"+
                "                <td height=\"10\"></td>\n"+
                "                <td> </td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"16\">\n"+
                "                <td height=\"16\">EVALUACIONES</td>\n"+
                "                <td>Nº</td>\n"+
                "                <td></td>\n"+
                "                <td colspan=\"4\"></td>\n"+
                "                <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"16\">\n"+
                "                <td height=\"16\"></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td> </td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"14\">\n"+
                "                <td height=\"14\">· Programadas</td>\n"+
                "                <td>#planeadasEstudiante#</td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"14\">\n"+
                "                <td height=\"14\">· No Presentadas</td>\n"+
                "                <td>#noPresentados#</td>\n"+
                "                <td></td>\n"+
                "                <td>  </td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "                <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"14\">\n"+
                "                <td height=\"14\">· Presentadas</td>\n"+
                "                <td>#presentadas#</td>\n"+
                "                <td></td>\n"+
                  "              <td> </td>\n"+
                  "              <td> </td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"14\">\n"+
                  "              <td height=\"14\">· Aprobadas</td>\n"+
                  "              <td>#ganadosTotal#</td>\n"+
                  "              <td></td>\n"+
                  "              <td> </td>\n"+
                  "              <td> </td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"14\">\n"+
                  "              <td height=\"14\"></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"18\">\n"+
                  "              <td height=\"18\">SITUACIÓN ACADÉMICA</td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                "              <tr>\n"+
                "              <table border=\"1\">\n"+
                "              <tr height=\"20\" style=\"border:3px\">\n"+
                  "              <td height=\"20\">&nbsp;</td>\n"+
                  "              <td colspan=\"5\" width=\"496\">EVALUACIONES</td>\n"+
                  "              <td width=\"88\"></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"20\">\n"+
                  "              <td height=\"20\">ÁREAS</td>\n"+
                  "              <td>TOTAL</td>\n"+
                  "              <td>APROBADAS</td>\n"+
                  "              <td>YA</td>\n"+
                  "              <td>FALTAN</td>\n"+
                  "              <td>NOTA</td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"20\">\n"+
                  "              <td height=\"20\">&nbsp;</td>\n"+
                  "              <td>GRADO</td>\n"+
                  "              <td>EN EL MES</td>\n"+
                  "              <td>APROBADAS</td>\n"+
                  "              <td> </td>\n"+
                  "              <td>DEFINITIVA</td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"20\">\n"+
                  "              <td height=\"20\">TRABAJO INDIVIDUAL</td>\n"+
                  "              <td>&nbsp;</td>\n"+
                  "              <td>&nbsp;</td>\n"+
                  "              <td>&nbsp;</td>\n"+
                  "              <td>&nbsp;</td>\n"+
                  "              <td>&nbsp;</td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                             "#TablaExamenes#\n"+
                "              <tr height=\"20\">\n"+
                  "              <td height=\"20\">TOTAL  GRADO</td>\n"+
                  "              <td>#totalGrado#</td>\n"+
                  "              <td>#aprovadasMes#</td>\n"+
                  "              <td>#aprovadasAntes#</td>\n"+
                  "              <td>#faltan#</td>\n"+
                  "              <td> </td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                "              <tr height=\"20\">\n"+
                  "              <td height=\"20\">POCENTAJE  GRADO (%)  </td>\n"+
                  "              <td>100</td>\n"+
                  "              <td>#%aprovadasMes#</td>\n"+
                  "              <td>#%aprovadasAntes#</td>\n"+
                  "              <td>#%faltan#</td>\n"+
                  "              <td>&nbsp;</td>\n"+
                  "              <td></td>\n"+
                "              </tr>\n"+
                "             </table>\n"+
                "         </tr>\n"+
                "            </table>\n"+
                "    </td></tr>\n"+
                "</table>\n"+
                "</td></tr>\n"+
                "</table>\n"+
                "<p>&nbsp;</p>\n"+
                "</body>\n"+
                "</html>\n";
        //ganadosMesTotal=0,ganadosAntesTotal=0,faltanTotal=0
                
        //int totalGrado, totalAprovadas, totalFaltan;
        informe = informe.replace("#nombreEstudiante#", nombreEstudiante);
        informe = informe.replace("#codigoEstudiante#", codigoEstudiante);
        informe = informe.replace("#grado#", String.valueOf(grado));
        informe = informe.replace("#noPresentados#", String.valueOf(noPresentados));
        informe = informe.replace("#ganadosTotal#", String.valueOf(ganadosTotal));
        informe = informe.replace("#mesInforme#", mesInforme);
        informe = informe.replace("#añoInforme#", añoInforme);
        informe = informe.replace("#planeadasEstudiante#", planeadasEstudiante);
        informe = informe.replace("#nombreEstudiante#", nombreEstudiante);
        informe = informe.replace("#presentadas#", String.valueOf(presentados));
        
        // presentadosMateria, ganadosAntesMateria, ganadosMesMateria, totalExamenesDeMateria
        
        
                  
        //Crear string de la lista de materias con ganados presentados etc
        String plantillaTablaMaterias=
                  "              <tr height=\"20\">\n"+
                  "              <td height=\"20\">#nombreDeMateria#</td>\n"+
                  "              <td>#totalMateria#</td>\n"+
                  "              <td>#ganadosMes#</td>\n"+
                  "              <td>#ganadosAntes#</td>\n"+
                  "              <td>#faltan#</td>\n"+
                  "              <td>#notaDefinitiva#</td>\n"+
                  "              <td></td>\n"+
                  "              </tr>\n";
        String TablaMaterias="";
        Enumeration materias = totalExamenesDeMateria.propertyNames();
        int ganadosMesTotal=0,ganadosAntesTotal=0,faltanTotal=0,totalGrado=0;
        while(materias.hasMoreElements()){
            Object materia = materias.nextElement();
            TablaMaterias+=plantillaTablaMaterias;
            TablaMaterias = TablaMaterias.replace("#nombreDeMateria#", materia.toString());
            String total = totalExamenesDeMateria.getProperty(materia.toString());
            TablaMaterias = TablaMaterias.replace("#totalMateria#", total);
            totalGrado += Integer.parseInt(total);
            String ganadosM = ganadosMesMateria.getProperty(materia.toString(), "0");
            TablaMaterias = TablaMaterias.replace("#ganadosMes#", ganadosM);
            ganadosMesTotal += Integer.parseInt(ganadosM);
            String ganadosA = ganadosAntesMateria.getProperty(materia.toString(), "0");
            TablaMaterias = TablaMaterias.replace("#ganadosAntes#", ganadosA);
            ganadosAntesTotal += Integer.parseInt(ganadosA); 
            int faltan = Integer.parseInt(total) -(Integer.parseInt(ganadosM) + Integer.parseInt(ganadosA));
            TablaMaterias = TablaMaterias.replace("#faltan#", String.valueOf(faltan));
            faltanTotal += faltan;
            String notaDef = notasDefinitivasMateria.getProperty(materia.toString(), "");
            TablaMaterias = TablaMaterias.replace("#notaDefinitiva#", notaDef);
            
        }
        informe = informe.replace("#TablaExamenes#", TablaMaterias);
        
        informe = informe.replace("#totalGrado#", String.valueOf(totalGrado));
        informe = informe.replace("#aprovadasMes#", String.valueOf(ganadosMesTotal));
        informe = informe.replace("#aprovadasAntes#", String.valueOf(ganadosAntesTotal));
        informe = informe.replace("#faltan#", String.valueOf(faltanTotal));
        
        informe = informe.replace("#%aprovadasMes#", String.valueOf(Math.round(((float)ganadosMesTotal/(float)totalGrado)*100)));
        informe = informe.replace("#%aprovadasAntes#", String.valueOf(Math.round(((float)ganadosAntesTotal/(float)totalGrado)*100)));
        informe = informe.replace("#%faltan#", String.valueOf(Math.round(((float)faltanTotal/(float)totalGrado)*100)));
        return informe;
    }
    
    private static void enviarInforme(String informe){
        
    }
    
    public static void main(String[] args) throws NoItemFoundException {
        //generarInformesMensuales();
        //String informe = generarInforme("", "", 0, 0, 0, "", "", "", new Properties(), new Properties(), new Properties(), new Properties());
        //System.out.println(informe);
        generarInformesMensuales();
        
    }
}
