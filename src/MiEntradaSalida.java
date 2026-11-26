import java.util.Scanner;

public class MiEntradaSalida {

    //LEER CARACTER Admitido
    public static char leerCaracterAdmitido(String mensaje, char[] admitidos) {
        Scanner sc = new Scanner(System.in);
        char caracter = '@';
        boolean salir = false;

        do {
            System.out.printf("%s: ", mensaje);
            caracter = sc.next().toLowerCase().charAt(0);
            for (int i = 0; i < admitidos.length; i++) {
                if (admitidos[i] == caracter) {salir = true;}
            }
        } while (salir == false);
        return caracter;
    }

    //Pulsa z ParaContinuar
    public static void zParaContinuar(String mensaje) {
        Scanner sc = new Scanner(System.in);
        char caracter = '@';
        boolean salir = false;

        do {
            System.out.println("==========================");
            System.out.printf("%s: ", mensaje);
            System.out.println();
            System.out.println("==========================");
            caracter = sc.next().toLowerCase().charAt(0);
            if (caracter == 'z') {salir = true;}
        } while (salir == false);
    }
}
