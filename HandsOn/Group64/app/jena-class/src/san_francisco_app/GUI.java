package san_francisco_app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class GUI {
    private JPanel panelGeneral;
    private JSplitPane splitPanel;

    private GenericDomainTableModel<Film> modelFilms;
    private GenericDomainTableModel<ArtCollection> modelArts;
    private GenericDomainTableModel<Park> modelParks;

    private JComboBox<String> comboBoxArt;
    private JComboBox<String> comboBoxFilm;
    private JComboBox<String> comboBoxPark;
    private JTextArea textArea;
    private JTable tableResult;
    private JFrame frame;
    private JScrollPane scrollPaneResult;

    private GUI() {
        frame = new JFrame();
        panelGeneral = new JPanel(new BorderLayout());
        splitPanel = new JSplitPane();

        this.createUpperPanel();
        this.createSearchPanel();
        this.createGeneralPanel();

        this.panelGeneral.add(splitPanel, BorderLayout.CENTER);
        this.splitPanel.setDividerSize(0);
        this.splitPanel.setEnabled(false);

        frame.getRootPane().setGlassPane(new JComponent() {
            public void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        });

        frame.setContentPane(panelGeneral);
        frame.setSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("San Francisco Culture");
        frame.setVisible(true);
    }

    private void createUpperPanel() {
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHeader.setBackground(Color.decode("#515151"));

        JLabel labelHeader = new JLabel("San Francisco");
        labelHeader.setFont(new Font("Roboto", 0, 24));
        labelHeader.setBorder(new EmptyBorder(5, 15, 2, 0));
        labelHeader.setForeground(Color.decode("#c9e0ff"));
        panelHeader.add(labelHeader);
        // TODO

        panelGeneral.add(panelHeader, BorderLayout.NORTH);
    }

    private void createSearchPanel() {
        JLabel labelArt = new JLabel("Art Dataset");
        labelArt.setFont(new Font("Roboto Light", 0, 20));
        labelArt.setBorder(new EmptyBorder(5, 5, 5, 10));
        labelArt.setHorizontalTextPosition(JLabel.LEFT);

        JLabel labelFilm = new JLabel("Film Dataset");
        labelFilm.setFont(new Font("Roboto Light", 0, 20));
        labelFilm.setBorder(new EmptyBorder(5, 0, 5, 0));
        labelFilm.setHorizontalTextPosition(JLabel.LEFT);

        JLabel labelPark = new JLabel("Park Dataset");
        labelPark.setFont(new Font("Roboto Light", 0, 20));
        labelPark.setBorder(new EmptyBorder(5, 0, 5, 0));
        labelPark.setHorizontalTextPosition(JLabel.LEFT);

        comboBoxArt = new JComboBox<>();
        comboBoxFilm = new JComboBox<>();
        comboBoxPark = new JComboBox<>();

        comboBoxArt.addItem("-- Select a Query --");
        comboBoxArt.addItem("All Arts Collections!");
        comboBoxFilm.addItem("-- Select a Query --");
        comboBoxFilm.addItem("All Films!");
        comboBoxFilm.addItem("Get filming location");
        comboBoxFilm.addItem("Get director");
        comboBoxPark.addItem("-- Select a Query --");

        comboBoxArt.addActionListener(e -> calculate(0));
        comboBoxFilm.addActionListener(e -> calculate(1));
        comboBoxPark.addActionListener(e -> calculate(2));

        JPanel panelCombo = new JPanel();
        panelCombo.setBackground(Color.decode("#D3D3D3"));
        panelCombo.setLayout(new GridLayout(12, 0));
        panelCombo.setBorder(new EmptyBorder(5, 5, 5, 5));

        panelCombo.add(labelArt, 0);
        panelCombo.add(comboBoxArt, 1);
        panelCombo.add(labelFilm, 2);
        panelCombo.add(comboBoxFilm, 3);
        panelCombo.add(labelPark, 4);
        panelCombo.add(comboBoxPark, 5);

        splitPanel.setLeftComponent(panelCombo);
    }

    private void createGeneralPanel() {
        // Creating JTable
        modelParks = new GenericDomainTableModel<Park>(Arrays.asList(new String[]{"Name", "Location", "Zip Code"})) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex >= 0 && columnIndex <= 2)
                    return String.class;
                else
                    return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Park park = getDomainObject(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return park.getParkName();
                    case 1:
                        return park.getZipCode();
                    case 2:
                        return park.getLocation();
                    default:
                        return null;
                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            }
        };
        modelArts = new GenericDomainTableModel<ArtCollection>(Arrays.asList(new String[]{"Title", "Artist"})) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex >= 0 && columnIndex <= 2)
                    return String.class;
                else
                    return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                ArtCollection art = getDomainObject(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return art.getTitle();
                    case 1:
                        return art.getArtist();
                    case 2:
                        return art.getLocation();
                    default:
                        return null;
                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }
        };
        modelFilms = new GenericDomainTableModel<Film>(Arrays.asList(new String[]{"Title", "Location", "Director", "Writer"})) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex >= 0 && columnIndex <= 3)
                    return String.class;
                else
                    return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Film film = getDomainObject(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return film.getTitle();
                    case 1:
                        return film.getLocation();
                    case 2:
                        return film.getDirector();
                    case 3:
                        return film.getWriter();
                }
                return null;
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }
        };
        tableResult = new JTable();
        tableResult.getTableHeader().setFont(new Font("Roboto", 0, 18));
        tableResult.setRowHeight(40);
        tableResult.setFont(new Font("Roboto Light", 0, 14));
        scrollPaneResult = new JScrollPane(tableResult);
        // Creating JTextArea
        textArea = new JTextArea("Select a query from the ComboBoxes at the left panel");
        splitPanel.setRightComponent(scrollPaneResult);
    }

    private void calculate(int i) {
        switch (i) {
            case 0:
                if (comboBoxArt.getSelectedIndex() == 0)
                    return;
                else if (comboBoxArt.getSelectedIndex() == 1) {
                    modelArts.clearTableModelData();
                    tableResult.setModel(modelArts);
                    modelArts.addRows(Sparql.allArtsCollection());
                    splitPanel.setRightComponent(scrollPaneResult);
                }
                comboBoxPark.setSelectedIndex(0);
                comboBoxFilm.setSelectedIndex(0);
                break;
            case 1:
                if (comboBoxFilm.getSelectedIndex() == 0)
                    return;
                else if (comboBoxFilm.getSelectedIndex() == 1) {
                    modelFilms.clearTableModelData();
                    tableResult.setModel(modelFilms);
                    modelFilms.addRows(Sparql.allFilm());
                    splitPanel.setRightComponent(scrollPaneResult);
                } else if (comboBoxFilm.getSelectedIndex() == 2) {
                    String result = newDialog(0);
                    splitPanel.setRightComponent(textArea);
                    if (result != null) {
                        textArea.setText(result + " was filmed in:\n");
                        for (String location : Sparql.getLocations(result))
                            textArea.setText(textArea.getText() + "\t" + location + "\n");
                    }
                } else if (comboBoxFilm.getSelectedIndex() >= 3) {
                    String result = newDialog(0);
                    splitPanel.setRightComponent(textArea);
                    if (result != null) {
                        textArea.setText("The director(s) of " + result + " is/are:\n");
                        for (String director : Sparql.getDirector("\"" + result + "\""))
                            textArea.setText(textArea.getText() + "\t" + director + "\n");
                    }
                }
                comboBoxPark.setSelectedIndex(0);
                comboBoxArt.setSelectedIndex(0);
                break;
            case 2:
                if (comboBoxPark.getSelectedIndex() == 0)
                    return;
                System.out.println("Park " + comboBoxPark.getSelectedItem());
                modelParks.clearTableModelData();
                tableResult.setModel(modelParks);
                comboBoxFilm.setSelectedIndex(0);
                comboBoxArt.setSelectedIndex(0);
                break;
            default:
                break;
        }
    }

    public String newDialog(int type) {
        panelGeneral.getRootPane().getGlassPane().setVisible(true);
        DialogSelectFrom dialog = new DialogSelectFrom();
        switch (type) {
            case 0: // film titles
                dialog.setComboContent(Sparql.getAllTitle());
                break;
        }
        dialog.pack();
        dialog.setLocationRelativeTo(panelGeneral);
        dialog.setVisible(true);
        panelGeneral.getRootPane().getGlassPane().setVisible(false);
        return dialog.getResult();
    }

    public static void main(String[] args) {
        new GUI();
    }
}
