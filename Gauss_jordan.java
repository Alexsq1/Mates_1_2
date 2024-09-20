import java.util.Scanner;
public class Gauss_jordan {

    public static void main(String[] args) {

        int f = iniciofilas();
        int c = iniciocolumnas();

        int[][] matriz = new int[f][c];
        matriz = guardar(matriz, f, c);

        int[][] matrizbackup = new int[f][c];
        matrizbackup = guardarCopia(matriz, matrizbackup, f, c);

        //output (matriz, matrizbackup, f, c);

        int opción = 0;

        while (opción != 4) {


            System.out.println("");
            opción = elegirOpción();

            switch (opción) {

                case 1:
                    imprimir(matrizbackup, f, c);
                    break;
                case 2:
                    matriz = restaurarCopia(matriz, matrizbackup, f, c);

                    escalonar(matriz, 0, 0, f, c);
                    imprimir(matriz, f, c);
                    break;

                case 3:
                    matriz = restaurarCopia(matriz, matrizbackup, f, c);

                    System.out.println("");
                    escalonar(matriz, 0, 0, f, c);
                    escalonarReducida(matriz, f - 1, c - 1, f, c);
                    imprimir(matriz, f, c);
                    break;
                case 4:
                    System.out.println("Muchas gracias por usar este programa");
                    break;
                default:
                    System.out.println("Valor no válido");
                    break;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////FUNCIONES BÁSICAS

    public static int iniciofilas() {

        int filas;

        do {
            Scanner leer = new Scanner(System.in);
            System.out.println("Introduzca número de filas");
            filas = leer.nextInt();

            if (filas <= 0) {
                System.out.println("Valor no válidos");
            }
        } while (filas <= 0);

        return filas;
    }

    public static int iniciocolumnas() {

        int columnas;

        do {
            Scanner leer = new Scanner(System.in);
            System.out.println("Introduzca número de columnas");
            columnas = leer.nextInt();

            if (columnas <= 0) {
                System.out.println("Valor no válidos");
            }
        } while (columnas <= 0);

        return columnas;
    }

    public static int[][] guardar(int[][] matrix, int filas, int columnas) {

        for (int i = 0; i < filas; i++) {
            System.out.println("Introduzca los dígitos de la " + (i + 1) + "ª fila separados por intros");
            for (int j = 0; j < columnas; j++) {

                Scanner leer = new Scanner(System.in);
                matrix[i][j] = leer.nextInt();
            }
        }
        return matrix;
    }

    public static int[][] guardarCopia(int[][] matrix, int[][] matrixbackup, int filas, int columnas) {

        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < filas; i++)
                matrixbackup[i][j] = matrix[i][j];
        }
        return matrixbackup;
    }

    public static int[][] restaurarCopia(int[][] matrix, int[][] matrixbackup, int filas, int columnas) {

        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < filas; i++)
                matrix[i][j] = matrixbackup[i][j];
        }
        return matrix;
    }

    public static int elegirOpción() {

        System.out.println("Elija su opción: \n");
        System.out.println("1. Imprimir la matriz inicial");
        System.out.println("2. Matriz escalonada");
        System.out.println("3. Matriz escalonada reducida");
        System.out.println("4. Salir");

        int respuesta;
        Scanner leer = new Scanner(System.in);
        respuesta = leer.nextInt();

        return respuesta;
    }

    /*public static void output(int[][] matrix, int[][] matrixbackup, int maxFilas, int maxColumnas) {

    int opción = 0;

        while(opción !=5) {

        System.out.println("");
        opción = elegirOpción();

        switch (opción) {

            case 1:
                imprimir(matrixbackup, maxFilas, maxColumnas);
                break;
            case 2:
                matrix = restaurarCopia(matrix, matrixbackup, maxFilas, maxColumnas);

                escalonar(matrix, 0, 0, maxFilas, maxFilas);
                imprimir(matrix, maxFilas, maxColumnas);
                break;

            case 3:
                matrix = restaurarCopia(matrix, matrixbackup, maxFilas, maxColumnas);

                System.out.println("");
                escalonar(matrix, 0, 0, maxFilas, maxColumnas);
                escalonarReducida(matrix, maxFilas - 1, maxColumnas - 1, maxFilas, maxColumnas);
                imprimir(matrix, maxFilas, maxColumnas);
                break;
            case 5:
                System.out.println("Muchas gracias por usar este programa");
        }
    }
}*/

    public static void imprimir(int[][] matrix, int filas, int columnas) {

        for (int i = 0; i < filas; i++) {
            System.out.print("( ");
            for (int j = 0; j < columnas; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print(")");
            System.out.println("");
        }
    }

    public static int[][] eik(int[][] matrix, int fila, int k, int columnas) {

        for (int i = 0; i < columnas; i++) {
            matrix[fila - 1][i] *= k;
        }
        return matrix;
    }

    public static int[][] eij(int matrix[][], int fila1, int fila2, int columnas) {

        int[] aux = new int[columnas];

        for (int i = 0; i < columnas; i++) {
            aux[i] = matrix[fila1 - 1][i];
        }
        for (int i = 0; i < columnas; i++) {
            matrix[fila1 - 1][i] = matrix[fila2 - 1][i];
        }
        for (int i = 0; i < columnas; i++) {
            matrix[fila2 - 1][i] = aux[i];
        }
        return matrix;
    }

    public static int[][] eijk(int matrix[][], int fila1, int fila2, int k, int columnas) {

        for (int i = 0; i < columnas; i++) {
            matrix[fila1 - 1][i] = matrix[fila1 - 1][i] + matrix[fila2 - 1][i] * k;
        }
        return matrix;
    }

    public static int [][] maximoComunDivisor (int matrix[][], int maxFila, int maxColumna) {

        for (int i = 0; i < maxFila; i++) {

            int mcd = 0;

            for (int j = 0; j < maxColumna; j++) {
                if (matrix[i][j] > mcd)
                    mcd = matrix[i][j];
                if ((matrix[i][j] * -1) > mcd)
                    mcd = -1 * matrix[i][j];
            }

            int check = 1;

            if (mcd != 0) {
                while (check != 0) {
                    for (int j = 0; j < maxColumna; j++) {
                        if (matrix[i][j] > 0)
                            check += matrix[i][j] % mcd;
                        if (matrix[i][j] < 0)
                            check -= matrix[i][j] % mcd;
                    }
                    if (check == 1)
                        check--;

                    else {
                        check = 1;
                        mcd--;
                    }
                }

                for (int j = 0; j < maxColumna; j++)
                    matrix[i][j] /= mcd;
            }
        }

        for (int i = 0; i < maxFila; i++){

            int numnegativo = 0;
            int numpositivo = 0;
            for (int j = 0; j < maxColumna; j++){
                if (matrix[i][j] < 0)
                    numnegativo++;
                if (matrix[i][j] > 0)
                    numpositivo++;
            }
            if (numnegativo > numpositivo)
                eik (matrix, i + 1,-1, maxColumna);
        }
        return matrix;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ESCALONAR

    public static int[][] escalonar(int matrix[][], int fila, int columna, int maxFilas, int maxColumna){

        for (int i = 0; i < maxFilas - 1 && columna < maxColumna; i++) {

            ordenarColumna(matrix, i, columna, maxFilas, maxColumna);
            columna = pivote(matrix, i, columna, maxFilas, maxColumna);

            if (columnaNula(matrix, i,columna, maxFilas, maxColumna) == false)
                diagColumna(matrix, i, columna, maxFilas, maxColumna);
        }
        maximoComunDivisor(matrix, maxFilas,maxColumna);
        return matrix;
    }

    public static int[][] ordenarColumna(int matrizordenada[][], int fila, int columna, int maxFilas, int maxColumnas) {

        moverCeros(matrizordenada, fila, columna, maxFilas, maxColumnas);
        int filabackup = fila;

        if (matrizordenada[fila][columna] != 1) {

            while (matrizordenada[fila][columna] != 1 && fila <= (maxFilas - 2)) {
                fila++;
            }

            if (matrizordenada[fila][columna] == 1)
                eij(matrizordenada, filabackup + 1, fila + 1, maxColumnas);
        }
        return matrizordenada;
    }

    public static int[][] moverCeros(int matrix[][], int fila1, int columna, int maxFilas, int maxColumna) {    //mueve si hay 0 en la 1ª posición

        int fila2 = maxFilas - 1;

        if (matrix[fila1][columna] == 0) {
            while (matrix[fila2][columna] == 0 && fila2 > (fila1 + 1)) {
                fila2--;
            }

            if (matrix[fila2][columna] != 0) {
                eij(matrix, fila1 + 1, fila2 + 1, maxColumna);
            }
        }
        return matrix;
    }
    public static int pivote(int matrix[][], int fila, int columna, int maxFila, int maxColumna){

        int i = columna;
        while (matrix[fila][i] == 0 && i < maxColumna - 1){
            i++;
            ordenarColumna(matrix, fila, i, maxFila, maxColumna);
        }
        return i;
    }

    public static int[][] diagColumna(int matrix[][], int fila1, int columna, int maxFilas, int maxColumna) {

        if (matrix[fila1][columna] != 0) {

            if (matrix[fila1][columna] != 1 && matrix[fila1][columna] != -1)
                minimoComunMultiplo(matrix, fila1, columna, maxFilas, maxColumna);

            for (int fila2 = fila1 + 1; fila2 < maxFilas; fila2++) {
                eijk(matrix, fila2 + 1, fila1 + 1, (-1 * matrix[fila2][columna] / matrix[fila1][columna]), maxColumna);
            }
        }
        maximoComunDivisor(matrix, maxFilas, maxColumna);
        return matrix;
    }

    public static boolean columnaNula(int matrix[][], int fila, int columna, int maxFila, int maxColumna){

        int check = 0;
        for (int i = fila + 1; i < maxFila; i++){

            if (matrix[i][columna] != 0)
                check++;
        }
        if (check > 0)
            return false;
        else
            return true;
    }

    public static int[][] minimoComunMultiplo(int matrix[][], int fila, int columna, int maxFila, int maxColumna) {

        int mcm = 1;
        int check = 1;

        while (check != 0) {
            for (int i = fila; i < maxFila; i++)
                if (matrix[i][columna] != 0) {
                    check += mcm % matrix[i][columna];
                }
            if (check == 1)
                check--;

            else{
                check = 1;
                mcm++;
            }
        }

        for (int i = 0; i < maxFila; i++) {
            if (matrix[i][columna] != 0) {
                eik(matrix, i + 1, mcm / matrix[i][columna], maxColumna);
            }
        }
        return matrix;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////REDUCIDA

    public static int[][] escalonarReducida(int matrix[][], int fila, int columna, int maxFilas, int maxColumna){

        for (int i = fila; i >= 0 && columna > 0; i--) {

            columna = pivoteReducida(matrix, i, columna, maxFilas, maxColumna);

            if (columnaNulaReducida(matrix, i,columna, maxFilas, maxColumna) == false)
                diagColumnaReducida(matrix, i, columna, maxFilas, maxColumna);
            if (matrix[i][columna] < 0)
                eik(matrix, i + 1, -1, maxColumna);                                     //pone el pivote positivo
        }
        arregloPivotes(matrix, 0, 0, maxFilas, maxColumna);
        return matrix;
    }

    public static int pivoteReducida(int matrix[][], int fila, int columna, int maxFila, int maxColumna){

        int i = 0;
        while (matrix[fila][i] == 0 && i < maxColumna - 1){
            i++;
        }
        return i;
    }

    public static int[][] diagColumnaReducida(int matrix[][], int fila1, int columna, int maxFilas, int maxColumna) {

        if (matrix[fila1][columna] != 0) {

            if (matrix[fila1][columna] != 1 && matrix[fila1][columna] != -1)
                minimoComunMultiploReducido(matrix, fila1, columna, maxFilas, maxColumna);

            for (int fila2 = fila1 - 1; fila2 >= 0; fila2--) {
                eijk(matrix, fila2 + 1, fila1 + 1, (-1 * matrix[fila2][columna] / matrix[fila1][columna]), maxColumna);
            }
        }
        maximoComunDivisor(matrix, maxFilas, maxColumna);
        return matrix;
    }

    public static boolean columnaNulaReducida(int matrix[][], int fila, int columna, int maxFila, int maxColumna){

        int check = 0;
        for (int i = fila - 1; i >= 0; i--){

            if (matrix[i][columna] != 0)
                check++;
        }
        if (check > 0)
            return false;
        else
            return true;
    }

    public static int[][] minimoComunMultiploReducido(int matrix[][], int fila, int columna, int maxFila, int maxColumna) {

        int mcm = 1;
        int check = 1;

        while (check != 0) {
            for (int i = fila; i >= 0; i--)
                if (matrix[i][columna] != 0) {
                    check += mcm % matrix[i][columna];
                }
            if (check == 1)
                check--;

            else{
                check = 1;
                mcm++;
            }
        }

        for (int i = 0; i >= 0; i--) {
            if (matrix[i][columna] != 0) {
                eik(matrix, i + 1, mcm / matrix[i][columna], maxColumna);
            }
        }
        return matrix;
    }

    public static int[][] arregloPivotes(int matrix[][], int fila, int columna, int maxFila, int maxColumna) {

        int[] pivotes = new int[maxFila];
        int[] filasPivotes = new int[maxFila];

        for (int i = 0; i < maxFila; i++) {
            pivotes[i] = buscarPivoteReducida(matrix, i, columna, maxFila, maxColumna);
            filasPivotes[i] = buscarFilasPivotes (matrix, i, columna, maxFila, maxColumna);
        }

        int mcm = minimoComunMultiploPivotes (pivotes, maxFila);

        for (int i = 0; i < maxFila - 1; i++){
            if (filasPivotes[i] != 0)
                eik(matrix, filasPivotes[i], mcm / pivotes[i], maxColumna);
        }
        if (mcm != 1)
            System.out.println("1/" + mcm);
        return matrix;
    }

    public static int buscarPivoteReducida(int matrix[][], int fila, int columna, int maxFila, int maxColumna){

        int i = columna;
        while (matrix[fila][i] == 0 && i < maxColumna - 1){
            i++;
        }

        if (i == maxColumna - 1)
            return 0;
        else
            return matrix[fila][i];
    }

    public static int buscarFilasPivotes(int matrix[][], int fila, int columna, int maxFila, int maxColumna){

        int i = columna;
        while (matrix[fila][i] == 0 && i < maxColumna - 1){
            i++;
        }

        if (i == maxColumna - 1)
            return 0;
        else
            return fila + 1;
    }

    public static int minimoComunMultiploPivotes(int pivotes[], int maxFila){

        int mcm = 1;
        int check = 1;

        while (check != 0) {
            for (int i = 0; i < maxFila; i++)
                if (pivotes[i] != 0) {
                    check += mcm % pivotes[i];
                }
            if (check == 1)
                check--;

            else{
                check = 1;
                mcm++;
            }
        }
        return mcm;
    }
}