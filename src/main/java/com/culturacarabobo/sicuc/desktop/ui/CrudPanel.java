package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.culturacarabobo.sicuc.desktop.models.CultorResponse;
import com.culturacarabobo.sicuc.desktop.services.CultorService;
import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.ExcelExporter;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.IdToNameResolver;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InitialDataLoader;
import com.formdev.flatlaf.FlatClientProperties;

public class CrudPanel extends JPanel {

    // UI state and filter fields
    private final Window WINDOW;
    private int municipalityId;
    private int parishId;
    private String gender;
    private int artCategoryId;
    private int artDisciplineId;
    private String disability;
    private String illness;
    private final JTextField SEARCH_FIELD;
    private final String[] COLUMN_NAMES;
    private final DefaultTableModel MODEL;
    private final JTable TABLE;
    private final JScrollPane SCROLL_PANE;
    private final JPanel EMPTY_PANEL;
    private final JPanel LOADER_PANEL;
    private SwingWorker<Void, Void> laodDataWorker;
    private SwingWorker<Void, Void> showDetailsWorker;

    public CrudPanel(Window window) {
        super();
        setLayout(null);
        setBackground(ColorPalette.INPUT_COLOR);

        // Initialize filters and window reference
        WINDOW = window;
        municipalityId = 0;
        parishId = 0;
        gender = "";
        artCategoryId = 0;
        artDisciplineId = 0;
        disability = "";
        illness = "";

        // Hamburger menu button for navigation
        HamburgerMenuButton hamburgerMenuButton = new HamburgerMenuButton(WINDOW);
        hamburgerMenuButton.setBounds(10, 10, 49, 49);
        add(hamburgerMenuButton);

        // Search field with styling and action to load data
        SEARCH_FIELD = new JTextField();
        SEARCH_FIELD.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        SEARCH_FIELD.setFont(FontStyles.FONT_TEXT.deriveFont(17f));
        SEARCH_FIELD.setForeground(ColorPalette.TERTIARY_COLOR);
        SEARCH_FIELD.setBackground(ColorPalette.TEXT_COLOR);
        SEARCH_FIELD.setBounds(235, 20, 350, 50);
        SEARCH_FIELD.addActionListener(e -> {
            loadData(SEARCH_FIELD.getText().trim());
        });
        add(SEARCH_FIELD);

        // Search button triggers data loading
        JButton searchButton = new JButton("BUSCAR");
        searchButton.setIcon(ImageResources.SEARCH_ICON);
        searchButton.setIconTextGap(10);
        searchButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        searchButton.setForeground(ColorPalette.TEXT_COLOR);
        searchButton.setBackground(ColorPalette.PRIMARY_COLOR);
        searchButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        searchButton.setCursor(CursorStyles.HAND_CURSOR);
        searchButton.setBounds(595, 20, 170, 50);
        searchButton.addActionListener(e -> {
            loadData(SEARCH_FIELD.getText().trim());
        });
        add(searchButton);

        CrudPanel crudPanel = this;

        // Filter icon with hover and click effects opens FilterDialog
        JLabel filterButton = new JLabel(ImageResources.FILTER_ICON);
        filterButton.setBounds(930, 15, 49, 49);
        filterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                filterButton.setIcon(ImageResources.FILTER_ICON_HOVER);
                filterButton.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                filterButton.setIcon(ImageResources.FILTER_ICON);
                filterButton.setCursor(CursorStyles.DEFAULT_CURSOR);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                filterButton.setIcon(ImageResources.FILTER_ICON_PRESSED);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x >= 0 && x <= 48 && y >= 0 && y <= 48) {
                    filterButton.setIcon(ImageResources.FILTER_ICON_HOVER);
                    new FilterDialog(WINDOW, crudPanel, municipalityId, parishId, artCategoryId, artDisciplineId,
                            gender, disability, illness);
                } else {
                    filterButton.setIcon(ImageResources.FILTER_ICON);
                }
            }
        });
        add(filterButton);

        // Define table columns
        COLUMN_NAMES = new String[10];
        COLUMN_NAMES[0] = "Nombres";
        COLUMN_NAMES[1] = "Apellidos";
        COLUMN_NAMES[2] = "Género";
        COLUMN_NAMES[3] = "Cédula";
        COLUMN_NAMES[4] = "Teléfono";
        COLUMN_NAMES[5] = "Correo";
        COLUMN_NAMES[6] = "Municipio";
        COLUMN_NAMES[7] = "Parroquia";
        COLUMN_NAMES[8] = "Categoría";
        COLUMN_NAMES[9] = "Disciplina";

        // Table model non-editable
        MODEL = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Table setup with colors, fonts, selection and tooltips
        TABLE = new JTable(MODEL) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                if (c instanceof JComponent) {
                    Object value = getValueAt(row, column);
                    String text = (value == null) ? "" : value.toString();
                    ((JComponent) c).setToolTipText(text);
                }

                return c;
            }
        };
        TABLE.setBackground(ColorPalette.INPUT_COLOR);
        TABLE.setForeground(ColorPalette.TERTIARY_COLOR);
        TABLE.setFont(FontStyles.FONT_TEXT.deriveFont(14f));
        TABLE.setGridColor(ColorPalette.TERTIARY_COLOR);
        TABLE.setShowHorizontalLines(true);
        TABLE.setShowVerticalLines(true);
        TABLE.setRowHeight(24);
        TABLE.setCellSelectionEnabled(false);
        TABLE.setRowSelectionAllowed(true);
        TABLE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Double-click row to show details
        TABLE.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();

                    int row = TABLE.rowAtPoint(e.getPoint());

                    if (row >= 0) {
                        showDetails(row);
                    }
                }
            }
        });

        // Customize table header look
        JTableHeader header = TABLE.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel label = new JLabel(value.toString());
                label.setHorizontalAlignment(CENTER);
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, ColorPalette.TERTIARY_COLOR));
                label.setBackground(ColorPalette.PRIMARY_COLOR);
                label.setForeground(ColorPalette.TEXT_COLOR);
                label.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
                label.setOpaque(true);

                return label;
            }
        });
        header.setFocusable(false);
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);
        header.setUI(new BasicTableHeaderUI() {
        });

        // Scroll pane wrapping table with border
        SCROLL_PANE = new JScrollPane(TABLE);
        SCROLL_PANE.setBackground(ColorPalette.INPUT_COLOR);
        SCROLL_PANE.setBorder(new LineBorder(ColorPalette.TERTIARY_COLOR, 1));
        SCROLL_PANE.getVerticalScrollBar().setBorder(new LineBorder(ColorPalette.TERTIARY_COLOR, 1));
        SCROLL_PANE.setBounds(20, 110, 960, 450);
        add(SCROLL_PANE);

        // Empty panel shown when no data
        JLabel empty = new JLabel("No hay registros para mostrar.");
        empty.setFont(FontStyles.FONT_TITLE.deriveFont(33f));
        empty.setForeground(ColorPalette.TERTIARY_COLOR);
        empty.setBounds(260, 195, 450, 44);

        EMPTY_PANEL = new JPanel();
        EMPTY_PANEL.setLayout(null);
        EMPTY_PANEL.setBackground(null);
        EMPTY_PANEL.setBorder(new LineBorder(ColorPalette.TERTIARY_COLOR, 1));
        EMPTY_PANEL.setBounds(20, 110, 960, 450);
        EMPTY_PANEL.add(empty);
        add(EMPTY_PANEL);

        // Loader panel with loading animation
        JLabel loader = new JLabel(ImageResources.LOADER_ICON_200);
        loader.setBounds(380, 125, 200, 200);

        LOADER_PANEL = new JPanel();
        LOADER_PANEL.setLayout(null);
        LOADER_PANEL.setBackground(null);
        LOADER_PANEL.setBorder(new LineBorder(ColorPalette.TERTIARY_COLOR, 1));
        LOADER_PANEL.setBounds(20, 110, 960, 450);
        LOADER_PANEL.add(loader);
        add(LOADER_PANEL);

        // Button to show details of selected row; shows dialog if no selection or no
        // data
        JButton viewDetailsButton = new JButton("VER DETALLES");
        viewDetailsButton.setIcon(ImageResources.VIEW_DETAILS_ICON);
        viewDetailsButton.setIconTextGap(10);
        viewDetailsButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        viewDetailsButton.setForeground(ColorPalette.TEXT_COLOR);
        viewDetailsButton.setBackground(ColorPalette.PRIMARY_COLOR);
        viewDetailsButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        viewDetailsButton.setCursor(CursorStyles.HAND_CURSOR);
        viewDetailsButton.setBounds(80, 588, 210, 50);
        viewDetailsButton.addActionListener(e -> {
            if (SCROLL_PANE.isVisible()) {
                int row = TABLE.getSelectedRow();

                if (row >= 0) {
                    showDetails(row);
                } else {
                    (new GeneralDialog(WINDOW, "Selección Requerida", ImageResources.ERROR_ICON,
                            "Selecciona un registro para visualizar.",
                            20f, ColorPalette.ERROR_COLOR, "ACEPTAR")).setVisible(true);
                }
            } else {
                (new GeneralDialog(WINDOW, "Sin Datos Disponibles", ImageResources.ERROR_ICON,
                        "No hay registros para realizar esta acción.", 20f, ColorPalette.ERROR_COLOR,
                        "ACEPTAR"))
                        .setVisible(true);
            }
        });
        add(viewDetailsButton);

        // Button to refresh table data; clears search and reloads data
        JButton refreshButton = new JButton("ACTUALIZAR");
        refreshButton.setIcon(ImageResources.REFRESH_ICON);
        refreshButton.setIconTextGap(10);
        refreshButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        refreshButton.setForeground(ColorPalette.TEXT_COLOR);
        refreshButton.setBackground(ColorPalette.PRIMARY_COLOR);
        refreshButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        refreshButton.setCursor(CursorStyles.HAND_CURSOR);
        refreshButton.setBounds(310, 588, 210, 50);
        refreshButton.addActionListener(e -> {
            clearSearch();
            loadData("");
        });
        add(refreshButton);

        // Button to clear all filters and reload data
        JButton resetFiltersButton = new JButton("LIMPIAR FILTROS");
        resetFiltersButton.setIcon(ImageResources.RESET_FILTERS_ICON);
        resetFiltersButton.setIconTextGap(10);
        resetFiltersButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        resetFiltersButton.setForeground(ColorPalette.TEXT_COLOR);
        resetFiltersButton.setBackground(ColorPalette.PRIMARY_COLOR);
        resetFiltersButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        resetFiltersButton.setCursor(CursorStyles.HAND_CURSOR);
        resetFiltersButton.setBounds(540, 588, 210, 50);
        resetFiltersButton.addActionListener(e -> {
            municipalityId = 0;
            parishId = 0;
            gender = "";
            artCategoryId = 0;
            artDisciplineId = 0;
            disability = "";
            illness = "";
            clearSearch();
            loadData("");
        });
        add(resetFiltersButton);

        // Button to export visible data to Excel with error handling
        JButton exportFileButton = new JButton("EXPORTAR ARCHIVO");
        exportFileButton.setIcon(ImageResources.EXPORT_FILE_ICON);
        exportFileButton.setIconTextGap(10);
        exportFileButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        exportFileButton.setForeground(ColorPalette.TEXT_COLOR);
        exportFileButton.setBackground(ColorPalette.PRIMARY_COLOR);
        exportFileButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        exportFileButton.setCursor(CursorStyles.HAND_CURSOR);
        exportFileButton.setBounds(770, 588, 210, 50);
        exportFileButton.addActionListener(e -> {
            if (SCROLL_PANE.isVisible()) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar como");
                int userSelection = fileChooser.showSaveDialog(WINDOW);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!filePath.endsWith(".xlsx")) {
                        filePath += ".xlsx";
                    }

                    try {
                        ExcelExporter.exportJTableToExcel(TABLE, filePath);
                        (new GeneralDialog(WINDOW, "Exportación Exitosa", ImageResources.CHECKED_ICON,
                                "El archivo se ha guardado correctamente.", 20f, ColorPalette.PRIMARY_COLOR, "ACEPTAR"))
                                .setVisible(true);
                    } catch (IOException ex) {
                        (new GeneralDialog(WINDOW, "Error al Exportar", ImageResources.ERROR_ICON,
                                "Ocurrió un problema al guardar el archivo.", 20f, ColorPalette.ERROR_COLOR,
                                "REINTENTAR"))
                                .setVisible(true);
                    }
                }
            } else {
                (new GeneralDialog(WINDOW, "Sin Datos Disponibles", ImageResources.ERROR_ICON,
                        "No hay registros para realizar esta acción.", 20f, ColorPalette.ERROR_COLOR,
                        "ACEPTAR"))
                        .setVisible(true);
            }
        });
        add(exportFileButton);
    }

    // Loads data asynchronously, showing loader and updating panels based on
    // results
    public void loadData(String query) {
        cancelWorkers();

        laodDataWorker = new SwingWorker<>() {
            private List<CultorResponse> cultors;

            @Override
            protected Void doInBackground() {
                SCROLL_PANE.setVisible(false);
                EMPTY_PANEL.setVisible(false);
                LOADER_PANEL.setVisible(true);

                cultors = null;

                do {
                    if (isCancelled())
                        return null;

                    cultors = InitialDataLoader.getCultors(WINDOW.getUsername(), WINDOW.getPassword(),
                            municipalityId, parishId, gender, artCategoryId, artDisciplineId, disability, illness,
                            query);

                    if (isCancelled())
                        return null;

                    if (cultors == null) {
                        new NetworkErrorDialog(WINDOW);
                    }
                } while (cultors == null);
                return null;
            }

            @Override
            protected void done() {
                if (isCancelled())
                    return;

                if (cultors.size() > 0) {

                    cultors.sort(Comparator
                            .comparing(CultorResponse::getLastName, String.CASE_INSENSITIVE_ORDER)
                            .thenComparing(CultorResponse::getFirstName, String.CASE_INSENSITIVE_ORDER));

                    String[][] data = listToTable(cultors, COLUMN_NAMES);

                    MODEL.setRowCount(0);

                    for (Object[] fila : data) {
                        MODEL.addRow(fila);
                    }

                    LOADER_PANEL.setVisible(false);
                    EMPTY_PANEL.setVisible(false);
                    SCROLL_PANE.getViewport().setViewPosition(new Point(0, 0));
                    SCROLL_PANE.setVisible(true);

                } else {
                    LOADER_PANEL.setVisible(false);
                    SCROLL_PANE.setVisible(false);
                    EMPTY_PANEL.setVisible(true);
                }
            }
        };

        laodDataWorker.execute();
    }

    // Converts list of cultors into array for JTable
    public String[][] listToTable(List<CultorResponse> cultors, String[] columnNames) {
        if (cultors != null && cultors.size() > 0) {
            String[][] cultorsArray = new String[cultors.size()][columnNames.length];
            for (int i = 0; i < cultorsArray.length; i++) {
                CultorResponse cultor = cultors.get(i);
                cultorsArray[i][0] = cultor.getFirstName();
                cultorsArray[i][1] = cultor.getLastName();
                cultorsArray[i][2] = cultor.getGender().equals("F") ? "Femenino" : "Masculino";
                cultorsArray[i][3] = cultor.getIdNumber();
                cultorsArray[i][4] = cultor.getPhoneNumber();
                cultorsArray[i][5] = cultor.getEmail();
                cultorsArray[i][6] = IdToNameResolver.getMunicipality(cultor.getMunicipalityId());
                cultorsArray[i][7] = IdToNameResolver.getParish(cultor.getParishId());
                cultorsArray[i][8] = IdToNameResolver.getArtCategory(cultor.getArtCategoryId());
                cultorsArray[i][9] = IdToNameResolver.getArtDiscipline(cultor.getArtDisciplineId());
            }
            return cultorsArray;
        } else {
            return null;
        }
    }

    // Loads and shows detailed info of selected row asynchronously
    public void showDetails(int row) {
        cancelWorkers();

        showDetailsWorker = new SwingWorker<>() {
            private String idNumber;
            private List<CultorResponse> cultors;

            @Override
            protected Void doInBackground() {
                idNumber = String.valueOf(TABLE.getValueAt(row, 3));
                cultors = null;

                do {
                    if (isCancelled())
                        return null;
                    cultors = CultorService
                            .getCultors(WINDOW.getUsername(), WINDOW.getPassword(), 0, 0,
                                    "", 0, 0, "", "", idNumber);
                    if (isCancelled())
                        return null;
                    if (cultors == null) {
                        new NetworkErrorDialog(WINDOW);
                    }
                } while (cultors == null);

                return null;
            }

            @Override
            protected void done() {
                if (isCancelled())
                    return;
                CultorResponse cultor = cultors.stream().filter(cul -> cul.getIdNumber().equals(idNumber))
                        .findFirst().orElse(null);
                (new CultorDialog(WINDOW, cultor)).setVisible(true);
            }
        };

        showDetailsWorker.execute();
    }

    // Loads and shows detailed info of selected row asynchronously
    public void cancelWorkers() {
        if (laodDataWorker != null)
            laodDataWorker.cancel(true);

        if (showDetailsWorker != null)
            showDetailsWorker.cancel(true);
    }

    // Clears the search field
    public void clearSearch() {
        SEARCH_FIELD.setText("");
    }

    // Setters for filters
    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public void setParishId(int parishId) {
        this.parishId = parishId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setArtCategoryId(int artCategoryId) {
        this.artCategoryId = artCategoryId;
    }

    public void setArtDisciplineId(int artDisciplineId) {
        this.artDisciplineId = artDisciplineId;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

}
