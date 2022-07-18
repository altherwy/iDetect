/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainPage.java
 *
 * Created on 02/05/2010, 11:00:09 م
 */
package idetect;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author youssef 19
 */
public class MainPage extends javax.swing.JFrame {

    int i = 0;
    InfiniteProgressPanel pane;
    int red = 0;
    int stateNumber = 0;
    String text1 = "";
    String text2 = "";
    String text3 = "";
    java.util.Vector<String> listURL = new java.util.Vector<String>();
    ArrayList<String> phishingValue = new ArrayList<String>();
    Vector<String> URLs = new Vector<String>();
    JTable jt = new JTable();
    JScrollPane js = new JScrollPane();
    JScrollPane js3 = new JScrollPane();
    boolean Phishing = false;
    boolean Hacking = false;
    JCheckBoxMenuItem validSort = new JCheckBoxMenuItem("Sort by valid state");
    JCheckBoxMenuItem URLSort = new JCheckBoxMenuItem("Sort by  URL");
    JCheckBoxMenuItem OnlineSort = new JCheckBoxMenuItem("Sort by online state");
    JCheckBoxMenuItem CheckSort = new JCheckBoxMenuItem("Sort by checking time");
    // InfiniteProgressPanel pane;

    /** Creates new form MainPage */
    public MainPage() {
        /*pane = new InfiniteProgressPanel();
        this.setGlassPane(pane);
        pane.start();
        pane.setVisible(false);*/
        initComponents();
        jPopupMenu1.add(URLSort);
        jPopupMenu1.add(OnlineSort);
        jPopupMenu1.add(validSort);
        jPopupMenu1.add(CheckSort);
        try {
            UIManager.setLookAndFeel("com.easynth.lookandfeel.EaSynthLookAndFeel");
            SwingUtilities.updateComponentTreeUI(jPanel5);
            jButton19.setText("# of Phishing");
            jButton19.setForeground(new Color(255, 255, 255));
            jButton19.setToolTipText("# of Phishing websites for given URL");
            String defaults = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(defaults);
            SwingUtilities.updateComponentTreeUI(jFileChooser1);
            SwingUtilities.updateComponentTreeUI(jRadioButton1);
            SwingUtilities.updateComponentTreeUI(jRadioButton2);
            SwingUtilities.updateComponentTreeUI(jRadioButton3);
            SwingUtilities.updateComponentTreeUI(jRadioButton4);
            SwingUtilities.updateComponentTreeUI(jCheckBox1);
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel");
            SwingUtilities.updateComponentTreeUI(jToolBar1);
            SwingUtilities.updateComponentTreeUI(jScrollPane3);
            SwingUtilities.updateComponentTreeUI(jScrollPane1);
            SwingUtilities.updateComponentTreeUI(jScrollPane2);
            SwingUtilities.updateComponentTreeUI(jScrollPane4);
            SwingUtilities.updateComponentTreeUI(jScrollPane5);
            SwingUtilities.updateComponentTreeUI(jScrollPane6);
            SwingUtilities.updateComponentTreeUI(jTextField6);
            SwingUtilities.updateComponentTreeUI(jTextField1);
            SwingUtilities.updateComponentTreeUI(jTextField5);
            SwingUtilities.updateComponentTreeUI(jTextField4);
            SwingUtilities.updateComponentTreeUI(jTextField3);
            SwingUtilities.updateComponentTreeUI(jTextField2);
            SwingUtilities.updateComponentTreeUI(jTextField7);
            SwingUtilities.updateComponentTreeUI(jComboBox1);
            SwingUtilities.updateComponentTreeUI(jComboBox2);
            SwingUtilities.updateComponentTreeUI(jComboBox3);
            SwingUtilities.updateComponentTreeUI(jComboBox4);
            SwingUtilities.updateComponentTreeUI(jComboBox5);
            SwingUtilities.updateComponentTreeUI(jComboBox6);
            SwingUtilities.updateComponentTreeUI(jComboBox7);
            SwingUtilities.updateComponentTreeUI(jComboBox8);
            SwingUtilities.updateComponentTreeUI(jButton16);
            SwingUtilities.updateComponentTreeUI(jButton17);
            SwingUtilities.updateComponentTreeUI(jButton18);
            SwingUtilities.updateComponentTreeUI(jTextField8);



        } catch (Exception ex) {
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new MyPanel("Main_Panel2.JPG");
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new MyPanel("jozef.png");
        jPanel5 = new MyPanel("Main_Panel.JPG");
        jToolBar1 = new MyToolBar("toolbar.jpg")
        ;
        jToggleButton2 = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jToggleButton4 = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jToggleButton3 = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jToggleButton5 = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jToggleButton1 = new javax.swing.JToggleButton();
        jTextField6 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList();
        jComboBox6 = new javax.swing.JComboBox();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jComboBox8 = new javax.swing.JComboBox();
        jButton19 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jPanel6 = new MyPanel("logo_96.png");
        jComboBox5 = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel4 = new MyPanel("new_Bottom.JPG");

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(idetect.IDetectApp.class).getContext().getResourceMap(MainPage.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, resourceMap.getColor("jPanel2.border.matteColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setBackground(new Color(225, 235, 238));
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jLabel1.border.lineColor"), 1, true)); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel1MouseMoved(evt);
            }
        });

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setIcon(resourceMap.getIcon("jLabel3.icon")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jLabel3.border.lineColor"), 1, true)); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setIcon(resourceMap.getIcon("jLabel2.icon")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jLabel2.border.lineColor"), 1, true)); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setOpaque(true);

        jLabel4.setBackground(new Color(225, 235, 238));
        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jLabel4.border.lineColor"), 1, true)); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });
        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel4MouseMoved(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 943, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, resourceMap.getColor("jPanel5.border.matteColor"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel5ComponentShown(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jToggleButton2.setFont(resourceMap.getFont("jToggleButton2.font")); // NOI18N
        jToggleButton2.setForeground(resourceMap.getColor("jToggleButton2.foreground")); // NOI18N
        jToggleButton2.setText(resourceMap.getString("jToggleButton2.text")); // NOI18N
        jToggleButton2.setName("jToggleButton2"); // NOI18N
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton2);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        jToggleButton4.setFont(resourceMap.getFont("jToggleButton4.font")); // NOI18N
        jToggleButton4.setForeground(resourceMap.getColor("jToggleButton4.foreground")); // NOI18N
        jToggleButton4.setText(resourceMap.getString("jToggleButton4.text")); // NOI18N
        jToggleButton4.setName("jToggleButton4"); // NOI18N
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton4);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        jToggleButton3.setFont(resourceMap.getFont("jToggleButton3.font")); // NOI18N
        jToggleButton3.setForeground(resourceMap.getColor("jToggleButton3.foreground")); // NOI18N
        jToggleButton3.setText(resourceMap.getString("jToggleButton3.text")); // NOI18N
        jToggleButton3.setName("jToggleButton3"); // NOI18N
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton3);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        jToggleButton5.setFont(resourceMap.getFont("jToggleButton5.font")); // NOI18N
        jToggleButton5.setForeground(resourceMap.getColor("jToggleButton5.foreground")); // NOI18N
        jToggleButton5.setText(resourceMap.getString("jToggleButton5.text")); // NOI18N
        jToggleButton5.setName("jToggleButton5"); // NOI18N
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton5);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBar1.add(jSeparator4);

        jToggleButton1.setFont(resourceMap.getFont("jToggleButton1.font")); // NOI18N
        jToggleButton1.setForeground(resourceMap.getColor("jToggleButton1.foreground")); // NOI18N
        jToggleButton1.setText(resourceMap.getString("jToggleButton1.text")); // NOI18N
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setName("jToggleButton1"); // NOI18N
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton1);

        jPanel5.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 3, 640, -1));

        jTextField6.setText(resourceMap.getString("jTextField6.text")); // NOI18N
        jTextField6.setToolTipText(resourceMap.getString("jTextField6.toolTipText")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N
        jPanel5.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 250, -1));

        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(resourceMap.getString("jCheckBox1.toolTipText")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.setOpaque(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel5.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 73, 130, 20));

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setToolTipText(resourceMap.getString("jTextField1.toolTipText")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jPanel5.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 142, -1));

        jButton2.setForeground(resourceMap.getColor("jButton2.foreground")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, -1, -1));

        jButton5.setForeground(resourceMap.getColor("jButton5.foreground")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 170, -1));

        jComboBox1.setToolTipText(resourceMap.getString("jComboBox1.toolTipText")); // NOI18N
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        jRadioButton1.setText(resourceMap.getString("jRadioButton1.text")); // NOI18N
        jRadioButton1.setToolTipText(resourceMap.getString("jRadioButton1.toolTipText")); // NOI18N
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.setOpaque(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, 20));

        jRadioButton2.setText(resourceMap.getString("jRadioButton2.text")); // NOI18N
        jRadioButton2.setToolTipText(resourceMap.getString("jRadioButton2.toolTipText")); // NOI18N
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.setOpaque(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 103, -1, 20));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setToolTipText(resourceMap.getString("jList1.toolTipText")); // NOI18N
        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 180, 240));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jList2.setName("jList2"); // NOI18N
        jScrollPane2.setViewportView(jList2);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 170, 240));

        jLabel5.setIcon(resourceMap.getIcon("jLabel5.icon")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setToolTipText(resourceMap.getString("jLabel5.toolTipText")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, -1));

        jLabel6.setIcon(resourceMap.getIcon("jLabel6.icon")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setToolTipText(resourceMap.getString("jLabel6.toolTipText")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setToolTipText(resourceMap.getString("jLabel7.toolTipText")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, -1, -1));

        jLabel8.setIcon(resourceMap.getIcon("jLabel8.icon")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setToolTipText(resourceMap.getString("jLabel8.toolTipText")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, -1, -1));

        jButton3.setForeground(resourceMap.getColor("jButton3.foreground")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, -1, -1));

        jButton6.setForeground(resourceMap.getColor("jButton6.foreground")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setToolTipText(resourceMap.getString("jButton6.toolTipText")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 140, -1));

        jScrollPane3.setBackground(resourceMap.getColor("js4.background")); // NOI18N
        jScrollPane3.setName("js4"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setName("jt1"); // NOI18N
        jScrollPane3.setViewportView(jTable1);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 630, 200));

        jButton1.setForeground(resourceMap.getColor("jButton1.foreground")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 120, 20));

        jButton4.setForeground(resourceMap.getColor("jButton4.foreground")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setToolTipText(resourceMap.getString("jButton4.toolTipText")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jButton7.setForeground(resourceMap.getColor("jButton7.foreground")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 170, -1));

        jButton8.setForeground(resourceMap.getColor("jButton8.foreground")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N
        jPanel5.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jButton9.setForeground(resourceMap.getColor("jButton9.foreground")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jList3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "1", "2", "6", "0", "d", "e", "i", "m", "p", "s", "v", "b" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList3.setName("jList3"); // NOI18N
        jScrollPane4.setViewportView(jList3);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 120, 230));

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jList4.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "l", "2", "b", "o", "b", "a", "j", "n", "q", "z", "w", "l" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList4.setName("jList4"); // NOI18N
        jScrollPane5.setViewportView(jList4);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 130, 230));

        jRadioButton3.setText(resourceMap.getString("jRadioButton3.text")); // NOI18N
        jRadioButton3.setToolTipText(resourceMap.getString("jRadioButton3.toolTipText")); // NOI18N
        jRadioButton3.setName("jRadioButton3"); // NOI18N
        jRadioButton3.setOpaque(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jRadioButton4.setText(resourceMap.getString("jRadioButton4.text")); // NOI18N
        jRadioButton4.setToolTipText(resourceMap.getString("jRadioButton4.toolTipText")); // NOI18N
        jRadioButton4.setName("jRadioButton4"); // NOI18N
        jRadioButton4.setOpaque(false);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jButton10.setForeground(resourceMap.getColor("jButton10.foreground")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jButton11.setForeground(resourceMap.getColor("jButton11.foreground")); // NOI18N
        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jButton12.setForeground(resourceMap.getColor("jButton12.foreground")); // NOI18N
        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jButton13.setForeground(resourceMap.getColor("jButton13.foreground")); // NOI18N
        jButton13.setText(resourceMap.getString("jButton13.text")); // NOI18N
        jButton13.setName("jButton13"); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, -1, -1));

        jComboBox2.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        jComboBox3.setForeground(resourceMap.getColor("jComboBox3.foreground")); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));
        jComboBox3.setName("jComboBox3"); // NOI18N
        jPanel5.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jComboBox4.setForeground(resourceMap.getColor("jComboBox4.foreground")); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox4.setName("jComboBox4"); // NOI18N
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N
        jPanel5.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 150, 20));

        jTextField5.setText(resourceMap.getString("jTextField5.text")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N
        jPanel5.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 130, -1));

        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

        jButton15.setText(resourceMap.getString("jButton15.text")); // NOI18N
        jButton15.setName("jButton15"); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        jList5.setToolTipText(resourceMap.getString("jList5.toolTipText")); // NOI18N
        jList5.setName("jList5"); // NOI18N
        jScrollPane6.setViewportView(jList5);

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 240, 120));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.setToolTipText(resourceMap.getString("jComboBox6.toolTipText")); // NOI18N
        jComboBox6.setName("jComboBox6"); // NOI18N
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        jTextField7.setText(resourceMap.getString("jTextField7.text")); // NOI18N
        jTextField7.setToolTipText(resourceMap.getString("jTextField7.toolTipText")); // NOI18N
        jTextField7.setName("jTextField7"); // NOI18N
        jPanel5.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 210, -1));

        jLabel9.setIcon(resourceMap.getIcon("jLabel9.icon")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 60, -1));

        jFileChooser1.setName("jFileChooser1"); // NOI18N
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });
        jPanel5.add(jFileChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 60, 180, 80));

        jLabel10.setBackground(resourceMap.getColor("jLabel10.background")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setOpaque(true);
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 230, 20));

        jLabel12.setIcon(resourceMap.getIcon("jLabel12.icon")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        jComboBox7.setForeground(resourceMap.getColor("jComboBox7.foreground")); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));
        jComboBox7.setName("jComboBox7"); // NOI18N
        jPanel5.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jComboBox8.setForeground(resourceMap.getColor("jComboBox8.foreground")); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBox8.setName("jComboBox8"); // NOI18N
        jPanel5.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jButton19.setText(resourceMap.getString("jButton19.text")); // NOI18N
        jButton19.setName("jButton19"); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        jTextField8.setText(resourceMap.getString("jTextField8.text")); // NOI18N
        jTextField8.setToolTipText(resourceMap.getString("jTextField8.toolTipText")); // NOI18N
        jTextField8.setName("jTextField8"); // NOI18N
        jPanel5.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 220, -1));

        jButton16.setText(resourceMap.getString("jButton16.text")); // NOI18N
        jButton16.setName("jButton16"); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hour", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "0" }));
        jComboBox5.setName("jComboBox5"); // NOI18N

        jPanel7.setBackground(resourceMap.getColor("jPanel7.background")); // NOI18N
        jPanel7.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jPanel7.border.lineColor"), 1, true)); // NOI18N
        jPanel7.setName("jPanel7"); // NOI18N

        jLabel11.setIcon(resourceMap.getIcon("jLabel11.icon")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jLabel11.setName("jLabel11"); // NOI18N

        jCheckBox2.setBackground(resourceMap.getColor("jCheckBox2.background")); // NOI18N
        jCheckBox2.setText(resourceMap.getString("jCheckBox2.text")); // NOI18N
        jCheckBox2.setName("jCheckBox2"); // NOI18N

        jCheckBox3.setBackground(resourceMap.getColor("jCheckBox3.background")); // NOI18N
        jCheckBox3.setText(resourceMap.getString("jCheckBox3.text")); // NOI18N
        jCheckBox3.setName("jCheckBox3"); // NOI18N

        jCheckBox4.setBackground(resourceMap.getColor("jCheckBox4.background")); // NOI18N
        jCheckBox4.setText(resourceMap.getString("jCheckBox4.text")); // NOI18N
        jCheckBox4.setName("jCheckBox4"); // NOI18N

        jCheckBox5.setBackground(resourceMap.getColor("jCheckBox5.background")); // NOI18N
        jCheckBox5.setText(resourceMap.getString("jCheckBox5.text")); // NOI18N
        jCheckBox5.setName("jCheckBox5"); // NOI18N

        jCheckBox6.setBackground(resourceMap.getColor("jCheckBox6.background")); // NOI18N
        jCheckBox6.setText(resourceMap.getString("jCheckBox6.text")); // NOI18N
        jCheckBox6.setName("jCheckBox6"); // NOI18N

        jCheckBox7.setBackground(resourceMap.getColor("jCheckBox7.background")); // NOI18N
        jCheckBox7.setText(resourceMap.getString("jCheckBox7.text")); // NOI18N
        jCheckBox7.setName("jCheckBox7"); // NOI18N

        jCheckBox8.setBackground(resourceMap.getColor("jCheckBox8.background")); // NOI18N
        jCheckBox8.setText(resourceMap.getString("jCheckBox8.text")); // NOI18N
        jCheckBox8.setName("jCheckBox8"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jCheckBox4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jCheckBox5, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jCheckBox6, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jCheckBox7, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jCheckBox8, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox8))
        );

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        jList6.setName("jList6"); // NOI18N
        jScrollPane7.setViewportView(jList6);

        jButton17.setText(resourceMap.getString("jButton17.text")); // NOI18N
        jButton17.setName("jButton17"); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText(resourceMap.getString("jButton18.text")); // NOI18N
        jButton18.setName("jButton18"); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jButton17)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))))))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(334, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton17)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 943, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jPanel5.setVisible(false);
        this.setResizable(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(jToggleButton1);
        bg.add(jToggleButton2);
        bg.add(jToggleButton3);
        bg.add(jToggleButton4);
        bg.add(jToggleButton5);
        jTextField1.setVisible(false);
        jCheckBox1.setVisible(false);
        jList6.setListData(new Time_Date().getCheckingTimes());

    }//GEN-LAST:event_formWindowOpened

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        Phishing = false;
        Hacking = false;
        ButtonGroup bg4 = new ButtonGroup();
        bg4.add(jRadioButton3);
        bg4.add(jRadioButton4);
        jPanel5.setVisible(true);
        this.changeVisiblity(6);
        jToolBar1.setVisible(false);

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseMoved
        // TODO add your handling code here:
       /* i++;
        if(i%2 != 0){
        jLabel1.setBackground(Color.blue);
        }
        else
        jLabel1.setBackground(Color.white);*/
    }//GEN-LAST:event_jLabel1MouseMoved

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered


        // Color c = new Color(255, 255, 255);
        //jLabel1.setBackground(c);
        jLabel1.setOpaque(true);
        jLabel1.setBackground(new Color(204, 244, 255));
        text2 = jLabel1.getText();
        jLabel1.setText("<html><u>" + text2 + "</u></html>");
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jLabel1.setBackground(new Color(229, 235, 237));
        jLabel1.setText(text2);
        jLabel1.setOpaque(false);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1AncestorAdded

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        Phishing = true;
        Hacking = false;
        this.changeVisiblity(100);
        jToolBar1.setVisible(true);
        jPanel5.setVisible(true);

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        jLabel3.setOpaque(true);
        jLabel3.setBackground(new Color(204, 244, 255));
        text1 = jLabel3.getText();
        jLabel3.setText("<html><u>" + text1 + "</u></html>");
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        //jLabel3.setBackground(new Color(229,235,237));
        jLabel3.setBackground(Color.red);
        jLabel3.setText(text1);
        jLabel3.setOpaque(false);
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Hacking = true;
        Phishing = false;
        this.changeVisiblity(100);
        jToolBar1.setVisible(true);
        jPanel5.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        // TODO add your handling code here:
        jLabel4.setOpaque(true);
        jLabel4.setBackground(new Color(204, 244, 255));
        text3 = jLabel4.getText();
        jLabel4.setText("<html><u>" + text3 + "</u></html>");
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // TODO add your handling code here:
        jLabel4.setBackground(new Color(229, 235, 237));
        jLabel4.setText(text3);
        jLabel4.setOpaque(false);
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseMoved

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:

        this.changeVisiblity(1);
        java.util.Vector<String> allWebsites = new java.util.Vector<String>();
        if (Phishing) {
            allWebsites = new WebSite().runPrepareDetectList("Phishing");
        } else {
            allWebsites = new WebSite().runPrepareDetectList("Hacking");
        }
        jList5.setListData(allWebsites);

    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jPanel5ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel5ComponentShown
        // TODO add your handling code here:
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jButton12.setVisible(false);
        jButton13.setVisible(false);
        jButton14.setVisible(false);
        jButton15.setVisible(false);
        jTextField1.setVisible(false);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jTextField6.setVisible(false);
        jCheckBox1.setVisible(false);
        jComboBox1.setVisible(false);
        jComboBox2.setVisible(false);
        jComboBox3.setVisible(false);
        jComboBox4.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        if (Hacking == false && Phishing == false) {
            jRadioButton3.setVisible(true);
            jRadioButton4.setVisible(true);
        } else {
            jRadioButton3.setVisible(false);
            jRadioButton4.setVisible(false);
        }
        jScrollPane1.setVisible(false);
        jScrollPane2.setVisible(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
    }//GEN-LAST:event_jPanel5ComponentShown
    public void changeVisiblity(int number) {
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jButton12.setVisible(false);
        jButton13.setVisible(false);
        jButton14.setVisible(false);
        jButton15.setVisible(false);
        jButton19.setVisible(false);
        jTextField1.setVisible(false);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jTextField6.setVisible(false);
        jTextField7.setVisible(false);
        jTextField8.setVisible(false);
        jCheckBox1.setVisible(false);
        jComboBox1.setVisible(false);
        jComboBox2.setVisible(false);
        jComboBox3.setVisible(false);
        jComboBox4.setVisible(false);
        jComboBox6.setVisible(false);
        jComboBox7.setVisible(false);
        jComboBox8.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jScrollPane1.setVisible(false);
        jScrollPane2.setVisible(false);
        jScrollPane3.setVisible(false);
        jTable1.setModel(new DefaultTableModel());
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(false);
        jScrollPane6.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel12.setVisible(false);
        jFileChooser1.setVisible(false);

        if (number == 1) {
            // Add many websites //
            jCheckBox1.setVisible(true);
            jTextField7.setVisible(true);
            jButton2.setVisible(true);
            jScrollPane3.setVisible(true);
            jScrollPane6.setVisible(true);
            jLabel9.setVisible(true);
            jButton4.setText("Browse");
            jButton4.setVisible(true);
        }
        if (number == 2) {
            /// Modify category ///
            jComboBox1.setVisible(true);

        }
        if (number == 3) {
            /// Modify website phishing value ///
            jButton1.setVisible(true);
            jButton4.setVisible(true);
            jButton4.setText("Get website");
            jTextField6.setVisible(true);
            jComboBox1.setVisible(true);
            jComboBox6.setVisible(true);
        }
        if (number == 4) {
            jScrollPane1.setVisible(true);
            jScrollPane2.setVisible(true);
            jLabel5.setVisible(true);
            jLabel6.setVisible(true);
            jLabel7.setVisible(true);
            jLabel8.setVisible(true);
            jRadioButton1.setVisible(true);
            jRadioButton2.setVisible(true);
            if (Phishing == true) {
                jButton3.setVisible(true);
                jButton6.setVisible(true);
            } else {
                jButton8.setVisible(true);
            }
        }
        if (number == 5) {
            if (Phishing == true) {
                jButton9.setVisible(true);
                jTextField2.setVisible(true);
                jTextField3.setVisible(true);
            }
        }
        if (number == 6) {
            /// Statistical Page ///
            jRadioButton3.setVisible(true);
            jRadioButton4.setVisible(true);
        }
        if (number == 7) {
            jTextField4.setVisible(true);
            jTextField5.setVisible(true);
            jButton14.setVisible(true);
            jButton14.setForeground(new Color(255, 255, 255));
            jButton15.setVisible(true);
            jButton15.setForeground(new Color(255, 255, 255));
        }
    }
    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // TODO add your handling code here:
        this.changeVisiblity(2);
        java.util.Vector<String> listName = new java.util.Vector<String>();
        if (Phishing == true) {
            listName = new WebSite().runGetCategoryNames("Phishing");
        } else {
            listName = new WebSite().runGetCategoryNames("Hacking");
        }
        jComboBox1.removeAllItems();
        for (int j = 0; j < listName.size(); j++) {
            jComboBox1.addItem(listName.get(j));
        }
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        /*
         * pass Jtable of website(s) to runSaveWebsites with Category name
         */


        URLs.removeAllElements();
        if (Phishing == true) {
            if (jCheckBox1.isSelected()) {
                if (!jTextField1.getText().isEmpty()) {
                    new WebSite().runSaveWebsites(jTextField1.getText(), jTable1);
                } else {
                    JOptionPane.showMessageDialog(null, "You must add category name !", "Error", 0);
                }
            } else {
                new WebSite().runSaveWebsites("None", jTable1);
            }
            this.googleOpacity("Done save website(s)");
        } else {
            if (Hacking == true) {
                DefaultTableModel hdf = (DefaultTableModel) jTable1.getModel();
                for (int j = 0; j < hdf.getRowCount(); j++) {

                    if ((String) hdf.getValueAt(j, 0) != null) {
                        String Url = new WebSite().addHttp((String) hdf.getValueAt(j, 0));
                        Url = new WebSite().removeWWW(Url);
                        if (jCheckBox1.isSelected()) {
                            WebSite w = new WebSite(Url, jTextField1.getText(), (String) hdf.getValueAt(j, 1));
                        } else {
                            WebSite w = new WebSite(Url, "None", (String) hdf.getValueAt(j, 1));
                        }
                    }
                }
                this.googleOpacity("Done save website(s)");

            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        if (Phishing) {
            new WebSite().runModifyCategory(jTable1, listURL, (String) jComboBox1.getSelectedItem(), "Phishing");
        } else {
            new WebSite().runModifyCategory(jTable1, listURL, (String) jComboBox1.getSelectedItem(), "Hacking");
        }
        this.googleOpacity("Done modification");


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:

        try {
            java.util.Vector<String> listName = new java.util.Vector<String>();
            this.changeVisiblity(3);
            jTextField6.setText("");
            if (Phishing == true) {
                listName = new WebSite().runGetCategoryNames("Phishing");
            } else {
                listName = new WebSite().runGetCategoryNames("Hacking");
            }
            jComboBox1.removeAllItems();
            jComboBox1.addItem("");
            for (int j = 0; j < listName.size(); j++) {
                jComboBox1.addItem(listName.get(j));
            }
            jComboBox6.removeAllItems();
            jComboBox6.addItem("");
            if (Phishing) {
                jComboBox6.addItem("Valid");
                jComboBox6.addItem("InValid");
                jComboBox6.addItem("Blocked");
                jComboBox6.addItem("Unknown");
                jComboBox6.addItem("Suspicious");
            } else {
                jComboBox6.addItem("Suspicious");
                jComboBox6.addItem("Hacked");
                jComboBox6.addItem("Safe");
            }
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        // TODO add your handling code here:
        ButtonGroup bg = new ButtonGroup();
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        this.changeVisiblity(4);
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        java.util.Vector<String> allWebsites = new java.util.Vector<String>();
        DefaultListModel dl = new DefaultListModel();
        jList1.setModel(dl);
        jList1.setVisible(true);
        jList2.setVisible(true);
        if (Phishing == true) {
            allWebsites = new WebSite().runPrepareDetectList("Phishing");
        } else {
            allWebsites = new WebSite().runPrepareDetectList("Hacking");
        }

        jList2.setListData(allWebsites);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        jList1.setListData(jList2.getSelectedValues());
        jList2.clearSelection();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        ListModel lm = jList2.getModel();
        java.util.Vector<String> save = new java.util.Vector<String>();
        for (int j = 0; j < lm.getSize(); j++) {
            save.add((String) lm.getElementAt(j));
        }
        jList1.setListData(save);
        jList2.clearSelection();

    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        int selectedItems[] = jList1.getSelectedIndices();
        ListModel lm = jList1.getModel();
        java.util.Vector<String> saveSelected = new java.util.Vector<String>();
        java.util.Vector<String> newItems = new java.util.Vector<String>();
        for (int j = 0; j < selectedItems.length; j++) {
            saveSelected.add((String) lm.getElementAt(selectedItems[j]));
        }
        for (int j = 0; j < lm.getSize(); j++) {
            if (!saveSelected.contains((String) lm.getElementAt(j))) {
                newItems.add((String) lm.getElementAt(j));
            }
        }
        jList1.setListData(newItems);
        jList1.clearSelection();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        DefaultListModel dl = new DefaultListModel();
        jList1.setModel(dl);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        DefaultListModel dl = new DefaultListModel();
        jList1.setModel(dl);
        if (Phishing == true) {
            jList2.setListData(new WebSite().runGetCategoryNames("Phishing"));
        } else {
            jList2.setListData(new WebSite().runGetCategoryNames("Hacking"));
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
        pane = new InfiniteProgressPanel();
        this.setGlassPane(pane);
        pane.start();
        this.detectWebsites();
        }
        catch(Exception ex){
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void detectWebsites() {
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        ListModel lm = jList1.getModel();
        Vector<String> save = new Vector<String>();
        Vector<String> saveWebsite = new Vector<String>();
        for (int j = 0; j < lm.getSize(); j++) {

            save.add((String) lm.getElementAt(j));
        }

        if (jRadioButton2.isSelected()) {
            ProgressThread pr = new ProgressThread(save, "None", df, jTable1, jScrollPane3, pane, "Website", false);
            Thread t = new Thread(pr);
            t.start();

        }
        else if (jRadioButton1.isSelected()) {
            ProgressThread pr = new ProgressThread(save, "", df, jTable1, jScrollPane3, pane, "Category", false);
            Thread t = new Thread(pr);
            t.start();
        }

        jScrollPane2.setVisible(false);
        jScrollPane1.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jButton3.setVisible(false);
        jButton6.setVisible(false);

    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try{

        int number = -1;
        pane = new InfiniteProgressPanel();
        this.setGlassPane(pane);
        pane.start();
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        ListModel lm = jList1.getModel(); /// The result List ///
        Vector<String> save = new Vector<String>();/// Containe all elements in JList1///
        Vector<String> saveWebsite = new Vector<String>();
        for (int j = 0; j < lm.getSize(); j++) {
            save.add((String) lm.getElementAt(j));
        }
        if (jRadioButton2.isSelected()) {
            ProgressThread pr = new ProgressThread(save, "None", df, jTable1, jScrollPane3, pane, "EnumerationWebsite", true);
            Thread t = new Thread(pr);
            t.start();
            //number = new WebSite().runDetectingWithEnumeration(save, "None");
            //  new HistoryDA().getResults(df, number);
        } else if (jRadioButton1.isSelected()) {
            ProgressThread pr = new ProgressThread(save, "", df, jTable1, jScrollPane3, pane, "EnumerationCategory", true);
            Thread t = new Thread(pr);
            t.start();
        }
        jScrollPane2.setVisible(false);
        jScrollPane1.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jButton3.setVisible(false);
        jButton6.setVisible(false);
        }
        catch(Exception ex){
            
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        if (jButton1.isVisible()) {
            try {
                DefaultTableModel df = new DefaultTableModel() {

                    @Override
                    public boolean isCellEditable(int row, int col) {
                        if (col != 2) {
                            return false;
                        }
                        return true;
                    }
                };
                if (Phishing == true) {
                    String[] items = {"Valid", "InValid", "Suspicious", "Unknown", "Blocked"};
                    new WebSite().runModifyCategoryWebsitesValue(df, (String) jComboBox1.getSelectedItem(), "Phishing");
                    jTable1.setModel(df);
                    TableColumn tcol = jTable1.getColumnModel().getColumn(2);
                    tcol.setCellEditor(new MyComboBoxEditor(items));
                } else {

                    String[] items = {"Safe", "Hacked", "Suspicious"};
                    new WebSite().runModifyCategoryWebsitesValue(df, (String) jComboBox1.getSelectedItem(), "Hacking");
                    jTable1.setModel(df);
                    TableColumn tcol = jTable1.getColumnModel().getColumn(2);
                    tcol.setCellEditor(new MyComboBoxEditor(items));
                }
                jScrollPane3.setVisible(true);
                jButton7.setVisible(true);
            } catch (Exception ex) {
            }
        } else {
            if (Phishing == true) {
                listURL = new WebSite().getAllCategoryWebsite((String) jComboBox1.getSelectedItem(), "Phishing");
            } else {
                listURL = new WebSite().getAllCategoryWebsite((String) jComboBox1.getSelectedItem(), "Hacking");
            }
            java.util.Vector<String> vv = new java.util.Vector<String>();
            vv.add("URL");
            DefaultTableModel df = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int col) {
                    return true;
                }
            };
            df.setColumnIdentifiers(vv);
            for (int j = 0; j < listURL.size(); j++) {
                Vector<String> temp = new Vector<String>();
                temp.add(listURL.get(j));
                df.addRow(temp);
            }

            jTable1.setModel(df);
            jScrollPane3.setVisible(true);
            jButton5.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (jButton2.isVisible()) {
            jFileChooser1.setVisible(true);
            int returnVal = jFileChooser1.showOpenDialog(jPopupMenu1);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser1.getSelectedFile();
                try {
                    if (Phishing) {
                        new WebSite().prepareExternalFile(file, "Phishing");
                    } else {
                        new WebSite().prepareExternalFile(file, "Hacking");
                    }


                } catch (Exception ex) {
                    System.out.println("problem accessing file" + file.getAbsolutePath());
                }
            } else {
                System.out.println("File access cancelled by user.");
            }
            return;
        }
        try {
            if (!jTextField6.getText().isEmpty()) {

                DefaultTableModel df = new DefaultTableModel() {

                    @Override
                    public boolean isCellEditable(int row, int col) {
                        if (col != 2) {
                            return false;
                        }
                        return true;
                    }
                };
                phishingValue.clear();
                if (Phishing == true) {
                    String[] items = {"Valid", "InValid", "Suspicious", "Unknown", "Blocked"};
                    phishingValue = new WebSite().runModifyWebsiteValue(df, jTextField6.getText(), "Phishing");
                    jTable1.setModel(df);
                    TableColumn tcol = jTable1.getColumnModel().getColumn(2);
                    tcol.setCellEditor(new MyComboBoxEditor(items));
                } else {
                    String[] items = {"Safe", "Hacked", "Suspicious"};
                    phishingValue = new WebSite().runModifyHackingWebsiteValue(df, jTextField6.getText(), "Hacking");
                    jTable1.setModel(df);
                    TableColumn tcol = jTable1.getColumnModel().getColumn(2);
                    tcol.setCellEditor(new MyComboBoxEditor(items));
                }
                jScrollPane3.setVisible(true);
                if (df.getRowCount() > 0) {
                    jButton7.setVisible(true);
                } else {
                    jButton7.setVisible(false);
                }



            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {
            DefaultTableModel df = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int col) {
                    if (Phishing == true) {
                        if (col != 2) {
                            return false;
                        }
                        return true;
                    } else {
                        if (col == 2 || col == 4) {
                            return true;
                        }
                    }
                    return false;
                }
            };
            phishingValue.clear();

            if (Phishing == true) {
                String[] items = {"Valid", "InValid", "Suspicious", "Unknown", "Blocked"};
                phishingValue = new WebSite().runModifyWebsiteValue(df, "%%", "Phishing");
                jTable1.setModel(df);
                TableColumn tcol = jTable1.getColumnModel().getColumn(2);
                tcol.setCellEditor(new MyComboBoxEditor(items));
            } else {
                String[] items = {"Safe", "Hacked", "Suspicious"};
                phishingValue = new WebSite().runModifyHackingWebsiteValue(df, "%%", "Hacking");
                jTable1.setModel(df);
                TableColumn tcol = jTable1.getColumnModel().getColumn(2);
                tcol.setCellEditor(new MyComboBoxEditor(items));
            }
            ///Hacked , Safe , "Suspicious" ////


            jScrollPane3.setVisible(true);
            if (df.getRowCount() > 0) {
                jButton7.setVisible(true);
            } else {
                jButton7.setVisible(false);
            }
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            int ii = 0;
            while (ii < df.getRowCount()) {
                if (Phishing == true) {
                    new WebSite().runUpdatePhishingState((String) df.getValueAt(ii, 0), (String) df.getValueAt(ii, 2));
                } else {
                    new WebSite().runUpdateHackingState((String) df.getValueAt(ii, 0), (String) df.getValueAt(ii, 2));
                }
                ii++;
            }
            this.googleOpacity("Done modification");
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //// Remove wwwwwwww //////
        pane = new InfiniteProgressPanel();
        this.setGlassPane(pane);
        pane.start();
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        ListModel lm = jList1.getModel();
        Vector<String> save = new Vector<String>();
        Vector<String> saveWebsite = new Vector<String>();
        for (int j = 0; j < lm.getSize(); j++) {
            save.add((String) lm.getElementAt(j));
        }
        if (jRadioButton2.isSelected()) {
            ProgressThread pr = new ProgressThread(save, df, jTable1, jScrollPane3, pane, "Hacking", true);
            Thread t = new Thread(pr);
            t.start();
        } else {
            if (jRadioButton1.isSelected()) {
                ProgressThread pr = new ProgressThread(save, df, jTable1, jScrollPane3, pane, "Hacking", false);
                Thread t = new Thread(pr);
                t.start();
            }
        }
        jScrollPane2.setVisible(false);
        jScrollPane1.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jButton8.setVisible(false);





    }//GEN-LAST:event_jButton8ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        if (Phishing == true) {
            jTextField2.setText("First Char");
            jTextField3.setText("Second Char");
            this.changeVisiblity(5);
            Vector<Character> saveFirst = new Vector<Character>();
            Vector<Character> saveSecond = new Vector<Character>();
            saveFirst = new WebSite().runFirstPhishingCustomization();
            saveSecond = new WebSite().runSecondPhishingCustomization();
            jList3.setListData(saveFirst);
            jList4.setListData(saveSecond);
            jScrollPane4.setVisible(true);
            jScrollPane5.setVisible(true);

        } else {
            this.changeVisiblity(7);
            jList1.setListData(new hacked_keywordsDA().getHackedKeywords());
            jScrollPane1.setVisible(true);
            jList2.setListData(new hacker_nicknamesDA().getHackerNicknames());
            jScrollPane2.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Vector<Character> saveFirst = new Vector<Character>();
        Vector<Character> saveSecond = new Vector<Character>();
        ListModel lm1 = jList3.getModel();
        ListModel lm2 = jList4.getModel();
        if (!jTextField2.getText().isEmpty() && !jTextField3.getText().isEmpty()) {
            if (jTextField2.getText().length() == 1 && jTextField3.getText().length() == 1) {
                if (!new WebSite().runGetAllEnumeration(jTextField2.getText(), jTextField3.getText()) && !new WebSite().runGetAllEnumeration(jTextField3.getText(), jTextField2.getText())) {
                    new WebSite().runPhishingCustomization(jTextField2.getText(), jTextField3.getText());
                    saveFirst = new WebSite().runFirstPhishingCustomization();
                    saveSecond = new WebSite().runSecondPhishingCustomization();
                    jList3.setListData(saveFirst);
                    jList4.setListData(saveSecond);
                    jScrollPane4.setVisible(true);
                    jScrollPane5.setVisible(true);
                }
            }
        }


    }//GEN-LAST:event_jButton9ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        jButton10.setVisible(true);
        jButton11.setVisible(true);
        jButton12.setVisible(true);
        jButton13.setVisible(true);
        jComboBox2.setVisible(true);
        jComboBox3.setVisible(true);
        jComboBox4.setVisible(true);
        jLabel12.setVisible(true);
        jButton19.setVisible(true);
        jTextField8.setVisible(true);
        jScrollPane3.setVisible(false);
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        jButton10.setVisible(true);
        jButton11.setVisible(false);
        jButton12.setVisible(true);
        jButton13.setVisible(false);
        jComboBox2.setVisible(true);
        jComboBox3.setVisible(true);
        jComboBox4.setVisible(true);
        jLabel12.setVisible(true);
        jScrollPane3.setVisible(false);
        jButton19.setVisible(false);
        jTextField8.setVisible(false);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String year = (String) jComboBox3.getSelectedItem();
        String month = (String) jComboBox2.getSelectedItem();
        String day = (String) jComboBox4.getSelectedItem();
        String endYear = (String) jComboBox7.getSelectedItem();
        String endMonth = (String) jComboBox8.getSelectedItem();
        if (jComboBox7.isVisible()) {
            if (!year.equals("Year") && !month.equals("Month") && !endYear.equals("Year") && !endMonth.equals("Month")) {
                if (jRadioButton4.isSelected()) {
                    new WebSite().runPeriodQuery(df, Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(endYear), Integer.parseInt(endMonth), "Phishing");
                } else {
                    new WebSite().runPeriodQuery(df, Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(endYear), Integer.parseInt(endMonth), "Hacking");
                }
                jTable1.setModel(df);
                jScrollPane3.setVisible(true);
                jComboBox2.setVisible(false);
                jComboBox3.setVisible(false);
                jComboBox4.setVisible(false);
                jComboBox7.setVisible(false);
                jComboBox8.setVisible(false);
                jLabel12.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Enter valid date", "Error message", 0);
            }

            return;
        }
        if (!year.equals("Year") && !month.equals("Month") && !day.equals("Day")) {
            if (jRadioButton4.isSelected()) {
                new WebSite().runSpeceficPeriodQuery(df, Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), "Phishing");
            } else {
                new WebSite().runSpeceficPeriodQuery(df, Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), "Hacking");
            }
            jTable1.setModel(df);
            jScrollPane3.setVisible(true);
            jLabel12.setVisible(false);


        } else {
            JOptionPane.showMessageDialog(null, "Enter valid date", "Error message", 0);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        new WebSite().runMostAttackedQuery(df);
        jTable1.setModel(df);
        jScrollPane3.setVisible(true);
        jLabel12.setVisible(false);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        // Hacker's keywords//
        if (!jTextField4.getText().isEmpty()) {
            new hacked_keywordsDA().insertNewHackedKeyword(jTextField4.getText());
            jList1.setListData(new hacked_keywordsDA().getHackedKeywords());
            jScrollPane1.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "You must enter hacker's keyword");
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        if (!jTextField5.getText().isEmpty()) {
            new hacker_nicknamesDA().insertNewHackerNickname(jTextField5.getText());
            jList2.setListData(new hacker_nicknamesDA().getHackerNicknames());
            jScrollPane2.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "You must enter hacker's nicknames");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        if (jRadioButton3.isSelected()) {
            new WebSite().runMostActivePeriodQuery(df, "Hacking");
        } else {
            if (jRadioButton4.isSelected()) {
                new WebSite().runMostActivePeriodQuery(df, "Phishing");
            }
        }
        jTable1.setModel(df);
        jScrollPane3.setVisible(true);
        jLabel12.setVisible(false);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        new WebSite().runEnumerationNumberQuery(df);
        jTable1.setModel(df);
        jScrollPane3.setVisible(true);
        jLabel12.setVisible(false);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        String autoRunTime = (String) jComboBox5.getSelectedItem();
        if (!autoRunTime.equals("Hour")) {
            if (jCheckBox2.isSelected()) {
                if (!new Time_Date().checkingTimeAvailablity(7, Integer.parseInt(autoRunTime))) {
                    new Time_Date().insertCheckingTime(7, Integer.parseInt(autoRunTime));
                }
            }
            if (jCheckBox3.isSelected()) {
                if (!new Time_Date().checkingTimeAvailablity(1, Integer.parseInt(autoRunTime))) {
                    new Time_Date().insertCheckingTime(1, Integer.parseInt(autoRunTime));
                }
            }
            if (jCheckBox4.isSelected()) {
                if (!new Time_Date().checkingTimeAvailablity(2, Integer.parseInt(autoRunTime))) {
                    new Time_Date().insertCheckingTime(2, Integer.parseInt(autoRunTime));
                }
            }
            if (jCheckBox5.isSelected()) {
                if (!new Time_Date().checkingTimeAvailablity(3, Integer.parseInt(autoRunTime))) {
                    new Time_Date().insertCheckingTime(3, Integer.parseInt(autoRunTime));
                }
            }
            if (jCheckBox6.isSelected()) {
                if (!new Time_Date().checkingTimeAvailablity(4, Integer.parseInt(autoRunTime))) {
                    new Time_Date().insertCheckingTime(4, Integer.parseInt(autoRunTime));
                }
            }
            if (jCheckBox7.isSelected()) {
                if (!new Time_Date().checkingTimeAvailablity(5, Integer.parseInt(autoRunTime))) {
                    new Time_Date().insertCheckingTime(5, Integer.parseInt(autoRunTime));
                }
            }
            if (jCheckBox8.isSelected()) {
                if (!new Time_Date().checkingTimeAvailablity(6, Integer.parseInt(autoRunTime))) {
                    new Time_Date().insertCheckingTime(6, Integer.parseInt(autoRunTime));
                }
            }
        }
        new PhishTankDataBase().downloadPhishTankDB();
        jList6.setListData(new Time_Date().getCheckingTimes());
        Time_Date phishingRun = new Time_Date(new WebSite().runAutoRun("Phishing"), "Phishing");
        Thread t1 = new Thread(phishingRun);
        t1.start();
        Time_Date hackingRun = new Time_Date(new WebSite().runAutoRun("Hacking"), "Hacking");
        Thread t2 = new Thread(hackingRun);
        t2.start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        if ((evt.getButton() == MouseEvent.BUTTON3) && jButton1.isVisible()) {
            jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());

            validSort.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (validSort.isSelected()) {
                        DefaultTableModel tableData = (DefaultTableModel) jTable1.getModel();
                        java.util.Vector<String> data = tableData.getDataVector();
                        Collections.sort(data, new ColumnSorter((2)));
                        tableData.fireTableStructureChanged();
                    }
                }
            });
            URLSort.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (URLSort.isSelected()) {
                        DefaultTableModel tableData = (DefaultTableModel) jTable1.getModel();
                        java.util.Vector<String> data = tableData.getDataVector();
                        Collections.sort(data, new ColumnSorter((0)));
                        tableData.fireTableStructureChanged();
                    }
                }
            });
            OnlineSort.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (OnlineSort.isSelected()) {
                        DefaultTableModel tableData = (DefaultTableModel) jTable1.getModel();
                        java.util.Vector<String> data = tableData.getDataVector();
                        Collections.sort(data, new ColumnSorter((1)));
                        tableData.fireTableStructureChanged();
                    }
                }
            });
            CheckSort.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (CheckSort.isSelected()) {
                        DefaultTableModel tableData = (DefaultTableModel) jTable1.getModel();
                        java.util.Vector<String> data = tableData.getDataVector();
                        if (Phishing) {
                            Collections.sort(data, new ColumnSorter((4)));
                        } else {
                            Collections.sort(data, new ColumnSorter((5)));
                        }
                        tableData.fireTableStructureChanged();
                    }
                }
            });


        }
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
        // jLabel9.setText("<html><u>Save websites from the table/external file</u></html>");
    }//GEN-LAST:event_jButton2MouseEntered

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.isVisible()) {
            jTextField1.setVisible(false);
        } else {
            jTextField1.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
        jComboBox1.setSelectedIndex(0);
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        if (Phishing) {
            new WebSite().runGetAllWebsitesByState(df, (String) jComboBox6.getSelectedItem(), "Phishing");
        } else {
            new WebSite().runGetAllWebsitesByState(df, (String) jComboBox6.getSelectedItem(), "Hacking");
        }
        jTable1.setModel(df);
        jScrollPane3.setVisible(true);
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 1) {
                    return true;
                }
                return false;
            }
        };
        if (!jTextField7.getText().isEmpty()) {
            URLs.add(jTextField7.getText());
            df.addColumn("URL", URLs);
            df.addColumn("Auto run");
        } else {
            JOptionPane.showMessageDialog(null, "You must add website", "Error message", 0);
            return;
        }
        String[] items = {"No", "Yes"};
        jTable1.setModel(df);
        TableColumn tcol = jTable1.getColumnModel().getColumn(1);
        tcol.setCellEditor(new MyComboBoxEditor(items));
        jScrollPane3.setVisible(true);

    }//GEN-LAST:event_jLabel9MouseClicked

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        new ShowResultFrame().setVisible(true);

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        Object[] times = jList6.getSelectedValues();
        String day = "";
        int checkDay = 0;
        int checkHour = -1;
        for (int ii = 0; ii < times.length; ii++) {
            day = (String) times[ii];
            if (day.startsWith("Sund")) {
                checkDay = 1;
            }
            if (day.startsWith("Mond")) {
                checkDay = 2;
            }
            if (day.startsWith("Tues")) {
                checkDay = 3;
            }
            if (day.startsWith("Wed")) {
                checkDay = 4;
            }
            if (day.startsWith("Thur")) {
                checkDay = 5;
            }
            if (day.startsWith("Fri")) {
                checkDay = 6;
            }
            if (day.startsWith("Sat")) {
                checkDay = 7;
            }
            int index = times[ii].toString().indexOf(":");
            checkHour = Integer.parseInt(times[ii].toString().substring(index + 1));
            new Time_Date().deleteCheckingTimes(checkDay, checkHour);
        }
        jList6.setListData(new Time_Date().getCheckingTimes());
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        jComboBox7.setVisible(true);
        jComboBox8.setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel df = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        if (!jTextField8.getText().isEmpty()) {
            new WebSite().runPhishingWebsitesForURL(df, jTextField8.getText());
        }
        jTable1.setModel(df);
        jLabel12.setVisible(false);
        jScrollPane3.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed
    public class MyComboBoxEditor extends DefaultCellEditor {

        public MyComboBoxEditor(String[] items) {
            super(new JComboBox(items));
        }
    }

    class MyCustomFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".txt" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".txt");
        }

        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "Text documents (*.txt)";
        }
    }

    public void googleOpacity(String text) {

        red = 0;
        javax.swing.Timer t;
        jLabel10.setText(" " + text);
        jLabel10.setVisible(true);
        try {
            t = new javax.swing.Timer(150, new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        red += 7;
                        if (red >= 255) {
                            throw new Exception();
                        }
                        Color color2 = new Color(red, red, 255);
                        jLabel10.setBackground(color2);

                    } catch (Exception ex) {
                        jLabel10.setVisible(false);
                        return;

                    }

                }
            });
            t.start();
        } catch (Exception ex) {
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    final java.util.List<idetect.History> historyList = null;
    final java.util.List<idetect.History> historyList1 = null;
    final javax.persistence.Query historyQuery = null;
    final javax.persistence.EntityManager idetectPUEntityManager = null;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JList jList5;
    private javax.swing.JList jList6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
