import {html, LitElement} from 'lit';
import {customElement, property} from 'lit/decorators.js';
import '@vaadin/horizontal-layout';
import '@vaadin/vertical-layout';

@customElement('keypad-field')
export class KeypadField extends LitElement {
    @property({ type: String }) pinCode = "";

    render() {
        return html`
            <form action="/login" method="POST" on-formdata="_onFormData" style="display: none;" id="formId">
                <input name="username"  id="unf" @input=${this.clickAction} .value="${this.pinCode}"/>
                <button type="submit" id="submit"></button>
            </form>
        `;
    }

    // Remove this method to render the contents of this view inside Shadow DOM
    createRenderRoot() {
        return this;
    }

    private clickAction() {
        const inputField = document.getElementById('unf') as HTMLInputElement;
        const form = document.getElementById('formId') as HTMLFormElement;
        console.log(inputField.value.length)
        if (inputField.value.length === 3) {
            HTMLFormElement.prototype.submit.call(form)
        }
    }
}
