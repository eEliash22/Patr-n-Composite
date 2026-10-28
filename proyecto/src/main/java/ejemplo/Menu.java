package ejemplo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Menu extends ComponenteMenu {
    private final List<ComponenteMenu> hijos;

    public Menu(String nombre, List<ComponenteMenu> hijos) {
        super(nombre);
        if (hijos == null || hijos.contains(null)) {
            throw new IllegalArgumentException("Hijos no validos");
        }
        this.hijos = Collections.unmodifiableList(
                new ArrayList<>(hijos));
    }

    public List<ComponenteMenu> getHijos() { return hijos; }

    @Override
    public String texto() {
        StringBuilder salida = new StringBuilder();
        salida.append(getNombre()).append(System.lineSeparator());
        for (ComponenteMenu hijo : hijos) {
            salida.append(hijo.texto());
        }
        return salida.toString();
    }
}
