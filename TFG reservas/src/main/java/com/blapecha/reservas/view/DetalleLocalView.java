package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.Resenya;
import com.blapecha.reservas.entity.Reserva;
import com.blapecha.reservas.service.LocalService;
import com.blapecha.reservas.service.ResenyaService;
import com.blapecha.reservas.service.ReservaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.primefaces.model.*;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Named
@SessionScoped
@Data
public class DetalleLocalView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private LocalService localService;

    @Autowired
    private ResenyaService resenyaService;

    private Local local;

    private BarChartModel barModel;

    private List<Resenya> resenyas;
    private List<Reserva> reservas;
    private ScheduleModel eventModel = new DefaultScheduleModel();
    private ScheduleEvent<?> event = new DefaultScheduleEvent<>();
    private String serverTimeZone = ZoneId.systemDefault().toString();
    @Autowired
    private ReservaService reservaService;


    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        event = new DefaultScheduleEvent<>();

        String parameter = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        if(parameter != null) {
            Long idLocal = Long.valueOf(parameter);
            local = localService.buscarPorId(idLocal);

            this.resenyas = resenyaService.getResenyas(local);
            this.reservas = this.reservaService.findByLocal(idLocal);
            for(Reserva reserva: reservas) {

                LocalDateTime localDateTimeStart = reserva.getTarde()? reserva.getFecha().atTime(15,00): reserva.getFecha().atTime(8,00);
                LocalDateTime localDateTimeEnd = reserva.getTarde()? reserva.getFecha().atTime(22,00): reserva.getFecha().atTime(14,30);

                String color = reserva.getTarde()? "orange": "green";
                String color2 = reserva.getTarde()? "lightgreen": "red";

                var event = DefaultScheduleEvent.builder()
                        .id(reserva.getCodigo())
                        .title(reserva.getCodigo()+" "+reserva.getCliente().getNombre() +" "+reserva.getCliente().getApellidos())
                        .description("Reserva")
                        .borderColor(color)
                        .backgroundColor(color2)
                        .startDate(localDateTimeStart)
                        .display(ScheduleDisplayMode.AUTO)
                        .endDate(localDateTimeEnd)
                        .build();
                eventModel.addEvent(event);
            }
            createBarModel();
            createBarModelCount();
        }

    }
    @SuppressWarnings("deprecation")
    public void createBarModel(){
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("€ Reservas");

        var acumManyana = 0.0;
        var acumTarde = 0.0;
        for(Reserva reserva: this.reservas) {
            if(reserva.getTarde()){
                acumTarde += reserva.getLocal().getPrecioTarde();
            }else{
                acumManyana += reserva.getLocal().getPrecioManyana();
            }
        }
        List<Object> values = new ArrayList<>();
        values.add(acumManyana);
        values.add(acumTarde);
        barDataSet.setData(values);


        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        barDataSet.setBackgroundColor(bgColor);


        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Mañana");
        labels.add("Tarde");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        options.setMaintainAspectRatio(false);
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Beneficio");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);

    }

    @SuppressWarnings("deprecation")
    public void createBarModelCount(){
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Num Reservas");

        var acumManyana = 0.0;
        var acumTarde = 0.0;
        for(Reserva reserva: this.reservas) {
            if(reserva.getTarde()){
                acumTarde += 1;
            }else{
                acumManyana += 1;
            }
        }
        List<Object> values = new ArrayList<>();
        values.add(acumManyana);
        values.add(acumTarde);
        barDataSet.setData(values);


        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        barDataSet.setBackgroundColor(bgColor);


        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Mañana");
        labels.add("Tarde");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        options.setMaintainAspectRatio(false);
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Número");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);

    }

    public void generarPDF() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();

            // Configurar la respuesta HTTP
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseHeader("Content-Disposition",
                    "attachment; filename=\"detalles-local-" + local.getId() + ".pdf\"");

            // Crear documento PDF directamente en el stream de salida
            try (PDDocument document = new PDDocument();
                 OutputStream outputStream = externalContext.getResponseOutputStream()) {

                // Crear página
                PDPage page = new PDPage();
                document.addPage(page);

                // Crear contenido
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    // Título
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                    contentStream.newLineAtOffset(50, 750);
                    contentStream.showText("Detalles del Local: " + local.getNombre());
                    contentStream.endText();

                    // Información básica
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.newLineAtOffset(50, 720);
                    contentStream.showText("Dirección: " + local.getDireccion());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Precio Mañana: " + local.getPrecioManyana() + " €");
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Precio Tarde: " + local.getPrecioTarde() + " €");
                    contentStream.endText();

                    // Sección de reservas
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                    contentStream.newLineAtOffset(50, 650);
                    contentStream.showText("Resumen de Reservas");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.newLineAtOffset(50, 630);
                    contentStream.showText("Número total de reservas: " + reservas.size());
                    contentStream.endText();

                    // Estadísticas de reservas
                    int reservasMañana = 0;
                    int reservasTarde = 0;
                    double ingresosMañana = 0;
                    double ingresosTarde = 0;

                    for (Reserva reserva : reservas) {
                        if (reserva.getTarde()) {
                            reservasTarde++;
                            ingresosTarde += local.getPrecioTarde();
                        } else {
                            reservasMañana++;
                            ingresosMañana += local.getPrecioManyana();
                        }
                    }

                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.newLineAtOffset(50, 610);
                    contentStream.showText("Reservas por la mañana: " + reservasMañana);
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Reservas por la tarde: " + reservasTarde);
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Ingresos por la mañana: " + ingresosMañana + " €");
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Ingresos por la tarde: " + ingresosTarde + " €");
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Ingresos totales: " + (ingresosMañana + ingresosTarde) + " €");
                    contentStream.endText();
                }

                // Guardar el documento en el stream de salida
                document.save(outputStream);
            }

            // Completar la respuesta
            facesContext.responseComplete();

        } catch (IOException e) {
            // Manejar errores
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar PDF", e.getMessage()));
            log.error("Error al generar PDF", e);
        }
    }

}