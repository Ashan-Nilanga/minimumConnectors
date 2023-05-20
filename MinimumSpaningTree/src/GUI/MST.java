package GUI;


import Code.Database;
import Code.Edge;
import Code.MinimumSpanningTree;
import Code.Vertex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MST {
    private JTable distance_table;
    private JPanel MainPanel;
    private JButton button_main, button_restart;
    private JLabel start_city_lb;

    private JTextField min_distance;

    private JComboBox<String> l1_1, l1_2, l2_1, l2_2, l3_1, l3_2, l4_1, l4_2,
            l5_1, l5_2, l6_1, l6_2, l7_1, l7_2, l8_1, l8_2, l9_1, l9_2;


    // ------------------------- Game Variables -------------------------------

    private final String[][] tableData; // Matrix use to save data
    private List<String> minimumConnectors; // List used to store correct minimum Connectors
    private int minimumDistance; // variable used to store total minimum distance


    private List<Vertex> graph;
    private Vertex root_city;

    private Database database;

    // ----------------------------------- Code Implementation ------------------------------------------

    public MST() {

        this.database = new Database();

        this.tableData = new String[10][10];
        startGame();



        button_main.addActionListener(e -> {

                int input_minimum_distance = this.isValidInt(min_distance);

                // Get inputs connectors
                ArrayList<String> input_connectors = getConnectionInputs();
                String[] input_connectors_array = new String[9];

                for(int i = 0; i < input_connectors_array.length; i++){
                    input_connectors_array[i] = input_connectors.get(i);
                }


                // Check connector inputs are correct
                boolean is_minimum_connectors_correct = this.isConnectionInputsCorrect();


                if(input_minimum_distance == this.minimumDistance && is_minimum_connectors_correct){
                    String player = null;
                    while(player == null || player.equals("")) {
                        player = JOptionPane.showInputDialog(
                                null,
                                "You have calculated paths correctly!!!\nEnter Your Name",
                                "Congratulations",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(
                            null,
                            "Congratulations " + player,
                            "Congratulations",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Adding player details and game session details to the database
                    database.saveWinner(player, "City " + root_city.getName());

                    int lastWinnerId = database.getLatestWinnerID();

                    database.saveUserInputs(lastWinnerId, input_connectors_array, input_minimum_distance);

                    // recreating data in table_data
                    String[][] gameData = new String[9][9];
                    for(int i = 1; i < this.tableData.length; i++){
                        gameData[i - 1] = Arrays.copyOfRange(this.tableData[i], 1, 10);
                    }

                    database.saveCurrentGamePlayValues(lastWinnerId, gameData);


                    // Asking user whether he wants to restart the game because he solved for these values
                    int user_option = JOptionPane.showConfirmDialog(
                            null,
                            "You have found the solution for this game,\n" +
                                    "Do you want to restart the game?",
                            "Restarting Game",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if(user_option == 0){
                        startGame();
                    }

                }
                else if(input_minimum_distance != -1){
                    JOptionPane.showMessageDialog(
                            null,
                            "Incorrect answers!!!\nCheck your calculations",
                            "Try Again",
                            JOptionPane.INFORMATION_MESSAGE);
                }


            }
        );


        button_restart.addActionListener(e -> {
            int user_option = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to restart the game?",
                    "Restarting Game",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if(user_option == 0){
                startGame();
            }

        });
    }

    /**
     * Main method to run the application
     * @param args: command line arguments
     */
    public static void main(String[] args) {
        // This code is to get the Nimbus look and feel in the Application
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }

        JFrame MainFrame = new JFrame("ShortestPath");

        MainFrame.setContentPane(new MST().MainPanel);

        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainFrame.pack();
        MainFrame.setVisible(true);

    }

    // ------------------------- These are game functions ---------------------------------------------

    /**
     * This can be used to start or restart the game
     */
    private void startGame(){
        createTable();
        createGraph();


        // Adding the graph to a MinimumSpanningTree
        MinimumSpanningTree primAlgoSolver = new MinimumSpanningTree(graph);

        Random rand = new Random();
        int rand_int = rand.nextInt(0, 9);

        this.root_city = graph.get(rand_int);

        // Updating the root city in the GUI
        start_city_lb.setText("City " + root_city.getName());

        // Run the prim's Algorithm in the graph to find the MST
        primAlgoSolver.run(rand_int);

        // The graph is now created as a MST

        this.minimumConnectors = primAlgoSolver.getMinimumConnectors();
        this.minimumDistance = primAlgoSolver.getMinimumDistance();

        System.out.println(minimumConnectors);
        System.out.println(minimumDistance);

    }



    /**
     * This will create a table in the game window and add values to the table
     */
    private void createTable() {

        String[] headers = new String[]{"", "City A", "City B", "City C", "City D", "City E", "City F", "City G", "City H", "City I"};

        String[] rowHeaders = new String[]{"City A", "City B", "City C", "City D", "City E", "City F", "City G", "City H", "City I", "City J"};


        // This is the loop to create the table_data matrix template to hold data for the table
        for (int row_index = 0; row_index < tableData.length; row_index++) {
            Arrays.fill(tableData[row_index], "-");
            tableData[row_index][0] = rowHeaders[row_index];
        }

        // This is the loop to getting distances values for the table data
        Random rand = new Random();
        int upperBound = 50;
        int lowerBond = 5;

        for(int i = 1; i < tableData.length; i++){
            for(int j = 1; j <= i; j++){

                this.tableData[i][j] = "" + rand.nextInt(lowerBond, upperBound);
            }
        }

        // Creating the table and adding values to it
        distance_table.setModel(new DefaultTableModel(this.tableData, headers));

    }


    /**
     * This will create the graph for this game
     */
    private void createGraph(){

        // Initializing the graph
        this.graph = new ArrayList<>();

        // creating the vertexes
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        Vertex i = new Vertex("I");
        Vertex j = new Vertex("J");

        // adding vertexes to the graph
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
        graph.add(f);
        graph.add(g);
        graph.add(h);
        graph.add(i);
        graph.add(j);

        // creating the links between vertices
        for(int row = 0; row < graph.size(); row++){
            for (int col = 1; col <= row; col++){
                graph.get(row).addEdge(graph.get(col - 1), new Edge(Integer.parseInt(tableData[row][col])));
            }
        }
    }

    // ------------------------ Validating user inputs ------------------------------------

    /**
     * This will validate the user distance input
     * @param distant_input: JTextField for the minimum distance input field
     * @return: Validated Integer -1 if the input value is invalid
     */
    private int isValidInt(JTextField distant_input){
        try {
            return Integer.parseInt(distant_input.getText());
        } catch(NumberFormatException | NullPointerException exception){
            JOptionPane.showMessageDialog(
                    this.MainPanel,
                    "Enter a valid input to the minimum distance",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }


    }

    /**
     * use to get user inputs for the connections
     * @return: ArrayList containing connection values entered by user
     */
    private ArrayList<String> getConnectionInputs(){
        ArrayList<String> return_list = new ArrayList<>();

        return_list.add(String.valueOf(l1_1.getSelectedItem()) + l1_2.getSelectedItem());
        return_list.add(String.valueOf(l2_1.getSelectedItem()) + l2_2.getSelectedItem());
        return_list.add(String.valueOf(l3_1.getSelectedItem()) + l3_2.getSelectedItem());
        return_list.add(String.valueOf(l4_1.getSelectedItem()) + l4_2.getSelectedItem());
        return_list.add(String.valueOf(l5_1.getSelectedItem()) + l5_2.getSelectedItem());
        return_list.add(String.valueOf(l6_1.getSelectedItem()) + l6_2.getSelectedItem());
        return_list.add(String.valueOf(l7_1.getSelectedItem()) + l7_2.getSelectedItem());
        return_list.add(String.valueOf(l8_1.getSelectedItem()) + l8_2.getSelectedItem());
        return_list.add(String.valueOf(l9_1.getSelectedItem()) + l9_2.getSelectedItem());

        return return_list;
    }



    /**
     * This will check whether the inputs given by users for the MST connectors are correct
     * @return: boolean: true if the user inputs are correct
     */
    private boolean isConnectionInputsCorrect(){

        ArrayList<String> return_list = getConnectionInputs();

        for(String input: return_list){
            // To get the reverse of the user input connection
            StringBuilder stringBuilder = new StringBuilder();
            String input_reverse = stringBuilder.append(input).reverse().toString();

            // check whether input is available as a correct input
            if(this.minimumConnectors.contains(input)){
                continue;
            }
            // This will check reverse of the input is in the correct inputs
            else if(this.minimumConnectors.contains(input_reverse)){
                continue;
            }
            return false;
        }

        return true;

    }


}
