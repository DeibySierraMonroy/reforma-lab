package co.com.activos.model.solicitud;

public enum Estado {

    Pendiente("P"),
    Inactivo("I"),
    Aprobado("A");

    private String nombre;

    Estado(String name) {
        this.nombre = name;
    }

    public String getName() {
        return nombre;
    }
}
