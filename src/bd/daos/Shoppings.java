package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Shoppings
{
    public static boolean cadastrado (String cep, int numero) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Shop.Shopping " +
                  "WHERE cep = ? AND numero = ? ";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, cep);
            BDSQLServer.COMANDO.setInt (2, numero);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            throw new Exception ("Erro ao procurar shopping");
        }

        return retorno;
    }

    public static void incluir (Shopping shopping) throws Exception
    {
        if (shopping==null)
            throw new Exception ("Shopping não fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO Shop.Shopping " +
                  "(cep,numero,nome) " +
                  "VALUES " +
                  "(?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString    (1, shopping.getCep ());
            BDSQLServer.COMANDO.setInt    (2, shopping.getNumero ());
            BDSQLServer.COMANDO.setString (3, shopping.getNome ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            erro.getStackTrace();
            throw new Exception ("Erro ao inserir shopping");
        }
    }

    public static void excluir (String cep, int numero) throws Exception
    {
        if (!cadastrado(cep,numero))
            throw new Exception ("Nao cadastrado");
            
        try
        {
            String sql;

            sql = "DELETE FROM Shop.Shopping " +
                  "WHERE cep = ? AND numero = ? ";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, cep);
            BDSQLServer.COMANDO.setInt (2, numero);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao excluir shopping");
        }
    }

    public static void alterar (Shopping shopping) throws Exception
    {
        if (shopping==null)
            throw new Exception ("Shopping nao fornecido");

        if (!cadastrado (shopping.getCep(),shopping.getNumero()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql ="UPDATE Shop.Shopping " +
                "SET nome = ? "+
                "WHERE cep = ? AND numero = ? ";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, shopping.getNome());
            BDSQLServer.COMANDO.setString (2, shopping.getCep());
            BDSQLServer.COMANDO.setInt (3, shopping.getNumero());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de shopping");
        }
    }

    public static Shopping getShopping (String cep, int numero) throws Exception
    {
        Shopping shopping = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Shop.Shopping " +
                  "WHERE cep = ? AND numero = ? ";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, cep);
            BDSQLServer.COMANDO.setInt(2, numero);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first()) throw new Exception ("Nao cadastrado");

            shopping = new Shopping (resultado.getString("cep"), resultado.getInt ("numero"), resultado.getString ("nome"));
            //shopping = new Shopping(cep, numero, resultado.getString("nome"));
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
            throw new Exception ("Erro ao procurar Shopping");
        }

        return shopping;
    }

    public static MeuResultSet getShoppings () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Shop.Shopping";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar Shoppings");
        }

        return resultado;
    }
}