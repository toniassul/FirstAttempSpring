package com.example.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class ArticleView extends JFrame {
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtBrand;
    private JTextField txtPrice;
    private JTextField txtStock;
    private JTextField txtFilter;
    private JTable table;
    private JButton btnInsert;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnExit;
    private JComboBox<String> cboFilter;
    private DefaultTableModel tableModel;

    public ArticleView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Article Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(800, 600);

        // Initialize components
        JLabel lblId = new JLabel("Id_Articulo:");
        JLabel lblName = new JLabel("Nombre de Articulo:");
        JLabel lblBrand = new JLabel("Marca:");
        JLabel lblPrice = new JLabel("Precio:");
        JLabel lblStock = new JLabel("Stock:");
        JLabel lblFilter = new JLabel("Filtrar por:");

        txtId = new JTextField();
        txtName = new JTextField();
        txtBrand = new JTextField();
        txtPrice = new JTextField();
        txtStock = new JTextField();
        txtFilter = new JTextField();

        btnInsert = new JButton("Insertar");
        btnUpdate = new JButton("Actualizar");
        btnDelete = new JButton("Eliminar");
        btnClear = new JButton("Limpiar");
        btnExit = new JButton("Salir");

        cboFilter = new JComboBox<>(new String[]{"ID_ARTICULO", "NOMBRE_ARTICULO", "MARCA", "PRECIO"});

        // Set component bounds
        lblId.setBounds(30, 30, 100, 25);
        txtId.setBounds(140, 30, 100, 25);
        //txtId.setEditable(false); // ID should not be editable

        lblName.setBounds(30, 70, 120, 25);
        txtName.setBounds(140, 70, 200, 25);

        lblBrand.setBounds(30, 110, 100, 25);
        txtBrand.setBounds(140, 110, 200, 25);

        lblPrice.setBounds(30, 150, 100, 25);
        txtPrice.setBounds(140, 150, 100, 25);

        lblStock.setBounds(30, 190, 100, 25);
        txtStock.setBounds(140, 190, 100, 25);

        btnInsert.setBounds(400, 30, 100, 25);
        btnUpdate.setBounds(400, 70, 100, 25);
        btnDelete.setBounds(400, 110, 100, 25);
        btnClear.setBounds(400, 150, 100, 25);
        btnExit.setBounds(400, 190, 100, 25);

        // Table
        String[] columns = {"ID_ARTICULO", "NOMBRE_ARTICULO", "MARCA", "PRECIO", "STOCK"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 280, 700, 200);

        // Filter components
        lblFilter.setBounds(30, 500, 80, 25);
        cboFilter.setBounds(110, 500, 150, 25);
        txtFilter.setBounds(270, 500, 200, 25);

        // Add components to frame
        add(lblId);
        add(txtId);
        add(lblName);
        add(txtName);
        add(lblBrand);
        add(txtBrand);
        add(lblPrice);
        add(txtPrice);
        add(lblStock);
        add(txtStock);
        add(btnInsert);
        add(btnUpdate);
        add(btnDelete);
        add(btnClear);
        add(btnExit);
        add(scrollPane);
        add(lblFilter);
        add(cboFilter);
        add(txtFilter);

        setLocationRelativeTo(null);
    }

    // Getters for the components
    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtBrand() { return txtBrand; }
    public JTextField getTxtPrice() { return txtPrice; }
    public JTextField getTxtStock() { return txtStock; }
    public JTextField getTxtFilter() { return txtFilter; }
    public JTable getTable() { return table; }
    public JButton getBtnInsert() { return btnInsert; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnExit() { return btnExit; }
    public JComboBox<String> getCboFilter() { return cboFilter; }
    public DefaultTableModel getTableModel() { return tableModel; }
}
