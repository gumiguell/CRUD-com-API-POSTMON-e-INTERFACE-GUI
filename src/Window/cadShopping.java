package Window;

import bd.dbos.*;
import bd.core.MeuResultSet;
import bd.daos.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.*;

import api.*;

public class cadShopping implements ActionListener 
{
    JFrame frame = new JFrame("CRUD");
    JPanel panel = new JPanel(new GridLayout(16,1));
    JLabel header = new JLabel("CRUD", SwingConstants.CENTER);
    JLabel label = new JLabel("CEP: ");
    JTextField cep = new JTextField();
    JLabel label1 = new JLabel("Número: ");
    JTextField numero = new JTextField();
    JLabel label2 = new JLabel("Nome: ");
    JTextField nome = new JTextField();
    JLabel label3 = new JLabel("POSTMON: ");
    JButton incluir = new JButton("Incluir Dados");
    JButton buscar = new JButton("Buscar");
    JButton verTabela = new JButton("Ver tabela");
    JButton atualizar = new JButton("Atualizar linha");
    JButton deletar = new JButton("Deletar linha (informe o CEP e Numero)");
    JButton voltar = new JButton("Voltar para tela principal");
    JPanel emptyPanel = new JPanel();
    

    cadShopping()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250,700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        Font font = new Font("Bahnschrift", Font.BOLD, 14);
        Font font2 = new Font("Bahnschrift", Font.BOLD, 12);
        header.setFont(new Font("Bahnschrift",Font.BOLD,30));
        header.setFocusable(false);

        label.setFont(font);
        cep.setFont(font);
        label1.setFont(font);
        numero.setFont(font);
        label2.setFont(font);
        nome.setFont(font);
        label3.setFont(font2);
        incluir.setFont(font);
        buscar.setFont(font);
        verTabela.setFont(font);
        atualizar.setFont(font);
        deletar.setFont(font);
        voltar.setFont(font);

        cep.setPreferredSize(new Dimension(200, 50));
        numero.setPreferredSize(new Dimension(200, 50));
        nome.setPreferredSize(new Dimension(200, 50));
        incluir.setPreferredSize(new Dimension(500, 50));
        buscar.setPreferredSize(new Dimension(500, 50));
        verTabela.setPreferredSize(new Dimension(500, 50));
        atualizar.setPreferredSize(new Dimension(500, 50));
        deletar.setPreferredSize(new Dimension(500, 50));
        voltar.setPreferredSize(new Dimension(500, 50));
        emptyPanel.setPreferredSize(new Dimension(500, 50));

        panel.add(header);
        panel.add(label);
        panel.add(cep);
        panel.add(label1);
        panel.add(numero);
        panel.add(label2);
        panel.add(nome);
        panel.add(label3);
        panel.add(emptyPanel); // Adiciona um espaço vazio
        panel.add(incluir);
        panel.add(buscar);
        panel.add(verTabela);
        panel.add(atualizar);
        panel.add(deletar);
        panel.add(voltar);
        frame.add(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        incluir.addActionListener(this);
        buscar.addActionListener(this);
        verTabela.addActionListener(this);
        atualizar.addActionListener(this);
        deletar.addActionListener(this);
        voltar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        
        if(e.getSource()==incluir)
        {
            try {
                Shopping data = new Shopping(cep.getText(), Integer.parseInt(numero.getText()), nome.getText());
                Shoppings.incluir(data);

               
                Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", data.getCep());
                String logradouroStr = logradouro.toString();
                label3.setText(logradouroStr);

            
                JOptionPane.showMessageDialog(frame,"Dados incluídos com sucesso!");
            }
            catch(Exception erro)
            {
                erro.printStackTrace();
                JOptionPane.showMessageDialog(frame,"Dados inválidos. " + erro.getMessage());
            }
        }
        if(e.getSource()==buscar)
        {
            try
            {
                Shopping data = Shoppings.getShopping(cep.getText(), Integer.parseInt(numero.getText()));
        
                cep.setText(""+data.getCep()); 
                numero.setText(""+data.getNumero());
                nome.setText(""+data.getNome());

                Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", data.getCep());
                String logradouroStr = logradouro.toString();
                label3.setText(logradouroStr);
            }
            catch(Exception erro)
            {
                erro.printStackTrace();
                JOptionPane.showMessageDialog(frame,"Dados inválidos. " + erro.getMessage());
            }
        }
        if(e.getSource()==verTabela)
        {
            new tabelaShopping();
        }

        if(e.getSource()==atualizar)
        {
            try {
                int resposta;
                resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja atualizar esses dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION)
                {
                    Shopping data = new Shopping(cep.getText(), Integer.parseInt(numero.getText()), nome.getText());
                    Shoppings.alterar(data);
                    JOptionPane.showMessageDialog(frame,"Dados atualizados!");
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Update cancelado");
                }

                Shopping data = Shoppings.getShopping(cep.getText(), Integer.parseInt(numero.getText()));
        
                cep.setText(""+data.getCep()); 
                numero.setText(""+data.getNumero());
                nome.setText(""+data.getNome());
                
                Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", data.getCep());
                String logradouroStr = logradouro.toString();
                label3.setText(logradouroStr);
            }
            catch(Exception erro)
                {
                    erro.printStackTrace();
                    JOptionPane.showMessageDialog(frame,"Dados inválidos. " +erro.getMessage());
                }
        }

        if(e.getSource()==deletar)
        {
            
            try {
                    int resposta;
                    resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esses dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION)
                    {
                        Shoppings.excluir(cep.getText(), Integer.parseInt(numero.getText()));
                        JOptionPane.showMessageDialog(frame,"Dados deletados com sucesso!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame,"Delete cancelado");
                    }
                }
            catch(Exception erro)
                {
                    erro.printStackTrace();
                    JOptionPane.showMessageDialog(frame,"Dados inválidos. " +erro.getMessage());
                }
        }
        if(e.getSource()==voltar)
        {
            new LaunchPage();
            frame.dispose();
        }
    }

}
