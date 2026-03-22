
import java.awt.*;
import  java.awt.event.*;;

public class ScrollBarExample  extends  Frame implements AdjustmentListener{

    Scrollbar scrollbar;
    public ScrollBarExample()
    {
        setSize(100,300);
        FlowLayout flow = new FlowLayout();
        setLayout(flow);
        scrollbar = new Scrollbar(1,5,10,0,500);
        scrollbar.setSize(0, 200);
        add(scrollbar);

        scrollbar.addAdjustmentListener(this);

    }
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e)
    {
        setTitle("Vertical Scroll = " + e.getValue());
    }

    public static void main(String[] args) {
        ScrollBarExample sb = new ScrollBarExample();
        sb.show();
    }
}
