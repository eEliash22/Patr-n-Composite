package ejemplo;

import java.util.Arrays;

public class Principal {
    public static void main(String[] args) {
        Menu bebidas = new Menu("Bebidas", Arrays.asList(
                new Plato("Cafe"), new Plato("Jugo")));
        ComponenteMenu carta = new Menu("Menu principal",
                Arrays.asList(new Plato("Sandwich"), bebidas));
        carta.imprimir();
    }
}
