package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Banco;
import model.Usuario;

@WebServlet("/Cadastro")
public class Cadastro extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 		
			throws ServletException, IOException {		
			Usuario user = new Usuario();		
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");				
			user.setLogin(login);
			user.setSenha(senha);		
			if(autenticar(user)){
				request.setAttribute("ok", "Cadastro realizado com sucesso!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("erro", "Não foi possível realizar o cadastro!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
				dispatcher.forward(request, response);
			}
		}
	private boolean autenticar(Usuario user) {
		   boolean autenticado = false;		
		   Connection con = Banco.getConnection();
		   String sql = 	"INSERT INTO USUARIO (LOGIN, SENHA)" + 
				   		"VALUES('" + user.getLogin().trim() + "' , " +
				   		" '" + user.getSenha().trim() + "');" ;
		   try {
			Statement stmt = con.createStatement();
			int rowsAffected = stmt.executeUpdate(sql);
	        if (rowsAffected > 0) {        
	            autenticado = true;
	        }      
		   }catch (SQLException e) {
			e.printStackTrace();
		   }
		   return autenticado;		
		}
}
