package com.example.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ArticleView extends JFrame {
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtBrand;
    private JTextField txtPrice;
    private JTextField txtStock;
    private JTextField txtFilter;
    private JTextField txtSearchId;
    private JTable table;
    private JButton btnInsert;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnExit;
    private JButton btnSearch;
    private JComboBox<String> cboFilter;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;
    private JPanel searchPanel;
    private JPanel crudPanel;

    public ArticleView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Gestión de Artículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 700);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();

        // Initialize search panel
        initSearchPanel();

        // Initialize CRUD panel
        initCrudPanel();

        // Add panels to tabbed pane
        tabbedPane.addTab("Búsqueda", searchPanel);
        tabbedPane.addTab("Gestión de Artículos", crudPanel);

        // Add tabbed pane to frame
        add(tabbedPane, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private void initSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(null);
        
        // Search components
        JLabel lblSearchId = new JLabel("ID Artículo:");
        txtSearchId = new JTextField();
        btnSearch = new JButton("Buscar");
        
        // Results components
        JLabel lblIdResult = new JLabel("ID:");
        JLabel lblNameResult = new JLabel("Nombre:");
        JLabel lblBrandResult = new JLabel("Marca:");
        JLabel lblPriceResult = new JLabel("Precio:");
        JLabel lblStockResult = new JLabel("Stock:");
        
        JTextField txtIdResult = new JTextField();
        JTextField txtNameResult = new JTextField();
        JTextField txtBrandResult = new JTextField();
        JTextField txtPriceResult = new JTextField();
        JTextField txtStockResult = new JTextField();
        
        // Set read-only
        txtIdResult.setEditable(false);
        txtNameResult.setEditable(false);
        txtBrandResult.setEditable(false);
        txtPriceResult.setEditable(false);
        txtStockResult.setEditable(false);

        // Set bounds for search components
        lblSearchId.setBounds(30, 30, 100, 25);
        txtSearchId.setBounds(140, 30, 100, 25);
        btnSearch.setBounds(250, 30, 100, 25);

        // Set bounds for results
        lblIdResult.setBounds(30, 80, 100, 25);
        txtIdResult.setBounds(140, 80, 200, 25);
        
        lblNameResult.setBounds(30, 120, 100, 25);
        txtNameResult.setBounds(140, 120, 200, 25);
        
        lblBrandResult.setBounds(30, 160, 100, 25);
        txtBrandResult.setBounds(140, 160, 200, 25);
        
        lblPriceResult.setBounds(30, 200, 100, 25);
        txtPriceResult.setBounds(140, 200, 200, 25);
        
        lblStockResult.setBounds(30, 240, 100, 25);
        txtStockResult.setBounds(140, 240, 200, 25);

        // Add components to search panel
        searchPanel.add(lblSearchId);
        searchPanel.add(txtSearchId);
        searchPanel.add(btnSearch);
        searchPanel.add(lblIdResult);
        searchPanel.add(txtIdResult);
        searchPanel.add(lblNameResult);
        searchPanel.add(txtNameResult);
        searchPanel.add(lblBrandResult);
        searchPanel.add(txtBrandResult);
        searchPanel.add(lblPriceResult);
        searchPanel.add(txtPriceResult);
        searchPanel.add(lblStockResult);
        searchPanel.add(txtStockResult);
    }

    private void initCrudPanel() {
        crudPanel = new JPanel();
        crudPanel.setLayout(null);

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

        cboFilter = new JComboBox<>(new String[]{"ID_ARTICULO", "NOMBRE_ARTICULO", "MARCA"});

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

        // Add components to CRUD panel
        crudPanel.add(lblId);
        crudPanel.add(txtId);
        crudPanel.add(lblName);
        crudPanel.add(txtName);
        crudPanel.add(lblBrand);
        crudPanel.add(txtBrand);
        crudPanel.add(lblPrice);
        crudPanel.add(txtPrice);
        crudPanel.add(lblStock);
        crudPanel.add(txtStock);
        crudPanel.add(btnInsert);
        crudPanel.add(btnUpdate);
        crudPanel.add(btnDelete);
        crudPanel.add(btnClear);
        crudPanel.add(btnExit);
        crudPanel.add(scrollPane);
        crudPanel.add(lblFilter);
        crudPanel.add(cboFilter);
        crudPanel.add(txtFilter);
    }

    // Getters for the components
    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtBrand() { return txtBrand; }
    public JTextField getTxtPrice() { return txtPrice; }
    public JTextField getTxtStock() { return txtStock; }
    public JTextField getTxtFilter() { return txtFilter; }
    public JTextField getTxtSearchId() { return txtSearchId; }
    public JTable getTable() { return table; }
    public JButton getBtnInsert() { return btnInsert; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnExit() { return btnExit; }
    public JButton getBtnSearch() { return btnSearch; }
    public JComboBox<String> getCboFilter() { return cboFilter; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTabbedPane getTabbedPane() { return tabbedPane; }
}
