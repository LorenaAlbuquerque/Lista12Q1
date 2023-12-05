package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Banco;
import model.Usuario;


@WebServlet("/AtualizarSenha")
public class AtualizarSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String novaSenha = request.getParameter("novaSenha");

        Connection con = Banco.getConnection();
        String sql = "UPDATE USUARIO SET SENHA = ? WHERE ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, novaSenha);
            stmt.setInt(2, user.getId()); 

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                user.setSenha(novaSenha); 
                request.getSession().setAttribute("user", user); 
                response.sendRedirect("home.jsp"); 
            } else {
                request.setAttribute("erro", "Não foi possível atualizar a senha.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("atualizarSenha.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
