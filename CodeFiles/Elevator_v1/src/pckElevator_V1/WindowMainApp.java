package pckElevator_V1;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.imageio.ImageIO;

public class WindowMainApp extends javax.swing.JFrame {

    //data create 1 single sontroller
    private final Controller controller;

    //Constructor of the window
    public WindowMainApp(Controller controller) {
        this.controller = controller;
        initComponents();  // This is Created from the Design Window
       this.setTitle("Prject Morpheus Elevator Project");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanellMainView = new javax.swing.JPanel();
        jtabSimulation = new javax.swing.JTabbedPane();
        javax.swing.JPanel jpnlWelcomePanel = new javax.swing.JPanel();
        jlblProjectName = new javax.swing.JLabel();
        jlblProjectName1 = new javax.swing.JLabel();
        jlblProjectName2 = new javax.swing.JLabel();
        jlblProjectName3 = new javax.swing.JLabel();
        jlblProjectName4 = new javax.swing.JLabel();
        jtabSelectScenario = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lblScenarioType = new javax.swing.JLabel();
        btnSaveScenario_1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblScenarioType2 = new javax.swing.JLabel();
        btnSaveScenario_2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblScenarioType1 = new javax.swing.JLabel();
        btnSaveScenario_3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSimulation = new javax.swing.JTable();
        btnRunSimulation = new javax.swing.JButton();
        btnStopSimulation = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(java.awt.Color.white);

        jPanellMainView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanellMainView.setToolTipText("");

        jtabSimulation.setBackground(new java.awt.Color(0, 0, 0));
        jtabSimulation.setForeground(new java.awt.Color(255, 255, 255));
        jtabSimulation.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jtabSimulation.setToolTipText("RunSimulationTab");

        jlblProjectName.setFont(new java.awt.Font("Engravers MT", 1, 35)); // NOI18N
        jlblProjectName.setText("Elevator Project");

        jlblProjectName1.setFont(new java.awt.Font("Engravers MT", 1, 20)); // NOI18N
        jlblProjectName1.setText("Project Morpheus");

        jlblProjectName2.setFont(new java.awt.Font("Engravers MT", 1, 20)); // NOI18N
        jlblProjectName2.setText("Alex Kozlowski");

        jlblProjectName3.setFont(new java.awt.Font("Engravers MT", 1, 20)); // NOI18N
        jlblProjectName3.setText("By:");

        jlblProjectName4.setFont(new java.awt.Font("Engravers MT", 1, 20)); // NOI18N
        jlblProjectName4.setText("Ixe Velazquez");

        javax.swing.GroupLayout jpnlWelcomePanelLayout = new javax.swing.GroupLayout(jpnlWelcomePanel);
        jpnlWelcomePanel.setLayout(jpnlWelcomePanelLayout);
        jpnlWelcomePanelLayout.setHorizontalGroup(
            jpnlWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlWelcomePanelLayout.createSequentialGroup()
                .addGroup(jpnlWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlblProjectName1)
                    .addGroup(jpnlWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnlWelcomePanelLayout.createSequentialGroup()
                            .addGap(169, 169, 169)
                            .addComponent(jlblProjectName))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlWelcomePanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jlblProjectName3)
                            .addGap(48, 48, 48)
                            .addGroup(jpnlWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlblProjectName2)
                                .addComponent(jlblProjectName4))
                            .addGap(25, 25, 25))))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jpnlWelcomePanelLayout.setVerticalGroup(
            jpnlWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlWelcomePanelLayout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jlblProjectName)
                .addGap(18, 18, 18)
                .addComponent(jlblProjectName1)
                .addGap(81, 81, 81)
                .addGroup(jpnlWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblProjectName3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblProjectName4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jlblProjectName2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        jtabSimulation.addTab("Welcome!", jpnlWelcomePanel);

        jtabSelectScenario.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        lblScenarioType.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblScenarioType.setText("Scenario:   Custom Scenario ");

        btnSaveScenario_1.setText("Select Scenario");
        btnSaveScenario_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveScenario_1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScenarioType)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(338, Short.MAX_VALUE)
                .addComponent(btnSaveScenario_1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScenarioType, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 602, Short.MAX_VALUE)
                .addComponent(btnSaveScenario_1)
                .addGap(100, 100, 100))
        );

        jtabSelectScenario.addTab("Custom", jPanel2);

        lblScenarioType2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblScenarioType2.setText("Scenario 1:   Default Scenario ");

        btnSaveScenario_2.setText("Select Scenario");
        btnSaveScenario_2.setFocusable(false);
        btnSaveScenario_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveScenario_2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblScenarioType2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnSaveScenario_2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(339, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScenarioType2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 602, Short.MAX_VALUE)
                .addComponent(btnSaveScenario_2)
                .addGap(100, 100, 100))
        );

        jtabSelectScenario.addTab("Top Down", jPanel4);

        lblScenarioType1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblScenarioType1.setText("Scenario 2:   Single Visitor");

        btnSaveScenario_3.setText("Select Scenario");
        btnSaveScenario_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveScenario_3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScenarioType1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(btnSaveScenario_3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScenarioType1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 602, Short.MAX_VALUE)
                .addComponent(btnSaveScenario_3)
                .addGap(100, 100, 100))
        );

        jtabSelectScenario.addTab("Single", jPanel3);

        jtabSimulation.addTab("1. Choose Scenario", jtabSelectScenario);

        tblSimulation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblSimulation);

        btnRunSimulation.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnRunSimulation.setText("Run");
        btnRunSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunSimulationActionPerformed(evt);
            }
        });

        btnStopSimulation.setText("Stop  / Pause");
        btnStopSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopSimulationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRunSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStopSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRunSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStopSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        jtabSimulation.addTab("2. Run Simulation", jPanel1);

        javax.swing.GroupLayout jPanellMainViewLayout = new javax.swing.GroupLayout(jPanellMainView);
        jPanellMainView.setLayout(jPanellMainViewLayout);
        jPanellMainViewLayout.setHorizontalGroup(
            jPanellMainViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtabSimulation)
        );
        jPanellMainViewLayout.setVerticalGroup(
            jPanellMainViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtabSimulation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanellMainView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanellMainView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStopSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopSimulationActionPerformed
        // call controller to stop
        controller.stopSimulation();
    }//GEN-LAST:event_btnStopSimulationActionPerformed

    private void btnRunSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunSimulationActionPerformed
        // call Controller to run
        controller.runSimulation();
    }//GEN-LAST:event_btnRunSimulationActionPerformed

    private void btnSaveScenario_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveScenario_3ActionPerformed
        controller.setScenario(2);          // set the scenario to the right type
        configureBuildingDisplay();         // configure the display table
        update();                           // update table to display scenario
        jtabSimulation.setSelectedIndex(1); // Go-to Simlation tab view
    }//GEN-LAST:event_btnSaveScenario_3ActionPerformed

    private void btnSaveScenario_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveScenario_2ActionPerformed
        controller.setScenario(1);          // set the scenario to the right type
        configureBuildingDisplay();         // configure the display table
        update();                           // update table to display scenario
        jtabSimulation.setSelectedIndex(1);  // update table to display scenario
    }//GEN-LAST:event_btnSaveScenario_2ActionPerformed

    private void btnSaveScenario_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveScenario_1ActionPerformed
        // This needs custom coding of input from user
        controller.customScenario(          // Fill Custom Settings
            9 /*numberOfFloors*/,
            1 /*numberOfElevators*/,
            7 /*numberOfVisitors*/);
        controller.setScenario(0);          // set the Custom (0) scenario
        configureBuildingDisplay();         // configure the display table
        update();                           // update table to display scenario
        jtabSimulation.setSelectedIndex(1); // Go-to Simlation tab view
    }//GEN-LAST:event_btnSaveScenario_1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WindowMainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowMainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowMainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowMainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        // create the controller object and 
        // Execute initialization of all requied business objects
        final Controller controller = new Controller();

        // create and start new thread: First create TheadClass Object
        ThreadAnimation thp = new ThreadAnimation(controller);
        Thread thread = new Thread(thp); // Place this in the thread
        thread.start();

        // Execute initialization of all required buseness objects
        /* create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // create the window
                // Let controller know the window 
                // Visualize the window, pass in the 'controller'
                WindowMainApp mainWindow = new WindowMainApp(controller);
                controller.setWindow(mainWindow);
                mainWindow.setVisible(true);
            }//void run()
        });// java.awt.EventQueue
    }// main()

    public void configureBuildingDisplay() {
        int numberOfElevators = controller.getScenario().getNumberOfElevators();
        int columns
                = 2 // floor occupancy and floor label columns
                + // each elevator gets its own column in the grid:
                numberOfElevators;
        String[] columnNames = new String[columns];
        for (int idx = 0; idx < columns; ++idx) {
            if (idx < numberOfElevators) {
                // this column represents the elevator shaft
                columnNames[idx]
                        = ElevatorBank.GetInstance().getElevator(idx).getLabel();
            } else if (idx == columns - 1) {
                // last column is the floor label
                columnNames[idx] = "Floor#";
            } else {
                // floor occupancy
                columnNames[idx] = "Floor Visitors";
            }
        }
        // populate table rows
        int numberOffloors = controller.getScenario().getNumberOfFloors();
        Object[][] tableContent = new Object[numberOffloors][columns];
        //while row length is greater than Zero, decrement
        for (int row = tableContent.length - 1; row > -1; --row) {
            // for each column, create array list of floors and fill with Flor number
            ArrayList<Floor> floors = ElevatorBank.GetInstance().getFloors();
            // to traverse the floors top-down, calculate index of the
            // floor as follows:
            Floor floor = floors.get(floors.size() - row - 1);
            // update last column (floor label)
            tableContent[row][tableContent[row].length - 1]
                    = floor.getLabel();
        }
        // pass the data to the table gadget:
        tblSimulation.setModel(
                new javax.swing.table.DefaultTableModel(
                        tableContent,
                        columnNames
                )
        );
    }// configureBuildingDisplay

    public void update() {
        tblSimulation.clearSelection();         // at the begining of each scinario clear
        // controller calls this method to update the elevator-floor view 
        // Save scenario also calls this to visualize latest config
        ArrayList<Elevator> elevatorsArray = ElevatorBank.GetInstance().getElevators();
        ArrayList<Floor> floorsArray = ElevatorBank.GetInstance().getFloors();
        //ArrayList<IVisitor> vistorsArray = ElevatorBank.GetInstance().getVisitors();
        int columnIndex = 0; // first elevator shown in the leftmost table column
        for (Elevator elevator : elevatorsArray) {
            // update elevator location:
            int rowIndex = floorsArray.size() - elevator.getFloor() - 1;  //Index is top down!
            //int visitorIndex = vistorsArray.size();
            // ***** POPULATE: FlOOR collumn ****************
            tblSimulation.getModel().setValueAt(
                elevator.getElevatorRiders(),         // What it reads onscreen
                rowIndex,                                           // int Row
                controller.getScenario().getNumberOfElevators()     // int col
            );
            // ***** POPULATE: ELEVATOR collumn ****************
            tblSimulation.getModel().setValueAt(
                elevator.getLabel() + ": " + elevator.getElevatorRiders(), // Elevator Name + # of Riders
                rowIndex, 
                columnIndex
            );
            // clear all locations:
            for (int idx = 0; idx < floorsArray.size(); ++idx) {
                if (idx != rowIndex) {
                    tblSimulation.getModel().setValueAt(
                            null/*content*/, idx, columnIndex
                    );
                }
            }
            ++columnIndex; // next elevator shown in the next table column
        }// for
    }//update

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRunSimulation;
    private javax.swing.JButton btnSaveScenario_1;
    private javax.swing.JButton btnSaveScenario_2;
    private javax.swing.JButton btnSaveScenario_3;
    private javax.swing.JButton btnStopSimulation;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanellMainView;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlblProjectName;
    private javax.swing.JLabel jlblProjectName1;
    private javax.swing.JLabel jlblProjectName2;
    private javax.swing.JLabel jlblProjectName3;
    private javax.swing.JLabel jlblProjectName4;
    private javax.swing.JTabbedPane jtabSelectScenario;
    private javax.swing.JTabbedPane jtabSimulation;
    private javax.swing.JLabel lblScenarioType;
    private javax.swing.JLabel lblScenarioType1;
    private javax.swing.JLabel lblScenarioType2;
    private javax.swing.JTable tblSimulation;
    // End of variables declaration//GEN-END:variables

}
