/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.rendimiento;

import com.liceoval.businesslayer.control.rcp.exceptions.EstudianteNoEncontradoException;
import com.liceoval.businesslayer.control.rcp.RCPRegistros;
import com.liceoval.businesslayer.entities.Estado;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.ExamenSolicitado;
import com.liceoval.businesslayer.entities.PlaneacionAnual;
import com.liceoval.businesslayer.entities.MateriaPlaneada;
import com.liceoval.businesslayer.entities.Registro;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;

import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYDataItem;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/** Produce el Dataset para la gráfica de rendimiento.
 *
 *  @author Sergio
 */
public class GeneradoraGraficoRendimiento implements DatasetProducer
{
    /** Produce el DataSet para la gráfica.
     * 
     *  @param params Los parámetros entregados por el Servlet:
     *      <p style="padding-left:20px"><b>idEstdiante:</b> El id del estudiante</p>
     *  @return El DataSet para la gráfica.
     */
    public Object produceDataset(Map params) throws DatasetProduceException
    {
        Iterator<MateriaPlaneada> materiasPlaneadasIterator;
        Iterator<ExamenSolicitado> examenesSolicitadosIterator;
        Iterator<Registro> registrosIterator;
        ExamenSolicitado examenSolicitado;
        PlaneacionAnual planeacionAnual;
        MateriaPlaneada materiaPlaneada;
        XYDataItem currentDataItem;
        Date fechaInicioGrado;
        Estudiante estudiante;
        Calendar calInicial;
        Calendar calExamen;
        Registro registro;
        Map planeadosMes;
        int idEstudiante;
        int[] planeados;
        int[] ganados;
        Integer month;
        int mes;
        int i;
        
        XYSeries seriePlaneados;
        XYSeries serieGanados;
        
        idEstudiante = ((Integer)params.get("idEstudiante")).intValue();

        try
        {
            estudiante = RCPRegistros.getEstudiante(idEstudiante);
        }
        catch(EstudianteNoEncontradoException eneEx)
        {
            throw new DatasetProduceException();
        }      
        
        fechaInicioGrado = estudiante.getFechaInicioGrado();
        calInicial = Calendar.getInstance();
        calExamen = Calendar.getInstance();

        ganados = new int[16];
        planeados = new int[16];
        Arrays.fill(ganados, 0);
        Arrays.fill(planeados, 0);
        
        registrosIterator = estudiante.getRegistros().iterator();
        while(registrosIterator.hasNext())
        {
            registro = registrosIterator.next();
            examenesSolicitadosIterator = registro.getExamenesSolicitados().iterator();
            while(examenesSolicitadosIterator.hasNext())
            {
                examenSolicitado = examenesSolicitadosIterator.next();
                if(examenSolicitado.getEstado().equals(Estado.GANADO))
                {
                    calInicial.setTime(fechaInicioGrado);
                    calExamen.setTime(examenSolicitado.getFecha());
                    mes = 0;
                    
                    if(calInicial.after(calExamen))
                        throw new DatasetProduceException("Error de integridad. La fecha del exámens es anterior a la fecha de inicio del grado.");

                    
                    while(calInicial.get(Calendar.MONTH) < calExamen.get(Calendar.MONTH) || calInicial.get(Calendar.YEAR) < calExamen.get(Calendar.YEAR))
                    {
                      calInicial.add(Calendar.MONTH, 1);
                      mes++;
                    }
                    
                    if(mes > 15)
                        throw new DatasetProduceException("Este estudiante ha tardado demasiado tiempo en completar el grado. Ha superado los 16 meses.");
                    
                    ganados[mes]++;
                }
            }
        }

        planeacionAnual = estudiante.getPlaneacionAnual();
        
        if(planeacionAnual == null)
        {
            throw new DatasetProduceException("El estudiante no ha hecho planeación para el grado actual.");
        }
        
        materiasPlaneadasIterator = planeacionAnual.getMateriasPlaneadas().iterator();
        while(materiasPlaneadasIterator.hasNext())
        {
            materiaPlaneada = materiasPlaneadasIterator.next();
            planeadosMes = materiaPlaneada.getPlaneadosMes();
            for(i = 0; i < 16; i++)
            {
                month = new Integer(i);
                if(planeadosMes.containsKey(month))
                {
                    planeados[i]++;
                }
            }
        }
        
        DefaultTableXYDataset dataset = new DefaultTableXYDataset();
        seriePlaneados = new XYSeries("Planeados", true, false);
        serieGanados = new XYSeries("Ganados", true, false);
        
        for(i = 0; i < ganados.length; i++)
        {
            currentDataItem = new XYDataItem(i, ganados[i]);
            serieGanados.add(currentDataItem);
            
            currentDataItem = new XYDataItem(i, planeados[i]);
            seriePlaneados.add(currentDataItem);
        }

        // dataset.addSeries(seriePlaneados);
        dataset.addSeries(serieGanados);
        
        return dataset;
    }

    /**
     *  This producer's data is invalidated after 5 seconds. By this method the
     *  producer can influence Cewolf's caching behaviour the way it wants to.
     */
    public boolean hasExpired(Map params, Date since)
    {		
        return (System.currentTimeMillis() - since.getTime())  > 5000;
    }

    /**
     * Returns a unique ID for this DatasetProducer
     */
    public String getProducerId()
    {
            return "Grafica de Rendimiento (DatasetProducer)";
    }
        
    /**
     * @see java.lang.Object#finalize()
     */
    protected void finalize() throws Throwable
    {
            super.finalize();
    }
}
