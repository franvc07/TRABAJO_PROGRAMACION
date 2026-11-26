
public class preuba {
    public static void main(String[] args) {

        //Definr variables y tal (codigo que no se repetira)

        String[][] mapa = {
                {"0", "pared", "pared", "0", "0", "0",},
                {"0", "0", "0", "0", "0", "0",},
                {"0", "arbol", "pared", "0", "0", "0",},
                {"0", "0", "0", "pared", "0", "0",},
                {"0", "0", "0", "pared", "0", "0",},
                {"0", "0", "0", "0", "0", "0",}

        };
        char accionPrincipalTurno = 0;
        char[] accionesPosibles = {'w', 'a' ,'s', 'd', 'm', 'ñ'};
        int[] posision = {1, 2};
        boolean abandonarPartida = false;
        boolean[][] explorado;
        explorado = new boolean[mapa.length][mapa.length];

        //==============================================
        //==============================================

        do {

            //Bucle que se repite hasta que ganas o sales del juego

            //Calcular exploración
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int filaActual = posision[0] - 1 + i;
                    int colActual = posision[1] - 1 + j;

                    // Revisar si se sale por arriba, abajo, izquierda o derecha
                    if (filaActual < 0 || filaActual >= mapa.length || colActual < 0 || colActual >= mapa[i].length) {
                        continue;
                    }

                    //Marcar como exploradas las casillas
                    explorado[colActual][filaActual] = true;
                }
            }

            //================================================================
            //Textos al iniciar el turno
            accionPrincipalTurno = Texto(mapa, accionesPosibles, posision, accionPrincipalTurno);

            //Acciones turno
            switch (accionPrincipalTurno) {

                //===========================================================
                //MOVIMIENTO

                case 'w':
                    // Mover arriba
                    if (posision[1] > 0) {
                        if (mapa[posision[1] - 1][posision[0]] != "pared") {
                            posision[1] = posision[1] - 1;
                            System.out.println("Te mueves arriba, posision j es " + posision[1]);
                        } else {
                            System.out.println("Movimiento bloqueado por pared");
                            MiEntradaSalida.zParaContinuar("Pulsa z para continuar");
                        }
                    } else {
                        System.out.println("No puedes ir ahi");
                    }

                    break;
                case 'a':
                    // Mover arriba
                    if (posision[0] > 0) {
                        posision[0] = posision[0] - 1;
                        System.out.println("Te mueves izq");
                    } else {
                        System.out.println("No puedes ir ahi");
                    }
                    break;
                case 's':
                    // Mover abajo
                    if (posision[1] + 1 < mapa.length) {
                        posision[1] = posision[1] + 1;
                        System.out.println("Te mueves abajo, posision j es " + posision[1]);
                    } else {
                        System.out.println("No puedes ir ahi");
                    }

                    break;
                case 'd':
                    // Mover abajo
                    if (posision[0] + 1 < mapa.length) {
                        posision[0] = posision[0] + 1;
                        System.out.println("Te mueves derecha");
                    } else {
                        System.out.println("No puedes ir ahi");
                    }
                    break;

                    //============================================
                    //Otras acciones

                case 'm':
                    // Abrir mapa
                    System.out.println("Abriendo mapa");
                    AbrirMapa(mapa,explorado, accionesPosibles, posision);
                    break;
                case 'ñ':
                    // Abrir mapa
                    abandonarPartida = true;
                    break;
                default:
                    // Opción por si acaso entra otra letra
                    System.out.println("Acción no válida");
                    break;
            }

            //=====================================
            //=====================================

            //Terminar bucle si ganas o abandonas

        } while (abandonarPartida == false);

        if (abandonarPartida == true) {
            System.out.println("fin de la partida");
        } else {
            System.out.println("has ganao");
        }

    }

    //===========================================
    //===========================================
    //Texto Al Iniciar Turno

    public static char Texto(String[][] mapa, char[] accionesPosibles, int[] posision, char accionPrincipalTurno) {
        //limpiar
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
        //Unicacion
        System.out.println("=========ENTORNO=========");
        System.out.println("Estas en " + mapa[posision[1]][posision[0]]);
        System.out.println();

        //Textos de acciones
        System.out.println("=========ACCIONES=========");
        System.out.println("-Mapa: m");
        System.out.println("-abandonar partida: ñ");
        System.out.println("-moverte: WASD");
        System.out.println();
        accionPrincipalTurno = MiEntradaSalida.leerCaracterAdmitido("elije acción", accionesPosibles);
        return accionPrincipalTurno;
    }

    //===========================================
    //===========================================
    //MAPA

    public static void AbrirMapa(String[][] mapa, boolean[][] explorado, char[] accionesPosibles, int[] posision) {

        for (int i = 0; i < mapa.length; i++) {

            //Vorde arriba (deco)
            for (int j = 0; j < mapa.length -1; j++) {
                System.out.print("+-------------");
            }
            //pico derecha(deco)
            System.out.print("+-------------+");
            System.out.println(" ");

            //Lineas antes de las ubicaciones (deco)
            for (int j = 0; j < 2; j++) {
                //Lineas verticales
                for (int k = 0; k < mapa.length; k++) {
                    System.out.print("|             ");
                }
                //Fin derecha de las lineas (deco)
                System.out.print("|");
                System.out.println(" ");
            }

            //UBICACIONES
            for (int j = 0; j < mapa.length; j++) {

                if (posision[1] == i && posision[0] == j) {
                    System.out.print("|      Tu     ");
                } else {

                    if (explorado[i][j] == true) {
                        if (mapa[i][j] == "0") {System.out.print("|  explorado  ");}
                        if (mapa[i][j] == "pared") {System.out.print("|    pared    ");}
                        if (mapa[i][j] == "arbol") {System.out.print("|     arbol   ");}
                    } else {
                        System.out.print("| inexplorado ");
                    }
                }
            }

            //Fin derecha de las lineas (deco)
            System.out.print("|");
            System.out.println(" ");

            //Lineas despues de las ubicaciones (deco)
            for (int j = 0; j < 2; j++) {
                //Lineas verticales
                for (int K = 0; K < mapa.length; K++) {
                    System.out.print("|             ");
                }
                //Fin derecha de las lineas (deco)
                System.out.print("|");
                System.out.println(" ");
            }
        }

        //Vorde Abajo (deco)
        for (int i = 0; i < mapa.length -1; i++) {
            System.out.print("+-------------");
        }
        //pico derecha (deco)
        System.out.print("+-------------+");
        System.out.println(" ");

        //SALIR

        accionesPosibles = new char[1];
        accionesPosibles[0] = 'z';
        char salir = MiEntradaSalida.leerCaracterAdmitido("Pulsa z para volver ", accionesPosibles);

    }

    //===========================================
    //===========================================
    //INVENTARIO

}
