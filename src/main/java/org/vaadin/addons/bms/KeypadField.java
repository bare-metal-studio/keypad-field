package org.vaadin.addons.bms;

import com.helger.commons.annotation.VisibleForTesting;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;

import java.util.function.Consumer;

@Tag("keypad-field")
@CssImport("./styles/keypad-field.css")
@JsModule("./views/keypad-field.ts")
public class KeypadField extends VerticalLayout {
    @Id("unf")
    private Input usernameField;
    private final PinCodeField pinField;
    private final H1 titleH1;
    private final Paragraph subtitle;
    private final Button signInButton;


    public KeypadField() {
        // Title
        titleH1 = new H1("Enter Pin");
        titleH1.getStyle().set("margin-bottom", "0");

        // Subtitle
        subtitle = new Paragraph("Your Pin is required to log you in");
        subtitle.getStyle().set("color", "#666666");

        // Pin code field (hidden input)
        pinField = new PinCodeField();

        // Numeric keypad
        VerticalLayout keypad = new VerticalLayout();
        keypad.addClassName("keypad");
        for (int i = 1; i <= 9; i++) {
            Button button = createButton(String.valueOf(i));
            keypad.add(button);
        }

        // Bottom row of keypad
        HorizontalLayout bottomRow = new HorizontalLayout();
        bottomRow.addClassName("bottom-row");
        bottomRow.add(
                createButton(VaadinIcon.CLOSE.create(), event -> pinField.reset()),
                createButton("0"),
                createButton(VaadinIcon.BACKSPACE_A.create(), event -> pinField.removeNumber())
        );

        // Sign In button
        signInButton = new Button("Sign In", e -> {
            Notification.show(pinField.getValue());
        });
        //<theme-editor-local-classname>
        signInButton.addClassName("pin-view-sign-in-btn");
        signInButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        signInButton.setVisible(false);

        // Add components to the main layout
        add(titleH1, subtitle, pinField, keypad, bottomRow, signInButton);
    }

    public void setTitle(String title) {
        this.titleH1.setText(title);
    }

    public void setDescription(String desc) {
        this.subtitle.setText(desc);
    }

    private Button createButton(Component caption, Consumer<Button> clickListener) {
        Button button = new Button(caption);
        button.addClickListener(event -> {
            clickListener.accept(button);
            updateUsernameField();
        });
        button.addClassName("keypad-button");
        button.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_CONTRAST);
        return button;
    }

    private Button createButton(String caption) {
        Button button = new Button(caption, event -> {
            pinField.addNumber(caption);
            updateUsernameField();
        });
        button.addClassName("keypad-button");
        button.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_CONTRAST);
        return button;
    }

    private void updateUsernameField() {
//        this.usernameField.setValue(pinField.getValue());
        getElement().setProperty("pinCode", pinField.getValue());
        UI.getCurrent().getPage().executeJs("document.getElementById('unf').dispatchEvent(new InputEvent(\"input\"));");
    }

    @VisibleForTesting
    public PinCodeField getPinField() {
        return pinField;
    }

    public Button getSignInButton() {
        return signInButton;
    }
}
