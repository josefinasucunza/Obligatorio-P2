
package dominio;


public class Tablero {


    public static void main(String[] args) {
        
        mostrarTablero(TableroDefault());
    
    }
    public static String[][] TableroDefault(){
        
        String[][] mat = new String[17][41];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                
                if (i % 2 == 0) {
                    if (j % 4 == 0) {
                        mat[i][j] = ("+");
                    } else {
                        mat[i][j] = ("-");
                    }
                } else {
                    if (j % 4 == 0) {
                        mat[i][j] = ("|");
                    } else{
                        mat[i][j] = " ";
                        }
                }
            }
        }
        for (int i = 1; i < 9 ; i = i+2) {
            for (int j = 2; j < mat[0].length; j = j+4) {
                if (i==1 && j >= 10 && mat[i][j-4].equalsIgnoreCase(" ") && mat[i][j-8].equalsIgnoreCase(" ")) {
                    mat[i][j] = "N";
                    mat[i][j+4] = "N";
                }
                if (i == 3) {
                    mat[i][j] = "N";
                }
                if (i == 5 && j<mat[0].length-3) {
                   
                    if (j>=10 && (((mat[i][j-4].equalsIgnoreCase("N")) && (mat[i][j-8].equalsIgnoreCase("N"))) 
                              || ((mat[i][j-4].equalsIgnoreCase(" ")) && (mat[i][j-8].equalsIgnoreCase("N"))))) {
                        mat[i][j] = " "; 
                        mat[i][j+4] = " ";
                    }else{
                        mat[i][j] = "N";
                        mat[i][j+4] = "N";
                    }
                   
                }
            }
        }
        for (int i = 9; i < mat.length; i = i+2) {
            for (int j = 2; j < mat[0].length; j = j+4) {
                if (i == 11 && j<mat[0].length-3) {
                   
                    if (j>=10 && (((mat[i][j-4].equalsIgnoreCase("B")) && (mat[i][j-8].equalsIgnoreCase("B"))) 
                              || ((mat[i][j-4].equalsIgnoreCase(" ")) && (mat[i][j-8].equalsIgnoreCase("B"))))) {
                        mat[i][j] = " "; 
                        mat[i][j+4] = " ";
                    }else{
                        mat[i][j] = "B";
                        mat[i][j+4] = "B";
                    }
                   
                }
                if (i == 13) {
                    mat[i][j] = "B";
                }
                if (i==15 && j >= 10 && mat[i][j-4].equalsIgnoreCase(" ") && mat[i][j-8].equalsIgnoreCase(" ")) {
                    mat[i][j] = "B";
                    mat[i][j+4] = "B";
                }
            }
        }
              
        return mat;
    }

    public static void mostrarTablero(String[][] mat) {

        for (int i = 0; i < mat.length; i++) {
            System.out.println();
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j]);

            }
        }
        
    }
    
    6846516168
    
    
    
}