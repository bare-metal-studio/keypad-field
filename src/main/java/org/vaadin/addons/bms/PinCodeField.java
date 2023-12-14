package org.vaadin.addons.bms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;


@Tag("pin-code")
public class PinCodeField extends CustomField<String> {
    private final TextField hiddenField = new TextField();
    private final HorizontalLayout circlesLayout = new HorizontalLayout();
    private final int pinLength = 4;

    public PinCodeField() {
        hiddenField.setVisible(false);
        add(hiddenField, circlesLayout);
        circlesLayout.setWidthFull();
        circlesLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        for (int i = 0; i < pinLength; i++) {
            Div circle = new Div();
            circle.addClassName("pin-circle");
            circlesLayout.add(circle);
        }
    }

    @Override
    protected String generateModelValue() {
        return hiddenField.getValue();
    }

    @Override
    protected void setPresentationValue(String newPresentationValue) {
        hiddenField.setValue(newPresentationValue == null ? "" : newPresentationValue);
        updateCircles();
    }

    public void updateCircles() {
        String value = hiddenField.getValue();
        for (int i = 0; i < circlesLayout.getComponentCount(); i++) {
            Component circle = circlesLayout.getComponentAt(i);
            circle.getElement().getClassList().set("filled", i < value.length());
        }
    }

    public void addNumber(String number) {
        if (hiddenField.getValue().length() < pinLength) {
            setValue(hiddenField.getValue() + number);
        }
    }

    public void reset() {
        setValue("");
    }

    public void removeNumber() {
        String value = hiddenField.getValue();
        if (!value.isEmpty()) {
            setValue(value.substring(0, value.length() - 1));
        }
    }

}
