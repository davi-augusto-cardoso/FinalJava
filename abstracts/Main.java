//Davi Augusto Cardoso
//Alexandre dos Santos Bindo Cabral
//Caroline Jenuario Rodrigues da Silva

package abstracts;

import javax.swing.JOptionPane;

public class Main {
  public static void main(String[] args){
    Program prg = new Program();

    boolean turnOn = true;

    while(turnOn){  
      String choose = JOptionPane.showInputDialog("Escolha uma opcao:\n 1 - Cadastrar\n 2 - Listar\n 3 - Atualizar\n 4 - Deletar\n 0 - Sair");
      try{
        switch(choose){ 
          case "1":
            prg.register();
            break;
          case "2":
            prg.list();
            break;
          case "3":
            prg.update();
            prg.list();
            break;
          case "4":
            prg.delete();
            prg.list();
            break;
          case "0":
            turnOn = false;
            break;
        }
      }catch(Exception e){
        System.out.println(e);
      }
      finally{
        prg.showCriptography();
      }
    }
  }
}
