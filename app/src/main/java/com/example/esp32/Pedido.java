package com.example.esp32;

public class Pedido {

    private String bebida;
    private String bomba;
    private long timestamp;

    public Pedido() {
        // Constructor vacío requerido por Firebase
    }

    public Pedido(String bebida, String bomba, long timestamp) {
        this.bebida = bebida;
        this.bomba = bomba;
        this.timestamp = timestamp;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getBomba() {
        return bomba;
    }

    public void setBomba(String bomba) {
        this.bomba = bomba;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

