import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonExample extends Frame implements ItemListener
{
    Checkbox r1, r2, r3;
    Label l1, l2, l3;
    CheckboxGroup group;

    public RadioButtonExample()
    {
        setSize(300, 400);
        FlowLayout flow = new FlowLayout();
        setLayout(flow);
        setTitle("Favorite Foods (Radio)");

        group = new CheckboxGroup();
        r1 = new Checkbox("Dosa", group, true);
        r2 = new Checkbox("Parotta", group, false);
        r3 = new Checkbox("Fried Rice", group, false);

        add(r1);
        add(r2);
        add(r3);

        l1 = new Label("");
        l2 = new Label("");
        l3 = new Label("");
        add(l1);
        add(l2);
        add(l3);

        r1.addItemListener(this);
        r2.addItemListener(this);
        r3.addItemListener(this);

        updateLabels();
    }

    private void updateLabels()
    {
        Checkbox selected = group.getSelectedCheckbox();
        l1.setText(selected == r1 ? r1.getLabel() : "");
        l2.setText(selected == r2 ? r2.getLabel() : "");
        l3.setText(selected == r3 ? r3.getLabel() : "");
    }

    public void itemStateChanged(ItemEvent e)
    {
        updateLabels();
    }

    public static void main(String[] args)
    {
        RadioButtonExample rb = new RadioButtonExample();
        rb.show();
    }
}
