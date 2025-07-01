package com.example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
    private JTextField txtIdResult;
    private JTextField txtNameResult;
    private JTextField txtBrandResult;
    private JTextField txtPriceResult;
    private JTextField txtStockResult;
    private JMenuBar menuBar;
    private JMenu menuArchivo;
    private JMenu menuAyuda;
    private JMenuItem itemSalir;
    private JMenuItem itemAcercaDe;

    public ArticleView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Gestión de Artículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(850, 750);
        getContentPane().setBackground(new Color(240, 240, 240));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Barra de menú
        menuBar = new JMenuBar();
        menuArchivo = new JMenu("Archivo");
        menuAyuda = new JMenu("Ayuda");
        itemSalir = new JMenuItem("Salir");
        itemAcercaDe = new JMenuItem("Acerca de...");
        menuArchivo.add(itemSalir);
        menuAyuda.add(itemAcercaDe);
        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);

        // Acción para salir
        itemSalir.addActionListener(e -> System.exit(0));
        // Acción para Acerca de
        itemAcercaDe.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Sistema de Gestión de Artículos\nVersión 2.0\nDesarrollado por tu equipo.",
                "Acerca de", JOptionPane.INFORMATION_MESSAGE));

        // Create tabbed pane with custom styling
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabbedPane.setBackground(new Color(255, 255, 255));
        tabbedPane.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200)),
            new EmptyBorder(5, 5, 5, 5)
        ));

        // Initialize panels
        initSearchPanel();
        initCrudPanel();

        // Add panels to tabbed pane with icons
        tabbedPane.addTab("Búsqueda", new ImageIcon(), searchPanel);
        tabbedPane.addTab("Gestión de Artículos", new ImageIcon(), crudPanel);

        // Add tabbed pane to frame
        add(tabbedPane, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private void initSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Search components
        JLabel lblSearchId = new JLabel("ID Artículo:");
        txtSearchId = new JTextField();
        btnSearch = new JButton("Buscar");
        
        // Style components
        styleLabel(lblSearchId);
        styleTextField(txtSearchId);
        styleButton(btnSearch);
        
        // Results components with styling
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(null);
        resultsPanel.setBounds(30, 70, 750, 220);
        resultsPanel.setBackground(new Color(245, 245, 245));
        resultsPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 220, 220)),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblIdResult = new JLabel("ID:");
        JLabel lblNameResult = new JLabel("Nombre:");
        JLabel lblBrandResult = new JLabel("Marca:");
        JLabel lblPriceResult = new JLabel("Precio:");
        JLabel lblStockResult = new JLabel("Stock:");
        // Usar atributos de instancia
        txtIdResult = new JTextField();
        txtNameResult = new JTextField();
        txtBrandResult = new JTextField();
        txtPriceResult = new JTextField();
        txtStockResult = new JTextField();
        
        // Style all labels and text fields
        styleLabel(lblIdResult);
        styleLabel(lblNameResult);
        styleLabel(lblBrandResult);
        styleLabel(lblPriceResult);
        styleLabel(lblStockResult);
        
        styleTextField(txtIdResult);
        styleTextField(txtNameResult);
        styleTextField(txtBrandResult);
        styleTextField(txtPriceResult);
        styleTextField(txtStockResult);
        
        // Set read-only
        txtIdResult.setEditable(false);
        txtNameResult.setEditable(false);
        txtBrandResult.setEditable(false);
        txtPriceResult.setEditable(false);
        txtStockResult.setEditable(false);

        // Set bounds for search components
        lblSearchId.setBounds(30, 30, 100, 25);
        txtSearchId.setBounds(140, 30, 150, 25);
        btnSearch.setBounds(300, 30, 100, 25);

        // Set bounds for results within the results panel
        lblIdResult.setBounds(20, 20, 100, 25);
        txtIdResult.setBounds(130, 20, 200, 25);
        
        lblNameResult.setBounds(20, 55, 100, 25);
        txtNameResult.setBounds(130, 55, 200, 25);
        
        lblBrandResult.setBounds(20, 90, 100, 25);
        txtBrandResult.setBounds(130, 90, 200, 25);
        
        lblPriceResult.setBounds(20, 125, 100, 25);
        txtPriceResult.setBounds(130, 125, 200, 25);
        
        lblStockResult.setBounds(20, 160, 100, 25);
        txtStockResult.setBounds(130, 160, 200, 25);

        // Add components to results panel
        resultsPanel.add(lblIdResult);
        resultsPanel.add(txtIdResult);
        resultsPanel.add(lblNameResult);
        resultsPanel.add(txtNameResult);
        resultsPanel.add(lblBrandResult);
        resultsPanel.add(txtBrandResult);
        resultsPanel.add(lblPriceResult);
        resultsPanel.add(txtPriceResult);
        resultsPanel.add(lblStockResult);
        resultsPanel.add(txtStockResult);

        // Add components to search panel
        searchPanel.add(lblSearchId);
        searchPanel.add(txtSearchId);
        searchPanel.add(btnSearch);
        searchPanel.add(resultsPanel);
    }

    private void initCrudPanel() {
        crudPanel = new JPanel();
        crudPanel.setLayout(null);
        crudPanel.setBackground(Color.WHITE);
        crudPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize components
        JLabel lblId = new JLabel("Id Artículo:");
        JLabel lblName = new JLabel("Nombre de Artículo:");
        JLabel lblBrand = new JLabel("Marca:");
        JLabel lblPrice = new JLabel("Precio:");
        JLabel lblStock = new JLabel("Stock:");
        JLabel lblFilter = new JLabel("Filtrar por:");

        // Style labels
        styleLabel(lblId);
        styleLabel(lblName);
        styleLabel(lblBrand);
        styleLabel(lblPrice);
        styleLabel(lblStock);
        styleLabel(lblFilter);

        // Initialize text fields
        txtId = new JTextField();
        txtName = new JTextField();
        txtBrand = new JTextField();
        txtPrice = new JTextField();
        txtStock = new JTextField();
        txtFilter = new JTextField();

        // Style text fields
        styleTextField(txtId);
        styleTextField(txtName);
        styleTextField(txtBrand);
        styleTextField(txtPrice);
        styleTextField(txtStock);
        styleTextField(txtFilter);

        // Initialize and style buttons
        btnInsert = new JButton("Insertar");
        btnUpdate = new JButton("Actualizar");
        btnDelete = new JButton("Eliminar");
        btnClear = new JButton("Limpiar");
        btnExit = new JButton("Salir");

        styleButton(btnInsert);
        styleButton(btnUpdate);
        styleButton(btnDelete);
        styleButton(btnClear);
        btnExit.setBackground(new Color(180, 70, 70));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnExit.setBorder(new CompoundBorder(
            new LineBorder(new Color(170, 60, 60)),
            new EmptyBorder(5, 15, 5, 15)
        ));
        btnExit.setFocusPainted(false);

        // Style combo box
        cboFilter = new JComboBox<>(new String[]{"ID_ARTICULO", "NOMBRE_ARTICULO", "MARCA"});
        cboFilter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cboFilter.setBackground(Color.WHITE);

        // Create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(30, 30, 750, 230);
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 220, 220)),
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Set component bounds within form panel
        lblId.setBounds(20, 20, 100, 25);
        txtId.setBounds(130, 20, 100, 25);

        lblName.setBounds(20, 55, 120, 25);
        txtName.setBounds(130, 55, 200, 25);

        lblBrand.setBounds(20, 90, 100, 25);
        txtBrand.setBounds(130, 90, 200, 25);

        lblPrice.setBounds(20, 125, 100, 25);
        txtPrice.setBounds(130, 125, 100, 25);

        lblStock.setBounds(20, 160, 100, 25);
        txtStock.setBounds(130, 160, 100, 25);

        btnInsert.setBounds(400, 20, 120, 30);
        btnUpdate.setBounds(400, 60, 120, 30);
        btnDelete.setBounds(400, 100, 120, 30);
        btnClear.setBounds(400, 140, 120, 30);
        btnExit.setBounds(400, 180, 120, 30);

        // Add components to form panel
        formPanel.add(lblId);
        formPanel.add(txtId);
        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(lblBrand);
        formPanel.add(txtBrand);
        formPanel.add(lblPrice);
        formPanel.add(txtPrice);
        formPanel.add(lblStock);
        formPanel.add(txtStock);
        formPanel.add(btnInsert);
        formPanel.add(btnUpdate);
        formPanel.add(btnDelete);
        formPanel.add(btnClear);
        formPanel.add(btnExit);

        // Table styling
        String[] columns = {"ID_ARTICULO", "NOMBRE_ARTICULO", "MARCA", "PRECIO", "STOCK"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.setGridColor(new Color(230, 230, 230));
        
        // Style table header
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 280, 750, 200);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));

        // Filter panel
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(null);
        filterPanel.setBounds(30, 500, 750, 40);
        filterPanel.setBackground(new Color(245, 245, 245));
        filterPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 220, 220)),
            new EmptyBorder(5, 5, 5, 5)
        ));

        lblFilter.setBounds(20, 8, 80, 25);
        cboFilter.setBounds(100, 8, 150, 25);
        txtFilter.setBounds(260, 8, 200, 25);

        filterPanel.add(lblFilter);
        filterPanel.add(cboFilter);
        filterPanel.add(txtFilter);

        // Add panels to CRUD panel
        crudPanel.add(formPanel);
        crudPanel.add(scrollPane);
        crudPanel.add(filterPanel);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBorder(new CompoundBorder(
            new LineBorder(new Color(60, 120, 170)),
            new EmptyBorder(5, 15, 5, 15)
        ));
        button.setFocusPainted(false);
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200)),
            new EmptyBorder(5, 5, 5, 5)
        ));
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    // Getters para los campos de resultados de búsqueda
    public JTextField getTxtIdResult() { return txtIdResult; }
    public JTextField getTxtNameResult() { return txtNameResult; }
    public JTextField getTxtBrandResult() { return txtBrandResult; }
    public JTextField getTxtPriceResult() { return txtPriceResult; }
    public JTextField getTxtStockResult() { return txtStockResult; }
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
