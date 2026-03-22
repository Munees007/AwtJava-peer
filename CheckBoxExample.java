import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBoxExample extends Frame implements ItemListener
{
    Checkbox c1,c2,c3;
    Label l1,l2,l3;
    public CheckBoxExample()
    {
        setSize(300,400);
        FlowLayout flow = new FlowLayout();
        setLayout(flow);
        setTitle("Favorite Foods");
        c1 = new Checkbox("Dosa");
        c2 = new Checkbox("Parotta");
        c3 = new Checkbox("Fried Rice");

        add(c1);
        add(c2);
        add(c3);
        l1 = new Label("");
        l2 = new Label("");
        l3 = new Label("");
        add(l1);
        add(l2);
        add(l3);
        c1.addItemListener(this);
        c2.addItemListener(this);
        c3.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(c1.getState())
        {
            l1.setText(c1.getLabel());
        }
        else
        {
            l1.setText("");
        }
        if(c2.getState())
        {
            l2.setText(c2.getLabel());
        }
        else
        {
            l2.setText("");
        }
        if(c3.getState())
        {
            l3.setText(c3.getLabel());
        }
        else
        {
            l3.setText("");
        }
    }

    public static void main(String[] args)
    {
        CheckBoxExample cb = new CheckBoxExample();
        cb.show();
    }
}