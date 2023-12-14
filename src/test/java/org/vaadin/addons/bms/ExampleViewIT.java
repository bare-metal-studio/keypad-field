package org.vaadin.addons.bms;


import com.vaadin.testbench.TestBenchElement;
import org.junit.Assert;
import org.junit.Test;

/** Integration test for the component.
 *
 */
public class ExampleViewIT extends AbstractTestBenchIntegrationTest {

    public ExampleViewIT() {
        super();
    }

    @Test
    public void componentIsPresent()  {
        final TestBenchElement keyfield = $("keypad-field").waitForFirst();
        // Check that axa-text contains at least one other element, which means that
        // is has been upgraded to a custom element and not just rendered as an empty
        // tag
        Assert.assertFalse(keyfield.$(TestBenchElement.class).all().isEmpty());
    }
}