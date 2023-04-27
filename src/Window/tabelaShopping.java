package Window;

import bd.dbos.*;
import bd.core.MeuResultSet;
import bd.daos.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import api.*;

public class tabelaShopping {
    
    private JTable table;
    private DefaultTableModel tableModel;
    
    tabelaShopping() {
        
        // Configurações da janela
        JFrame frame = new JFrame("Tabela");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1400, 700);
        frame.setLocationRelativeTo(null);
        
        // Configurações do painel
        JPanel panel = new JPanel(new BorderLayout());
        
        // Configurações da tabela
        String[] colunas = {"CEP", "Número", "Nome", "Postmon"};
        tableModel = new DefaultTableModel(colunas, 0);
        table = new JTable(tableModel);
        table.getTableHeader().setFont(new Font("Bahnschrift", Font.BOLD, 14));
        table.setRowHeight(25);
        table.setFont(new Font("Bahnschrift", Font.PLAIN, 14));

        // Define a subclasse de DefaultTableCellRenderer para personalizar as células da tabela
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Define a cor de fundo das células ímpares como cinza claro
                if (row % 2 == 1) {
                    c.setBackground(new Color(240, 240, 240));
                } else {
                    c.setBackground(Color.WHITE);
                }
                
                // Define a cor de fundo da célula de logradouro como amarelo claro
                if (column == 3) {
                    c.setBackground(new Color(173, 216, 230));
                }
                
                return c;
            }
        };

        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
        table.getColumnModel().getColumn(1).setCellRenderer(renderer);
        table.getColumnModel().getColumn(2).setCellRenderer(renderer);
        table.getColumnModel().getColumn(3).setCellRenderer(renderer);
        // Define o tamanho da coluna de logradouro para 500 pixels
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn logradouroColumn = columnModel.getColumn(3);
        logradouroColumn.setPreferredWidth(700);


        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Exibe a janela
        frame.setContentPane(panel);
        frame.setVisible(true);
        
        // Atualiza a tabela com os dados iniciais
        atualizarTabela();
    }   
    
    private void atualizarTabela() {

        try {
            // Obtém os shoppings do banco de dados
            MeuResultSet result = Shoppings.getShoppings();

            // Adiciona cada shopping como uma nova linha na tabela
            while(result.next()) {
                String cep = result.getString("cep");
                int numero = result.getInt("numero");
                String nome = result.getString("nome");
                Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cep);
                String logradouroStr = logradouro.toString();
                
                Object[] row = {cep, numero, nome, logradouroStr};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar os shoppings.");
        }
    }
}

// package Window;

// import bd.dbos.*;
// import bd.core.MeuResultSet;
// import bd.daos.*;

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import javax.swing.table.*;

// import api.*;

// public class tabelaShopping {
    
//     private JTable table;
//     private DefaultTableModel tableModel;
    
//     tabelaShopping() {
        
//         // Configurações da janela
//         JFrame frame = new JFrame("Tabela");
//         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         frame.setSize(800, 600);
//         frame.setLocationRelativeTo(null);
        
//         // Configurações do painel
//         JPanel panel = new JPanel(new BorderLayout());
        
//         // Configurações da tabela
//         String[] colunas = {"CEP", "Número", "Nome", "Logradouro"};
//         tableModel = new DefaultTableModel(colunas, 0);
//         table = new JTable(tableModel);
//         table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//         table.setRowHeight(25);
//         table.setFont(new Font("Arial", Font.PLAIN, 14));
//         JScrollPane scrollPane = new JScrollPane(table);
//         panel.add(scrollPane, BorderLayout.CENTER);
        
//         // Configurações do botão
//         JButton btnAtualizar = new JButton("Atualizar");
//         btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 14));
//         btnAtualizar.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 atualizarTabela();
//             }
//         });
//         panel.add(btnAtualizar, BorderLayout.SOUTH);
        
//         // Exibe a janela
//         frame.setContentPane(panel);
//         frame.setVisible(true);
        
//         // Atualiza a tabela com os dados iniciais
//         atualizarTabela();
//     }   
    
//     private void atualizarTabela() {
//         // Remove todas as linhas da tabela
//         tableModel.setRowCount(0);

//         try {
//             // Obtém os shoppings do banco de dados
//             MeuResultSet result = Shoppings.getShoppings();

//             // Adiciona cada shopping como uma nova linha na tabela
//             while(result.next()) {
//                 String cep = result.getString("cep");
//                 int numero = result.getInt("numero");
//                 String nome = result.getString("nome");
//                 Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cep);
//                 String logradouroStr = logradouro.toString();
                
//                 Object[] row = {cep, numero, nome, logradouroStr};
//                 tableModel.addRow(row);
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar os shoppings.");
//         }
//     }
// }


       



    // package Window;

    // import bd.dbos.*;
    // import bd.core.MeuResultSet;
    // import bd.daos.*;

    // import java.awt.event.ActionEvent;
    // import java.awt.event.ActionListener;
    // import java.awt.geom.Area;

    // import javax.swing.*;

    // import java.awt.*;
    // import java.awt.event.*;
    // import api.*;

    // public class tabelaShopping
    // {
    //     JFrame frame = new JFrame("Tabela");
    //     JPanel panel = new JPanel(new GridLayout(1,1));
    //     JTextArea area = new JTextArea(20,10);

    //     tabelaShopping()
    //     {
    //         String dados = "";

    //         try {
    //             MeuResultSet result = Shoppings.getShoppings();
    //             while(result.next()) {
    //                 Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class,  "https://api.postmon.com.br/v1/cep", result.getString("cep"));
    //                 dados += ""+result.getString("cep")+"           "+result.getInt("numero")+"          "
    //                 +result.getString("nome")+"           "+ logradouro.toString()+"\n";
    //                 // dados += ""+result.getString("cep")+"          "+result.getInt("numero")+"          "
    //                 // +result.getString("nome")+"           "+ logradouro.toString()+"\n";
    //             }
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //            JOptionPane.showMessageDialog(frame,"ERROR");
    //         }
    //         area.setText(dados);
    //         panel.add(area);
    //         frame.setSize(1250,700);
    //         frame.setVisible(true);
    //         frame.add(panel);
    //     }   
    // }
