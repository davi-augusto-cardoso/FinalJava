package abstracts;

public class Program implements Crud, Menu, criptography{
    public Program(this, String nameProgram){
        this.nameProgram = nameProgram;
    }
    
    public void write() {};
    public void read()  {};
    public void list()  {};
    public void delete(){};
    public void options(){};
    
    private void encript(){};
    private void decrip(){};   
}
