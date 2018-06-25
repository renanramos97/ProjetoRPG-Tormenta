package GUI;

import GUI.criarFicha.TelaCriarFichaController;
import static database.Database.executarQuery;
import database.TABLE_Fichas;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import parser.ParserJSON;
import parser.Ficha;

public class TelaInicialController implements Initializable {
    
    @FXML
    private Button btnCriarFicha, btnConsultaDB, btnCriaDB, btnCarregarFicha;
    
    private int ficha_id;
    
    @FXML
    private void telaCriarFicha(ActionEvent event) throws Exception {
        Ficha ficha = new Ficha();
        TABLE_Fichas.inserir(ficha);
    
        ResultSet result = executarQuery("SELECT MAX(ID) FROM FICHAS");
        //System.out.println(result);
        //System.out.println("Fora da query: " + result.isClosed());
        try{
            //System.out.println("Numero de colunas: " + result.getMetaData().getColumnCount());
            //System.out.println("Nome da coluna: " + result.getMetaData().getColumnName(1));
            //System.out.println("Valor da coluna: " + result.getInt("MAX(ID)"));
            ficha_id = result.getInt("MAX(ID)");
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
                
        Stage stage = (Stage) btnCriarFicha.getScene().getWindow();
        
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/GUI/criarFicha/TelaCriarFicha.fxml"));
        Parent root = fxmlloader.load();
        
        TelaCriarFichaController controller = fxmlloader.getController();
        controller.setFicha_id(ficha_id);
        controller.preencherCampos();
        
        Scene novaScene = new Scene(root, 1000, 690);
        stage.setScene(novaScene);
    }
    
    @FXML
    private void consultaDB(ActionEvent event) throws Exception{
        Stage stage = (Stage) btnConsultaDB.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/consultarDB/TelaConsultarMagias.fxml"));
        
        Scene telaConsultarMagias = new Scene(loader.load(), 1000, 720);
        stage.setScene(telaConsultarMagias);
    }
    
    @FXML
    private void criarDB(ActionEvent event) throws Exception{
        try{
            ParserJSON.cadastrarMagias();
            ParserJSON.cadastrarRacas();
            ParserJSON.cadastrarTracosRaciais();
        } catch(Exception e){
            System.err.print(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
