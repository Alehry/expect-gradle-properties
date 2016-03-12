package org.niksi.properties

class RemoteProperty {
    final String name
    String value
    boolean isMandatory
    RemoteProperty (String name) {
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
