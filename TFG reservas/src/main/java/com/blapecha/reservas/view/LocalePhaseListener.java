package com.blapecha.reservas.view;

import com.blapecha.reservas.view.LocaleView;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

import java.io.Serializable;

public class LocalePhaseListener implements PhaseListener, Serializable {
    
    private static final long serialVersionUID = 1L;

    @Override
    public void beforePhase(PhaseEvent event) {
        // No hacemos nada antes de restaurar la vista
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        // Después de restaurar la vista, ya podemos acceder a viewRoot
        FacesContext context = event.getFacesContext();

        if (context.getViewRoot() != null) {
            try {
                LocaleView localeView = context.getApplication()
                        .evaluateExpressionGet(context, "#{localeView}", LocaleView.class);

                if (localeView != null && localeView.getLocale() != null) {
                    context.getViewRoot().setLocale(localeView.getLocale());
                }
            } catch (Exception e) {
                // Manejo de errores
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}