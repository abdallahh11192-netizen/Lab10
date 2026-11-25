import javax.swing.*;
import java.awt.*;

/**
 * Swing GUI for the SortedList lab.
 * Add strings (stored in sorted order), then search for strings.
 */
public class SortedListFrame extends JFrame
{
    private final SortedList list = new SortedList();

    private final JTextField addField = new JTextField(15);
    private final JTextField searchField = new JTextField(15);
    private final JTextArea display = new JTextArea(18, 40);

    public SortedListFrame()
    {
        super("Sorted List - Binary Search");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel top = new JPanel(new GridLayout(2, 1, 4, 4));

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addBtn = new JButton("Add");
        addPanel.add(new JLabel("Enter string to add:"));
        addPanel.add(addField);
        addPanel.add(addBtn);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton searchBtn = new JButton("Search");
        searchPanel.add(new JLabel("Enter string to search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        top.add(addPanel);
        top.add(searchPanel);
        add(top, BorderLayout.NORTH);

        display.setEditable(false);
        display.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        add(new JScrollPane(display), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton clearBtn = new JButton("Clear List");
        JButton quitBtn = new JButton("Quit");
        bottom.add(clearBtn);
        bottom.add(quitBtn);
        add(bottom, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> doAdd());
        addField.addActionListener(e -> doAdd());
        searchBtn.addActionListener(e -> doSearch());
        searchField.addActionListener(e -> doSearch());
        clearBtn.addActionListener(e ->
        {
            list.clear();
            display.setText("List cleared.\n");
        });
        quitBtn.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null);
    }

    private void doAdd()
    {
        String s = addField.getText().trim();
        if (s.isEmpty())
        {
            return;
        }
        int pos = list.add(s);
        display.append("ADD: \"" + s + "\" inserted at index " + pos + "\n");
        showList();
        addField.setText("");
        addField.requestFocusInWindow();
    }

    private void doSearch()
    {
        String s = searchField.getText().trim();
        if (s.isEmpty())
        {
            return;
        }
        int result = list.search(s);
        if (result >= 0)
        {
            display.append("SEARCH: \"" + s + "\" FOUND at index " + result
                    + " -> element: " + list.get(result) + "\n");
        }
        else
        {
            int insertAt = -(result + 1);
            display.append("SEARCH: \"" + s + "\" NOT FOUND. It would be at index "
                    + insertAt + " in the list.\n");
        }
        showList();
        searchField.setText("");
        searchField.requestFocusInWindow();
    }

    private void showList()
    {
        display.append("Current list (" + list.size() + " items):\n");
        display.append(list.toString());
        display.append("----------------------------------------\n");
        display.setCaretPosition(display.getDocument().getLength());
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new SortedListFrame().setVisible(true));
    }
}
