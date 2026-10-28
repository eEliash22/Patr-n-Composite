package ejemplo;

public abstract class ComponenteMenu {
    private final String nombre;

    protected ComponenteMenu(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        this.nombre = nombre;
    }

    public final String getNombre() { return nombre; }
    public abstract String texto();

    public final void imprimir() {
        System.out.print(texto());
    }
}
