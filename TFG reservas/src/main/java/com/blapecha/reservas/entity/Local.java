package com.blapecha.reservas.entity;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseId;
import jakarta.persistence.*;
import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Data
@Entity
public class Local {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  private String nombre;
  private TipoLocalEnum tipo;
  private Integer precioManyana;
  private Integer precioTarde;
  private String direccion;
  private String municipio;
  private boolean bloqueado;

  @Lob
  public byte[] imagen;

  @ManyToOne
  @JoinColumn(name = "duenyo_id", nullable = false)
  private Duenyo duenyo;

  public StreamedContent getImagen() {
    if (imagen != null) {
      FacesContext context = FacesContext.getCurrentInstance();

      if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
        // Durante la fase de renderizado, retornamos un stream vacío
         return DefaultStreamedContent.builder()
                .contentType("image/png") // o el tipo de contenido apropiado
                .stream(() -> new ByteArrayInputStream(imagen))
                .build();
      } else {

        return DefaultStreamedContent.builder()
            .contentType("image/png") // o el tipo de contenido apropiado
            .stream(() -> new ByteArrayInputStream(imagen))
            .build();
      }
    }
    return null;
  }

  public InputStream getImagenStream() {
    return getImagen().getStream().get();
  }

  public byte[] getChartAsByteArray() throws IOException {
    InputStream is = getImagenStream();
    byte[] array = new byte[is.available()];
    is.read(array);
    return array;
  }
}