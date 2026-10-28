package ejemplo;

public final class Plato extends ComponenteMenu {
    public Plato(String nombre) { super(nombre); }

    @Override
    public String texto() {
        return "- " + getNombre() + System.lineSeparator();
    }
}
