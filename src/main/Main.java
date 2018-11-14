package main;

import main.GUI.ItemView;
import main.controller.PriceCrawler;
import main.item.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;

/**
 * A dialog for tracking the price of an item.
 * @author Yoonsik Cheon, Joshua Dagda
 *
 *
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(800, 600);

    //private SoundFX wololo = new SoundFX();
    /** Special panel to display the watched item. */
    private ItemView itemView;

    /** Creates an instance of PriceCheck*/
    private PriceCrawler priceCrawler =new PriceCrawler();

    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    /** Create an instance of Item */
    private Item item;
    /** Create a new dialog. */
    public Main() {

        this(DEFAULT_SIZE);
        String bookName="Ghost In the Wires";
        String bookURL="https://www.amazon.com/Ghost-Wires-Adventures-Worlds-Wanted/dp/0316037729/";
        double bookPrice = 17.00;
        double priceChange = 0.00;
        String bookAdded = "4/24/12";
        item = new Item(bookName,bookURL,bookPrice,priceChange,bookAdded);
        itemView.setItem(item);

    }

    /** Create a new dialog of the given screen dimension. */
    public Main(Dimension dim) {
        super("Price Watcher");
        setSize(dim);

        configureUI();
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //setResizable(false);
        showMessage("Welcome!");
    }

    /** Callback to be invoked when the refresh button is clicked.
     * Find the current price of the watched item and display it
     * along with a percentage price change. */
    private void refreshButtonClicked(ActionEvent event) {

        PriceCrawler priceCrawler = new PriceCrawler();
        item.setPrice(priceCrawler.randomPrice());
        showMessage(Double.toString(item.getPrice()));
        itemView.repaint();

        if(item.getInitialPrice() > item.getPrice()) {

            // Open an audio input stream.
    //        URL url = this.getClass().getClassLoader().getResource("/image/wololo.wav");
      //      AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
     //       Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
     //       clip.open(audioIn);
     //       clip.start();
        }else{
            //play audio
        }


    }

    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */
    private void viewPageClicked() {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI oURL = new URI(item.getURL());
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        showMessage("View clicked!");
    }

    /** Configure UI. */
    private void configureUI() {
        setLayout(new BorderLayout());
        JPanel control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        add(control, BorderLayout.NORTH);
        JPanel board = new JPanel();
        board.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10,16,0,16),
                BorderFactory.createLineBorder(Color.GRAY)));
        board.setLayout(new GridLayout(1,1));
        itemView = new ItemView();
        itemView.setClickListener(this::viewPageClicked);
        board.add(itemView);
        add(board, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
    }

    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        panel.add(refreshButton);
        return panel;
    }

    /** Show briefly the given string in the message bar. */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
            try {
                Thread.sleep(3 * 1000); // 3 seconds
            } catch (InterruptedException e) {
            }
            if (msg.equals(msgBar.getText())) {
                SwingUtilities.invokeLater(() -> msgBar.setText(" "));
            }
        }).start();
    }

    public static void main(String[] args) {
        new Main();
    }


}