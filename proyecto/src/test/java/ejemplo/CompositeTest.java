package ejemplo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompositeTest {
    private final String nl = System.lineSeparator();

    @Test public void conservaNombre() {
        ComponenteMenu c = new Plato("Cafe");
        assertEquals("Cafe", c.getNombre());
    }

    @Test public void generaTextoDeHoja() {
        ComponenteMenu c = new Plato("Cafe");
        assertEquals("- Cafe" + nl, c.texto());
    }

    @Test public void generaTextoDeMenuVacio() {
        Menu m = new Menu("Bebidas", Collections.emptyList());
        assertEquals("Bebidas" + nl, m.texto());
    }

    @Test public void recorreSubmenuIntegracion() {
        Menu bebidas = new Menu("Bebidas",
                Arrays.asList(new Plato("Cafe")));
        ComponenteMenu c = new Menu("Carta", Arrays.asList(
                new Plato("Pan"), bebidas));
        assertEquals("Carta" + nl + "- Pan" + nl
                + "Bebidas" + nl + "- Cafe" + nl, c.texto());
    }

    @Test public void imprimeEnSalidaEstandar() throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream captura = new PrintStream(buffer, true, "UTF-8");
        try {
            System.setOut(captura);
            ComponenteMenu c = new Plato("Cafe");
            c.imprimir();
            assertEquals("- Cafe" + nl, buffer.toString("UTF-8"));
        } finally {
            System.setOut(original);
            captura.close();
        }
    }

    @Test public void rechazaNombreVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plato(" "));
    }

    @Test public void rechazaNombreNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plato(null));
    }

    @Test public void rechazaListaNula() {
        assertThrows(IllegalArgumentException.class,
                () -> new Menu("Carta", null));
    }

    @Test public void rechazaHijoNulo() {
        List<ComponenteMenu> hijos = Arrays.asList((ComponenteMenu) null);
        assertThrows(IllegalArgumentException.class,
                () -> new Menu("Carta", hijos));
    }

    @Test public void conservaHijosEnOrden() {
        Plato p = new Plato("Cafe");
        Menu m = new Menu("Carta", Arrays.asList(p));
        assertEquals(1, m.getHijos().size());
        assertSame(p, m.getHijos().get(0));
    }

    @Test public void copiaLaListaRecibida() {
        List<ComponenteMenu> lista = new ArrayList<>();
        lista.add(new Plato("Cafe"));
        Menu m = new Menu("Carta", lista);
        lista.clear();
        assertEquals(1, m.getHijos().size());
    }

    @Test public void impideModificarHijos() {
        Menu m = new Menu("Carta", Collections.emptyList());
        assertThrows(UnsupportedOperationException.class,
                () -> m.getHijos().add(new Plato("Cafe")));
    }
}
