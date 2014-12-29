package Gerencia;





import java.io.*;
import java.util.ArrayList;

public class Leitura {
String texto ="";
  
    ArrayList<String> lista = new ArrayList<>();
    public Leitura(String local) {
        
        int c = 0;
        
              File arquivo = new File(local);
                    try {
                        FileReader MeuArquivo = new FileReader(arquivo);
                        BufferedReader br = new BufferedReader(MeuArquivo);
                        
                        while (br.ready()) {
                                         
                            String linha = br.readLine();
                                        lista.add(linha);
                                         texto += linha+"\n";
                                         
                                         c++;
                                         
                                        }
                       }  catch (IOException e) {
                          e.printStackTrace();
                          }
//                System.out.println(texto);
//                System.out.println("total: ");
//                System.out.print(c);
    }
}
