package org.javierhernandez.report;

import java.io.InputStream;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.javierhernandez.db.Conexion;

/**
 *
 * @author javih
 */
public class GenerarReportes {
    public static void mostrarReportes(String nombreReporte, String tituloReporte, Map parametro) {
        
        try {
             InputStream reporte = GenerarReportes.class.getResourceAsStream(nombreReporte);
            JasperReport Reporte = (JasperReport) JRLoader.loadObject(reporte);
            
            JasperPrint ReporteImpreso = JasperFillManager.fillReport(Reporte, parametro, Conexion.getInstancia().getConexion());// Administrar la impresion de los reporte atraves de lo parametros
            
            JasperViewer visor = new JasperViewer(ReporteImpreso, false); //Administro la impresion y la vista previa del reporte 
            visor.setTitle(tituloReporte);
            visor.setVisible(true);
                    
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
/*
Interface MAP 
    HasMap es uno de los objetos que implementa  un conjunto de key-value.
    tiene un constructor sin parametros es new HashMap() su finalidad suele referirse para agrupar informacion
    en un unico objeto.

    Tiene cierta similitud con la coleccion de objetos (Arraylist) con la diferencia 
    que no tiene orden.

    HashMap hace referencia a una tecnia de organizacion de archivos , se conoce como hashing(abierto-cerrado)
    en la cual se almacena registro en una dirccion que es generada por una funcion se aplica a la llave del registro.

    JAVA el HashMap posee un espacio de memoria y cuando se guarda un objeto en dicho espacio se determina su direccion aplicando una funcion 
    a la llave que le indiquemos nosotros.Cada objeto se identifica mediante algun identificador apropiado
 */
