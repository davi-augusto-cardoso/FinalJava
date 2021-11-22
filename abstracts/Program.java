package abstracts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Program implements Crud, Criptography{

    ArrayList<String> textArrayList = new ArrayList<String>();
     
    public Program(){
        this.textArrayList = read();
    }

    @Override
    public String encript(String text) {
      String key = "12345678";
      char[] keyChar = key.toCharArray();
      char[] textChar = text.toCharArray();
      int[] encriptChar = new int[textChar.length];
      
      int count = 0;
      for(int i = 0; i < textChar.length; i++){
        encriptChar[i] = (int)textChar[i] * (int)keyChar[count];
        if (count < 7){
          count ++;
        }else{
          count = 0;
        }  
      }
      for(int i = 0; i < encriptChar.length; i++){
        textChar[i] = (char)encriptChar[i];
      }
      return String.valueOf(textChar);
    }

    @Override
    public String decript(String text) {
      String key = "12345678";
      char[] keyChar = key.toCharArray();
      char[] textChar = text.toCharArray();
      int[] encriptChar = new int[textChar.length];
      int count = 0;
      for(int i = 0; i < textChar.length; i++){
        encriptChar[i] = (int)textChar[i] / (int)keyChar[count];
        
        if (count < 7){
          count ++;
        }else{
          count = 0;
        }   
      }
      for(int i = 0; i < encriptChar.length; i++){
        textChar[i] = (char)encriptChar[i];
      }
      return String.copyValueOf(textChar);
    }

    // Faz a escrita do arquivo
    private void write() {
      Path filePath = Paths.get("./logUsers.txt");// caminho relativo do arquivo
      String text= "";

      for(int i = 0; i < toListStr(this.textArrayList).length; i++){
        text += this.toListStr(this.textArrayList)[i]+ ","; 
      }
      text = encript(text);
      try {
        Files.writeString(filePath, text);//faz a escrita do texto encriptado em um arquivo txt
      } catch (IOException e) {
        e.printStackTrace();
      } 
    }

    //Faz a leitura do arquivo
    private ArrayList<String> read(){
      Path filePath = Paths.get("./logUsers.txt");
      String text = "";

      try {
        text = Files.readString(filePath);
        text = decript(text);
  
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      return toArrayListStr(text.split(","));
    }

    public void showCriptography(){
      String text = "";
      for(int t = 0; t < this.textArrayList.size(); t++ ){
        if(!(t % 2 == 0)){
          text += this.textArrayList.get(t) + "\n";
        }
      }
      JOptionPane.showMessageDialog(null, "Relatório da Ação: "+encript(text));
    }

    //Faz a listagem dos usuários
    @Override
    public void list() {
      String text = "";
      for(int t = 0; t < this.textArrayList.size(); t++ ){
        if(!(t % 2 == 0)){
          text += this.textArrayList.get(t) + "\n";
        }
      }
      JOptionPane.showMessageDialog(null, text);
    }

    //Faz o registro dos usuários
    @Override
    public void register()throws AlreadyRegisterException{
      String text = JOptionPane.showInputDialog(null, "Digite login:");
      
      if (this.textArrayList.contains(text)){
        throw new AlreadyRegisterException("Usuário já cadastrado!");
      }
      else{
        this.textArrayList.add(text);
        this.textArrayList.add(JOptionPane.showInputDialog(null, "Digite senha:"));
      }
      write();
    }

    @Override
    public void update()throws  AbsentUserException, IncorretPasswordException{ 
      String str = JOptionPane.showInputDialog(null, "Digite o login para troca de usuário:");
      
      if(this.textArrayList.contains(str)){
        String key = JOptionPane.showInputDialog(null, "Digite a senha: ");
        int i = this.textArrayList.indexOf(str);
        if(key.equals(this.textArrayList.get(i+1))){
          this.textArrayList.remove(i);
          this.textArrayList.remove(i);
          this.textArrayList.add(i,JOptionPane.showInputDialog(null, "Digite o novo Login: "));
          this.textArrayList.add(i+1,JOptionPane.showInputDialog(null, "Digite a nova senha: "));
        }else{
          throw new IncorretPasswordException("Senha Incorreta!");
        }  
      }else{
        throw new AbsentUserException("Usuário não encontrado!");
      }
      write();
    }

    @Override
    public void delete()throws  AbsentUserException, IncorretPasswordException{  
      String str = JOptionPane.showInputDialog(null, "Digite o login para troca de usuário:");
      
      if(this.textArrayList.contains(str)){
        String key = JOptionPane.showInputDialog(null, "Digite a senha: ");
        int i = this.textArrayList.indexOf(str);

        if(key.equals(this.textArrayList.get(i+1))){
          this.textArrayList.remove(i);
          this.textArrayList.remove(i);
        }else{
          throw new IncorretPasswordException("Senha Incorreta!");
        }
      }else{
        throw new AbsentUserException("Usuário não encontrado!");
      }
      write(); 
    } 

    ///////////////////////////////////////////////////////////////////////////
    private ArrayList<String> toArrayListStr(String[] textArray){
      for (String str : textArray){
        this.textArrayList.add(str);
      }
      return this.textArrayList;
    }

    private String[] toListStr(ArrayList<String> textArrayList){
      String[] textArray = new String[this.textArrayList.size()];
      for(int i = 0; i < this.textArrayList.size(); i++){
        textArray[i] = textArrayList.get(i);
      }
      return textArray;
    }
}
