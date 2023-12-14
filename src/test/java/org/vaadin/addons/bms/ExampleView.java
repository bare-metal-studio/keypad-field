package org.vaadin.addons.bms;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/** Test view for manual and automated testing of the component.
 *
 */
@Route("")
public class ExampleView extends VerticalLayout {
    public ExampleView() {

        KeypadField keypadField = new KeypadField();
        add(keypadField);
    }
}
