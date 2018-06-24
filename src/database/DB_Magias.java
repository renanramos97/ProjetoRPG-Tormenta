package database;

import java.sql.*;
import java.util.ArrayList;
import parser.Magia;
import static database.Database.connect;


public class DB_Magias {
    
    public static void gerarTableMagias(){
        Connection c = connect();
        
        String sql = "CREATE TABLE MAGIAS (" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "NOME TEXT NOT NULL," +
                            "NIVEL TEXT NOT NULL," +
                            "TEMPO_EXECUCAO TEXT," +
                            "ALCANCE TEXT," +
                            "AREA TEXT," +
                            "EFEITO TEXT," +
                            "ALVO TEXT," +
                            "DURACAO TEXT," +
                            "TESTE_RESISTENCIA TEXT," +
                            "FONTE TEXT," +
                            "DESCRICAO TEXT)";
        
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            s.close();
            c.close();
            System.out.println("Tabela MAGIAS gerada com sucesso.");
            
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public static void inserirMagia(Magia magia){ //TODO
        Connection c = connect();
        
        String sql = "INSERT INTO MAGIAS VALUES("
                    + "NULL,"
                    + "\"" + magia.getNome() + "\","
                    + "\"" + magia.getNivel() + "\","
                    + "\"" + magia.getTempoExecucao() + "\","
                    + "\"" + magia.getAlcance() + "\","
                    + "\"" + magia.getArea() + "\","
                    + "\"" + magia.getEfeito() + "\","
                    + "\"" + magia.getAlvo() + "\","
                    + "\"" + magia.getDuracao() + "\","
                    + "\"" + magia.getTesteResistencia() + "\","
                    + "\"" + magia.getFonte() + "\","
                    + "\"" + magia.getDescricao() + "\")";
        
        try{
            Statement s = c.createStatement();            
            s.executeUpdate(sql);
            s.close();
            c.close();
            
            System.out.println("Magia " + magia.getNome() + " inserida com sucesso");
                    
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public static ArrayList<String> gerarArrayMagias(String query){
        Connection c = connect();
        ArrayList<String> saida = new ArrayList<>();
        
        try{
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(query);
            
            int resultSize = result.getMetaData().getColumnCount();
            
            while(result.next()){
                int i = 1;
                while(i <= resultSize){
                    saida.add(result.getString(i++));
                }
            }
            
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return saida;
    }
    
    public static Magia consultarMagia(String retorno, String busca){
        Connection c = connect();
        String sql = "SELECT " + retorno + " FROM MAGIAS";
        if (busca != null){
            sql = sql + " WHERE " + busca;
        }
        
        Magia magia = null;
        
        try {
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(sql);
            magia = new Magia(result);
            System.out.println("Nome: " + magia.getNome());
            
        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        return magia;
    }
}
