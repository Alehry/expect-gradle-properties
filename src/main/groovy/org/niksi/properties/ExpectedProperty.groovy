package org.niksi.properties

class ExpectedProperty {
    final String name
    String value
    boolean isMandatory
    ExpectedProperty (String name) {
        this.name = name
    }
    void isMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory
    }
    void defaultValue(String value) {
        if (this.value == null) {
            this.value = value
        }
    }
    void defaultValue(Object value) {
        defaultValue(value + "")
    }
}
