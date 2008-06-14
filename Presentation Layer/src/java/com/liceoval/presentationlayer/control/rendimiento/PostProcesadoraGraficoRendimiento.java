/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.rendimiento;

import de.laures.cewolf.ChartPostProcessor;

import java.util.Map;
import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;

/**
 *
 * @author Sergio
 */
public class PostProcesadoraGraficoRendimiento implements ChartPostProcessor
{
    public void processChart(Object chart, Map params)
    {
        JFreeChart jChart;
        jChart = (JFreeChart)chart;
        LegendTitle legend;
        XYPlot chartPlot;
        XYItemRenderer renderer;
        
        chartPlot = jChart.getXYPlot();
        chartPlot.getRangeAxis(0).setRange(0, 26);
        chartPlot.getDomainAxis(0).setRange(0, 16);
        jChart.removeLegend();
        
        renderer = chartPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesPaint(1, Color.RED);
    }
}
