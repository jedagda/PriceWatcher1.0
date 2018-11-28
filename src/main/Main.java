package main;

import main.GUI.ItemRenderer;
import main.GUI.ItemView;
import main.GUI.ToolBar;
import main.controller.AppCloser;
import main.controller.PriceCrawler;
import main.item.Item;
import main.item.ItemListHolder;
import main.item.ItemManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    private ItemManager itemManager = new ItemManager();

    private JList<Item> itemList;

    private ItemListHolder itemListHolder = new ItemListHolder();
    /** Create a new dialog. */

    private JMenuBar menuBar = new JMenuBar();

    private JPanel itemBoard = new JPanel();

    JPopupMenu menu = new JPopupMenu("Popup");

    public JPanel getItemBoard(){
        return this.itemBoard;
    }

    public ItemListHolder getItemListHolder(){
        return this.itemListHolder;
    }

    public Main() {
        this(DEFAULT_SIZE);

    }

    public ItemManager listSample(){
        itemManager.addItem(new Item("Ghost In the Wires","https://www.amazon.com/Ghost-Wires-Adventures-Worlds-Wanted/dp/0316037729/" ,"4/24/12","gitw"));
        itemManager.addItem(new Item("Snow Crash","https://www.amazon.com/Snow-Crash-Neal-Stephenson/dp/0553380958" ,"4/02/00","snow-crash"));
        return itemManager;
    }

    public void setItemList(JPanel board, ItemManager itemManager){
        itemList = convertListToJList(itemManager);
        itemList.setFixedCellHeight(160);
        JScrollPane listScroll = new JScrollPane(itemList);
        if(board.getComponentCount() > 0){
            board.remove(0);
        }
        board.add(listScroll);
        board.updateUI();
        itemList.setCellRenderer(new ItemRenderer());
    }

    public JList<Item> convertListToJList(ItemManager itemManager){
        DefaultListModel<Item> listModel = new DefaultListModel<>();
        for(int i = 0; i < itemManager.count(); i++){
            System.out.println(itemManager.getItems().get(i).getName());
            listModel.addElement(itemManager.getItemAtI(i));
        }
        itemList = new JList<>(listModel);
        return itemList;
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
        for(int i = 0; i < itemManager.count(); i++){
            itemManager.getItems().get(i).setPrice(priceCrawler.randomPrice());
        }
        showMessage("New Price Obtained");
        itemList.setCellRenderer(new ItemRenderer());

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

        addWindowListener( new AppCloser());

        JMenuBar menuBar = new JMenuBar();
        menuBar = makeMenuBar(menuBar);
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
        menuBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(menuBar, BorderLayout.BEFORE_LINE_BEGINS);


        setLayout(new BorderLayout());

        JPanel control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        add(control, BorderLayout.NORTH);



        itemBoard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10,16,0,16),
                BorderFactory.createLineBorder(Color.GRAY)));
        itemBoard.setLayout(new GridLayout(1,1));
     //   itemView = new ItemView();
      //  itemView.setClickListener(this::viewPageClicked);

        itemListHolder.setItemManager(listSample());

        setItemList(itemBoard, this.itemListHolder.getItemManager());


        add(itemBoard, BorderLayout.CENTER);


        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);

        JMenuItem item = new JMenuItem("Change");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(item);
        menu.setVisible(true);


    }

    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton refreshButton = new JButton("Refresh");
        JButton addItemButton = new JButton("Add");
        ToolBar toolBar = new ToolBar();
        toolBar.setMain(this);
        panel.add(toolBar);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        panel.add(refreshButton);
        panel.add(addItemButton);
        return panel;
    }

    private JMenuBar makeMenuBar(JMenuBar menuBar){
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem exit= new JMenuItem("Exit");
        file.add(exit);

        JMenu help = new JMenu("Help");
        menuBar.add(help);
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        return menuBar;

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

    public ItemManager getItemManager(){
        return itemManager;
    }
    class MyLabel extends JLabel {
        public MyLabel(String text) {
            super(text);
            addMouseListener(new PopupTriggerListener());
        }

        class PopupTriggerListener extends MouseAdapter {

            public void mousePressed(MouseEvent ev) {

                if (ev.isPopupTrigger()) {
                    menu.show(ev.getComponent(), ev.getX(), ev.getY());
                }
            }
            public void mouseReleased(MouseEvent ev) {
                if (ev.isPopupTrigger()) {
                    menu.show(ev.getComponent(), ev.getX(), ev.getY());
                }
            }
            public void mouseClicked(MouseEvent ev) {

            }
        }
    }



    public static void main(String[] args) {
        new Main();
    }




}
