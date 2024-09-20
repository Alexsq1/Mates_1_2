import java.util.Scanner;

public class Sist_congruencia {

    public static void main(String[] args) {

        int numeroEcuaciones = inicio();

        int[][] matriz = new int[numeroEcuaciones][3];

        guardar(matriz, numeroEcuaciones);
        escribirSistema(matriz, numeroEcuaciones);

        cancelativa(matriz, numeroEcuaciones);
        arreglo(matriz, numeroEcuaciones);

        boolean haySolucion1 = haySolucion1(matriz, numeroEcuaciones);

        if (haySolucion1) {
            resolverEcuaciones(matriz, numeroEcuaciones);
            boolean haySolucion2 = haySolucion2(matriz, numeroEcuaciones);
            if (haySolucion2) {
                int mcm = minimoComunMultiploModulos(matriz, numeroEcuaciones);
                int x = resolverSistema(matriz, numeroEcuaciones);
                System.out.println("La solución al sistema es: " + x + " + " + mcm + "t");
            } else
                System.out.println("El sistema de ecuaciones no tiene solución");
        } else
            System.out.println("El sistema de ecuaciones no tiene solución");
    }

    public static int inicio() {

        int numeroEcuaciones = 0;

        do {
            Scanner leer = new Scanner(System.in);
            System.out.println("Introduzca número de ecuaciones");
            numeroEcuaciones = leer.nextInt();

            if (numeroEcuaciones <= 0) {
                System.out.println("Valor no válidos");
            }
        } while (numeroEcuaciones <= 0);

        return numeroEcuaciones;
    }

    public static int[][] guardar(int[][] matriz, int numeroEcuaciones) {

        for (int i = 0; i < numeroEcuaciones; i++) {
            System.out.println("\n" + (i + 1) + "ª ecuacion: ax ≡ b (mod m)");

            do {

                System.out.println("Inserte a: ");
                Scanner leer1 = new Scanner(System.in);
                matriz[i][0] = leer1.nextInt();

            } while (matriz[i][0] == 0);

            System.out.println("Inserte b: ");
            Scanner leer2 = new Scanner(System.in);
            matriz[i][1] = leer2.nextInt();

            do {
                System.out.println("Inserte m: ");
                Scanner leer3 = new Scanner(System.in);
                matriz[i][2] = leer3.nextInt();
            } while (matriz[i][2] <= 1);

        }

        return matriz;
    }

    public static int resto(int dividendo, int divisor) {

        int resto = dividendo;

        while (resto >= divisor) {

            if (divisor > 0) {
                resto -= divisor;
            } else {
                resto += divisor;
            }
        }

        while (resto < 0) {
            if (divisor > 0) {
                resto += divisor;
            } else {
                resto -= divisor;
            }
        }
        return resto;
    }

    public static int mcd(int a, int b) {

        if (a < 0)
            a *= -1;
        if (b < 0)
            b *= -1;
        if (a * b == 0)
            return 1;
        else
            return mcd2(a, b);
    }

    public static int mcd2(int a, int b) {

        if (a % b == 0)
            return b;
        else
            return mcd2(b, a % b);
    }

    public static boolean haySolucion1(int[][] matriz, int numeroEcuaciones) {

        boolean haySolucion = true;
        for (int i = 0; i < numeroEcuaciones; i++) {

            if (resto(matriz[i][1], (mcd(matriz[i][0], matriz[i][2]))) != 0)
                haySolucion = false;
        }
        return haySolucion;
    }

    public static boolean haySolucion2(int[][] matriz, int numeroEcuaciones) {

        boolean haySolucion = true;

        for (int i = 0; i < numeroEcuaciones; i++) {
            for (int j = i; j < numeroEcuaciones; j++) {

                if (i != j) {
                    if (resto(matriz[i][1] - matriz[j][1], mcd(matriz[i][2], matriz[j][2])) != 0)
                        haySolucion = false;
                }
            }
        }
        return haySolucion;
    }

    public static int[][] cancelativa(int[][] matriz, int numeroEcuaciones) {

        for (int i = 0; i < numeroEcuaciones; i++) {

            int divisorComun;


            if (matriz[i][1] != 0) {
                divisorComun = mcd(matriz[i][0], matriz[i][1]);
            } else
                divisorComun = matriz[i][0];

            if (divisorComun != 1) {
                matriz[i][0] /= divisorComun;
                matriz[i][1] /= divisorComun;
                matriz[i][2] /= mcd(divisorComun, matriz[i][2]);
            }
        }
        return matriz;
    }

    public static int[][] arreglo(int[][] matriz, int numeroEcuaciones) {

        for (int i = 0; i < numeroEcuaciones; i++) {
            for (int j = 0; j < 2; j++) {
                matriz[i][j] = resto(matriz[i][j], matriz[i][2]);
            }
        }
        return matriz;
    }

    public static int[][] resolverEcuaciones(int[][] matriz, int numeroEcuaciones) {

        for (int i = 0; i < numeroEcuaciones; i++) {

            if (matriz[i][0] != 0) {
                if (mcd(matriz[i][0], matriz[i][2]) == 1) {

                    int inverso = 1;

                    while (resto(matriz[i][0] * inverso, matriz[i][2]) != 1) {
                        inverso++;
                    }
                    matriz[i][0] *= inverso;
                    matriz[i][1] *= inverso;
                } else {                        //no existe el inverso (mcd (a, m) != 1), ecuacion simplificada. No se si es necesario, resolver por diofántica
                    int x = 0;
                    while (resto(x * matriz[i][0], matriz[i][2]) != matriz[i][1] && x < matriz[i][2])
                        x++;

                    matriz[i][0] = 1;
                    matriz[i][1] = x;
                }
            }
        }
        arreglo(matriz, numeroEcuaciones);
        return matriz;
    }

    public static int resolverSistema(int[][] matriz, int numeroEcuaciones) {

        int x = 0;
        boolean resuelto = false;

        while (!resuelto) {
            resuelto = true;
            for (int i = 0; i < numeroEcuaciones; i++) {
                if (resto(x, matriz[i][2]) != matriz[i][1])
                    resuelto = false;
            }
            x++;
        }
        x--;
        return x;
    }

    public static int minimoComunMultiploModulos(int[][] matriz, int numeroEcuaciones) {

        int mcm = matriz[0][2];

        for (int i = 0; i < numeroEcuaciones; i++) {
            if (matriz[i][2] > mcm)
                mcm = matriz[0][2];
        }

        boolean check = false;

        while (!check) {

            check = true;

            for (int i = 0; i < numeroEcuaciones && check; i++) {
                if ((mcm % matriz[i][2]) != 0)
                    check = false;
            }
            mcm++;
        }
        mcm--;

        return mcm;
    }

    public static void escribirSistema(int[][] matriz, int numeroEcuaciones) {

        System.out.println("\nEl sistema introducido es: \n");
        for (int i = 0; i < numeroEcuaciones; i++) {
            System.out.println(matriz[i][0] + "x ≡ " + matriz[i][1] + " (mod " + matriz[i][2] + ")");
        }
        System.out.println("");
    }
}